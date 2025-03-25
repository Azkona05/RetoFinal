package controlador;

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
	public Map<String, Jugador> listarJugadores();
	//COMPETICION
	public void altaCompeticion(Competicion comp);
	public void bajaCompeticion (Competicion comp);
	public void modificarCompeticion (Competicion comp);
	public Map<String, Competicion> listarCompeticiones();
	public List<Partido> buscarEquiLiga(Competicion liga);
	//EQUIPO
	public void altaEquipo (Equipo eq);
	public void bajaEquipo (Equipo eq);
	public void modificarEquipo (Equipo eq);
	public Map<String, Equipo> listarEquipos();
	//PARTIDO
	public void altaPartido (Partido part);
	public void bajaPartido (Partido part);
	public void modificarPartido (Partido part);
	public Map<Integer, Partido> listarPartidos();
	List<String> buscarDifEquipo(Competicion liga);
}
