package modelo;

/**
 * Clase que representa a un jugador de un equipo.
 * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
 */
public class Jugador {

    /** DNI del jugador (identificador único) */
    private String dni;

    /** Nombre del jugador */
    private String nombre;

    /** Apellido del jugador */
    private String apellido;

    /** Número de dorsal del jugador */
    private int dorsal;

    /** Posición del jugador en el campo */
    private EnumPosicion posicion;

    /** Código del equipo al que pertenece el jugador */
    private String cod_equi;

    /**
     * Obtiene el DNI del jugador.
     *
     * @return DNI del jugador
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del jugador.
     *
     * @param dni DNI del jugador
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el nombre del jugador.
     *
     * @return Nombre del jugador
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del jugador.
     *
     * @param nombre Nombre del jugador
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el apellido del jugador.
     *
     * @return Apellido del jugador
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del jugador.
     *
     * @param apellido Apellido del jugador
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Obtiene el dorsal del jugador.
     *
     * @return Número de dorsal
     */
    public int getDorsal() {
        return dorsal;
    }

    /**
     * Establece el dorsal del jugador.
     *
     * @param dorsal Número de dorsal
     */
    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    /**
     * Obtiene la posición del jugador.
     *
     * @return Posición del jugador
     */
    public EnumPosicion getPosicion() {
        return posicion;
    }

    /**
     * Establece la posición del jugador.
     *
     * @param posicion Posición del jugador
     */
    public void setPosicion(EnumPosicion posicion) {
        this.posicion = posicion;
    }

    /**
     * Obtiene el código del equipo al que pertenece el jugador.
     *
     * @return Código del equipo
     */
    public String getCod_equi() {
        return cod_equi;
    }

    /**
     * Establece el código del equipo al que pertenece el jugador.
     *
     * @param cod_equi Código del equipo
     */
    public void setCod_equi(String cod_equi) {
        this.cod_equi = cod_equi;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto.
     *
     * @return Cadena con los datos del jugador
     */
    @Override
    public String toString() {
        return "Jugador [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", dorsal=" + dorsal
                + ", posicion=" + posicion + "]";
    }
}
