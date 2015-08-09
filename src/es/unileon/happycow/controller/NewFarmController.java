package es.unileon.happycow.controller;

import es.unileon.happycow.application.JFrameController;
import es.unileon.happycow.database.*;
import es.unileon.happycow.gui.panels.PanelNewFarm;
import es.unileon.happycow.handler.IdFarm;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public class NewFarmController extends IController{
    private final PanelNewFarm panel;
    private final IdHandler idFarm;

    public NewFarmController(PanelNewFarm panel) {
        //crear identificador de granja!!!
        this(panel, null);
    }

    public NewFarmController(PanelNewFarm panel, IdHandler idFarm) {
        this.panel = panel;
        this.idFarm = idFarm;
    }
    
    public void returnWindow(){
        //TODO
//        father.comeBack();
    }
    
    private boolean controlFarm(){
        boolean correct=true;
        StringBuilder errores=new StringBuilder();
        if(panel.getNameFarm().trim().compareToIgnoreCase("")==0){
            correct=false;
            errores.append("\nDebe dar un nombre a la granja.\nUse el identificador de la granja como nombre si no se le ocurre uno.");
        }
        
        if(panel.getNumberCows().trim().compareTo("")==0){
            correct=false;
            errores.append("\nDebe introducir el número de vacas exactas/aproximadas que tiene la granja.");
        }else if(!isNumeric(panel.getNumberCows())){
            correct=false;
            errores.append("\nDebe introducir un número entero de vacas.");
        }
        
        if(!correct){
            JOptionPane.showMessageDialog(panel, errores, "Mala entrada", JOptionPane.WARNING_MESSAGE);
        }
        
        return correct;
    }
    
    private boolean isNumeric(String cadena){
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
}
    
    public void saveFarm(){
        Farm farm;
        if(controlFarm()){
            if(idFarm!=null){
                farm=Database.getInstance().getFarm(idFarm);
                farm.setAddress(panel.getAddressFarm());
                farm.setCowNumber(Integer.parseInt(panel.getNumberCows()));
                farm.setDniFarmer(panel.getIdFarmer());
                farm.setFarmName(panel.getNameFarm());
                farm.setFarmerName(panel.getNameFarmer());
                farm.setOtherData(panel.getOtherData());
            }else{
                IdHandler identificationfarm=new IdFarm(Database.getInstance().nextIdFarm());
                farm=new Farm(identificationfarm, panel.getNameFarm(), panel.getIdFarm(), 
                        panel.getAddressFarm(), panel.getNameFarmer(),
                        panel.getIdFarmer(), Integer.parseInt(panel.getNumberCows()), 
                        Database.getInstance().getUser().getId(), panel.getOtherData());
            }

            if(idFarm!=null){
                if(!Database.getInstance().modifiedFarm(farm)){
                    JOptionPane.showMessageDialog(panel, 
                            "Errores al guardar la granja, inténtelo de nuevo",
                            "Error", JOptionPane.WARNING_MESSAGE);
                  
                }else{
//                    father.seeListFarm();
                }
            }else{
                if(!Database.getInstance().newFarm(farm)){
                    JOptionPane.showMessageDialog(panel, 
                            "Errores al guardar la granja, inténtelo de nuevo",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }else{
//                    father.seeListFarm();
                }
            }
        }
    }
}