package es.unileon.happycow.model.evaluation;

import es.unileon.happycow.handler.Category;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.InformationEvaluation;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Evaluation;
import es.unileon.happycow.model.composite.EvaluationCategory;
import es.unileon.happycow.model.composite.Valoration;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author dorian
 */
public interface IEvaluationModel extends Serializable{

    /**
     * Todas las operaciones que se quiernan realizar sobre el modelo deben
     * anhadirse aqui
     *
     */
    
    public Evaluation getComposite();
    /**
     * Anhade una valoracion a una categoria y criterio dado
     *
     * @param categoria
     * @param criterio
     * @param valoration
     * @return
     */
    public boolean add(IdHandler categoria, IdHandler criterio, Valoration valoration);

    /**
     * Anhade un criterio a una categoria dada
     *
     * @param categoria
     * @param criterion
     * @return
     */
    public boolean add(IdHandler categoria, Criterion criterion);

    /**
     * Anhade una categoria, si no existe.
     *
     * @param categoria
     * @return
     */
    public boolean add(IdHandler categoria);

    /**
     * Anhade una categoria si no existe
     *
     * @param categoria
     * @return
     */
    public boolean add(EvaluationCategory categoria);
    
    
    
    
    /**
     * Borra del arbol dado un handler
     * @param idHandler
     * @return 
     */
    public boolean remove(IdHandler idHandler);
    
    

    /**
     * Anhade una ponderacion dado un handler
     *
     * @param idHandler
     * @param weighing
     * @return
     */
    public boolean setWeighing(IdHandler idHandler, float weighing);
    
    /**
     * Devuelve la ponderacion dado el handler
     * @param idHandler
     * @return 
     */
    public float getWeighing(IdHandler idHandler);
    
    
    

    /**
     * Get a list of valorations filtered by a category
     *
     * @param category a category like a filter
     * @return a list of valorations
     */
    public LinkedList listOfCategory(IdHandler category);
    
    /**
     * 
     * @param category
     * @return 
     */
    public LinkedList<Valoration> listOfCategory(Category category);

    
    
    /**
     * Get a list of valorations filtered by a criterion
     *
     * @param criterion the criterion to filter
     * @return a list of valorations
     */
    public LinkedList<Valoration> listOfCriterion(IdHandler criterion);

    /**
     * Devueleve una lista de los criterios concretos 
     * que hay en una categoria concreta de la evaluacion
     *
     * @param category
     * @return una lista de criterios
     */
    public LinkedList<Criterion> getListCriterion(Category category);
    
    /**
     * Devuelve una lista de todos los criterios que hay en la evaluacion
     * @return una lista de criterios
     */
    public LinkedList<Criterion> getListCriterion();
    
    /**
     * Devuelve el handler de la evaluacion
     * @return handler de la evaluacion
     */
    public IdHandler getIdHandler();
    
    public InformationEvaluation getInformation();
    
    //TODO
    /**
     * Devolver una lista de evaluaciones por paquetes (vacas) --> esta interfaz debería
     * ser implementados por todos los modelos puesto que lo han de tratar procedimientos
     * 
     * Hay distintas implementaciones, todas ellas tratan el mismo árbol (con lo que
     * se puede mover de un modelo a otro y que sería el modelo propiamente dicho)
     * pero ofrecen distintos métodos de acuerdo a distintos enfoques, como puede
     * ser el enfoque por criterios, o el enfoque por vacas, o incluso un tercer enfoque
     * (a saber cuál sería)
     * 
     * Con ello, el controlador usa un modelo en concreto, junto con su panel concreto
     * (pattern abstract factory? para instanciar (como el modelo de ventanas y demás)
     * y el cambio entre unos y otros es tan sencillo como traspasar el árbol.
     * No tan sencillo es pasar la información entre ventanas tal cual está pensado,
     * para ello... consultar con la almohada.
     */
}
