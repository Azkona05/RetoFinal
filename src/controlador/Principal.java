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

	private static InterfazDao dao = new DaoImplementacion();

	public static void main(String[] args) throws LoginException {
		VMenuPrincipal vmp;
			vmp = new VMenuPrincipal();
			vmp.setVisible(true);
		
	}


	public static void login(Usuario usuario) throws LoginException {
		dao.login(usuario);
		}
	public static List<Partido> buscarEquiLiga (Competicion liga) throws LoginException {
		return dao.buscarEquiLiga(liga);
	}

	public static Map<String, Competicion> leerCompeticiones() throws LoginException {
		return dao.listarCompeticiones();
	}
	public static List<String> devolverEquipos(Competicion liga) throws LoginException {
		return dao.buscarDifEquipo(liga);
	}
	public static List<Partido> devolverPartidos(LocalDate fecha) throws LoginException {
		return dao.devolverPartidos(fecha);
	}

	public static void EliminarJugador(Jugador j) throws LoginException {
		dao.bajaJugador(j);
	}

	public static void modificarJugador(Jugador j) throws LoginException {
		dao.modificarJugador(j);

	}

	public static void altaJugador(Jugador j) throws LoginException {
		dao.altaJugador(j);

	}

	public static void modificarCompeticion(Competicion comp) throws LoginException {
		dao.modificarCompeticion(comp);
		
	}

	public static void eliminarCompeticion(Competicion comp) throws LoginException {
		dao.bajaCompeticion(comp);
		
	}

	public static void altaCompeticion(Competicion comp) throws LoginException {
		dao.altaCompeticion(comp);
		
	}
	
	public static void altaEquipo(Equipo eq) throws LoginException {
		dao.altaEquipo(eq);
	}
	
	public static void bajaEquipo(Equipo eq) throws LoginException {
		dao.bajaEquipo(eq);
	}
	
	public static void modificarEquipo(Equipo eq) throws LoginException {
		dao.modificarEquipo(eq);
	}
	
	public static List<Equipo> buscarEquipos () throws LoginException {
		return dao.buscarEquipos();
	}
	
	public static void altaPartido(Partido par) throws LoginException {
		dao.altaPartido(par);
	}
	
	public static void bajaPartido(Partido par) throws LoginException {
		dao.bajaPartido(par);
	}
	
	public static void modificarPartido(Partido par) throws LoginException {
		dao.modificarPartido(par);
	}

	public static Object[][] devolverCompeticiones(Competicion comp) throws LoginException {
		return dao.mostrarDatosCompeticion(comp);
	}


	public static Object[][] devolverPartidos(Partido part) throws LoginException{
		return dao.mostrarDatosPartido(part);
	}


	public static Object[][] devolverJugadores(Jugador jug) throws LoginException{
		return dao.mostrarDatosJugador(jug);
	}


	public static Object[][] devolverEquipos(Equipo eq) throws LoginException {
		return dao.mostrarDatosEquipo(eq);
	}


	public static List<Competicion> devolverCompeticiones() throws LoginException {
		return dao.devolverCompeticiones();
	}
	
}
