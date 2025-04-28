package controlador;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import excepciones.LoginException;
import modelo.Competicion;
import modelo.Equipo;
import modelo.Jugador;
import modelo.Partido;
import modelo.Usuario;
import vista.VMenuPrincipal;

/**
 * Clase controladora principal que gestiona la lógica de negocio entre la vista
 * y el DAO. Proporciona métodos para operaciones relacionadas con usuarios,
 * jugadores, equipos, competiciones y partidos.
 * 
 * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
 */
public class Principal {

	/**
	 * Implementación del DAO para operaciones de base de datos.
	 */
	private static InterfazDao dao = new DaoImplementacion();

	/**
	 * Punto de entrada principal de la aplicación.
	 * 
	 * @param args Argumentos de la línea de comandos (no utilizados).
	 */
	public static void main(String[] args) {
		VMenuPrincipal vmp;
		try {
			vmp = new VMenuPrincipal();
			vmp.setVisible(true);
		} catch (LoginException e) {
			e.printStackTrace();
		}
	}

	// LOGIN

	/**
	 * Autentica a un usuario en el sistema.
	 * 
	 * @param usuario Usuario con credenciales (nombre y contraseña).
	 * @throws LoginException Si las credenciales son incorrectas o hay un error en
	 *                        la base de datos.
	 */
	public static void login(Usuario usuario) throws LoginException {
		dao.login(usuario);
	}

	// COMPETICIÓN

	/**
	 * Busca los partidos asociados a una competición.
	 * 
	 * @param liga Competición de la que se buscan los partidos.
	 * @return Lista de partidos de la competición.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static List<Partido> buscarEquiLiga(Competicion liga) throws LoginException {
		return dao.buscarEquiLiga(liga);
	}

	/**
	 * Obtiene todas las competiciones del sistema.
	 * 
	 * @return Mapa de competiciones con su código como clave.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static Map<String, Competicion> leerCompeticiones() throws LoginException {
		return dao.listarCompeticiones();
	}

	/**
	 * Devuelve los equipos de una competición.
	 * 
	 * @param liga Competición a consultar.
	 * @return Lista de equipos participantes.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static List<Equipo> devolverEquipos(Competicion liga) throws LoginException {
		return dao.buscarDifEquipo(liga);
	}

	/**
	 * Devuelve los partidos de una fecha específica.
	 * 
	 * @param fecha Fecha de los partidos a buscar.
	 * @return Lista de partidos en esa fecha.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static List<Partido> devolverPartidos(LocalDate fecha) throws LoginException {
		return dao.devolverPartidos(fecha);
	}

	// JUGADOR

	/**
	 * Elimina un jugador del sistema.
	 * 
	 * @param jugador Jugador a eliminar.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static void eliminarJugador(Jugador jugador) throws LoginException {
		dao.bajaJugador(jugador);
	}

	/**
	 * Modifica los datos de un jugador.
	 * 
	 * @param jugador Jugador con los nuevos datos.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	/**
	 * @param jugador
	 * @throws LoginException
	 */
	public static void modificarJugador(Jugador jugador) throws LoginException {
		dao.modificarJugador(jugador);
	}

	/**
	 * Registra un nuevo jugador en el sistema.
	 * 
	 * @param jugador Jugador a registrar.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static void altaJugador(Jugador jugador) throws LoginException {
		dao.altaJugador(jugador);
	}

	/**
	 * Obtiene los jugadores de un equipo.
	 * 
	 * @param equipo Equipo del que se buscan los jugadores.
	 * @return Lista de jugadores del equipo.
	 */
	public static List<Jugador> jugadorEquipo(Equipo equipo) {
		return dao.jugadoresEquipo(equipo);
	}

	// COMPETICIÓN (Operaciones CRUD)

	/**
	 * Actualiza los datos de una competición.
	 * 
	 * @param competicion Competición con los nuevos datos.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static void modificarCompeticion(Competicion competicion) throws LoginException {
		dao.modificarCompeticion(competicion);
	}

	/**
	 * Elimina una competición del sistema.
	 * 
	 * @param competicion Competición a eliminar.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static void eliminarCompeticion(Competicion competicion) throws LoginException {
		dao.bajaCompeticion(competicion);
	}

	/**
	 * Registra una nueva competición en el sistema.
	 * 
	 * @param competicion Competición a registrar.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static void altaCompeticion(Competicion competicion) throws LoginException {
		dao.altaCompeticion(competicion);
	}

	// EQUIPO

	/**
	 * Registra un nuevo equipo en el sistema.
	 * 
	 * @param equipo Equipo a registrar.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static void altaEquipo(Equipo equipo) throws LoginException {
		dao.altaEquipo(equipo);
	}

	/**
	 * Busca un equipo por su nombre.
	 * 
	 * @param nombre Nombre del equipo a buscar.
	 * @return Equipo encontrado o null si no existe.
	 */
	public static Equipo devolverEquiNombre(String nombre) {
		return dao.devolverEquiNombre(nombre);
	}

	/**
	 * Elimina un equipo del sistema.
	 * 
	 * @param equipo Equipo a eliminar.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static void bajaEquipo(Equipo equipo) throws LoginException {
		dao.bajaEquipo(equipo);
	}

	/**
	 * Modifica los datos de un equipo.
	 * 
	 * @param equipo Equipo con los nuevos datos.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static void modificarEquipo(Equipo equipo) throws LoginException {
		dao.modificarEquipo(equipo);
	}

	/**
	 * Obtiene todos los equipos del sistema.
	 * 
	 * @return Lista de todos los equipos.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static List<Equipo> buscarEquipos() throws LoginException {
		return dao.buscarEquipos();
	}

	// PARTIDO

	/**
	 * Registra un nuevo partido en el sistema.
	 * 
	 * @param partido Partido a registrar.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static void altaPartido(Partido partido) throws LoginException {
		dao.altaPartido(partido);
	}

	/**
	 * Elimina un partido del sistema.
	 * 
	 * @param partido Partido a eliminar.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static void bajaPartido(Partido partido) throws LoginException {
		dao.bajaPartido(partido);
	}

	/**
	 * Modifica los datos de un partido.
	 * 
	 * @param partido Partido con los nuevos datos.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static void modificarPartido(Partido partido) throws LoginException {
		dao.modificarPartido(partido);
	}

	// DATOS

	/**
	 * Obtiene los datos de una competición en formato de tabla.
	 * 
	 * @param competicion Competición a consultar.
	 * @return Matriz de objetos con los datos.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static Object[][] devolverCompeticiones(Competicion competicion) throws LoginException {
		return dao.mostrarDatosCompeticion(competicion);
	}

	/**
	 * Obtiene los datos de un partido en formato de tabla.
	 * 
	 * @param partido Partido a consultar.
	 * @return Matriz de objetos con los datos.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static Object[][] devolverPartidos(Partido partido) throws LoginException {
		return dao.mostrarDatosPartido(partido);
	}

	/**
	 * Obtiene los datos de un jugador en formato de tabla.
	 * 
	 * @param jugador Jugador a consultar.
	 * @return Matriz de objetos con los datos.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static Object[][] devolverJugadores(Jugador jugador) throws LoginException {
		return dao.mostrarDatosJugador(jugador);
	}

	/**
	 * Obtiene los datos de un equipo en formato de tabla.
	 * 
	 * @param equipo Equipo a consultar.
	 * @return Matriz de objetos con los datos.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static Object[][] devolverEquipos(Equipo equipo) throws LoginException {
		return dao.mostrarDatosEquipo(equipo);
	}

	/**
	 * Obtiene todas las competiciones del sistema.
	 * 
	 * @return Lista de competiciones.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static List<Competicion> devolverCompeticiones() throws LoginException {
		return dao.devolverCompeticiones();
	}

	/**
	 * Obtiene los equipos que no están en una competición.
	 * 
	 * @param competicion Competición de referencia.
	 * @return Lista de equipos no participantes.
	 */
	public static List<Equipo> nuevosEquipos(Competicion competicion) {
		return dao.nuevosEquipos(competicion);
	}

	/**
	 * Obtiene el número total de partidos registrados.
	 * 
	 * @return Cantidad de partidos.
	 */
	public static int cantidadPartidos() {
		return dao.cantidadPartidos();
	}

	/**
	 * Obtiene todos los equipos del sistema.
	 * 
	 * @return Lista de equipos.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static List<Equipo> devolverEquipos() throws LoginException {
		return dao.buscarEquipos();
	}

	/**
	 * Obtiene todos los jugadores del sistema.
	 * 
	 * @return Lista de jugadores.
	 * @throws LoginException Si ocurre un error al acceder a la base de datos.
	 */
	public static List<Jugador> devolverJugadores() throws LoginException {
		return dao.listarJugadores();
	}
}
