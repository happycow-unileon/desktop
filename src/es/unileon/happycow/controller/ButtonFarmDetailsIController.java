package es.unileon.happycow.controller;

import es.unileon.happycow.handler.IdHandler;

/**
 * Interfaz añadida para los controladores que manejan la lista de granjas y la 
 * lista de granjas deshabilitadas
 * @author dorian
 */
public abstract class ButtonFarmDetailsIController extends Controller{
    /**
     * Método ejecutar para los botones, los cuales pasan el id de la granja
     * seleccionada
     * @param id identificador de la granja
     */
    public abstract void execute(IdHandler id);
}
