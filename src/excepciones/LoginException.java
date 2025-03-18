package excepciones;

import javax.swing.JOptionPane;

public class LoginException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	
	public LoginException(String msg) {
		this.msg = msg;
	}

	public void visualizarMsg() {
		JOptionPane.showMessageDialog(null, this.msg, "ERROR", 0);
	}
}
