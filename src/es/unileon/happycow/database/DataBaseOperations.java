package es.unileon.happycow.database;

import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.InformationEvaluation;
import es.unileon.happycow.model.User;
import es.unileon.happycow.model.composite.Evaluation;
import es.unileon.happycow.model.evaluation.IEvaluationModel;
import java.io.File;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author dorian
 */
public interface DataBaseOperations {
    
    /**
     * Limpia la base de datos para hacer el backup
     * @return 
     */
    public boolean clearDB();
    
    public void startTransaccion() throws SQLException;

    public void rollback();

    public void commit();


    /**
     * Operaciones que se necesite para abrir/conectar la base de datos
     * @return 
     */
    public boolean openDB();

    /**
     * Operaciones que se necesite para cerrar/desconectar de la base de 
     * datos de forma segura
     * @return 
     */
    public boolean closeDB();

    /**
     * Comprueba si los datos son correctos
     * @param user
     * @param passwd
     * @return true si el usuario y la contraseña son correctos
     */
    public boolean login(String user, String passwd);
    
    /**
     * Desconecta el usuario
     */
    public void logout();
    
    

    /**
     * Devuelve el usuario que está logeado
     * @return 
     */
    public User getUser();
    
    /**
     * Devuelve el usuario especificado en el id
     * @param id
     * @return 
     */
    public User getUser(IdHandler id);
    
     /**
     * Añade un nuevo usuario
     * @param user usuario nuevo
     * @return true si todo correcto
     */
    public boolean newUser(User user);
    
    
    public boolean updateUser(User user);
    
    /**
     * Elimina un usuario concreto
     * @param user el usuario a eliminar
     * @return true si todo correcto
     */
    public boolean removeUser(User user);
    
     /**
     * Devuelve una lista de usuarios existentes en la base de datos
     * @return 
     */
    public LinkedList<User> getListUsers();
    
    
    
    
    
    
    

    /**
     * Devuelve una lista de granjas del usuario con su información y listado de evaluaciones
     * pero sin sus valoraciones
     * @return 
     */
    public LinkedList<Farm> getListFarms();

    /**
     * Devuelve una lista de granjas del usuario deshabilitadas
     * @return 
     */
    public LinkedList<Farm> getDisabledFarms();
    
    /**
     * Guarda una nueva granja
     * @param farm la granja a guardar
     * @return 
     */
    public boolean newFarm(Farm farm);
    
    /**
     * Devuelve la granja especificada
     * @param id identificador de la granja
     * @return 
     */
    public Farm getFarm(IdHandler id);
    
    /**
     * ELimina una granja de la base de datos
     * @param farm
     * @return 
     */
    public boolean removeFarm(Farm farm);

    /**
     * Guarda una granja ya existente en la base de datos
     * @param farm la granja a guardar que ya existe
     * @return 
     */
    public boolean updateFarm(Farm farm);

    /**
     * Deshabilita la granja especificada
     * @param id identificador de la granja a deshabilitar
     * @return 
     */
    public boolean disableFarm(IdHandler id);

    /**
     * Habilita la granja especificada
     * @param id identificador de la granja
     * @return 
     */
    public boolean enableFarm(IdHandler id);
    
    /**
     * Devuelve el número de vacas que tiene la granja en una determinada evaluacion
     * @param id
     * @return 
     */
    public int getNumberCowEvaluation(IdHandler evaluation);
    
    public int getNumberCow(IdHandler idFarm);

    /**
     * Devuelve una lista de granjas de un determinado usuario
     * @param idUser
     * @return 
     */
    public LinkedList<Farm> getListFarms(IdHandler idUser);

    
    
    
    
    
    

    /**
     * Devuelve una lista de evaluaciones de la granja especificada. No devuelve
     * las valoraciones de las evaluaciones
     * @param idFarm el identificador de la granja
     * @return lista de evaluaciones (interface, instanciados con evaluationModel)
     */
    public LinkedList<InformationEvaluation> getListEvaluations(IdHandler idFarm);

    /**
     * Devuelve una evaluacion con todas sus valoraciones (al contrario que las demás,
     * en new EvaluationModel, se pasa un true en vez de false)
     * @param id identificador de la evaluación
     * @return 
     */
    public Evaluation getEvaluation(IdHandler id);
    
    public InformationEvaluation getInformationEvaluation(IdHandler id);
   
    /**
     * Guarda los datos de una evaluación 
     * @param evaluation evaluación a guardar
     * datos
     * @return 
     */
    public boolean saveEvaluation(IEvaluationModel evaluation);

    /**
     * Guarda los datos de una evaluación ya existente
     * @param evaluation evaluación a guardar que ya se encuentra en la base de
     * datos
     * @return 
     */
    public boolean updateEvaluation(IEvaluationModel evaluation);
    
    
    public boolean removeEvaluation(IdHandler id);
    
    

    

    /**
     * Devuelve todos los criterios existentes en la base de datos
     * @return 
     */
    public LinkedList<Criterion> getListCriterion();

    /**
     * Elimina un criterio específico
     * @param idCriterion el identificador del criterio a eliminar
     * @return 
     */
    public boolean removeCriterion(IdHandler idCriterion);
    
    /**
     * Añade un nuevo criterio
     * @param criterion el criterio a añadir
     * @return true si todo correcto 
     */
    public boolean newCriterion(Criterion criterion);
    
    /**
     * Devuelve el criterio especificado
     * @param id identificador del criterio
     * @return 
     */
    public Criterion getCriterion(IdHandler id);
    
    

    /**
     * Devuelve el siguiente identificador de una evaluacion.
     * cuando creamos una nueva evaluación, le tenemos que pasar un número 
     * como identificador, que se usa en la base de datos por ello, se hace 
     * una consulta para saber cuántas evaluaciones tiene ya guardadas para saber cuál es el siguiente número que podemos poner al nuevo
     * @return 
     */
    public int nextIdEvaluation();

    /**
     * Devuelve el siguiente identificador de una granja.
     * lo mismo que antes le pasa a la granja ya que ambos tienen índices que van por conteo simple
     * @return 
     */
    public int nextIdFarm();

    /**
     * Devuelve el identificador de una valoración.
     * ATENCIÓN: aquí se ha de tener en cuenta que se pedirá varias veces sin
     * ir guardando en la base de datos, por lo que se debe mantener una variable en
     * la clase para ir incrementándolo después de la primera consulta
     * @return 
     */
    public int nextIdValoration();

    
    
   

    
    /**
     * Devuelve todo el contenido de la tabla granjas, devolviendo una lista de
     * granjas (sin más datos que los de la tabla granjas)
     * @return 
     */
//    public LinkedList<Farm> getAllFarms();
    
    /**
     * Devuelve una lista de todos los datos de la tabla evaluaciones, devolviendo
     * una lista de evaluaciones, pero sin más datos que la tabla evaluaciones
     * @return 
     */
//    public LinkedList<DefaultEvaluationModel> getAllEvaluations();
    
    
    
    /**
     * Guardar la valoración con todos los datos dados.
     * @param idEvaluation
     * @param criterion
     * @param valoration 
     * @return  
     */
//    public boolean saveValoration(int idEvaluation, Criterion criterion, Valoration valoration);
    
   
    
//    public boolean saveEvaluationBackup(IEvaluationModel evaluation);
    
    
    
    public boolean saveFile(IdHandler handler, File file);
    
    public List<String> getFileNames(IdHandler idHandler);
    
    public byte[] getFile(IdHandler idHandler, String name);
     
    public void saveFileToTheSystem(byte[] arr, File file);
    
}
