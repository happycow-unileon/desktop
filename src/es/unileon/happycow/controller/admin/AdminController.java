package es.unileon.happycow.controller.admin;

import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.gui.admin.InterfaceTabs;
import java.util.LinkedList;

/**
 *
 * @author dorian
 */
public class AdminController implements InterfaceController{
    LinkedList<InterfaceTabs> panels;

    public AdminController() {
        panels=new LinkedList<>();
    }
    
    public void addTab(InterfaceTabs panel){
        panels.add(panel);
    }
    
    public void changeTab(int index){
        panels.get(index).updateInformation();
    }
}
