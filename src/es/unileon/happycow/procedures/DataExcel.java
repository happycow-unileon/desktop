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
 *
 * @author dorian
 */
public class DataExcel {
    private final LinkedHashMap<User, LinkedList<Farm>> model;
    private final HashMap<String, DefaultListCheckModel> evaluations;
    private final HashMap<String, DefaultListCheckModel> farms;
    private final HashMap<String, IdHandler> mapBetweenIdDate;
    private final HashMap<String, IdHandler> mapBetweenIdName;
    
    public DataExcel(LinkedHashMap<User, LinkedList<Farm>> model) {
        this.model = model;
        mapBetweenIdName=new HashMap<>();
        mapBetweenIdDate=new HashMap<>();
        evaluations=new HashMap<>();
        farms=new HashMap<>();
        
        for (User user : model.keySet()) {
            DefaultListCheckModel listFarm=new DefaultListCheckModel();
            
            for (Farm farm : model.get(user)) {
                DefaultListCheckModel listEvaluation=new DefaultListCheckModel();
                mapBetweenIdName.put(farm.getFarmName(), farm.getIdFarm());
                listFarm.addElement(farm.getFarmName());
                
                for (InformationEvaluation info : 
                        Database.getInstance().getListEvaluations(farm.getIdFarm())){
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
    
    public LinkedList<User> getUsers(){
        return new LinkedList<>(model.keySet());
    }
    
    public LinkedHashMap getModel(){
        return model;
    }
    
    public IdHandler getFarm(String farm){
        return mapBetweenIdName.get(farm);
    }
    
    public IdHandler getEvaluation(String evaluation){
        return mapBetweenIdDate.get(evaluation);
    }
    
    public DefaultListCheckModel getEvaluations(IdHandler farm){
        return evaluations.get(farm.toString());
    }
    
    public DefaultListCheckModel getFarms(IdHandler user){
        return farms.get(user.toString());
    }
    
}
