package modelo;

/**
 * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
 * Clase que representa una competición con un código identificador y un nombre.
 */
public class Competicion {

    /** Código identificador de la competición */
    private String cod_comp;

    /** Nombre de la competición */
    private String nombre_competicion;

    /**
     * Constructor por defecto.
     */
    public Competicion() {
    }

    /**
     * Constructor con parámetros.
     * 
     * @param cod_comp Código identificador de la competición
     * @param nombre_competicion Nombre de la competición
     */
    public Competicion(String cod_comp, String nombre_competicion) {
        this.cod_comp = cod_comp;
        this.nombre_competicion = nombre_competicion;
    }

    /**
     * Obtiene el código de la competición.
     * 
     * @return Código de la competición
     */
    public String getCod_comp() {
        return cod_comp;
    }

    /**
     * Establece el código de la competición.
     * 
     * @param cod_comp Código de la competición
     */
    public void setCod_comp(String cod_comp) {
        this.cod_comp = cod_comp;
    }

    /**
     * Obtiene el nombre de la competición.
     * 
     * @return Nombre de la competición
     */
    public String getNombre_competicion() {
        return nombre_competicion;
    }

    /**
     * Establece el nombre de la competición.
     * 
     * @param nombre_competicion Nombre de la competición
     */
    public void setNombre_competicion(String nombre_competicion) {
        this.nombre_competicion = nombre_competicion;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto.
     * 
     * @return Nombre de la competición
     */
    @Override
    public String toString() {
        return nombre_competicion;
    }
}