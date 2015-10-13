package es.unileon.happycow.procedures;

import es.unileon.happycow.database.Database;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.InformationEvaluation;
import es.unileon.happycow.model.User;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import org.japura.gui.model.DefaultListCheckModel;

/**
 * Data process for an excel
 * @author dorian
 */
public class DataExcel {
    /**
     * List of users and their farms
     */
    private final LinkedHashMap<User, LinkedList<Farm>> model;
    /**
     * List of evaluations checked
     */
    private final HashMap<String, DefaultListCheckModel> evaluations;
    /**
     * List of farm checked
     */
    private final HashMap<String, DefaultListCheckModel> farms;
    /**
     * map between the id and the date
     */
    private final HashMap<String, IdHandler> mapBetweenIdDate;
    /**
     * map between the id and the name
     */
    private final HashMap<String, IdHandler> mapBetweenIdName;
    
    public DataExcel(LinkedHashMap<User, LinkedList<Farm>> model) {
        this.model = model;
        mapBetweenIdName=new HashMap<>();
        mapBetweenIdDate=new HashMap<>();
        evaluations=new HashMap<>();
        farms=new HashMap<>();
        
        //for every user...
        for (User user : model.keySet()) {
            DefaultListCheckModel listFarm=new DefaultListCheckModel();
            //for every farm
            for (Farm farm : model.get(user)) {
                DefaultListCheckModel listEvaluation=new DefaultListCheckModel();
                //set the id farm with the name farm
                mapBetweenIdName.put(farm.getFarmName(), farm.getIdFarm());
                listFarm.addElement(farm.getFarmName());
                
                //for every evaluation
                for (InformationEvaluation info : 
                        Database.getInstance().getListEvaluations(farm.getIdFarm())){
                    //store the date
                    String date=info.getFecha().toString();
                    mapBetweenIdDate.put(date, info.getIdEvaluation());
                    listEvaluation.addElement(date);
                }
                listEvaluation.checkAll();
                evaluations.put(farm.getIdFarm().toString(), listEvaluation);
            }
            listFarm.checkAll();
            farms.put(user.getId().toString(), listFarm);
        }
        
    }
    
    /**
     * Get the users
     * @return 
     */
    public LinkedList<User> getUsers(){
        return new LinkedList<>(model.keySet());
    }
    
    /**
     * Get the final model
     * @return 
     */
    public LinkedHashMap getModel(){
        return model;
    }
    
    /**
     * Get the farm
     * @param farm
     * @return 
     */
    public IdHandler getFarm(String farm){
        return mapBetweenIdName.get(farm);
    }
    
    /**
     * Get the evaluation
     * @param evaluation
     * @return 
     */
    public IdHandler getEvaluation(String evaluation){
        return mapBetweenIdDate.get(evaluation);
    }
    
    /**
     * Get the evaluation of a farm
     * @param farm
     * @return 
     */
    public DefaultListCheckModel getEvaluations(IdHandler farm){
        return evaluations.get(farm.toString());
    }
    
    /**
     * Get the farms of an user
     * @param user
     * @return 
     */
    public DefaultListCheckModel getFarms(IdHandler user){
        return farms.get(user.toString());
    }
    
}
