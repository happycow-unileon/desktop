package es.unileon.happycow.model;

import es.unileon.happycow.database.concreteDatabase.DatabaseObject;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdUser;
import java.io.Serializable;

/**
 *
 * @author amdiaz8
 */
public class User implements Serializable {
    /**
     * Name
     */
    private String name;
    /**
     * Password
     */
    private String password;
    /**
     * Id
     */
    private IdHandler idHandler;
    /**
     * Rol
     */
    private Rol rol;

    public User(String name, String passwd, Rol rol) {
        this(new IdUser(name), passwd, rol);
    }
    
    public User(String name, String passwd, String rol) {
        this(new IdUser(name), passwd, rol);
    }

    public User(IdHandler id, String passwd, String rol) {
        this(id, passwd, Rol.valueOf(rol));
    }

    public User(IdHandler id, String passwd, Rol rol) {
        this.name = id.getValue();
        this.idHandler = id;
        this.password = DatabaseObject.encript(passwd);
        this.rol = rol;
    }

    /**
     * Get name
     * @return 
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get password
     * @return 
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Get rol
     * @return 
     */
    public Rol getRol() {
        return rol;
    }

    /**
     * set encrypted
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get string rol
     * @return 
     */
    public String getStringRol() {
        return rol.toString();
    }
    
    /**
     * Get id
     * @return 
     */
    public IdHandler getId() {
        return this.idHandler;
    }

    @Override
    public String toString() {
        return name + " " + password + " " + rol.toString();
    }

}
