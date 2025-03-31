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

public class Principal {

	private static InterfazDao di = new DaoImplementacion();

	public static void main(String[] args) throws LoginException {
		VMenuPrincipal vmp;
			vmp = new VMenuPrincipal();
			vmp.setVisible(true);
		

	}

	public static void login(Usuario usuario) throws LoginException {
		di.login(usuario);
	}

	public static void EliminarJugador(Jugador j) {
		di.bajaJugador(j);
	}

	public static void modificarJugador(Jugador j) {
		di.modificarJugador(j);

	}

	public static void altaJugador(Jugador j) {
		di.altaJugador(j);

	}

	public static List<Partido> buscarEquiLiga(Competicion liga) {
		return di.buscarEquiLiga(liga);
	}

	public static Map<String, Competicion> leerCompeticiones() {
		return di.listarCompeticiones();
	}

	public static List<String> devolverEquipos(Competicion liga) {
		return di.buscarDifEquipo(liga);
	}

	public static void modificarCompeticion(Competicion comp) {
		di.modificarCompeticion(comp);
		
	}

	public static void eliminarCompeticion(Competicion comp) {
		di.bajaCompeticion(comp);
		
	}

	public static void altaCompeticion(Competicion comp) {
		di.altaCompeticion(comp);
		
	}
	
	public static void altaEquipo(Equipo eq) {
		di.altaEquipo(eq);
	}
	
	public static void bajaEquipo(Equipo eq) {
		di.bajaEquipo(eq);
	}
	
	public static void modificarEquipo(Equipo eq) {
		di.modificarEquipo(eq);
	}
	
	public static void altaPartido(Partido par) {
		di.altaPartido(par);
	}
	
	public static void bajaPartido(Partido par) {
		di.bajaPartido(par);
	}
	
	public static void modificarPartido(Partido par) {
		di.modificarPartido(par);
	}
	
	public static List<Partido> devolverPartidos(LocalDate fecha) {
		return di.devolverPartidos(fecha);
	}
	
}
