package modelo;

public class ClaseJugador {

	private String dni;
	private String nombre;
	private String apellido;
	private int dorsal;
	private EnumPosicion posicion;
	private String cod_equi;

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

	public EnumPosicion getPosicion() {
		return posicion;
	}

	public String getCod_equi() {
		return cod_equi;
	}

	public void setCod_equi(String cod_equi) {
		this.cod_equi = cod_equi;
	}

	@Override
	public String toString() {
		return "Jugador [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", dorsal=" + dorsal
				+ ", posicion=" + posicion + "]";
	}
}
