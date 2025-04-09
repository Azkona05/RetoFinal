package modelo;

import java.time.LocalDate;

/**
 * Clase que representa un partido entre dos equipos.
 * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
 */
public class Partido {

    /** Código identificador del partido */
    private int cod_part;

    /** Nombre del equipo local */
    private String equipo_local;

    /** Nombre del equipo visitante */
    private String equipo_visitante;

    /** Nombre del equipo ganador */
    private String ganador;

    /** Fecha en la que se jugó el partido */
    private LocalDate fecha;

    /** Código de la competición en la que se jugó el partido */
    private String cod_comp;

    /**
     * Obtiene el código del partido.
     * 
     * @return Código del partido
     */
    public int getCod_part() {
        return cod_part;
    }

    /**
     * Establece el código del partido.
     * 
     * @param cod_part Código del partido
     */
    public void setCod_part(int cod_part) {
        this.cod_part = cod_part;
    }

    /**
     * Obtiene el nombre del equipo local.
     * 
     * @return Nombre del equipo local
     */
    public String getEquipo_local() {
        return equipo_local;
    }

    /**
     * Establece el nombre del equipo local.
     * 
     * @param equipo_local Nombre del equipo local
     */
    public void setEquipo_local(String equipo_local) {
        this.equipo_local = equipo_local;
    }

    /**
     * Obtiene el nombre del equipo visitante.
     * 
     * @return Nombre del equipo visitante
     */
    public String getEquipo_visitante() {
        return equipo_visitante;
    }

    /**
     * Establece el nombre del equipo visitante.
     * 
     * @param equipo_visitante Nombre del equipo visitante
     */
    public void setEquipo_visitante(String equipo_visitante) {
        this.equipo_visitante = equipo_visitante;
    }

    /**
     * Obtiene el nombre del equipo ganador.
     * 
     * @return Nombre del equipo ganador
     */
    public String getGanador() {
        return ganador;
    }

    /**
     * Obtiene una representación de cadena del equipo ganador.
     * 
     * @return Cadena con el nombre del equipo ganador
     */
    public String getGanadorString() {
        return ganador.toString();
    }

    /**
     * Establece el nombre del equipo ganador.
     * 
     * @param ganador Nombre del equipo ganador
     */
    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    /**
     * Obtiene la fecha en la que se jugó el partido.
     * 
     * @return Fecha del partido
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha en la que se jugó el partido.
     * 
     * @param fecha Fecha del partido
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el código de la competición en la que se jugó el partido.
     * 
     * @return Código de la competición
     */
    public String getCod_comp() {
        return cod_comp;
    }

    /**
     * Establece el código de la competición en la que se jugó el partido.
     * 
     * @param cod_comp Código de la competición
     */
    public void setCod_comp(String cod_comp) {
        this.cod_comp = cod_comp;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto.
     * 
     * @return Cadena con los detalles del partido
     */
    @Override
    public String toString() {
        return "Partido [cod_part=" + cod_part + ", equipo_local=" + equipo_local + ", equipo_visitante="
                + equipo_visitante + ", ganador=" + ganador + ", fecha=" + fecha + ", cod_comp= " + cod_comp + "]";
    }
}
