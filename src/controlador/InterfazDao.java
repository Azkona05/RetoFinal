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

/**
 * Interfaz que define los métodos para gestionar las operaciones de acceso a datos
 * relacionadas con usuarios, jugadores, competiciones, equipos y partidos.
 * Utiliza excepciones personalizadas para manejar errores durante los procesos.
 */
public interface InterfazDao {

    // LOGIN

    /**
     * Método para autenticar a un usuario en el sistema.
     * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
     * @param usuario El usuario que intenta iniciar sesión.
     * @throws LoginException Si las credenciales no son correctas o hay un error durante el login.
     */
    public void login(Usuario usuario) throws LoginException;

    // JUGADOR

    /**
     * Método para dar de alta un jugador en el sistema.
     * 
     * @param jug El jugador a ser dado de alta.
     * @throws LoginException Si ocurre un error durante el alta.
     */
    public void altaJugador(Jugador jug) throws LoginException;

    /**
     * Método para dar de baja a un jugador en el sistema.
     * 
     * @param jug El jugador a ser dado de baja.
     * @throws LoginException Si ocurre un error durante la baja.
     */
    public void bajaJugador(Jugador jug) throws LoginException;

    /**
     * Método para modificar los datos de un jugador.
     * 
     * @param jug El jugador cuyos datos serán modificados.
     * @throws LoginException Si ocurre un error durante la modificación.
     */
    public void modificarJugador(Jugador jug) throws LoginException;

    /**
     * Método para listar todos los jugadores en el sistema.
     * 
     * @return Lista de jugadores.
     * @throws LoginException Si ocurre un error durante la consulta.
     */
    public List<Jugador> listarJugadores() throws LoginException;

    /**
     * Método para mostrar los datos de un jugador en un formato de tabla.
     * 
     * @param jug El jugador cuyos datos se van a mostrar.
     * @return Una tabla de objetos con los datos del jugador.
     * @throws LoginException Si ocurre un error al mostrar los datos.
     */
    public Object[][] mostrarDatosJugador(Jugador jug) throws LoginException;

    // COMPETICION

    /**
     * Método para dar de alta una nueva competición.
     * 
     * @param comp La competición a ser dada de alta.
     * @throws LoginException Si ocurre un error durante el alta de la competición.
     */
    public void altaCompeticion(Competicion comp) throws LoginException;

    /**
     * Método para dar de baja una competición.
     * 
     * @param comp La competición a ser dada de baja.
     * @throws LoginException Si ocurre un error durante la baja.
     */
    public void bajaCompeticion(Competicion comp) throws LoginException;

    /**
     * Método para modificar los datos de una competición.
     * 
     * @param comp La competición cuyos datos se van a modificar.
     * @throws LoginException Si ocurre un error durante la modificación.
     */
    public void modificarCompeticion(Competicion comp) throws LoginException;

    /**
     * Método para listar todas las competiciones del sistema.
     * 
     * @return Un mapa con las competiciones, donde la clave es el código de la competición.
     * @throws LoginException Si ocurre un error al listar las competiciones.
     */
    public Map<String, Competicion> listarCompeticiones() throws LoginException;

    /**
     * Método para buscar los equipos de una competición.
     * 
     * @param liga La competición en la que se buscarán los equipos.
     * @return Lista de partidos asociados a la competición.
     * @throws LoginException Si ocurre un error al buscar los equipos.
     */
    public List<Partido> buscarEquiLiga(Competicion liga) throws LoginException;

    /**
     * Método para mostrar los datos de una competición en un formato de tabla.
     * 
     * @param comp La competición cuyos datos se van a mostrar.
     * @return Una tabla de objetos con los datos de la competición.
     * @throws LoginException Si ocurre un error al mostrar los datos.
     */
    public Object[][] mostrarDatosCompeticion(Competicion comp) throws LoginException;

    /**
     * Método para devolver una lista con todas las competiciones.
     * 
     * @return Lista de competiciones.
     * @throws LoginException Si ocurre un error al devolver las competiciones.
     */
    public List<Competicion> devolverCompeticiones() throws LoginException;

    // EQUIPO

    /**
     * Método para dar de alta un equipo.
     * 
     * @param eq El equipo a ser dado de alta.
     * @throws LoginException Si ocurre un error durante el alta del equipo.
     */
    public void altaEquipo(Equipo eq) throws LoginException;

    /**
     * Método para dar de baja un equipo.
     * 
     * @param eq El equipo a ser dado de baja.
     * @throws LoginException Si ocurre un error durante la baja del equipo.
     */
    public void bajaEquipo(Equipo eq) throws LoginException;

    /**
     * Método para modificar los datos de un equipo.
     * 
     * @param eq El equipo cuyos datos se van a modificar.
     * @throws LoginException Si ocurre un error durante la modificación del equipo.
     */
    public void modificarEquipo(Equipo eq) throws LoginException;

    /**
     * Método para buscar equipos en el sistema.
     * 
     * @return Lista de equipos.
     * @throws LoginException Si ocurre un error al buscar los equipos.
     */
    public List<Equipo> buscarEquipos() throws LoginException;

    /**
     * Método para listar todos los equipos del sistema.
     * 
     * @return Un mapa con los equipos, donde la clave es el código del equipo.
     * @throws LoginException Si ocurre un error al listar los equipos.
     */
    public Map<String, Equipo> listarEquipos() throws LoginException;

    /**
     * Método para mostrar los datos de un equipo en un formato de tabla.
     * 
     * @param eq El equipo cuyos datos se van a mostrar.
     * @return Una tabla de objetos con los datos del equipo.
     * @throws LoginException Si ocurre un error al mostrar los datos.
     */
    public Object[][] mostrarDatosEquipo(Equipo eq) throws LoginException;

    // PARTIDO

    /**
     * Método para obtener la cantidad total de partidos en el sistema.
     * 
     * @return Número de partidos.
     */
    public int cantidadPartidos();

    /**
     * Método para obtener los nuevos equipos en una competición.
     * 
     * @param comp La competición en la que se buscan los equipos.
     * @return Lista de equipos en la competición.
     */
    public List<Equipo> nuevosEquipos(Competicion comp);

    /**
     * Método para dar de alta un partido.
     * 
     * @param part El partido a ser dado de alta.
     * @throws LoginException Si ocurre un error durante el alta del partido.
     */
    public void altaPartido(Partido part) throws LoginException;

    /**
     * Método para dar de baja un partido.
     * 
     * @param part El partido a ser dado de baja.
     * @throws LoginException Si ocurre un error durante la baja del partido.
     */
    public void bajaPartido(Partido part) throws LoginException;
    
    /**
     * Método para obtener una lista de partidos desde la base de datos.
     * El parámetro puede servir como filtro para la búsqueda, dependiendo de la implementación.
     * 
     * @param part Objeto {@link Partido} que puede contener criterios para filtrar los resultados.
     * @return Lista de objetos {@link Partido} obtenidos según los criterios indicados o todos si no se filtra.
     */
    public List<Partido> mostrarPartidos(Partido part);

    /**
     * Método para modificar los datos de un partido.
     * 
     * @param part El partido cuyos datos se van a modificar.
     * @throws LoginException Si ocurre un error durante la modificación del partido.
     */
    public void modificarPartido(Partido part) throws LoginException;

    /**
     * Método para buscar equipos con diferencias en una competición.
     * 
     * @param liga La competición en la que se buscan las diferencias.
     * @return Lista de equipos con diferencias.
     * @throws LoginException Si ocurre un error al buscar los equipos.
     */
    List<Equipo> buscarDifEquipo(Competicion liga) throws LoginException;

    /**
     * Método para devolver los partidos que se jugaron en una fecha específica.
     * 
     * @param fecha La fecha de los partidos a buscar.
     * @return Lista de partidos jugados en esa fecha.
     * @throws LoginException Si ocurre un error al devolver los partidos.
     */
    List<Partido> devolverPartidos(LocalDate fecha) throws LoginException;

    /**
     * Método para mostrar los datos de un partido en un formato de tabla.
     * 
     * @param part El partido cuyos datos se van a mostrar.
     * @return Una tabla de objetos con los datos del partido.
     * @throws LoginException Si ocurre un error al mostrar los datos.
     */
    public Object[][] mostrarDatosPartido(Partido part) throws LoginException;

    /**
     * Método para obtener los jugadores que pertenecen a un equipo específico.
     * 
     * @param equi El equipo del cual se desean obtener los jugadores.
     * @return Lista de objetos {@link Jugador} que pertenecen al equipo especificado.
     */
	public List<Jugador> jugadoresEquipo(Equipo equi);

	/**
     * Método para obtener un equipo a partir de su nombre.
     * 
     * @param nombre El nombre del equipo a buscar.
     * @return Objeto {@link Equipo} que coincide con el nombre especificado, o null si no se encuentra.
     */
	public Equipo devolverEquiNombre(String nombre);
}
