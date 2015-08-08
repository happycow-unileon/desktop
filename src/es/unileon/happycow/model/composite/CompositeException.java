/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
