package controlador;

import excepciones.LoginException;
import modelo.Usuario;

public interface Interfaz_Dao {

	public void login(Usuario usuario) throws LoginException;
}
