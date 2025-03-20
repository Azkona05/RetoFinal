package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import excepciones.LoginException;
import modelo.ClaseCompeticion;
import modelo.ClaseEquipo;
import modelo.ClaseJugador;
import modelo.ClasePartido;
import modelo.ClaseUsuario;
import modelo.EnumGanador;

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
	final String LOGIN = "SELECT *FROM USUARIO WHERE NOM = ? AND CONTRASENIA = ?";
	// SQL Jugador
	final String ALTA_JUGADOR = "INSERT INTO JUGADOR (dni, nombre, apellido, dorsal, posicion, cod_equi) VALUES (?, ?, ?, ?, ?, ?)";
	final String BAJA_JUGADOR = "DELETE FROM JUGADOR WHERE dni = ?";
	final String MODIFICAR_JUGADOR = "UPDATE jugador SET nombre = ?, apellido = ?, dorsal = ?, posicion = ?, cod_equi = ? WHERE dni = ?";
	final String BUSCAR_JUGADOR = "SELECT * FROM JUGADOR";
	// SQL Competicion
	final String ALTA_COMPETICION = "INSERT INTO COMPETICICION (cod_comp, nombre_competicion) VALUES (?, ?)";
	final String BAJA_COMPETICION = "DELETE FROM COMPETICION WHERE cod_comp = ?";
	final String MODIFICAR_COMPETICION = "UPDATE COMPETICION SET nombre_competicion = ? WHERE cod_comp = ?";
	final String BUSCAR_COMPETICION = "SELECT * FROM COMPETICION";
	// SQL Equipo
	final String ALTA_EQUIPO = "INSERT INTO EQUIPO (cod_equi, nombre_equipo) VALUES (?, ?)";
	final String BAJA_EQUIPO = "DELETE FROM EQUIPO WHERE cod_equi = ?";
	final String MODIFICAR_EQUIPO = "UPDATE EQUIPO SET nombre_equipo = ? WHERE cod_equi = ?";
	final String BUSCAR_EQUIPO = "SELECT * FROM EQUIPO";
	// SQL Partido
	final String ALTA_PARTIDO = "INSERT INTO PARTIDO (cod_part, equipo_local, equipo_visitante, ganador, fecha, cod_comp) VALUES (?, ?, ?, ?, ?)";
	final String BAJA_PARTIDO = "DELETE FROM PARTIDO WHERE cod_part = ?";
	final String MODIFICAR_PARTIDO = "UPDATE PAARTIDO SET equipo_local = ?, equipo_visitante = ?, ganador = ?, fecha = ?, cod_comp = ? WHERE cod_part = ?";
	final String BUSCAR_PARTIDO = "SELECT * FROM PARTIDO";

	public DaoImplementacion() {
		this.configFile = ResourceBundle.getBundle("modelo.configClass");
		this.urlDB = this.configFile.getString("Conn");
		this.userDB = this.configFile.getString("DBUser");
		this.passwordDB = this.configFile.getString("DBPass");

	}

	private void openConnection() {

		try {
			con = DriverManager.getConnection(urlDB, this.userDB, this.passwordDB);
//			con = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/concesionario?serverTimezone=Europe/Madrid&useSSL=false", "root",
//					"abcd*1234");
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
	public void altaJugador(Jugador jug) {
		openConnection();
		try {
			stmt = con.prepareStatement(ALTA_JUGADOR);
			stmt.setString(1, jug.getDni());
			stmt.setString(2, jug.getNombre());
			stmt.setString(3, jug.getApellido());
			stmt.setInt(4, jug.getDorsal());
			stmt.setString(5, jug.getPosicion().name());
			stmt.setString(6, jug.getCod_equi());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void bajaJugador(Jugador jug) {

		openConnection();
		try {
			stmt = con.prepareStatement(BAJA_JUGADOR);
			stmt.setString(1, jug.getDni());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void modificarJugador(Jugador jug) {
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
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Map<String, Jugador> listarJugadores() {
		Jugador jug;
		Map<String, Jugador> jugadores = new TreeMap<>();
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_JUGADOR);
			rs = stmt.executeQuery();
			// Leemos de uno en uno los propietarios devueltos en el ResultSet
			while (rs.next()) {
				jug = new Jugador();
				jug.setDni(rs.getString("dni"));;
				jug.setNombre(rs.getString("nombre"));
				jug.setApellido(rs.getString("apellido"));
				jug.setDorsal(rs.getInt("dorsal"));
				//jug.setPosicion(rs.getString("posicion"));
				jugadores.put(jug.getDni(), jug);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
	public void altaCompeticion(Competicion comp) {
		openConnection();
		try {
			stmt = con.prepareStatement(ALTA_COMPETICION);
			stmt.setString(1, comp.getCod_comp());
			stmt.setString(2, comp.getNombre_competicion());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	}

	@Override
	public void bajaCompeticion(Competicion comp) {
		openConnection();
		try {
			stmt = con.prepareStatement(BAJA_COMPETICION);
			stmt.setString(1, comp.getCod_comp());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void modificarCompeticion(Competicion comp) {
		openConnection();
		try {
			stmt = con.prepareStatement(MODIFICAR_COMPETICION);
			stmt.setString(1, comp.getNombre_competicion());
			stmt.setString(2, comp.getCod_comp());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Map<String, Competicion> listarCompeticiones() {
		Competicion comp;
		Map<String, Competicion> competiciones = new TreeMap<>();
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_JUGADOR);
			rs = stmt.executeQuery();
			// Leemos de uno en uno los propietarios devueltos en el ResultSet
			while (rs.next()) {
				comp = new Competicion();
				comp.setNombre_competicion(rs.getString("nombre_competicion"));;
				competiciones.put(comp.getCod_comp(), comp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
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
	public void altaEquipo(Equipo eq) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bajaEquipo(Equipo eq) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarEquipo(Equipo eq) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Equipo> listarEquipos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void altaPartido(ClasePartido part) {
		openConnection();
		try {
			stmt = con.prepareStatement(ALTA_PARTIDO);
			stmt.setInt(0, part.getCod_part());
			stmt.setString(1, part.getEquipo_local());
			stmt.setString(2, part.getEquipo_visitante());
			stmt.setString(3, part.getGanadorString());
			stmt.setDate(4, Date.valueOf(part.getFecha()));
			stmt.setString(5, part.getCod_comp());
		}catch (SQLException e) {
		}
		finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void bajaPartido(ClasePartido part) {
		openConnection();
		try {
			stmt = con.prepareStatement(BAJA_PARTIDO);
			stmt.setInt(1, part.getCod_part());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void modificarPartido(ClasePartido part) {
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
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Map<Integer, ClasePartido> listarPartidos() {
		ResultSet rs = null;
		Map<Integer, ClasePartido> partidos = new HashMap<Integer, ClasePartido>();
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_PARTIDO);
			rs = stmt.executeQuery();

			while (rs.next()) {
				ClasePartido part = new ClasePartido();
				part.setCod_part(rs.getInt(1));
				part.setEquipo_local(rs.getString(2));
				part.setEquipo_visitante(rs.getString(3));
				part.setGanador(EnumGanador.valueOf(rs.getString(4)));
				part.setFecha(rs.getDate(5).toLocalDate());
				part.setCod_comp(rs.getString(6));
				partidos.put(part.getCod_part(), part);
			}

		} catch (SQLException e) {
			e.printStackTrace();
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
}
