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

public interface InterfazDao {
	//LOGIN
	public void login(Usuario usuario) throws LoginException;
	//JUGADOR
	public void altaJugador(Jugador jug) throws LoginException;
	public void bajaJugador(Jugador jug) throws LoginException;
	public void modificarJugador(Jugador jug) throws LoginException;
	public List<Jugador> listarJugadores() throws LoginException;
	public Object[][]  mostrarDatosJugador(Jugador jug) throws LoginException; 
	//COMPETICION
	public void altaCompeticion(Competicion comp) throws LoginException;
	public void bajaCompeticion (Competicion comp) throws LoginException;
	public void modificarCompeticion (Competicion comp) throws LoginException;
	public Map<String, Competicion> listarCompeticiones() throws LoginException;
	public List<Partido> buscarEquiLiga(Competicion liga) throws LoginException;
	public Object[][]  mostrarDatosCompeticion(Competicion comp) throws LoginException; 
	public List<Competicion> devolverCompeticiones() throws LoginException;
	//EQUIPO
	public void altaEquipo (Equipo eq) throws LoginException;
	public void bajaEquipo (Equipo eq) throws LoginException;
	public void modificarEquipo (Equipo eq) throws LoginException;
	public List<Equipo> buscarEquipos () throws LoginException;
	public Map<String, Equipo> listarEquipos() throws LoginException;
	public Object[][]  mostrarDatosEquipo(Equipo eq) throws LoginException; 
	//PARTIDO
	public void altaPartido (Partido part) throws LoginException;
	public void bajaPartido (Partido part) throws LoginException;
	public void modificarPartido (Partido part) throws LoginException;
	public Map<Integer, Partido> listarPartidos() throws LoginException;
	List<String> buscarDifEquipo(Competicion liga) throws LoginException;
	List<Partido> devolverPartidos(LocalDate fecha) throws LoginException;
	public Object[][]  mostrarDatosPartido(Partido part) throws LoginException;
}
