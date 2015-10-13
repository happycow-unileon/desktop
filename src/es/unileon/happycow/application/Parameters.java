package es.unileon.happycow.application;

import java.util.HashMap;

/**
 * Store and control a set of parameters
 * @author dorian
 */
public class Parameters {
    /**
     * Hashmap for strings
     */
    private HashMap<String,String> parametersString;
    /**
     * Hashmap for int
     */
    private HashMap<String,Integer> parametersInt;
    /**
     * Hashmap for booleans
     */
    private HashMap<String,Boolean> parametersBoolean;
    /**
     * Hashmap for objects
     */
    private HashMap<String,Object> parametersObjects;

    /**
     * Constructor
     */
    public Parameters() {
        parametersString=new HashMap<>();
        parametersInt=new HashMap<>();
        parametersBoolean=new HashMap<>();
        parametersObjects=new HashMap<>();
    }
    
    /**
     * Add a parameter
     * @param key
     * @param value 
     */
    public void addParameter(String key, Object value){
        parametersObjects.put(key, value);
    }
    
    /**
     * Add a parameter
     * @param key
     * @param value 
     */
    public void addParameter(String key, String value){
        parametersString.put(key, value);
    }
    
    /**
     * Add a parameter
     * @param key
     * @param value 
     */
    public void addParameter(String key, Boolean value){
        parametersBoolean.put(key, value);
    }
    
    /**
     * Add a parameter
     * @param key
     * @param value 
     */
    public void addParameter(String key, Integer value){
        parametersInt.put(key, value);
    }
    
    /**
     * Clear all parameters
     */
    public void clearParameters(){
        parametersString.clear();
        parametersBoolean.clear();
        parametersInt.clear();
        parametersObjects.clear();
    }
    
    /**
     * Get an object asociated to the key
     * @param key
     * @return the object or null if not setted
     */
    public Object getObject(Object key){
        return parametersObjects.get(key);
    }
    
    /**
     * Get a string asociated to the key
     * @param key
     * @return the string or null if not setted
     */
    public String getString(Object key){
        return parametersString.get(key);
    }
    
    /**
     * Get a boolean asociated to the key
     * @param key
     * @return the boolean or null if not setted
     */
    public Boolean getBoolean(Object key){
        return parametersBoolean.get(key);
    }
    
    /**
     * Get an integer asociated to the key
     * @param key
     * @return the integer or null if not setted
     */
    public Integer getInteger(Object key){
        return parametersInt.get(key);
    }

}
