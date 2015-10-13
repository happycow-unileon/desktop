package es.unileon.happycow.database;

import es.unileon.happycow.database.concreteDatabase.Oracle;
import es.unileon.happycow.database.concreteDatabase.SQLite;

/**
 * Class singleton with the only instance of database
 *
 * @author dorian
 */
public class Database {

    /**
     * Instance
     */
    private static DataBaseOperations instance;
    /**
     * Type of database to instantiate
     */
    private static TypeDatabase type = TypeDatabase.SQLITE;

    private Database() {
    }

    /**
     * Set the type of database
     *
     * @param typeData
     */
    public static void setType(TypeDatabase typeData) {
        type = typeData;
    }

    /**
     * Create the database
     */
    private static void createDatabase() {
        switch (type) {
            case SQLITE:
                instance = new SQLite();
                break;
            case ORACLE:
                instance = new Oracle();
                break;
        }
    }

    /**
     * Get the instance of database
     *
     * @return
     */
    public static DataBaseOperations getInstance() {
        if (instance == null) {
            createDatabase();
        }
        return instance;
    }
}
