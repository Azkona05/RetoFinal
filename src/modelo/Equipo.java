package modelo;

/**
 * Clase que representa un equipo con un código identificador y un nombre.
 * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
 */
public class Equipo {

    /** Código identificador del equipo */
    private String cod_equi;

    /** Nombre del equipo */
    private String nombre_equipo;

    /**
     * Obtiene el código del equipo.
     * 
     * @return Código del equipo
     */
    public String getCod_equi() {
        return cod_equi;
    }

    /**
     * Establece el código del equipo.
     * 
     * @param cod_equi Código del equipo
     */
    public void setCod_equi(String cod_equi) {
        this.cod_equi = cod_equi;
    }

    /**
     * Obtiene el nombre del equipo.
     * 
     * @return Nombre del equipo
     */
    public String getNombre_equipo() {
        return nombre_equipo;
    }

    /**
     * Establece el nombre del equipo.
     * 
     * @param nombre_equipo Nombre del equipo
     */
    public void setNombre_equipo(String nombre_equipo) {
        this.nombre_equipo = nombre_equipo;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto.
     * 
     * @return Nombre del equipo
     */
    @Override
    public String toString() {
        return nombre_equipo;
    }
}