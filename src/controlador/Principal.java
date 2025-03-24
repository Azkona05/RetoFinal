package controlador;

import excepciones.LoginException;
import modelo.Usuario;
import vista.VMenuPrincipal;

public class Principal {

	private static DaoImplementacion di = new DaoImplementacion();
	
	public static void main(String[] args) {
		VMenuPrincipal vmp = new VMenuPrincipal();
		vmp.setVisible(true);
	}

	public static void login(Usuario usuario) throws LoginException{
		di.login(usuario);
	}
}
