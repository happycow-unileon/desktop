package es.unileon.happycow.windows;

import es.unileon.happycow.windows.factory.IFactory;
import es.unileon.happycow.handler.IdWindow;

/**
 *Window evaluation
 * @author dorian
 */
public class Evaluation extends IWindow{
    public static Window TYPE=Window.EVALUATION;

    public Evaluation(IFactory factory) {
        super("Evaluación",false, false, new IdWindow(TYPE, true), factory);
    } 

    @Override
    public Window getType() {
        return TYPE;
    }
}
