package es.unileon.happycow.abstractFactory;

import es.unileon.happycow.controller.InterfaceController;
import es.unileon.happycow.controller.ReportController;
import es.unileon.happycow.gui.PanelReport;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.procedures.Report;
import javax.swing.JPanel;

/**
 * Create the panel and controller of the report window
 * @author dorian
 */
public class FactoryReport implements FactoryWindows{
    /**
     * Panel
     */
    private PanelReport panel;
    /**
     * Controller
     */
    private ReportController controller;
    /**
     * Controller needs the report
     */
    private Report report;
    /**
     * Farm which report come from
     */
    private IdHandler idFarm;
    /***
     * Result text of the report
     */
    private String reportString;

    /**
     * Create the factory with every element it can accept.
     * If report is available, it will be used instead of the string report.
     * If not, the string will be used
     * @param report the report of the farm
     * @param idFarm the identifier of the farm
     * @param reportString the content of the report
     */
    public FactoryReport(Report report, IdHandler idFarm, String reportString) {
        this.report = report;
        this.idFarm = idFarm;
        this.reportString = reportString;
    }
    
    /**
     * Builder
     * Create the factory with the string to show
     * @param idFarm the identifier of the farm
     * @param reportString the content of the report
     */
    public FactoryReport(IdHandler idFarm, String reportString){
        this(null, idFarm, reportString);
    }
    
    /**
     * Builder
     * Create the factory with the report asociated
     * @param report the report of the farm
     * @param idFarm the identifier of the farm
     */
    public FactoryReport(Report report, IdHandler idFarm){
        this(report, idFarm, "");
    }
    
    
    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getController() 
     */
    @Override
    public InterfaceController getController() {
        if(controller==null){
            createController();
        }
        return controller;
    }

    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#getPanel() 
     */
    @Override
    public JPanel getPanel() {
        if(panel==null){
            createPanel();
        }
        return panel;
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createController() 
     */
    @Override
    public void createController() {
        if(panel==null){
            createPanel();
        }
        
        if(controller==null){
            //create the controller checking if I have the report or the string
            if(report==null){
                controller=new ReportController(panel, reportString, idFarm);
            }else{
                controller=new ReportController(panel, report, idFarm);
            }
            panel.setController(controller);
        }
    }

    /**
     * 
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createPanel()
     */
    @Override
    public void createPanel() {
        if(panel==null){
            panel=new PanelReport();
        }
        
        //if controller exists, set the controller to the panel
        if(controller!=null){
            panel.setController(controller);
        }
    }

    /**
     * @see es.unileon.happycow.abstractFactory.FactoryWindows#createElements() 
     */
    @Override
    public void createElements() {
        createPanel();
        createController();
    }
}
