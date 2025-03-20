package controlador;

import java.util.Map;

import excepciones.LoginException;
import modelo.ClaseCompeticion;
import modelo.ClaseEquipo;
import modelo.ClaseJugador;
import modelo.ClasePartido;
import modelo.ClaseUsuario;

public interface InterfazDao {
	//LOGIN
	public void login(ClaseUsuario usuario) throws LoginException;
	//JUGADOR
	public void altaJugador(ClaseJugador jug);
	public void bajaJugador(ClaseJugador jug);
	public void modificarJugador(ClaseJugador jug);
	public Map<String, ClaseJugador> listarJugadores();
	//COMPETICION
	public void altaCompeticion(ClaseCompeticion comp);
	public void bajaCompeticion (ClaseCompeticion comp);
	public void modificarCompeticion (ClaseCompeticion comp);
	public Map<String, ClaseCompeticion> listarCompeticiones();
	//EQUIPO
	public void altaEquipo (ClaseEquipo eq);
	public void bajaEquipo (ClaseEquipo eq);
	public void modificarEquipo (ClaseEquipo eq);
	public Map<String, ClaseEquipo> listarEquipos();
	//PARTIDO
	public void altaPartido (ClasePartido part);
	public void bajaPartido (ClasePartido part);
	public void modificarPartido (ClasePartido part);
	public Map<Integer, ClasePartido> listarPartidos();
	
}
