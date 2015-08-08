package es.unileon.happycow.model;

import es.unileon.happycow.handler.IdEvaluation;
import es.unileon.happycow.handler.IdFarm;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.handler.IdUser;
import java.sql.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dorian
 */
public class FarmTest {

    private Farm filledFarm;
    private Farm emptyFarm;

    private IdFarm oneId;
    private IdFarm twoId;

    private IdHandler user;

    private InformationEvaluation info;

    @Before
    public void setUp() {
        oneId = new IdFarm(2);
        twoId = new IdFarm(3);

        user = new IdUser("marta");

        LinkedList<InformationEvaluation> list = new LinkedList<>();

        IdHandler evaluation = new IdEvaluation(20);
        info = new InformationEvaluation(evaluation, oneId, user, 4.3f, 2f, 2.1f, 2.2f, 2.3f,
                new Date(new GregorianCalendar(2000, 5, 22).getTimeInMillis()), 25);
        list.add(info);

        evaluation = new IdEvaluation(25);
        info = new InformationEvaluation(evaluation, oneId, user, 4.3f, 2f, 2.1f, 2.2f, 2.3f,
                new Date(new GregorianCalendar(2000, 5, 22).getTimeInMillis()), 25);
        list.add(info);

        evaluation = new IdEvaluation(30);
        info = new InformationEvaluation(evaluation, oneId, user, 4.3f, 2f, 2.1f, 2.2f, 2.3f,
                new Date(new GregorianCalendar(2000, 5, 22).getTimeInMillis()), 25);
        list.add(info);

        emptyFarm = new Farm(oneId, "nameFarmEmpty", "identifierEmpty", "addressEmpty", "nameEmptyFarmer", "dniFarmerEmpty", 55, user);
        filledFarm = new Farm(twoId, "nameFarmFilled", "identifierFilled", "addressFilled", "nameFarmerFilled", "dniFarmerFilled", 20, null, list);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isEnabled method, of class Farm.
     */
    @Test
    public void testIsEnabled() {
        assertTrue(emptyFarm.isEnabled());
        assertTrue(emptyFarm.isEnabled());

        emptyFarm.setEnabled(false);

        assertFalse(emptyFarm.isEnabled());
        assertTrue(filledFarm.isEnabled());
    }

    /**
     * Test of getAddress method, of class Farm.
     */
    @Test
    public void testGetAddress() {
        assertEquals("addressEmpty", emptyFarm.getAddress());
        assertEquals("addressFilled", filledFarm.getAddress());
    }

    /**
     * Test of getOtherData method, of class Farm.
     */
    @Test
    public void testGetOtherData() {
        assertEquals("", emptyFarm.getOtherData());
        assertEquals("", filledFarm.getOtherData());

        emptyFarm.setOtherData("other");
        assertEquals("other", emptyFarm.getOtherData());
        assertEquals("", filledFarm.getOtherData());
    }

    /**
     * Test of getCowNumber method, of class Farm.
     */
    @Test
    public void testGetCowNumber() {
        assertEquals(55, emptyFarm.getCowNumber());
        assertEquals(20, filledFarm.getCowNumber());
    }

    /**
     * Test of getFarmIdentifier method, of class Farm.
     */
    @Test
    public void testGetFarmIdentifier() {
        assertEquals("identifierEmpty", emptyFarm.getFarmIdentifier());
        assertEquals("identifierFilled", filledFarm.getFarmIdentifier());
    }

    /**
     * Test of getDniFarmer method, of class Farm.
     */
    @Test
    public void testGetDniFarmer() {
        assertEquals("dniFarmerEmpty", emptyFarm.getDniFarmer());
        assertEquals("dniFarmerFilled", filledFarm.getDniFarmer());
    }

    /**
     * Test of getFarmName method, of class Farm.
     */
    @Test
    public void testGetFarmName() {
        assertEquals("nameFarmEmpty", emptyFarm.getFarmName());
        assertEquals("nameFarmFilled", filledFarm.getFarmName());
    }

    /**
     * Test of getFarmerName method, of class Farm.
     */
    @Test
    public void testGetFarmerName() {
        assertEquals("nameEmptyFarmer", emptyFarm.getFarmerName());
        assertEquals("nameFarmerFilled", filledFarm.getFarmerName());
    }

    /**
     * Test of getIdFarm method, of class Farm.
     */
    @Test
    public void testGetIdFarm() {
        assertEquals(oneId, emptyFarm.getIdFarm());
        assertEquals(twoId, filledFarm.getIdFarm());
    }

    /**
     * Test of getNameUser method, of class Farm.
     */
    @Test
    public void testGetNameUser() {
        assertNull(filledFarm.getIdUser());
        assertEquals("marta", emptyFarm.getNameUser());
    }

    /**
     * Test of getIdUser method, of class Farm.
     */
    @Test
    public void testGetIdUser() {
        assertNotNull(emptyFarm.getIdUser());
        assertNull(filledFarm.getIdUser());

        IdUser user2 = new IdUser("dorian");
        Farm userFarm = new Farm(oneId, "nameFarmEmpty", "identifierEmpty", "addressEmpty", "nameEmptyFarmer", "dniFarmerEmpty", 55, user2);

        assertEquals(user2, userFarm.getIdUser());
    }

    /**
     * Test of setAddress method, of class Farm.
     */
    @Test
    public void testSetAddress() {
        assertEquals("addressEmpty", emptyFarm.getAddress());
        assertEquals("addressFilled", filledFarm.getAddress());
    }

    /**
     * Test of setCowNumber method, of class Farm.
     */
    @Test
    public void testSetCowNumber() {
        assertEquals(55, emptyFarm.getCowNumber());
        assertEquals(20, filledFarm.getCowNumber());

        emptyFarm.setCowNumber(10);
        filledFarm.setCowNumber(40);

        assertEquals(10, emptyFarm.getCowNumber());
        assertEquals(40, filledFarm.getCowNumber());
    }

    /**
     * Test of setDniFarmer method, of class Farm.
     */
    @Test
    public void testSetDniFarmer() {
        assertEquals("dniFarmerEmpty", emptyFarm.getDniFarmer());
        assertEquals("dniFarmerFilled", filledFarm.getDniFarmer());

        emptyFarm.setDniFarmer("dni1");
        filledFarm.setDniFarmer("dni2");

        assertEquals("dni1", emptyFarm.getDniFarmer());
        assertEquals("dni2", filledFarm.getDniFarmer());
    }

    /**
     * Test of setFarmName method, of class Farm.
     */
    @Test
    public void testSetFarmName() {
        assertEquals("nameFarmEmpty", emptyFarm.getFarmName());
        assertEquals("nameFarmFilled", filledFarm.getFarmName());

        emptyFarm.setFarmName("name1");
        filledFarm.setFarmName("name2");

        assertEquals("name1", emptyFarm.getFarmName());
        assertEquals("name2", filledFarm.getFarmName());
    }

    /**
     * Test of setFarmerName method, of class Farm.
     */
    @Test
    public void testSetFarmerName() {
        assertEquals("nameEmptyFarmer", emptyFarm.getFarmerName());
        assertEquals("nameFarmerFilled", filledFarm.getFarmerName());

        emptyFarm.setFarmerName("name1");
        filledFarm.setFarmerName("name2");

        assertEquals("name1", emptyFarm.getFarmerName());
        assertEquals("name2", filledFarm.getFarmerName());
    }

    /**
     * Test of setOtherData method, of class Farm.
     */
    @Test
    public void testSetOtherData() {
        assertEquals("", emptyFarm.getOtherData());
        assertEquals("", filledFarm.getOtherData());

        emptyFarm.setOtherData("data");
        filledFarm.setOtherData("other");

        assertEquals("data", emptyFarm.getOtherData());
        assertEquals("other", filledFarm.getOtherData());
    }

    /**
     * Test of addEvaluation method, of class Farm.
     */
    @Test
    public void testAddEvaluation() {
        assertEquals(0, emptyFarm.getListEvaluation().size());
        assertEquals(3, filledFarm.getListEvaluation().size());

        IdHandler evaluation = new IdEvaluation(100);
        info = new InformationEvaluation(evaluation, oneId, user, 4.3f, 2f, 2.1f, 2.2f, 2.3f,
                new Date(new GregorianCalendar(2000, 5, 22).getTimeInMillis()), 25);
        
        emptyFarm.addEvaluation(info);
        filledFarm.addEvaluation(info);
        
        assertEquals(1, emptyFarm.getListEvaluation().size());
        assertEquals(4, filledFarm.getListEvaluation().size());
        
        evaluation = new IdEvaluation(25);
        info = new InformationEvaluation(evaluation, oneId, user, 4.3f, 2f, 2.1f, 2.2f, 2.3f,
                new Date(new GregorianCalendar(2000, 5, 22).getTimeInMillis()), 25);
        
        emptyFarm.addEvaluation(info);
        filledFarm.addEvaluation(info);
        
        assertEquals(2, emptyFarm.getListEvaluation().size());
        assertEquals(4, filledFarm.getListEvaluation().size());
    }

    /**
     * Test of removeEvaluation method, of class Farm.
     */
    @Test
    public void testRemoveEvaluation() {
        assertEquals(0, emptyFarm.getListEvaluation().size());
        assertEquals(3, filledFarm.getListEvaluation().size());
        
        IdHandler evaluation = new IdEvaluation(25);
        info = new InformationEvaluation(evaluation, oneId, user, 4.3f, 2f, 2.1f, 2.2f, 2.3f,
                new Date(new GregorianCalendar(2000, 5, 22).getTimeInMillis()), 25);
        
        filledFarm.removeEvaluation(evaluation);
        emptyFarm.removeEvaluation(evaluation);
        
        assertEquals(0, emptyFarm.getListEvaluation().size());
        assertEquals(2, filledFarm.getListEvaluation().size());
        
        filledFarm.addEvaluation(info);
        
        assertEquals(3, filledFarm.getListEvaluation().size());
    }

    /**
     * Test of getEvaluation method, of class Farm.
     */
    @Test
    public void testGetEvaluation() {
        assertEquals(0, emptyFarm.getListEvaluation().size());
        assertEquals(3, filledFarm.getListEvaluation().size());
        
        IdHandler evaluation = new IdEvaluation(25);
        
        InformationEvaluation result=emptyFarm.getEvaluation(evaluation);
        assertNull(result);
        
        result=filledFarm.getEvaluation(evaluation);
        assertNotNull(result);
        
        assertEquals(oneId, result.getIdFarm());
        assertEquals(4.3, result.getNota(), 0.001);
        assertEquals(25, result.getNumberCows());
        
    }

    /**
     * Test of getListEvaluation method, of class Farm.
     */
    @Test
    public void testGetListEvaluation() {
        assertEquals(0, emptyFarm.getListEvaluation().size());
        assertEquals(3, filledFarm.getListEvaluation().size());
    }

    /**
     * Test of getInformation method, of class Farm.
     */
    @Test
    public void testGetInformation() {
        assertEquals("Granja: nameFarmEmpty - identifierEmpty<br>Granjero: "
                + "nameEmptyFarmer - dniFarmerEmpty<br>Dirección: addressEmpty"
                + "<br>Número de vacas actual: 55<br>Otros datos: <br>Número de "
                + "evaluaciones: 0<br><br>", emptyFarm.getInformation());
        
        assertEquals("Granja: nameFarmFilled - identifierFilled<br>Granjero: "
                + "nameFarmerFilled - dniFarmerFilled<br>Dirección: addressFilled"
                + "<br>Número de vacas actual: 20<br>Otros datos: <br>Número de "
                + "evaluaciones: 3<br><br>", filledFarm.getInformation());
    }

    /**
     * Test of toString method, of class Farm.
     */
    @Test
    public void testToString() {
        assertEquals("IdFarm = 2 FarmIdentifier = identifierEmpty NombreGranja = nameFarmEmpty", emptyFarm.toString());
        assertEquals("IdFarm = 3 FarmIdentifier = identifierFilled NombreGranja = nameFarmFilled", filledFarm.toString());
    }

    @Test
    public void testContructor() {
        String farmName = "farmName";
        String farmIdentifier = "farmIdentifier";
        String address = "address";
        String farmerName = "farmerName";
        String dniFarmer = "dniFarmer";
        String other = "other";
        LinkedList<InformationEvaluation> list = new LinkedList<>();
        IdHandler evaluation = new IdEvaluation(20);
        info = new InformationEvaluation(evaluation, oneId, user, 4.3f, 2f, 2.1f, 2.2f, 2.3f,
                new Date(new GregorianCalendar(2000, 5, 22).getTimeInMillis()), 25);
        list.add(info);
        LinkedList<InformationEvaluation> emptyList=new LinkedList<>();

        /**
         * public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
         * String address, String farmerName, String dniFarmer, int cowNumber,
         * IdHandler idUser, String otherData, LinkedList<InformationEvaluation>
         * list, boolean enabled) {
         */
        Farm test = new Farm(oneId, farmName, farmIdentifier, address, farmerName, dniFarmer,
                20, user, other, list, false);

        assertEquals(oneId, test.getIdFarm());
        assertEquals(farmName, test.getFarmName());
        assertEquals(farmIdentifier, test.getFarmIdentifier());
        assertEquals(address, test.getAddress());
        assertEquals(farmerName, test.getFarmerName());
        assertEquals(dniFarmer, test.getDniFarmer());
        assertTrue(test.getCowNumber() == 20);
        assertEquals(user, test.getIdUser());
        assertEquals(other, test.getOtherData());
        assertFalse(test.isEnabled());
        assertEquals(list, test.getListEvaluation());

        /**
         * public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
         * String address, String farmerName, String dniFarmer, int cowNumber,
         * IdHandler idUser, String otherData, LinkedList<InformationEvaluation>
         * list) { this(idFarm, farmName, farmIdentifier, address, farmerName,
         * dniFarmer, cowNumber, idUser, otherData, list, true); }
         *
         */
        test = new Farm(oneId, farmName, farmIdentifier, address, farmerName, dniFarmer,
                20, user, other, list);

        assertEquals(oneId, test.getIdFarm());
        assertEquals(farmName, test.getFarmName());
        assertEquals(farmIdentifier, test.getFarmIdentifier());
        assertEquals(address, test.getAddress());
        assertEquals(farmerName, test.getFarmerName());
        assertEquals(dniFarmer, test.getDniFarmer());
        assertTrue(test.getCowNumber() == 20);
        assertEquals(user, test.getIdUser());
        assertEquals(other, test.getOtherData());
        assertTrue(test.isEnabled());
        assertEquals(list, test.getListEvaluation());

        /**
         * public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
         * String address, String farmerName, String dniFarmer, int cowNumber,
         * IdHandler idUser) { this(idFarm, farmName, farmIdentifier, address,
         * farmerName, dniFarmer, cowNumber, idUser, "", null, true); }
         */
        test = new Farm(oneId, farmName, farmIdentifier, address, farmerName, dniFarmer,
                20, user);

        assertEquals(oneId, test.getIdFarm());
        assertEquals(farmName, test.getFarmName());
        assertEquals(farmIdentifier, test.getFarmIdentifier());
        assertEquals(address, test.getAddress());
        assertEquals(farmerName, test.getFarmerName());
        assertEquals(dniFarmer, test.getDniFarmer());
        assertTrue(test.getCowNumber() == 20);
        assertEquals(user, test.getIdUser());
        assertEquals("", test.getOtherData());
        assertTrue(test.isEnabled());
        assertEquals(emptyList, test.getListEvaluation());

        /**
         * public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
         * String address, String farmerName, String dniFarmer, int cowNumber,
         * IdHandler idUser, String otherData) { this(idFarm, farmName,
         * farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser,
         * otherData, null, true); }
         */
        test = new Farm(oneId, farmName, farmIdentifier, address, farmerName, dniFarmer,
                20, user, other);

        assertEquals(oneId, test.getIdFarm());
        assertEquals(farmName, test.getFarmName());
        assertEquals(farmIdentifier, test.getFarmIdentifier());
        assertEquals(address, test.getAddress());
        assertEquals(farmerName, test.getFarmerName());
        assertEquals(dniFarmer, test.getDniFarmer());
        assertTrue(test.getCowNumber() == 20);
        assertEquals(user, test.getIdUser());
        assertEquals(other, test.getOtherData());
        assertTrue(test.isEnabled());
        assertEquals(emptyList, test.getListEvaluation());

        /**
         * public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
         * String address, String farmerName, String dniFarmer, int cowNumber,
         * IdHandler idUser, LinkedList<InformationEvaluation> list) {
         * this(idFarm, farmName, address, farmerName, farmIdentifier,
         * dniFarmer, cowNumber, idUser, "", list, true); }
         */
        test = new Farm(oneId, farmName, farmIdentifier, address, farmerName, dniFarmer,
                20, user, list);

        assertEquals(oneId, test.getIdFarm());
        assertEquals(farmName, test.getFarmName());
        assertEquals(farmIdentifier, test.getFarmIdentifier());
        assertEquals(address, test.getAddress());
        assertEquals(farmerName, test.getFarmerName());
        assertEquals(dniFarmer, test.getDniFarmer());
        assertTrue(test.getCowNumber() == 20);
        assertEquals(user, test.getIdUser());
        assertEquals("", test.getOtherData());
        assertTrue(test.isEnabled());
        assertEquals(list, test.getListEvaluation());

        /**
         * public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
         * String address, String farmerName, String dniFarmer, int cowNumber,
         * IdHandler idUser, boolean enabled) { this(idFarm, farmName,
         * farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser,
         * "", null, enabled); }
         */
        test = new Farm(oneId, farmName, farmIdentifier, address, farmerName, dniFarmer,
                20, user, false);

        assertEquals(oneId, test.getIdFarm());
        assertEquals(farmName, test.getFarmName());
        assertEquals(farmIdentifier, test.getFarmIdentifier());
        assertEquals(address, test.getAddress());
        assertEquals(farmerName, test.getFarmerName());
        assertEquals(dniFarmer, test.getDniFarmer());
        assertTrue(test.getCowNumber() == 20);
        assertEquals(user, test.getIdUser());
        assertEquals("", test.getOtherData());
        assertFalse(test.isEnabled());
        assertEquals(emptyList, test.getListEvaluation());

        /**
         * public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
         * String address, String farmerName, String dniFarmer, int cowNumber,
         * IdHandler idUser, String otherData, boolean enabled) { this(idFarm,
         * farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber,
         * idUser, otherData, null, enabled); }
         */
        test = new Farm(oneId, farmName, farmIdentifier, address, farmerName, dniFarmer,
                20, user, other, false);

        assertEquals(oneId, test.getIdFarm());
        assertEquals(farmName, test.getFarmName());
        assertEquals(farmIdentifier, test.getFarmIdentifier());
        assertEquals(address, test.getAddress());
        assertEquals(farmerName, test.getFarmerName());
        assertEquals(dniFarmer, test.getDniFarmer());
        assertTrue(test.getCowNumber() == 20);
        assertEquals(user, test.getIdUser());
        assertEquals(other, test.getOtherData());
        assertFalse(test.isEnabled());
        assertEquals(emptyList, test.getListEvaluation());

        /**
         * public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
         * String address, String farmerName, String dniFarmer, int cowNumber,
         * IdHandler idUser, LinkedList<InformationEvaluation> list, boolean
         * enabled) { this(idFarm, farmName, farmIdentifier, address,
         * farmerName, dniFarmer, cowNumber, idUser, "", list, enabled); }
         */
        test = new Farm(oneId, farmName, farmIdentifier, address, farmerName, dniFarmer,
                20, user, list, false);

        assertEquals(oneId, test.getIdFarm());
        assertEquals(farmName, test.getFarmName());
        assertEquals(farmIdentifier, test.getFarmIdentifier());
        assertEquals(address, test.getAddress());
        assertEquals(farmerName, test.getFarmerName());
        assertEquals(dniFarmer, test.getDniFarmer());
        assertTrue(test.getCowNumber() == 20);
        assertEquals(user, test.getIdUser());
        assertEquals("", test.getOtherData());
        assertFalse(test.isEnabled());
        assertEquals(list, test.getListEvaluation());
    }

}
