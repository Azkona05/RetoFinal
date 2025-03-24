package controlador;

import excepciones.LoginException;
import modelo.Jugador;
import modelo.Usuario;
import vista.VMenuPrincipal;

public class Principal {

	private static InterfazDao di = new DaoImplementacion();
	
	public static void main(String[] args) {
		VMenuPrincipal vmp = new VMenuPrincipal();
		vmp.setVisible(true);
	}

	public static void login(Usuario usuario) throws LoginException{
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
}
