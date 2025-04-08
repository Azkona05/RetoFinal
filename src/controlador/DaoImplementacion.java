package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import excepciones.LoginException;
import modelo.Competicion;
import modelo.EnumPosicion;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Partido;
import modelo.Usuario;

public class DaoImplementacion implements InterfazDao {
	// Atributos

	private ResourceBundle configFile;
	private String urlDB;
	private String userDB;
	private String passwordDB;

	private Connection con;
	private PreparedStatement stmt;
	// Sentencias SQL
	// SQL Login
	final String LOGIN = "SELECT * FROM USUARIO WHERE NOM = ? AND CONTRASENIA = ?";
	// SQL Jugador
	final String ALTA_JUGADOR = "INSERT INTO JUGADOR (dni, nombre, apellido, dorsal, posicion, cod_equi) VALUES (?, ?, ?, ?, ?, ?)";
	final String BAJA_JUGADOR = "DELETE FROM JUGADOR WHERE dni = ?";
	final String MODIFICAR_JUGADOR = "UPDATE jugador SET nombre = ?, apellido = ?, dorsal = ?, posicion = ?, cod_equi = ? WHERE dni = ?";
	final String BUSCAR_JUGADOR = "SELECT * FROM JUGADOR";
	final String MOSTRAR_DATOS_JUGADOR = "SELECT dni, nombre, apellido, dorsal, posicion, cod_equi FROM jugador";
	// SQL Competicion
	final String ALTA_COMPETICION = "INSERT INTO COMPETICICION (cod_comp, nombre_competicion) VALUES (?, ?)";
	final String BAJA_COMPETICION = "DELETE FROM COMPETICION WHERE cod_comp = ?";
	final String MODIFICAR_COMPETICION = "UPDATE COMPETICION SET nombre_competicion = ? WHERE cod_comp = ?";
	final String BUSCAR_COMPETICION = "SELECT * FROM COMPETICION";
	final String BUSCAR_EQUIPOS_COMP = "SELECT nombre_equipo, (Select count(*) from partido p2 where p2.ganador = cod_equi) as victorias "
			+ "	FROM futbol_americano.partido " + "		JOIN equipo ON(equipo_local=cod_equi) "
			+ "    WHERE fecha between '2020-01-01' and current_date()  AND cod_comp like '%?%' "
			+ "    order by victorias DESC;";
	final String Metodo_Burro = "select * from partido where cod_comp=?";
	final String MOSTRAR_DATOS_COMPETICION = "SELECT cod_comp, nombre_competicion FROM competicion";
	// SQL Equipo
	final String ALTA_EQUIPO = "INSERT INTO EQUIPO (cod_equi, nombre_equipo) VALUES (?, ?)";
	final String BAJA_EQUIPO = "DELETE FROM EQUIPO WHERE cod_equi = ?";
	final String MODIFICAR_EQUIPO = "UPDATE EQUIPO SET nombre_equipo = ? WHERE cod_equi = ?";
	final String BUSCAR_EQUIPO = "SELECT * FROM EQUIPO";
	final String BUSCAR_EQUIPO_LIGA = "SELECT cod_equi from equipo where cod_equi IN (SELECT equipo_local from partido where cod_comp=?) OR cod_equi IN (SELECT equipo_visitante from partido where cod_comp=?)";
	final String MOSTRAR_DATOS_EQUIPO = "SELECT cod_equi, nombre_equipo FROM equipo";
	// SQL Partido
	final String ALTA_PARTIDO = "INSERT INTO PARTIDO (cod_part, equipo_local, equipo_visitante, ganador, fecha, cod_comp) VALUES (?, ?, ?, ?, ?)";
	final String BAJA_PARTIDO = "DELETE FROM PARTIDO WHERE cod_part = ?";
	final String MODIFICAR_PARTIDO = "UPDATE PAARTIDO SET equipo_local = ?, equipo_visitante = ?, ganador = ?, fecha = ?, cod_comp = ? WHERE cod_part = ?";
	final String BUSCAR_PARTIDO = "SELECT * FROM PARTIDO";

	final String PARTIDOS_DIA = "SELECT * FROM PARTIDO WHERE DATE(FECHA) = ? ORDER BY fecha ASC";
	final String MOSTRAR_DATOS_PARTIDO = "SELECT cod_part, equipo_local, equipo_visitante, ganador, fecha, cod_comp FROM partido";
	public DaoImplementacion() {
		this.configFile = ResourceBundle.getBundle("modelo.configClass");
		this.urlDB = this.configFile.getString("Conn");
		this.userDB = this.configFile.getString("DBUser");
		this.passwordDB = this.configFile.getString("DBPass");

	}

	private void openConnection() {

		try {
			con = DriverManager.getConnection(urlDB, this.userDB, this.passwordDB);
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol_americano?serverTimezone=Europe/Madrid&useSSL=false", "root",
//				"abcd*1234");
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
		}
	}

	private void closeConnection() throws SQLException {

		if (stmt != null) {
			stmt.close();
		}
		if (con != null)
			con.close();
	}

	@Override
	public void login(Usuario usuario) throws LoginException {
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(LOGIN);
			stmt.setString(1, usuario.getNombre());
			stmt.setString(2, usuario.getPassword());

			rs = stmt.executeQuery();

			if (!rs.next()) {
				throw new LoginException("ERROR, PARAMETROS INCORRECTOS");
			}

		} catch (SQLException e) {
			throw new LoginException("ERROR, SQL ERROR");
		} finally {
			try {
				rs.close();
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void altaJugador(Jugador jug) throws LoginException{
		openConnection();
		try {
			stmt = con.prepareStatement(ALTA_JUGADOR);
			stmt.setString(1, jug.getDni());
			stmt.setString(2, jug.getNombre());
			stmt.setString(3, jug.getApellido());
			stmt.setInt(4, jug.getDorsal());
			stmt.setString(5, jug.getPosicion().name());
			stmt.setString(6, jug.getCod_equi());
			if(stmt.executeUpdate() != 1) {
				throw new LoginException("Problemas con el alta de juagdor");
			}
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void bajaJugador(Jugador jug) throws LoginException{

		openConnection();
		try {
			stmt = con.prepareStatement(BAJA_JUGADOR);
			stmt.setString(1, jug.getDni());
			if (stmt.executeUpdate() != 1) {
				
			}
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void modificarJugador(Jugador jug) throws LoginException {
		openConnection();
		try {
			stmt = con.prepareStatement(MODIFICAR_JUGADOR);
			stmt.setString(1, jug.getNombre());
			stmt.setString(2, jug.getApellido());
			stmt.setInt(3, jug.getDorsal());
			stmt.setString(4, jug.getPosicion().name());
			stmt.setString(5, jug.getCod_equi());
			stmt.setString(6, jug.getDni());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<Jugador> listarJugadores() throws LoginException {
		Jugador jug;
		List<Jugador> jugadores = new ArrayList<>();
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_JUGADOR);
			rs = stmt.executeQuery();
			// Leemos de uno en uno los propietarios devueltos en el ResultSet
			while (rs.next()) {
				jug = new Jugador();
				jug.setDni(rs.getString("dni"));
				;
				jug.setNombre(rs.getString("nombre"));
				jug.setApellido(rs.getString("apellido"));
				jug.setDorsal(rs.getInt("dorsal"));
				jug.setPosicion(EnumPosicion.valueOf(rs.getString("posicion")));
				jugadores.add(jug);
			}
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				System.out.println("Error en cierre del ResultSet");
			}
		}
		return jugadores;
	}

	@Override
	public void altaCompeticion(Competicion comp) throws LoginException {
		openConnection();
		try {
			stmt = con.prepareStatement(ALTA_COMPETICION);
			stmt.setString(1, comp.getCod_comp());
			stmt.setString(2, comp.getNombre_competicion());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void bajaCompeticion(Competicion comp) throws LoginException {
		openConnection();
		try {
			stmt = con.prepareStatement(BAJA_COMPETICION);
			stmt.setString(1, comp.getCod_comp());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void modificarCompeticion(Competicion comp) throws LoginException {
		openConnection();
		try {
			stmt = con.prepareStatement(MODIFICAR_COMPETICION);
			stmt.setString(1, comp.getNombre_competicion());
			stmt.setString(2, comp.getCod_comp());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Map<String, Competicion> listarCompeticiones() throws LoginException {
		Competicion comp;
		String nombre;
		String codigo;
		Map<String, Competicion> competiciones = new TreeMap<>();
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_COMPETICION);
			rs = stmt.executeQuery();
			// Leemos de uno en uno los propietarios devueltos en el ResultSet
			while (rs.next()) {
				comp = new Competicion();
				nombre = rs.getString("nombre_competicion");
				codigo = nombre.length() >= 3 ? nombre.substring(0, 3).toUpperCase() : nombre.toUpperCase();
				comp.setCod_comp(codigo);
				comp.setNombre_competicion(nombre);
				competiciones.put(comp.getCod_comp(), comp);
			}
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				System.out.println("Error en cierre del ResultSet");
			}
		}
		return competiciones;
	}

	@Override
	public void altaEquipo(Equipo eq) throws LoginException {
		openConnection();
		try {
			stmt = con.prepareStatement(ALTA_EQUIPO);
			stmt.setString(1, eq.getCod_equi());
			stmt.setString(2, eq.getNombre_equipo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void bajaEquipo(Equipo eq)  throws LoginException{
		openConnection();
		try {
			stmt = con.prepareStatement(BAJA_EQUIPO);
			stmt.setString(1, eq.getCod_equi());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void modificarEquipo(Equipo eq) throws LoginException {
		openConnection();
		try {
			stmt = con.prepareStatement(MODIFICAR_EQUIPO);
			stmt.setString(1, eq.getCod_equi());
			stmt.setString(2, eq.getNombre_equipo());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Map<String, Equipo> listarEquipos() throws LoginException {
		Equipo equi;
		Map<String, Equipo> equipos = new TreeMap<>();
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_JUGADOR);
			rs = stmt.executeQuery();
			// Leemos de uno en uno los propietarios devueltos en el ResultSet
			while (rs.next()) {
				equi = new Equipo();
				equi.setCod_equi(rs.getString("Cod_equi"));
				equi.setNombre_equipo(rs.getString("Nombre_equipo"));
				equipos.put(equi.getCod_equi(), equi);
			}
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				System.out.println("Error en cierre del ResultSet");
			}
		}
		return equipos;
	}

	@Override
	public void altaPartido(Partido part) throws LoginException {
		openConnection();
		try {
			stmt = con.prepareStatement(ALTA_PARTIDO);
			stmt.setInt(0, part.getCod_part());
			stmt.setString(1, part.getEquipo_local());
			stmt.setString(2, part.getEquipo_visitante());
			stmt.setString(3, part.getGanadorString());
			stmt.setDate(4, Date.valueOf(part.getFecha()));
			stmt.setString(5, part.getCod_comp());
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void bajaPartido(Partido part) throws LoginException {
		openConnection();
		try {
			stmt = con.prepareStatement(BAJA_PARTIDO);
			stmt.setInt(1, part.getCod_part());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void modificarPartido(Partido part) throws LoginException {
		openConnection();
		try {
			stmt = con.prepareStatement(MODIFICAR_PARTIDO);
			stmt.setString(1, part.getEquipo_local());
			stmt.setString(2, part.getEquipo_visitante());
			stmt.setObject(3, part.getGanador());
			stmt.setDate(4, Date.valueOf(part.getFecha()));
			stmt.setString(5, part.getCod_comp());
			stmt.setInt(6, part.getCod_part());
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Map<Integer, Partido> listarPartidos() throws LoginException{
		ResultSet rs = null;
		Map<Integer, Partido> partidos = new HashMap<Integer, Partido>();
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_PARTIDO);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Partido part = new Partido();
				part.setCod_part(rs.getInt(1));
				part.setEquipo_local(rs.getString(2));
				part.setEquipo_visitante(rs.getString(3));
				part.setGanador(rs.getString(4));
				part.setFecha(rs.getDate(5).toLocalDate());
				part.setCod_comp(rs.getString(6));
				partidos.put(part.getCod_part(), part);
			}

		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				rs.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return partidos;
	}

	@Override
	public List<String> buscarDifEquipo(Competicion liga) throws LoginException {
		ResultSet rs = null;
		List<String> equipos = new ArrayList<String>();
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_EQUIPO_LIGA);
			stmt.setString(1, liga.getCod_comp());
			stmt.setString(2, liga.getCod_comp());
			rs = stmt.executeQuery();

			while (rs.next()) {
				equipos.add(rs.getString(1));
			}

		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				rs.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return equipos;
	}

	@Override
	public List<Partido> buscarEquiLiga(Competicion liga) throws LoginException {
		Partido part;
		ResultSet rs = null;
		List<Partido> partidos = new ArrayList<Partido>();
		openConnection();
		try {
			stmt = con.prepareStatement(Metodo_Burro);
			stmt.setString(1, liga.getCod_comp());
			rs = stmt.executeQuery();

			while (rs.next()) {
				part = new Partido();
				part.setEquipo_local(rs.getString(2));
				part.setEquipo_visitante(rs.getString(3));
				part.setGanador(rs.getString(4));
				partidos.add(part);

			}

		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				rs.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return partidos;
	}

	public List<Partido> devolverPartidos(LocalDate fecha) throws LoginException {
		Partido part;
		List<Partido> partidos = new ArrayList<Partido>();
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(PARTIDOS_DIA);
			stmt.setDate(1, Date.valueOf(fecha));
			rs = stmt.executeQuery();
			while (rs.next()) {
				part = new Partido();
				part.setCod_comp(rs.getString(6));
				part.setEquipo_local(rs.getString(2));
				part.setEquipo_visitante(rs.getString(3));
				part.setGanador(rs.getString(4));
				partidos.add(part);
			}
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				rs.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return partidos;
	}
	
	public Object[][]  mostrarDatosJugador(Jugador jug) throws LoginException {
		List<Object[]> listaJugadores = new ArrayList<>();
        ResultSet rs = null;

        try {
            openConnection();
            stmt = con.prepareStatement(MOSTRAR_DATOS_JUGADOR);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getInt("dorsal"),
                    rs.getString("posicion"),
                    rs.getString("cod_equi")
                };
                listaJugadores.add(fila);
            }

            return listaJugadores.toArray(new Object[0][0]);

        } catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
        } finally {
            try {
                if (rs != null) rs.close();
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	
	public Object[][]  mostrarDatosEquipo(Equipo eq) throws LoginException {
		List<Object[]> listaEquipos = new ArrayList<>();
        ResultSet rs = null;

        try {
            openConnection();
            stmt = con.prepareStatement(MOSTRAR_DATOS_EQUIPO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                    rs.getString("cod_equi"),
                    rs.getString("nombre_equipo")
                };
                listaEquipos.add(fila);
            }

            return listaEquipos.toArray(new Object[0][0]);

        } catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
        } finally {
            try {
                if (rs != null) rs.close();
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	public Object[][]  mostrarDatosCompeticion(Competicion comp) throws LoginException {
		List<Object[]> listaCompeticion = new ArrayList<>();
        ResultSet rs = null;

        try {
            openConnection();
            stmt = con.prepareStatement(MOSTRAR_DATOS_COMPETICION);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                    rs.getString("cod_comp"),
                    rs.getString("nombre_competicion")
                };
                listaCompeticion.add(fila);
            }

            return listaCompeticion.toArray(new Object[0][0]);

        } catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
        } finally {
            try {
                if (rs != null) rs.close();
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
	public Object[][]  mostrarDatosPartido(Partido part) throws LoginException {
		List<Object[]> listaPartidos = new ArrayList<>();
        ResultSet rs = null;

        try {
            openConnection();
            stmt = con.prepareStatement(MOSTRAR_DATOS_PARTIDO);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("cod_part"),
                    rs.getString("equipo_local"),
                    rs.getString("equipo_visitante"),
                    rs.getString("ganador"),
                    rs.getDate("fecha"),
                    rs.getString("cod_comp")
                };
                listaPartidos.add(fila);
            }

            return listaPartidos.toArray(new Object[0][0]);

        } catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
        } finally {
            try {
                if (rs != null) rs.close();
                closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

	public List<Competicion> devolverCompeticiones() throws LoginException {
		Competicion comp;
		List<Competicion> competiciones = new ArrayList<Competicion>();
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_COMPETICION);
			rs = stmt.executeQuery();
			while (rs.next()) {
				comp = new Competicion();
				comp.setCod_comp(rs.getString(1));
				comp.setNombre_competicion(rs.getString(2));
				competiciones.add(comp);
			}
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				rs.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return competiciones;
	}
	public List<Equipo> buscarEquipos() throws LoginException {
		Equipo equi;
		List<Equipo> equipos = new ArrayList<Equipo>();
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_EQUIPO);
			rs = stmt.executeQuery();
			while (rs.next()) {
				equi = new Equipo();
				equi.setCod_equi(rs.getString(1));
				equi.setNombre_equipo(rs.getString(2));
				equipos.add(equi);
			}
		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				rs.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return equipos;
	}
}
