package controlador;

import excepciones.LoginException;
import modelo.Usuario;

public class Principal {

	private static DaoImplementacion di = new DaoImplementacion();
	
	public static void main(String[] args) {
		
	}

	public static void login(Usuario usuario) throws LoginException{
		di.login(usuario);
	}
}
