package es.unileon.happycow.controller.admin;

import es.unileon.happycow.controller.Controller;
import es.unileon.happycow.gui.admin.InterfaceTabs;
import java.util.LinkedList;

/**
 * The main controller of admin
 * @author dorian
 */
public class AdminController extends Controller {
    /**
     * List of panels of admin
     */
    LinkedList<InterfaceTabs> panels;

    /**
     * Constructor
     */
    public AdminController() {
        panels=new LinkedList<>();
    }
    
    /**
     * Add a panel to admin
     * @param panel 
     */
    public void addTab(InterfaceTabs panel){
        panels.add(panel);
    }
    
    /**
     * Change to another panel and update its information
     * @param index 
     */
    public void changeTab(int index){
        panels.get(index).updateInformation();
    }
}
