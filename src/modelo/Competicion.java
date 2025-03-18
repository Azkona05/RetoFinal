package modelo;

public class Competicion {

	private String cod_comp;
	private String nombre_competicion;

	public String getCod_comp() {
		return cod_comp;
	}

	public void setCod_comp(String cod_comp) {
		this.cod_comp = cod_comp;
	}

	public String getNombre_competicion() {
		return nombre_competicion;
	}

	public void setNombre_competicion(String nombre_competicion) {
		this.nombre_competicion = nombre_competicion;
	}

	@Override
	public String toString() {
		return "Competicion [cod_comp=" + cod_comp + ", nombre_competicion=" + nombre_competicion + "]";
	}

}
