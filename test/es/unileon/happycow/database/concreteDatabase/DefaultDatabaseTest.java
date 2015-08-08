package es.unileon.happycow.database.concreteDatabase;

import es.unileon.happycow.database.FilesDB;
import es.unileon.happycow.database.PonderationDB;
import es.unileon.happycow.database.ValorationDB;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.Farm;
import es.unileon.happycow.model.User;
import es.unileon.happycow.model.composite.Criterion;
import es.unileon.happycow.model.composite.Valoration;
import es.unileon.happycow.model.facade.EvaluationModel;
import es.unileon.happycow.model.facade.IEvaluationModel;
import java.io.File;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class DefaultDatabaseTest {
    private DefaultDatabase database;
    
    @Before
    public void setUp() {
        database=new SQLite();
    }
    
    @After
    public void tearDown() {
        database.clearDB();
    }

    /**
     * Test of startTransaccion method, of class DefaultDatabase.
     */
    @Test
    public void testStartTransaccion() throws Exception {
        System.out.println("startTransaccion");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        instance.startTransaccion();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of rollback method, of class DefaultDatabase.
     */
    @Test
    public void testRollback() {
        System.out.println("rollback");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        instance.rollback();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of commit method, of class DefaultDatabase.
     */
    @Test
    public void testCommit() {
        System.out.println("commit");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        instance.commit();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of executeSQL method, of class DefaultDatabase.
     */
    @Test
    public void testExecuteSQL() throws Exception {
        System.out.println("executeSQL");
        PreparedStatement sqlConsulta = null;
        DefaultDatabase.TIPOSQL modo = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        instance.executeSQL(sqlConsulta, modo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeDB method, of class DefaultDatabase.
     */
    @Test
    public void testCloseDB() {
        System.out.println("closeDB");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.closeDB();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clearDB method, of class DefaultDatabase.
     */
    @Test
    public void testClearDB() {
        System.out.println("clearDB");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.clearDB();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of crearBaseDatos method, of class DefaultDatabase.
     */
    @Test
    public void testCrearBaseDatos() throws Exception {
        System.out.println("crearBaseDatos");
        DefaultDatabase.crearBaseDatos();
        
    }

    /**
     * Test of encript method, of class DefaultDatabase.
     */
    @Test
    public void testEncript() {
        System.out.println("encript");
        String toEncript = "";
        String expResult = "";
        String result = DefaultDatabase.encript(toEncript);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of login method, of class DefaultDatabase.
     */
    @Test
    public void testLogin() {
        System.out.println("login");
        String user = "";
        String passwd = "";
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.login(user, passwd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of logout method, of class DefaultDatabase.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        instance.logout();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPonderacionCategoria method, of class DefaultDatabase.
     */
    @Test
    public void testGetPonderacionCategoria() {
        System.out.println("getPonderacionCategoria");
        IdHandler idEvaluation = null;
        IdHandler idCategoria = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        float expResult = 0.0F;
        float result = instance.getPonderacionCategoria(idEvaluation, idCategoria);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class DefaultDatabase.
     */
    @Test
    public void testGetUser_0args() {
        System.out.println("getUser");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        User expResult = null;
        User result = instance.getUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUser method, of class DefaultDatabase.
     */
    @Test
    public void testGetUser_IdHandler() {
        System.out.println("getUser");
        IdHandler id = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        User expResult = null;
        User result = instance.getUser(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListUsers method, of class DefaultDatabase.
     */
    @Test
    public void testGetListUsers() {
        System.out.println("getListUsers");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<User> expResult = null;
        LinkedList<User> result = instance.getListUsers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newUser method, of class DefaultDatabase.
     */
    @Test
    public void testNewUser() {
        System.out.println("newUser");
        User user = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.newUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeUser method, of class DefaultDatabase.
     */
    @Test
    public void testRemoveUser() {
        System.out.println("removeUser");
        User user = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.removeUser(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of existUser method, of class DefaultDatabase.
     */
    @Test
    public void testExistUser() throws Exception {
        System.out.println("existUser");
        String usuario = "";
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.existUser(usuario);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changePassword method, of class DefaultDatabase.
     */
    @Test
    public void testChangePassword() {
        System.out.println("changePassword");
        IdHandler user = null;
        String password = "";
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.changePassword(user, password);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListFarms method, of class DefaultDatabase.
     */
    @Test
    public void testGetListFarms_0args() {
        System.out.println("getListFarms");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<Farm> expResult = null;
        LinkedList<Farm> result = instance.getListFarms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFarm method, of class DefaultDatabase.
     */
    @Test
    public void testGetFarm_IdHandler() {
        System.out.println("getFarm");
        IdHandler id = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        Farm expResult = null;
        Farm result = instance.getFarm(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFarm method, of class DefaultDatabase.
     */
    @Test
    public void testGetFarm_IdHandler_IdHandler() {
        System.out.println("getFarm");
        IdHandler idUser = null;
        IdHandler idFarm = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        Farm expResult = null;
        Farm result = instance.getFarm(idUser, idFarm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newFarm method, of class DefaultDatabase.
     */
    @Test
    public void testNewFarm() {
        System.out.println("newFarm");
        Farm farm = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.newFarm(farm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enableFarm method, of class DefaultDatabase.
     */
    @Test
    public void testEnableFarm() {
        System.out.println("enableFarm");
        IdHandler id = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.enableFarm(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disableFarm method, of class DefaultDatabase.
     */
    @Test
    public void testDisableFarm() {
        System.out.println("disableFarm");
        IdHandler id = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.disableFarm(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of switchFarm method, of class DefaultDatabase.
     */
    @Test
    public void testSwitchFarm() {
        System.out.println("switchFarm");
        IdHandler id = null;
        boolean enabled = false;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.switchFarm(id, enabled);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeFarm method, of class DefaultDatabase.
     */
    @Test
    public void testRemoveFarm() {
        System.out.println("removeFarm");
        Farm farm = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.removeFarm(farm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of modifiedFarm method, of class DefaultDatabase.
     */
    @Test
    public void testModifiedFarm() {
        System.out.println("modifiedFarm");
        Farm farm = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.modifiedFarm(farm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListFarms method, of class DefaultDatabase.
     */
    @Test
    public void testGetListFarms_IdHandler() {
        System.out.println("getListFarms");
        IdHandler idUser = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<Farm> expResult = null;
        LinkedList<Farm> result = instance.getListFarms(idUser);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisabledFarms method, of class DefaultDatabase.
     */
    @Test
    public void testGetDisabledFarms() {
        System.out.println("getDisabledFarms");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<Farm> expResult = null;
        LinkedList<Farm> result = instance.getDisabledFarms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberCow method, of class DefaultDatabase.
     */
    @Test
    public void testGetNumberCow_IdHandler_IdHandler() {
        System.out.println("getNumberCow");
        IdHandler id = null;
        IdHandler evaluation = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        int expResult = 0;
        int result = instance.getNumberCow(id, evaluation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberCow method, of class DefaultDatabase.
     */
    @Test
    public void testGetNumberCow_IdHandler() {
        System.out.println("getNumberCow");
        IdHandler id = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        int expResult = 0;
        int result = instance.getNumberCow(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListEvaluations method, of class DefaultDatabase.
     */
    @Test
    public void testGetListEvaluations_IdHandler() {
        System.out.println("getListEvaluations");
        IdHandler idFarm = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<IEvaluationModel> expResult = null;
        LinkedList<IEvaluationModel> result = instance.getListEvaluations(idFarm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListEvaluations method, of class DefaultDatabase.
     */
    @Test
    public void testGetListEvaluations_IdHandler_IdHandler() {
        System.out.println("getListEvaluations");
        IdHandler idUser = null;
        IdHandler idFarm = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<IEvaluationModel> expResult = null;
        LinkedList<IEvaluationModel> result = instance.getListEvaluations(idUser, idFarm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEvaluation method, of class DefaultDatabase.
     */
    @Test
    public void testGetEvaluation_IdHandler() {
        System.out.println("getEvaluation");
        IdHandler id = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        IEvaluationModel expResult = null;
        IEvaluationModel result = instance.getEvaluation(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEvaluation method, of class DefaultDatabase.
     */
    @Test
    public void testGetEvaluation_IdHandler_User() {
        System.out.println("getEvaluation");
        IdHandler id = null;
        User user = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        IEvaluationModel expResult = null;
        IEvaluationModel result = instance.getEvaluation(id, user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveEvaluation method, of class DefaultDatabase.
     */
    @Test
    public void testSaveEvaluation() {
        System.out.println("saveEvaluation");
        IEvaluationModel evaluation = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.saveEvaluation(evaluation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllValorationsFrom method, of class DefaultDatabase.
     */
    @Test
    public void testGetAllValorationsFrom() {
        System.out.println("getAllValorationsFrom");
        IdHandler idEvaluation = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<Valoration> expResult = null;
        LinkedList<Valoration> result = instance.getAllValorationsFrom(idEvaluation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveModifiedEvaluation method, of class DefaultDatabase.
     */
    @Test
    public void testSaveModifiedEvaluation() {
        System.out.println("saveModifiedEvaluation");
        IEvaluationModel evaluation = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.saveModifiedEvaluation(evaluation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveValoration method, of class DefaultDatabase.
     */
    @Test
    public void testSaveValoration_3args() {
        System.out.println("saveValoration");
        int idEvaluation = 0;
        Criterion criterion = null;
        Valoration valoration = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.saveValoration(idEvaluation, criterion, valoration);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeEvaluation method, of class DefaultDatabase.
     */
    @Test
    public void testRemoveEvaluation() {
        System.out.println("removeEvaluation");
        IdHandler id = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.removeEvaluation(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextIdFarm method, of class DefaultDatabase.
     */
    @Test
    public void testNextIdFarm() {
        System.out.println("nextIdFarm");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        int expResult = 0;
        int result = instance.nextIdFarm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextIdValoration method, of class DefaultDatabase.
     */
    @Test
    public void testNextIdValoration() {
        System.out.println("nextIdValoration");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        int expResult = 0;
        int result = instance.nextIdValoration();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextIdEvaluation method, of class DefaultDatabase.
     */
    @Test
    public void testNextIdEvaluation() {
        System.out.println("nextIdEvaluation");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        int expResult = 0;
        int result = instance.nextIdEvaluation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newCriterion method, of class DefaultDatabase.
     */
    @Test
    public void testNewCriterion() {
        System.out.println("newCriterion");
        Criterion criterion = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.newCriterion(criterion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCriterionInitialized method, of class DefaultDatabase.
     */
    @Test
    public void testIsCriterionInitialized() {
        System.out.println("isCriterionInitialized");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.isCriterionInitialized();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getListCriterion method, of class DefaultDatabase.
     */
    @Test
    public void testGetListCriterion() {
        System.out.println("getListCriterion");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<Criterion> expResult = null;
        LinkedList<Criterion> result = instance.getListCriterion();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCriterion method, of class DefaultDatabase.
     */
    @Test
    public void testRemoveCriterion() {
        System.out.println("removeCriterion");
        IdHandler idCriterion = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.removeCriterion(idCriterion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriterion method, of class DefaultDatabase.
     */
    @Test
    public void testGetCriterion() {
        System.out.println("getCriterion");
        IdHandler id = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        Criterion expResult = null;
        Criterion result = instance.getCriterion(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveValoration method, of class DefaultDatabase.
     */
    @Test
    public void testSaveValoration_ValorationDB() {
        System.out.println("saveValoration");
        ValorationDB valoration = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.saveValoration(valoration);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllFarms method, of class DefaultDatabase.
     */
    @Test
    public void testGetAllFarms() {
        System.out.println("getAllFarms");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<Farm> expResult = null;
        LinkedList<Farm> result = instance.getAllFarms();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCriterionPonderation method, of class DefaultDatabase.
     */
    @Test
    public void testGetCriterionPonderation() {
        System.out.println("getCriterionPonderation");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<PonderationDB> expResult = null;
        LinkedList<PonderationDB> result = instance.getCriterionPonderation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllEvaluations method, of class DefaultDatabase.
     */
    @Test
    public void testGetAllEvaluations() {
        System.out.println("getAllEvaluations");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<EvaluationModel> expResult = null;
        LinkedList<EvaluationModel> result = instance.getAllEvaluations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategoryPonderation method, of class DefaultDatabase.
     */
    @Test
    public void testGetCategoryPonderation() {
        System.out.println("getCategoryPonderation");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<PonderationDB> expResult = null;
        LinkedList<PonderationDB> result = instance.getCategoryPonderation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllValorations method, of class DefaultDatabase.
     */
    @Test
    public void testGetAllValorations() {
        System.out.println("getAllValorations");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<ValorationDB> expResult = null;
        LinkedList<ValorationDB> result = instance.getAllValorations();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveCategoryPonderation method, of class DefaultDatabase.
     */
    @Test
    public void testSaveCategoryPonderation() {
        System.out.println("saveCategoryPonderation");
        LinkedList<PonderationDB> list = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.saveCategoryPonderation(list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveCriterionPonderation method, of class DefaultDatabase.
     */
    @Test
    public void testSaveCriterionPonderation() {
        System.out.println("saveCriterionPonderation");
        LinkedList<PonderationDB> list = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.saveCriterionPonderation(list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of newPonderacionCriterio method, of class DefaultDatabase.
     */
    @Test
    public void testNewPonderacionCriterio() {
        System.out.println("newPonderacionCriterio");
        int idEvaluation = 0;
        String idCriterio = "";
        float ponderacion = 0.0F;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.newPonderacionCriterio(idEvaluation, idCriterio, ponderacion);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveEvaluationBackup method, of class DefaultDatabase.
     */
    @Test
    public void testSaveEvaluationBackup() {
        System.out.println("saveEvaluationBackup");
        IEvaluationModel evaluation = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.saveEvaluationBackup(evaluation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveFile method, of class DefaultDatabase.
     */
    @Test
    public void testSaveFile() {
        System.out.println("saveFile");
        IdHandler handler = null;
        File file = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.saveFile(handler, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileNames method, of class DefaultDatabase.
     */
    @Test
    public void testGetFileNames() {
        System.out.println("getFileNames");
        IdHandler idHandler = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        List<String> expResult = null;
        List<String> result = instance.getFileNames(idHandler);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFile method, of class DefaultDatabase.
     */
    @Test
    public void testGetFile() {
        System.out.println("getFile");
        IdHandler idHandler = null;
        String name = "";
        DefaultDatabase instance = new DefaultDatabaseImpl();
        byte[] expResult = null;
        byte[] result = instance.getFile(idHandler, name);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveFileToTheSystem method, of class DefaultDatabase.
     */
    @Test
    public void testSaveFileToTheSystem() {
        System.out.println("saveFileToTheSystem");
        byte[] arr = null;
        File file = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        instance.saveFileToTheSystem(arr, file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllFiles method, of class DefaultDatabase.
     */
    @Test
    public void testGetAllFiles() {
        System.out.println("getAllFiles");
        DefaultDatabase instance = new DefaultDatabaseImpl();
        LinkedList<FilesDB> expResult = null;
        LinkedList<FilesDB> result = instance.getAllFiles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveFiles method, of class DefaultDatabase.
     */
    @Test
    public void testSaveFiles() {
        System.out.println("saveFiles");
        LinkedList<FilesDB> list = null;
        DefaultDatabase instance = new DefaultDatabaseImpl();
        boolean expResult = false;
        boolean result = instance.saveFiles(list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class DefaultDatabaseImpl extends DefaultDatabase {

        @Override
        public boolean openDB() {
            return true;
        }
    }
    
}
