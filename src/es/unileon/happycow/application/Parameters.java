/*
 * 
 */
package es.unileon.happycow.application;

import java.util.HashMap;

/**
 *
 * @author dorian
 */
public class Parameters {
    private HashMap<String,String> parametersString;
    private HashMap<String,Integer> parametersInt;
    private HashMap<String,Boolean> parametersBoolean;
    private HashMap<String,Object> parametersObjects;

    public Parameters() {
        parametersString=new HashMap<>();
        parametersInt=new HashMap<>();
        parametersBoolean=new HashMap<>();
        parametersObjects=new HashMap<>();
    }
    
    public void addParameter(String key, Object value){
        parametersObjects.put(key, value);
    }
    
    public void addParameter(String key, String value){
        parametersString.put(key, value);
    }
    
    public void addParameter(String key, Boolean value){
        parametersBoolean.put(key, value);
    }
    
    public void addParameter(String key, Integer value){
        parametersInt.put(key, value);
    }
    
    public void clearParameters(){
        parametersString.clear();
        parametersBoolean.clear();
        parametersInt.clear();
        parametersObjects.clear();
    }
    
    public Object getObject(Object key){
        return parametersObjects.get(key);
    }
    
    public String getString(Object key){
        return parametersString.get(key);
    }
    
    public Boolean getBoolean(Object key){
        return parametersBoolean.get(key);
    }
    
    public Integer getInteger(Object key){
        return parametersInt.get(key);
    }

}
