package controlador;

import java.util.List;
import java.util.Map;

import excepciones.LoginException;
import modelo.Competicion;
import modelo.Jugador;
import modelo.Partido;
import modelo.Usuario;
import vista.VMenuPrincipal;

public class Principal {

	private static InterfazDao di = new DaoImplementacion();

	public static void main(String[] args) {
		VMenuPrincipal vmp;
		try {
			vmp = new VMenuPrincipal();
			vmp.setVisible(true);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
}
