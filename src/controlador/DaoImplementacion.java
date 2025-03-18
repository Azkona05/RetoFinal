package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import excepciones.LoginException;
import modelo.Usuario;

public class DaoImplementacion implements Interfaz_Dao {
	// Atributos

	private ResourceBundle configFile;
	private String urlDB;
	private String userDB;
	private String passwordDB;

	private Connection con;
	private PreparedStatement stmt;
	// Sentencias SQL
	final String LOGIN = "SELECT *FROM USUARIO WHERE NOM = ? AND CONTRASENIA = ?";

	public DaoImplementacion() {
		this.configFile = ResourceBundle.getBundle("modelo.configClass");
		this.urlDB = this.configFile.getString("Conn");
		this.userDB = this.configFile.getString("DBUser");
		this.passwordDB = this.configFile.getString("DBPass");

	}

	private void openConnection() {

		try {
			con = DriverManager.getConnection(urlDB, this.userDB, this.passwordDB);
//			con = DriverManager.getConnection(
//					"jdbc:mysql://localhost:3306/concesionario?serverTimezone=Europe/Madrid&useSSL=false", "root",
//					"abcd*1234");
		} catch (SQLException e) {
			System.out.println("Error al intentar abrir la BD");
		}
	}

	private void closeConnection() throws SQLException {

		if (stmt != null) {
			stmt.close();
		}
		if (con != null)
			con.close();
	}

	// Tenemos que definie el ResusultSet para recoger el resultado de la consulta

	@Override
	public void login(Usuario usuario) throws LoginException {
		ResultSet rs = null;
		openConnection();
		try {
			stmt = con.prepareStatement(LOGIN);
			stmt.setString(1, usuario.getNombre());
			stmt.setString(2, usuario.getPassword());

			rs = stmt.executeQuery();

			if (!rs.next()) {
				throw new LoginException("ERROR, PARAMETROS INCORRECTOS");
			}

		} catch (SQLException e) {
			throw new LoginException("ERROR, SQL ERROR");
		} finally {
			try {
				rs.close();
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
