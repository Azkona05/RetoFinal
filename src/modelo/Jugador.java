package modelo;

public class Jugador {

	private String dni;
	private String nombre;
	private String apellido;
	private int dorsal;
	private Posicion posicion;
	private int cod_equi;

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public int getCod_equi() {
		return cod_equi;
	}

	public void setCod_equi(int cod_equi) {
		this.cod_equi = cod_equi;
	}

	@Override
	public String toString() {
		return "Jugador [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", dorsal=" + dorsal
				+ ", posicion=" + posicion + "]";
	}
}
