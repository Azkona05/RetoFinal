package modelo;

import java.time.LocalDate;

public class ClasePartido {

	private int cod_part;
	private String equipo_local;
	private String equipo_visitante;
	private EnumGanador ganador;
	private LocalDate fecha;
	private String cod_comp;
	
	
	public int getCod_part() {
		return cod_part;
	}
	public void setCod_part(int cod_part) {
		this.cod_part = cod_part;
	}
	public String getEquipo_local() {
		return equipo_local;
	}
	public void setEquipo_local(String equipo_local) {
		this.equipo_local = equipo_local;
	}
	public String getEquipo_visitante() {
		return equipo_visitante;
	}
	public void setEquipo_visitante(String equipo_visitante) {
		this.equipo_visitante = equipo_visitante;
	}
	public EnumGanador getGanador() {
		return ganador;
	}
	public String getGanadorString() {
		
		return ganador.toString();
	}
	public void setGanador(EnumGanador ganador) {
		this.ganador = ganador;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public String getCod_comp() {
		return cod_comp;
	}
	public void setCod_comp(String cod_comp) {
		this.cod_comp = cod_comp;
	}
	@Override
	public String toString() {
		return "Partido [cod_part=" + cod_part + ", equipo_local=" + equipo_local + ", equipo_visitante="
				+ equipo_visitante + ", ganador=" + ganador + ", fecha=" + fecha + "]";
	}
	
	
}
