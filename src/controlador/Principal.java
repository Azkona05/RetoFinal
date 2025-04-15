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
 * Clase principal que contiene la lógica para gestionar operaciones
 * relacionadas con el login, jugadores, competiciones, equipos y partidos.
 * Utiliza la implementación de la interfaz {@link InterfazDao} para realizar
 * operaciones sobre los datos.
 */
public class Principal {

	/** Instancia del DAO que implementa las operaciones sobre los datos */
	private static InterfazDao dao = new DaoImplementacion();

	/**
	 * Método principal que inicia la aplicación y muestra el menú principal.
	 * 
	 * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
	 * @param args Argumentos de la línea de comandos.
	 * @throws LoginException Si ocurre un error durante el login.
	 */
	public static void main(String[] args) throws LoginException {
		VMenuPrincipal vmp;
		vmp = new VMenuPrincipal();
		vmp.setVisible(true);
	}

	// LOGIN

	/**
	 * Realiza el login del usuario en el sistema.
	 * 
	 * @param usuario El usuario que intenta iniciar sesión.
	 * @throws LoginException Si las credenciales son incorrectas o ocurre un error
	 *                        durante el login.
	 */
	public static void login(Usuario usuario) throws LoginException {
		dao.login(usuario);
	}

	// COMPETICION

	/**
	 * Busca las competiciones asociadas a una liga.
	 * 
	 * @param liga La competición a buscar.
	 * @return Una lista de partidos asociados a la competición.
	 * @throws LoginException Si ocurre un error durante la búsqueda de
	 *                        competiciones.
	 */
	public static List<Partido> buscarEquiLiga(Competicion liga) throws LoginException {
		return dao.buscarEquiLiga(liga);
	}

	/**
	 * Obtiene un mapa de todas las competiciones disponibles en el sistema.
	 * 
	 * @return Un mapa de competiciones, con su código como clave.
	 * @throws LoginException Si ocurre un error durante la obtención de
	 *                        competiciones.
	 */
	public static Map<String, Competicion> leerCompeticiones() throws LoginException {
		return dao.listarCompeticiones();
	}

	/**
	 * Devuelve los equipos que participan en una competición.
	 * 
	 * @param liga La competición a consultar.
	 * @return Lista de equipos asociados a la competición.
	 * @throws LoginException Si ocurre un error durante la búsqueda de equipos.
	 */
	public static List<Equipo> devolverEquipos(Competicion liga) throws LoginException {
		return dao.buscarDifEquipo(liga);
	}

	/**
	 * Devuelve los partidos de una fecha específica.
	 * 
	 * @param fecha La fecha de los partidos.
	 * @return Lista de partidos jugados en esa fecha.
	 * @throws LoginException Si ocurre un error durante la búsqueda de partidos.
	 */
	public static List<Partido> devolverPartidos(LocalDate fecha) throws LoginException {
		return dao.devolverPartidos(fecha);
	}

	// JUGADOR

	/**
	 * Elimina un jugador del sistema.
	 * 
	 * @param j El jugador a eliminar.
	 * @throws LoginException Si ocurre un error durante la eliminación del jugador.
	 */
	public static void EliminarJugador(Jugador j) throws LoginException {
		dao.bajaJugador(j);
	}

	/**
	 * Modifica los datos de un jugador en el sistema.
	 * 
	 * @param j El jugador a modificar.
	 * @throws LoginException Si ocurre un error durante la modificación del
	 *                        jugador.
	 */
	public static void modificarJugador(Jugador j) throws LoginException {
		dao.modificarJugador(j);
	}

	/**
	 * Da de alta un nuevo jugador en el sistema.
	 * 
	 * @param j El jugador a dar de alta.
	 * @throws LoginException Si ocurre un error durante el alta del jugador.
	 */
	public static void altaJugador(Jugador j) throws LoginException {
		dao.altaJugador(j);
	}

    /**
     * Da de alta un nuevo jugador en el sistema.
     * 
     * @param equi El equipo a buscar sus jugadores.
     * @throws LoginException Si ocurre un error durante la busqueda del equipo.
     */
    public static List <Jugador> jugadorEquipo (Equipo equi)  {
		return dao.jugadoresEquipo(equi);
	}

    // COMPETICION

	// COMPETICION

	/**
	 * Modifica los datos de una competición.
	 * 
	 * @param comp La competición a modificar.
	 * @throws LoginException Si ocurre un error durante la modificación de la
	 *                        competición.
	 */
	public static void modificarCompeticion(Competicion comp) throws LoginException {
		dao.modificarCompeticion(comp);
	}

	/**
	 * Elimina una competición del sistema.
	 * 
	 * @param comp La competición a eliminar.
	 * @throws LoginException Si ocurre un error durante la eliminación de la
	 *                        competición.
	 */
	public static void eliminarCompeticion(Competicion comp) throws LoginException {
		dao.bajaCompeticion(comp);
	}

	/**
	 * Da de alta una nueva competición en el sistema.
	 * 
	 * @param comp La competición a dar de alta.
	 * @throws LoginException Si ocurre un error durante el alta de la competición.
	 */
	public static void altaCompeticion(Competicion comp) throws LoginException {
		dao.altaCompeticion(comp);
	}

    /**
     * Da de alta un nuevo equipo en el sistema.
     * 
     * @param eq El equipo a dar de alta.
     * @throws LoginException Si ocurre un error durante el alta del equipo.
     */
    public static void altaEquipo(Equipo eq) throws LoginException {
        dao.altaEquipo(eq);
    }
	
    
	

    /**
     * Da de alta un nuevo equipo en el sistema.
     * 
     * @param nombre del equipo a buscar.
     * @throws LoginException Si ocurre un error durante la busqueda del equipo.
     */
	public static Equipo devolverEquiNombre(String nombre) {
		return dao.devolverEquiNombre(nombre);
	}
	
    /**
     * Da de baja un equipo en el sistema.
     * 
     * @param eq El equipo a dar de baja.
     * @throws LoginException Si ocurre un error durante la baja del equipo.
     */
    public static void bajaEquipo(Equipo eq) throws LoginException {
        dao.bajaEquipo(eq);
    }

	/**
	 * Da de baja un equipo en el sistema.
	 * 
	 * @param eq El equipo a dar de baja.
	 * @throws LoginException Si ocurre un error durante la baja del equipo.
	 */
	public static void bajaEquipo(Equipo eq) throws LoginException {
		dao.bajaEquipo(eq);
	}

	/**
	 * Modifica los datos de un equipo en el sistema.
	 * 
	 * @param eq El equipo a modificar.
	 * @throws LoginException Si ocurre un error durante la modificación del equipo.
	 */
	public static void modificarEquipo(Equipo eq) throws LoginException {
		dao.modificarEquipo(eq);
	}

	/**
	 * Busca todos los equipos en el sistema.
	 * 
	 * @return Lista de equipos.
	 * @throws LoginException Si ocurre un error durante la búsqueda de equipos.
	 */
	public static List<Equipo> buscarEquipos() throws LoginException {
		return dao.buscarEquipos();
	}

	// PARTIDO

	/**
	 * Da de alta un nuevo partido en el sistema.
	 * 
	 * @param par El partido a dar de alta.
	 * @throws LoginException Si ocurre un error durante el alta del partido.
	 */
	public static void altaPartido(Partido par) throws LoginException {
		dao.altaPartido(par);
	}

	/**
	 * Da de baja un partido en el sistema.
	 * 
	 * @param par El partido a dar de baja.
	 * @throws LoginException Si ocurre un error durante la baja del partido.
	 */
	public static void bajaPartido(Partido par) throws LoginException {
		dao.bajaPartido(par);
	}

	/**
	 * Modifica los datos de un partido en el sistema.
	 * 
	 * @param par El partido a modificar.
	 * @throws LoginException Si ocurre un error durante la modificación del
	 *                        partido.
	 */
	public static void modificarPartido(Partido par) throws LoginException {
		dao.modificarPartido(par);
	}

	// DATOS

	/**
	 * Devuelve los datos de una competición en formato de tabla.
	 * 
	 * @param comp La competición cuyos datos se van a devolver.
	 * @return Un arreglo bidimensional con los datos de la competición.
	 * @throws LoginException Si ocurre un error durante la obtención de los datos.
	 */
	public static Object[][] devolverCompeticiones(Competicion comp) throws LoginException {
		return dao.mostrarDatosCompeticion(comp);
	}

	/**
	 * Devuelve los datos de un partido en formato de tabla.
	 * 
	 * @param part El partido cuyos datos se van a devolver.
	 * @return Un arreglo bidimensional con los datos del partido.
	 * @throws LoginException Si ocurre un error durante la obtención de los datos.
	 */
	public static Object[][] devolverPartidos(Partido part) throws LoginException {
		return dao.mostrarDatosPartido(part);
	}

	/**
	 * Devuelve los datos de un jugador en formato de tabla.
	 * 
	 * @param jug El jugador cuyos datos se van a devolver.
	 * @return Un arreglo bidimensional con los datos del jugador.
	 * @throws LoginException Si ocurre un error durante la obtención de los datos.
	 */
	public static Object[][] devolverJugadores(Jugador jug) throws LoginException {
		return dao.mostrarDatosJugador(jug);
	}

	/**
	 * Devuelve los datos de un equipo en formato de tabla.
	 * 
	 * @param eq El equipo cuyos datos se van a devolver.
	 * @return Un arreglo bidimensional con los datos del equipo.
	 * @throws LoginException Si ocurre un error durante la obtención de los datos.
	 */
	public static Object[][] devolverEquipos(Equipo eq) throws LoginException {
		return dao.mostrarDatosEquipo(eq);
	}

	/**
	 * Devuelve una lista de competiciones del sistema.
	 * 
	 * @return Lista de competiciones.
	 * @throws LoginException Si ocurre un error durante la obtención de las
	 *                        competiciones.
	 */
	public static List<Competicion> devolverCompeticiones() throws LoginException {
		return dao.devolverCompeticiones();
	}

	/**
	 * Devuelve los nuevos equipos asociados a una competición.
	 * 
	 * @param comp La competición para buscar los equipos.
	 * @return Lista de equipos nuevos en la competición.
	 */
	public static List<Equipo> nuevosEquipos(Competicion comp) {
		return dao.nuevosEquipos(comp);
	}

	/**
	 * Obtiene la cantidad total de partidos en el sistema.
	 * 
	 * @return El número total de partidos.
	 */
	public static int cantidadPartidos() {
		return dao.cantidadPartidos();
	}

	public static List<Equipo> devolverEquipos() throws LoginException {
		return dao.buscarEquipos();
	}

}
