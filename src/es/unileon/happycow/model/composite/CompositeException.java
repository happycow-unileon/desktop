package es.unileon.happycow.model.composite;

/**
 *
 * @author dorian
 */
public class CompositeException extends Exception {

    /**
     * Constructor que pide un mensaje y ejecuta el constructor del padre
     *
     * @param msg mensaje de error
     */
    public CompositeException(String msg) {
        super(msg);
    }
}
