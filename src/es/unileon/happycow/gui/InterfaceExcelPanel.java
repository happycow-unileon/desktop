package es.unileon.happycow.gui;

import es.unileon.happycow.controller.ExcelController;
import es.unileon.happycow.gui.admin.InterfaceTabs;
import es.unileon.happycow.handler.IdHandler;
import org.japura.gui.model.DefaultListCheckModel;

/**
 *
 * @author dorian
 */
public interface InterfaceExcelPanel extends InterfaceTabs{
    public String getUser();
    
    public String getFarm();
    
    public void changeUser(IdHandler user);
    
    public void changeFarms(DefaultListCheckModel farms);
    
    public void changeEvaluations(DefaultListCheckModel evaluations);
    
    public DefaultListCheckModel getModelListFarm();
    
    public DefaultListCheckModel getModelListEvaluations();
    
    public void setController(ExcelController controller);
}
