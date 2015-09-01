package es.unileon.happycow.model;

import es.unileon.happycow.database.concreteDatabase.DatabaseObject;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdUser;
import java.io.Serializable;
//import es.unileon.happycow.procedures.PreferredView;

/**
 *
 * @author amdiaz8
 */
public class User implements Serializable {

    private String name;
    private String password;
    private IdHandler idHandler;
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

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

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

    public String getStringRol() {
        return rol.toString();
    }

//    public PreferredView getPreferredView(){
//        return this.preferredView;
//    }
    
    public IdHandler getId() {
        return this.idHandler;
    }

    @Override
    public String toString() {
        return name + " " + password + " " + rol.toString();
    }

}
