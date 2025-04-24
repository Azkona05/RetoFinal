package modelo;

/**
 * @author An Azkona, Ander Arilla, Nora Yakoubi, Maleck Benigno
 */
/**
 * Clase que representa a un usuario con un nombre y una contraseña.
 */
public class Usuario {

    /** Nombre del usuario */
    private String nom;

    /** Contraseña del usuario */
    private String contrasenia;

    
    /**
     * Constructor vacio de la clase usuario
     */
    public Usuario() {
    	
    }
    /**
     * Obtiene el nombre del usuario.
     * 
     * @return Nombre del usuario
     */
    public String getNombre() {
        return nom;
    }

    /**
     * Establece el nombre del usuario.
     * 
     * @param nombre Nombre del usuario
     */
    public void setNombre(String nombre) {
        this.nom = nombre;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return Contraseña del usuario
     */
    public String getPassword() {
        return contrasenia;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password Contraseña del usuario
     */
    public void setPassword(String password) {
        this.contrasenia = password;
    }

    /**
     * Devuelve una representación en forma de cadena del objeto.
     * 
     * @return Cadena con los detalles del usuario (nombre y contraseña)
     */
    @Override
    public String toString() {
        return "USUARIO: Nombre=" + nom + ", Password=" + contrasenia;
    }
}
