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
	public void altaJugador(Jugador jug);
	public void bajaJugador(Jugador jug);
	public void modificarJugador(Jugador jug);
	public List<Jugador> listarJugadores();
	public Object[][]  mostrarDatosJugador(Jugador jug) throws LoginException; 
	//COMPETICION
	public void altaCompeticion(Competicion comp);
	public void bajaCompeticion (Competicion comp);
	public void modificarCompeticion (Competicion comp);
	public Map<String, Competicion> listarCompeticiones();
	public List<Partido> buscarEquiLiga(Competicion liga);
	public Object[][]  mostrarDatosCompeticion(Competicion comp) throws LoginException; 
	public List<Competicion> devolverCompeticiones();
	//EQUIPO
	public void altaEquipo (Equipo eq);
	public void bajaEquipo (Equipo eq);
	public void modificarEquipo (Equipo eq);
	public List<Equipo> buscarEquipos ();
	public Map<String, Equipo> listarEquipos();
	public Object[][]  mostrarDatosEquipo(Equipo eq) throws LoginException; 
	//PARTIDO
	public void altaPartido (Partido part);
	public void bajaPartido (Partido part);
	public void modificarPartido (Partido part);
	public Map<Integer, Partido> listarPartidos();
	List<Equipo> buscarDifEquipo(Competicion liga);
	List<Partido> devolverPartidos(LocalDate fecha);
	public Object[][] mostrarDatosPartido(Partido part) throws LoginException;
	public List<Equipo> nuevosEquipos(Competicion comp);
}
