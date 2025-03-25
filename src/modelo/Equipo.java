package modelo;

public class Equipo {

	private String cod_equi;
	private String nombre_equipo;
	
	
	public Equipo() {
		
	}
	public String getCod_equi() {
		return cod_equi;
	}
	public void setCod_equi(String cod_equi) {
		this.cod_equi = cod_equi;
	}
	public String getNombre_equipo() {
		return nombre_equipo;
	}
	public void setNombre_equipo(String nombre_equipo) {
		this.nombre_equipo = nombre_equipo;
	}
	@Override
	public String toString() {
		return "Equipo [cod_equi=" + cod_equi + ", nombre_equipo=" + nombre_equipo + "]";
	}
	
	
}
