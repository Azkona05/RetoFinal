package excepciones;

/**
 * Excepción personalizada para manejar errores relacionados con el DNI.
 * Esta excepción puede ser lanzada cuando se detecta un problema con un DNI,
 * como formato incorrecto o duplicado.
 */
public class DniException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Constructor por defecto que llama al constructor de la superclase.
     */
    public DniException() {
        super();
    }

    /**
     * Constructor que permite pasar un mensaje de error.
     * 
     * @param message El mensaje que describe el error
     */
    public DniException(String message) {
        super(message);
    }
}
