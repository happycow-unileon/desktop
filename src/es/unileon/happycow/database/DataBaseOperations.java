package es.unileon.happycow.database;

import es.unileon.happycow.database.backup.FilesDB;
import es.unileon.happycow.database.backup.PonderationDB;
import es.unileon.happycow.database.backup.ValorationDB;
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
 * Interface of operations a database must implement
 * @author dorian
 */
public interface DataBaseOperations {
    
    /**
     * Limpia la base de datos para hacer el backup
     * @return 
     */
    public boolean clearDB();
    /**
     * Start a transaction
     * @throws SQLException 
     */
    public void startTransaccion() throws SQLException;
    /**
     * Rollback a transaction
     */
    public void rollback();
    /**
     * Commit a transaction
     */
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
    
    /**
     * Update the data user
     * @param user
     * @return 
     */
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
     * @param evaluation
     * @return 
     */
    public int getNumberCowEvaluation(IdHandler evaluation);
    /**
     * Devuelve el número de vacas que tiene una granja actualmente
     * @param idFarm
     * @return 
     */
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
    
    /**
     * Elimina la evaluación dada
     * @param id
     * @return 
     */
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
     * Devuelve todo el contenido de la tabla granjas, devolviendo una lista de
     * granjas (sin más datos que los de la tabla granjas)
     * @return 
     */
    public LinkedList<Farm> getAllFarms();
    
    /**
     * Devuelve una lista de todos los datos de la tabla evaluaciones, devolviendo
     * una lista de evaluaciones, pero sin más datos que la tabla evaluaciones
     * @return 
     */
    public LinkedList<InformationEvaluation> getAllInformationEvaluations();
 
    
    /**
     * Guarda un fichero
     * @param handler
     * @param file
     * @return 
     */
    public boolean saveFile(IdHandler handler, File file);
    /**
     * Devuelve la lista de ficheros de una evaluación dada
     * @param idHandler
     * @return 
     */
    public List<String> getFileNames(IdHandler idHandler);
    /**
     * Get a file given its name and id evaluation
     * @param idHandler
     * @param name
     * @return 
     */
    public byte[] getFile(IdHandler idHandler, String name);
     /**
      * Store the file in the computer to the user
      * @param arr
      * @param file 
      */
    public void saveFileToTheSystem(byte[] arr, File file);
    /**
     * Remove the file
     * @param idEvaluation
     * @param name 
     */
    public boolean removeFile(IdHandler idEvaluation, String name);
    
    
    //Backup
    /**
     * Get ALL files to backup
     * @return 
     */
    public LinkedList<FilesDB> getAllFiles();
    
    
    /**
     * Get ALL category ponderation to backup
     * @return 
     */
    public LinkedList<PonderationDB> getCategoryPonderation();
    /**
     * Get ALL criterion ponderation to backup
     * @return 
     */
    public LinkedList<PonderationDB> getCriterionPonderation();
    /**
     * Get ALL valorations to backup
     * @return 
     */
    public LinkedList<ValorationDB> getAllValorations();
    /**
     * Save files from backup
     * @param list
     * @return 
     */
    public boolean saveFiles(LinkedList<FilesDB> list);
    /**
     * Save category ponderation from backup
     * @param list
     * @return 
     */
    public boolean saveCategoryPonderation(LinkedList<PonderationDB> list);
    /**
     * Save criterion poneration from backup
     * @param list
     * @return 
     */
    public boolean saveCriterionPonderation(LinkedList<PonderationDB> list);
    /**
     * Save valoration from backup
     * @param valoration
     * @return 
     */
    public boolean saveValoration(ValorationDB valoration);
    /**
     * Save information evaluation from backup
     * @param info
     * @return 
     */
    public boolean saveInformationEvaluation(InformationEvaluation info);
    /**
     * Save a criterion from backup
     * @param criterion
     * @return 
     */
    public boolean newCriterionBackup(Criterion criterion);
    
}
