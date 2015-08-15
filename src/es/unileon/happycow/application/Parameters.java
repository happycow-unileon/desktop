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

    public Parameters() {
        parametersString=new HashMap<>();
        parametersInt=new HashMap<>();
        parametersBoolean=new HashMap<>();
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
