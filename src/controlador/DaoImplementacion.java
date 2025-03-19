package controlador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import excepciones.LoginException;
import modelo.Competicion;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Partido;
import modelo.Posicion;
import modelo.Usuario;

public class DaoImplementacion implements Interfaz_Dao {
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
	final String MODIFICAR_COMUPTICION = "UPDATE COMPETICION SET nombre_competicion = ? WHERE cod_comp = ?";
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

	// Tenemos que definie el ResusultSet para recoger el resultado de la consulta

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
			stmt.setString(3, jug.getApellido());
			stmt.setInt(4, jug.getDorsal());
			stmt.setString(5, jug.getPosicion().name());
			stmt.setString(3, jug.getDni());
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
		// TODO Auto-generated method stub

	}

	@Override
	public void bajaCompeticion(Competicion comp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarCompeticion(Competicion comp) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Competicion> listarCompeticiones() {
		// TODO Auto-generated method stub
		return null;
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
	public void altaPartido(Partido part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void bajaPartido(Partido part) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modificarPartido(Partido part) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<Integer, Partido> listarPartidos() {
		// TODO Auto-generated method stub
		return null;
	}
}
