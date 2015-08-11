package es.unileon.happycow.windows;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdWindow;

/**
 *
 * @author dorian
 */
public class ListFarm extends IWindow{
    public static Window TYPE=Window.LIST_FARMS;

    public ListFarm(IFactory factory) {
        super("Listado granjas",false, true, new IdWindow(TYPE, true), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}