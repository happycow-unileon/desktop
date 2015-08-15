package es.unileon.happycow.database2;

import es.unileon.happycow.database2.concreteDatabase.Oracle;
import es.unileon.happycow.database2.concreteDatabase.SQLite;




/**
 *
 * @author dorian
 */
public class Database {
    private static DataBaseOperations instance;
    private static TypeDatabase type=TypeDatabase.SQLITE;
    
    private Database(){
    }
    
    public static void setType(TypeDatabase typeData){
        type=typeData;
    }
    
    private static void createDatabase(){
        switch(type){
            case SQLITE:
                instance=new SQLite();
                break;
            case ORACLE:
                instance=new Oracle();
                break;
        }
    }
    
    

    public static DataBaseOperations getInstance() {
        if(instance==null){
            createDatabase();
        }
        return instance;
    }
}
