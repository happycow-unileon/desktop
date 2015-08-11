package es.unileon.happycow.controller;

import es.unileon.happycow.windows.Window;
import es.unileon.happycow.gui.PanelOptions;

/**
 *
 * @author dorian
 */
public class BarOptionsController extends IController {
    private final PanelOptions panel;
    private boolean view;

    public BarOptionsController(PanelOptions panel) {
        this.panel = panel;
        view=true;
    }
    
    public void close() {
        controller.exit();
    }

    public void unlogin() {
        controller.setState(Window.LOGIN);
    }

    public void comeBack() {
        controller.comeBack();
    }

    public void changeView() {
        if(view){
            panel.setButtonView("Modo Vacas");
        }else{
            panel.setButtonView("Modo Criterios");
        }
        view=!view;
        controller.changeView();
    }
    
    public void changePassword(){
        controller.setState(Window.PASSWORD);
    }
}
