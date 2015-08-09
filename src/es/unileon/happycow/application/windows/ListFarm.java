package es.unileon.happycow.application.windows;

import es.unileon.happycow.factory.Factory;
import es.unileon.happycow.handler.IdWindow;

/**
 *
 * @author dorian
 */
public class ListFarm extends IWindow{
    public static Window TYPE=Window.LIST_FARMS;

    public ListFarm(Factory factory) {
        super("Listado granjas",false, true, new IdWindow(TYPE, true), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}