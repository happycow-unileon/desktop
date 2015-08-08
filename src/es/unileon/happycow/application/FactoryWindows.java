package es.unileon.happycow.application;

import es.unileon.happycow.controller.IController;
import es.unileon.happycow.factory.Factory;
import es.unileon.happycow.factory.FactoryLogin;
import es.unileon.happycow.factory.FactoryNewFarm;
import es.unileon.happycow.gui.windows.Login;
import es.unileon.happycow.gui.windows.NewFarm;
import javax.swing.JPanel;

/**
 *
 * @author dorian
 */
public class FactoryWindows {
    public static IWindow create(Window type) {
        IWindow window = null;
        Factory factory = null;
        switch (type) {
            case LOGIN:
                factory = new FactoryLogin();
                break;
                
            case NEW_FARM:
                factory=new FactoryNewFarm(null);
                break;
        }

        IController controller;
        JPanel panel;
        if (factory == null) {
            return window;
        } else {
            factory.createElements();
            controller = factory.getController();
            panel = factory.getPanel();
        }

        switch (type) {
            case LOGIN:
                window = new Login(controller, panel);
                break;

            case NEW_FARM:
                window = new NewFarm(controller, panel);
                break;

            default:
                break;
        }
        return window;
    }
}
