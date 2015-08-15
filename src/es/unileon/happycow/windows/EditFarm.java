package es.unileon.happycow.windows;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdWindow;

/**
 *
 * @author dorian
 */
public class EditFarm extends IWindow{
    public static Window TYPE=Window.EDIT_FARM;

    public EditFarm(IFactory factory) {
        super("Editar granja", false, true, new IdWindow(TYPE, false), factory);
    }
    
    @Override
    public Window getType() {
        return TYPE;
    }
}
