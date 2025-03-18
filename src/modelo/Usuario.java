package modelo;

public class Usuario {
	private String nom;
	private String contrasenia;


	public String getNombre() {
		return nom;
	}

	public void setNombre(String nombre) {
		this.nom = nombre;
	}

	public String getPassword() {
		return contrasenia;
	}

	public void setPassword(String password) {
		this.contrasenia = password;
	}

	@Override
	public String toString() {
		return "USUARIO: Nombre=" + nom + ", Password=" + contrasenia;
	}
}
