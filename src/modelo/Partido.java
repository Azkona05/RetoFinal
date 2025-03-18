package modelo;

import java.time.LocalDate;

public class Partido {

	private String cod_part;
	private String equipo_local;
	private String equipo_visitante;
	private String ganador;
	private LocalDate fecha;
	
	
	public String getCod_part() {
		return cod_part;
	}
	public void setCod_part(String cod_part) {
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
	public String getGanador() {
		return ganador;
	}
	public void setGanador(String ganador) {
		this.ganador = ganador;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "Partido [cod_part=" + cod_part + ", equipo_local=" + equipo_local + ", equipo_visitante="
				+ equipo_visitante + ", ganador=" + ganador + ", fecha=" + fecha + "]";
	}
	
	
	
}
