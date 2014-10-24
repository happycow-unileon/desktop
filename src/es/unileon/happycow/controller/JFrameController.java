package es.unileon.happycow.controller;

import es.unileon.happycow.abstractFactory.FactoryChangePassword;
import es.unileon.happycow.abstractFactory.FactoryEnableFarm;
import es.unileon.happycow.abstractFactory.FactoryEvaluationCowView;
import es.unileon.happycow.abstractFactory.FactoryEvaluationCriterionView;
import es.unileon.happycow.abstractFactory.FactoryExcel;
import es.unileon.happycow.abstractFactory.FactoryWindows;
import es.unileon.happycow.abstractFactory.FactoryListFarm;
import es.unileon.happycow.abstractFactory.FactoryLogin;
import es.unileon.happycow.abstractFactory.FactoryManageFarm;
import es.unileon.happycow.abstractFactory.FactoryNewFarm;
import es.unileon.happycow.abstractFactory.FactoryReport;
import es.unileon.happycow.abstractFactory.admin.FactoryAdmin;
import es.unileon.happycow.database.*;
import es.unileon.happycow.gui.JFrameApplication;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.procedures.Report;
import java.awt.Dimension;
import java.util.LinkedList;


/**
 * El controlador general del programa que controla el jframe y por tanto las
 * ventanas que va pasando según las acciones del programa (otros controladores)
 * Tiene implementado el patrón singleton
 * @author dorian
 */
public class JFrameController implements InterfaceController{
    /**
     * Instancia singleton
     */
    private static JFrameController instance;
    /**
     * Factoría que instanciará los paneles/controladores necesarios en cada
     * momento
     */
    private FactoryWindows factory;
    private String title;
    
    private FactoryWindows comeBack;
    private String titleComeBack;
    
    private boolean canComeBack;
    private boolean criterionView;

    /**
     * Contructor privado
     */
    private JFrameController() {
        canComeBack=false;
        criterionView=true;
    }
    
    /**
     * Devuelve la única instancia de la clase
     * @return la única instancia de la clase
     */
    public static JFrameController getInstance(){
        if (instance==null){
            instance=new JFrameController();
        }
        return instance;
    }
    
    /**
     * Punto de partida del programa, establecido en el comienzo por el login
     */
    public void startApplication(){
        login();
        JFrameApplication.getInstance().setLocationRelativeTo(null);
    }
    
    public void changeView(){
        criterionView=!criterionView;
    }
    
    /**
     * Indica al jframe de cambiar un panel por otro.
     */
    private void changePanel(){
        //cambio el titulo
        JFrameApplication.getInstance().setTitle(title);
        //le indica el cambio de panel
        JFrameApplication.getInstance().changePanel(factory);
        //ajusta de nuevo el tamaño del jframe
        JFrameApplication.getInstance().pack();
        Dimension size=JFrameApplication.getInstance().getSize();
        JFrameApplication.getInstance().setSize((int)size.getWidth()+20, (int)size.getHeight()+20);
    }
    
    /**
     * Guarda la ventana que está actualmente mostrandose
     */
    private void saveComeBack(){
        if(factory!=null){
            canComeBack=true;
            comeBack=factory;
            titleComeBack=title;
        }
    }
    
    /**
     * Restaura la ventana que estaba guardada
     */
    public void comeBack(){
        if(comeBack!=null && canComeBack){
            canComeBack=false;
            factory=comeBack;
            title=titleComeBack;
            changePanel();
        }
    }
    
    public void administrator(){
        saveComeBack();
        factory=new FactoryAdmin();
        factory.createElements();
        title="Administración";
        changePanel();
    }
    
    /**
     * Establece la pantalla del login
     */
    public void login(){
        //crea la fábrica
        factory=new FactoryLogin();
        //crea panel y controlador
        factory.createElements();
        title="Login";
        //cambia el panel
        changePanel();
        canComeBack=false;
        
    }
    
    /**
     * Establece la pantalla del listado de granjas a seleccionar
     */
    public void seeListFarm(){
        saveComeBack();
        //crea la fábrica
        factory=new FactoryListFarm(Database.getInstance().getListFarms());
        //crea panel y controlador
        factory.createElements();
        title="Lista Granjas";
        //cambia el panel
        changePanel();
    }
    
    /**
     * Establece la pantalla de nueva granja
     * @param idFarm
     */
    public void newFarm(IdHandler idFarm){
        saveComeBack();
        if(idFarm==null){
            factory=new FactoryNewFarm(null);
            title="Nueva granja";
        }else{
            factory = new FactoryNewFarm(Database.getInstance().getFarm(idFarm));
            title="Modificar granja";
        }
        factory.createElements();
        changePanel();
    }
    
    /**
     * Establece la pantalla del listado de granjas deshabilitadas a habilitar
     */
    public void enableFarm(){
        saveComeBack();
        factory=new FactoryEnableFarm(Database.getInstance().getDisabledFarms());
        title="Granjas deshabilitadas";
        factory.createElements();
        changePanel();
    }
    
    /**
     * Establece la pantalla de la gestión de la granja especificada
     * @param id identificador de la granja que ha de mostrar
     */
    public void manageFarm(IdHandler id){
        saveComeBack();
        //crea el panel y el controlador
        factory=new FactoryManageFarm(Database.getInstance().getFarm(id));
        title="Datos granja";
        factory.createElements();
        //cambia el panel
        changePanel();
    }
    
    public void evaluation(IdHandler farm, IdHandler evaluation){
        saveComeBack();
        if(evaluation==null){
            if(criterionView){
                factory=new FactoryEvaluationCriterionView(farm);
            }else{
                factory=new FactoryEvaluationCowView(farm);
            }
        }else{
            if(criterionView){
                factory=new FactoryEvaluationCriterionView(Database.getInstance().getEvaluation(evaluation));
            }else{
                factory=new FactoryEvaluationCowView(Database.getInstance().getEvaluation(evaluation));
            }
        }
        
        title="Evaluación nueva";
        factory.createElements();
        changePanel();
    
    }
    
    public void report(Report rep, IdHandler idFarm){
        canComeBack=false;
        factory=new FactoryReport(rep, idFarm);
        title="Reporte";
        factory.createElements();
        changePanel();
    }
    
    public void excel(LinkedList<Farm> listFarms){
        
        saveComeBack();
        canComeBack=true;
        factory=new FactoryExcel(listFarms);
        title="Exportar a Excel";
        factory.createElements();
        changePanel();
    }
    
    public void changePassword(){
        if(comeBack.getClass()!=FactoryChangePassword.class){
            saveComeBack();
        }
        factory=new FactoryChangePassword();
        title="Cambiar contraseña";
        factory.createElements();
        changePanel();
    }
    
    /**
     * Da por finalizado el programa
     */
    public void exit(){
        //operaciones antes de cerrar todo...
        Database.getInstance().closeDB();
        System.exit(0);
    }
}
