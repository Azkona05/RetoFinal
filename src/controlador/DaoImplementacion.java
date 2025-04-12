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

/**
 * Implementación de la interfaz DAO para el sistema de gestión de fútbol
 * americano. Proporciona todos los métodos CRUD (Crear, Leer, Actualizar,
 * Eliminar) para las entidades: Usuario, Jugador, Competición, Equipo y
 * Partido, interactuando con una base de datos MySQL.
 * 
 * Esta clase maneja todas las operaciones utilizando JDBC y sigue el patrón
 * Data Access Object para separar la lógica de acceso a datos del resto de la
 * aplicación.
 * 
 * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
 * @version 1.0
 * @see InterfazDao
 */
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
	final String MOSTRAR_JUGADOR_EQUIPO = "SELECT * FROM JUGADOR WHERE cod_equi=?";
	// SQL Competicion
	final String ALTA_COMPETICION = "INSERT INTO COMPETICION (cod_comp, nombre_competicion) VALUES (?, ?)";
	final String BAJA_COMPETICION = "DELETE FROM COMPETICION WHERE cod_comp = ?";
	final String MODIFICAR_COMPETICION = "UPDATE COMPETICION SET nombre_competicion = ? WHERE cod_comp = ?";
	final String BUSCAR_COMPETICION = "SELECT * FROM COMPETICION";
	final String BUSCAR_EQUIPOS_COMP = "SELECT nombre_equipo, (Select count(*) from partido p2 where p2.ganador = cod_equi) as victorias "
			+ "	FROM futbol_americano.partido " + "		JOIN equipo ON(equipo_local=cod_equi) "
			+ "    WHERE fecha between '2020-01-01' and current_date()  AND cod_comp like '%?%' "
			+ "    order by victorias DESC;";
	final String Metodo_Burro = "select * from partido where cod_comp=?";
	// SQL Equipo
	final String ALTA_EQUIPO = "INSERT INTO EQUIPO (cod_equi, nombre_equipo) VALUES (?, ?)";
	final String BAJA_EQUIPO = "DELETE FROM EQUIPO WHERE cod_equi = ?";
	final String MODIFICAR_EQUIPO = "UPDATE EQUIPO SET nombre_equipo = ? WHERE cod_equi = ?";
	final String BUSCAR_EQUIPO = "SELECT * FROM EQUIPO";
	final String BUSCAR_EQUIPO_LIGA = "SELECT cod_equi, nombre_equipo from equipo where cod_equi IN (SELECT equipo_local from partido where cod_comp=?) OR cod_equi IN (SELECT equipo_visitante from partido where cod_comp=?)";
	// SQL Partido
	final String ALTA_PARTIDO = "INSERT INTO PARTIDO (cod_part, equipo_local, equipo_visitante, ganador, fecha, cod_comp) VALUES (?, ?, ?, ?, ?, ?)";
	final String BAJA_PARTIDO = "DELETE FROM PARTIDO WHERE cod_part = ?";
	final String MODIFICAR_PARTIDO = "UPDATE PARTIDO SET equipo_local = ?, equipo_visitante = ?, ganador = ?, fecha = ?, cod_comp = ? WHERE cod_part = ?";
	final String BUSCAR_PARTIDO = "SELECT * FROM PARTIDO";
	final String CANTIDAD_PARTIDOS = "SELECT COUNT(*) FROM PARTIDO";

	final String PARTIDOS_DIA = "SELECT * FROM PARTIDO WHERE DATE(FECHA) = ? ORDER BY fecha ASC";
	final String EQUIPOS_LIGA = "SELECT * FROM EQUIPO WHERE cod_equi in (select equipo_local from partido ";

	final String NUEVOS_EQUIPOS = "select * from equipo where cod_equi not in (select equipo_local from partido where cod_comp=?) and cod_equi not in (select equipo_visitante from partido where cod_comp=?)";

	/**
	 * Constructor que inicializa la configuración de conexión a la base de datos
	 * leyendo los parámetros desde el archivo de propiedades
	 * configClass.properties.
	 */
	public DaoImplementacion() {
		this.configFile = ResourceBundle.getBundle("modelo.configClass");
		this.urlDB = this.configFile.getString("Conn");
		this.userDB = this.configFile.getString("DBUser");
		this.passwordDB = this.configFile.getString("DBPass");

	}

	/**
	 * Establece una conexión con la base de datos utilizando los parámetros
	 * configurados en el constructor.
	 */
	private void openConnection() {

		try {
			con = DriverManager.getConnection(urlDB, this.userDB, this.passwordDB);
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/futbol_americano?serverTimezone=Europe/Madrid&useSSL=false", "root",
//				"abcd*1234");
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
		}
	}

	/**
	 * Cierra la conexión con la base de datos y libera los recursos asociados.
	 * 
	 * @throws SQLException Si ocurre algún error al cerrar los recursos
	 */
	private void closeConnection() throws SQLException {

		if (stmt != null) {
			stmt.close();
		}
		if (con != null)
			con.close();
	}

	/**
	 * Autentica un usuario en el sistema comprobando sus credenciales.
	 * 
	 * @param usuario Objeto Usuario que contiene nombre y contraseña
	 * @throws LoginException Si las credenciales son incorrectas o hay error de
	 *                        conexión
	 */
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
				e.printStackTrace();
			}
		}
	}

	/**
	 * Registra un nuevo jugador en la base de datos.
	 * 
	 * @param jug Objeto Jugador con los datos a registrar
	 * @throws LoginException Si ocurre algún error en la operación de base de datos
	 */
	@Override
	public void altaJugador(Jugador jug) throws LoginException {
		openConnection();
		try {
			stmt = con.prepareStatement(ALTA_JUGADOR);
			stmt.setString(1, jug.getDni());
			stmt.setString(2, jug.getNombre());
			stmt.setString(3, jug.getApellido());
			stmt.setInt(4, jug.getDorsal());
			stmt.setString(5, jug.getPosicion().name());
			stmt.setString(6, jug.getCod_equi());
			if (stmt.executeUpdate() != 1) {
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

	/**
	 * Elimina un jugador de la base de datos.
	 * 
	 * @param jug Objeto Jugador que se desea eliminar
	 * @throws LoginException Si ocurre algún error en la operación de base de datos
	 */
	@Override
	public void bajaJugador(Jugador jug) throws LoginException {

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

	/**
	 * Actualiza los datos de un jugador existente en la base de datos.
	 * 
	 * @param jug Objeto Jugador con los nuevos datos
	 * @throws LoginException Si ocurre algún error en la operación de base de datos
	 */
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

	/**
	 * Obtiene una lista con todos los jugadores registrados en el sistema.
	 * 
	 * @return Lista de objetos Jugador
	 * @throws LoginException Si ocurre algún error en la operación de base de datos
	 */
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
				String posicionStr = rs.getString("posicion");
				if (posicionStr != null) {
					jug.setPosicion(EnumPosicion.valueOf(posicionStr.trim().toUpperCase())); // Asegurarse de mayúsculas
					jugadores.add(jug);
				}
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

	/**
	 * Método que inserta una nueva competición en la base de datos.
	 * 
	 * @param comp El objeto Competicion que contiene la información a insertar.
	 * @throws LoginException Si ocurre un error durante la inserción en la base de
	 *                        datos.
	 */
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

	/**
	 * Método que elimina una competición de la base de datos.
	 * 
	 * @param comp El objeto Competicion que se desea eliminar.
	 * @throws LoginException Si ocurre un error durante la eliminación en la base
	 *                        de datos.
	 */
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

	/**
	 * Método que modifica los detalles de una competición en la base de datos.
	 * 
	 * @param comp El objeto Competicion que contiene la información actualizada.
	 * @throws LoginException Si ocurre un error durante la modificación en la base
	 *                        de datos.
	 */
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

	/**
	 * Método que obtiene todas las competiciones registradas en la base de datos.
	 * 
	 * @return Un mapa de competiciones, donde la clave es el código de la
	 *         competición y el valor es el objeto Competicion correspondiente.
	 * @throws LoginException Si ocurre un error durante la obtención de
	 *                        competiciones de la base de datos.
	 */
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

	/**
	 * Método que inserta un nuevo equipo en la base de datos.
	 * 
	 * @param eq El objeto Equipo que contiene la información del equipo a insertar.
	 * @throws LoginException Si ocurre un error durante la inserción en la base de
	 *                        datos.
	 */
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

	/**
	 * Método que elimina un equipo de la base de datos.
	 * 
	 * @param eq El objeto Equipo que se desea eliminar.
	 * @throws LoginException Si ocurre un error durante la eliminación en la base
	 *                        de datos.
	 */
	@Override
	public void bajaEquipo(Equipo eq) throws LoginException {
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

	/**
	 * Método que modifica los detalles de un equipo en la base de datos.
	 * 
	 * @param eq El objeto Equipo que contiene la información actualizada.
	 * @throws LoginException Si ocurre un error durante la modificación en la base
	 *                        de datos.
	 */
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

	/**
	 * Método que obtiene todos los equipos registrados en la base de datos.
	 * 
	 * @return Un mapa de equipos, donde la clave es el código del equipo y el valor
	 *         es el objeto Equipo correspondiente.
	 * @throws LoginException Si ocurre un error durante la obtención de equipos de
	 *                        la base de datos.
	 */
	@Override
	public Map<String, Equipo> listarEquipos() throws LoginException {
		Equipo equi;
		Map<String, Equipo> equipos = new TreeMap<>();
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_JUGADOR);
			rs = stmt.executeQuery();
			// Leemos de uno en uno los equipos devueltos en el ResultSet
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

	/**
	 * Método que inserta un nuevo partido en la base de datos.
	 * 
	 * @param part El objeto Partido que contiene la información del partido a
	 *             insertar.
	 * @throws LoginException Si ocurre un error durante la inserción en la base de
	 *                        datos.
	 */
	@Override
	public void altaPartido(Partido part) throws LoginException {
		openConnection();
		try {
			stmt = con.prepareStatement(ALTA_PARTIDO);
			stmt.setInt(1, part.getCod_part());
			stmt.setString(2, part.getEquipo_local());
			stmt.setString(3, part.getEquipo_visitante());
			stmt.setString(4, part.getGanadorString());
			stmt.setDate(5, Date.valueOf(part.getFecha()));
			stmt.setString(6, part.getCod_comp());
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

	/**
	 * Método que elimina un partido de la base de datos.
	 * 
	 * @param part El objeto Partido que se desea eliminar.
	 * @throws LoginException Si ocurre un error durante la eliminación en la base
	 *                        de datos.
	 */
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

	/**
	 * Método que modifica los detalles de un partido en la base de datos.
	 * 
	 * @param part El objeto Partido que contiene la información actualizada.
	 * @throws LoginException Si ocurre un error durante la modificación en la base
	 *                        de datos.
	 */
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

	/**
	 * Método que obtiene todos los partidos registrados en la base de datos.
	 * 
	 * @return Un mapa de partidos, donde la clave es el código del partido y el
	 *         valor es el objeto Partido correspondiente.
	 * @throws LoginException Si ocurre un error durante la obtención de partidos de
	 *                        la base de datos.
	 */
	@Override
	public Map<Integer, Partido> listarPartidos() throws LoginException {
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

	/**
	 * Obtiene una lista de equipos participantes en una competición específica.
	 * 
	 * @param liga Competición para la cual se buscan los equipos participantes
	 * @return Lista de equipos que participan en la competición especificada
	 * @throws LoginException Si ocurre algún error en la operación de base de datos
	 */
	@Override
	public List<Equipo> buscarDifEquipo(Competicion liga) throws LoginException {
		ResultSet rs = null;
		List<Equipo> equipos = new ArrayList<>();
		Equipo equi;
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_EQUIPO_LIGA);
			stmt.setString(1, liga.getCod_comp());
			stmt.setString(2, liga.getCod_comp());
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
				if (rs != null) {
					rs.close();
				}
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return equipos;
	}

	/**
	 * Obtiene una lista de partidos asociados a una competición específica.
	 * 
	 * @param liga Competición para la cual se buscan los partidos
	 * @return Lista de partidos pertenecientes a la competición especificada
	 * @throws LoginException Si ocurre algún error en la operación de base de datos
	 */
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

	/**
	 * Obtiene una lista de partidos programados para una fecha específica.
	 * 
	 * @param fecha Fecha para la cual se buscan partidos
	 * @return Lista de partidos programados para la fecha especificada
	 * @throws LoginException Si ocurre algún error en la operación de base de datos
	 */
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

	/**
	 * Obtiene una matriz de objetos con los datos de todos los jugadores.
	 * 
	 * @param jug Jugador (parámetro no utilizado, podría considerarse eliminarlo)
	 * @return Matriz de objetos con los datos de los jugadores [dni, nombre,
	 *         apellido, dorsal, posición, código equipo]
	 * @throws LoginException Si ocurre algún error en la operación de base de datos
	 */
	public Object[][] mostrarDatosJugador(Jugador jug) throws LoginException {
		List<Object[]> listaJugadores = new ArrayList<>();
		ResultSet rs = null;

		try {
			openConnection();
			stmt = con.prepareStatement(BUSCAR_JUGADOR);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Object[] fila = { rs.getString("dni"), rs.getString("nombre"), rs.getString("apellido"),
						rs.getInt("dorsal"), rs.getString("posicion"), rs.getString("cod_equi") };
				listaJugadores.add(fila);
			}

			return listaJugadores.toArray(new Object[0][0]);

		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				if (rs != null)
					rs.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Obtiene una matriz de objetos con los datos de todos los equipos.
	 * 
	 * @param eq Equipo (parámetro no utilizado, podría considerarse eliminarlo)
	 * @return Matriz de objetos con los datos de los equipos [código equipo, nombre
	 *         equipo]
	 * @throws LoginException Si ocurre algún error en la operación de base de datos
	 */
	public Object[][] mostrarDatosEquipo(Equipo eq) throws LoginException {
		List<Object[]> listaEquipos = new ArrayList<>();
		ResultSet rs = null;

		try {
			openConnection();
			stmt = con.prepareStatement(BUSCAR_EQUIPO);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Object[] fila = { rs.getString("cod_equi"), rs.getString("nombre_equipo") };
				listaEquipos.add(fila);
			}

			return listaEquipos.toArray(new Object[0][0]);

		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				if (rs != null)
					rs.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Obtiene una matriz de objetos con los datos de todas las competiciones.
	 * 
	 * @param comp Competición (parámetro no utilizado, podría considerarse
	 *             eliminarlo)
	 * @return Matriz de objetos con los datos de competiciones [código competición,
	 *         nombre competición]
	 * @throws LoginException Si ocurre algún error en la operación de base de datos
	 */
	public Object[][] mostrarDatosCompeticion(Competicion comp) throws LoginException {
		List<Object[]> listaCompeticion = new ArrayList<>();
		ResultSet rs = null;

		try {
			openConnection();
			stmt = con.prepareStatement(BUSCAR_COMPETICION);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Object[] fila = { rs.getString("cod_comp"), rs.getString("nombre_competicion") };
				listaCompeticion.add(fila);
			}

			return listaCompeticion.toArray(new Object[0][0]);

		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				if (rs != null)
					rs.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Obtiene una matriz de objetos con los datos de todos los partidos.
	 * 
	 * @param part Partido (parámetro no utilizado, podría considerarse eliminarlo)
	 * @return Matriz de objetos con los datos de partidos [código partido, equipo
	 *         local, equipo visitante, ganador, fecha, código competición]
	 * @throws LoginException Si ocurre algún error en la operación de base de datos
	 */
	public Object[][] mostrarDatosPartido(Partido part) throws LoginException {
		List<Object[]> listaPartidos = new ArrayList<>();
		ResultSet rs = null;

		try {
			openConnection();
			stmt = con.prepareStatement(BUSCAR_PARTIDO);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Object[] fila = { rs.getInt("cod_part"), rs.getString("equipo_local"), rs.getString("equipo_visitante"),
						rs.getString("ganador"), rs.getDate("fecha"), rs.getString("cod_comp") };
				listaPartidos.add(fila);
			}

			return listaPartidos.toArray(new Object[0][0]);

		} catch (SQLException e) {
			throw new LoginException("Problemas en la BDs");
		} finally {
			try {
				if (rs != null)
					rs.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Obtiene una lista de todas las competiciones registradas en el sistema.
	 * 
	 * @return Lista de todas las competiciones
	 * @throws LoginException Si ocurre algún error en la operación de base de datos
	 */
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

	/**
	 * Obtiene una lista de todos los equipos registrados en el sistema.
	 * 
	 * @return Lista de todos los equipos
	 * @throws LoginException Si ocurre algún error en la operación de base de datos
	 */
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

	/**
	 * Obtiene una lista de equipos que no participan en una competición específica.
	 * 
	 * @param comp Competición para la cual se buscan equipos no participantes
	 * @return Lista de equipos que no participan en la competición especificada
	 */
	@Override
	public List<Equipo> nuevosEquipos(Competicion comp) {
		Equipo equi;
		List<Equipo> equipos = new ArrayList<Equipo>();
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(NUEVOS_EQUIPOS);
			stmt.setString(1, comp.getCod_comp());
			stmt.setString(2, comp.getCod_comp());
			rs = stmt.executeQuery();
			while (rs.next()) {
				equi = new Equipo();
				equi.setCod_equi(rs.getString(1));
				equi.setNombre_equipo(rs.getString(2));
				equipos.add(equi);
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
		return equipos;
	}

	/**
	 * Obtiene el número total de partidos registrados en el sistema.
	 * 
	 * @return Cantidad total de partidos
	 */
	public int cantidadPartidos() {
		int i = 0;
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(CANTIDAD_PARTIDOS);
			rs = stmt.executeQuery();
			if (rs.next()) {
				i = rs.getInt(1);
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

		return i;
	}

	public List<Partido> mostarPartidos(Partido part) {
		Partido p;
		ResultSet rs = null;
		List<Partido> partidos = new ArrayList<Partido>();
		openConnection();
		try {
			stmt = con.prepareStatement(BUSCAR_PARTIDO);
			rs = stmt.executeQuery();
			while (rs.next()) {
				p = new Partido();
				p.setCod_part(rs.getInt(1));
				p.setEquipo_local(rs.getString(2));
				p.setEquipo_visitante(rs.getString(3));
				p.setCod_part(rs.getInt(1));
				p.setCod_part(rs.getInt(1));
				partidos.add(p);
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
