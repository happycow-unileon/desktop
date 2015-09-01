package es.unileon.happycow.model;

import es.unileon.happycow.handler.IdHandler;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author dorian
 */
public class Farm implements Serializable{

    private IdHandler idFarm;
    private String farmName;
    private String farmIdentifier;
    private String address;
    private String farmerName;
    private String dniFarmer;
    private int cowNumber;
    private IdHandler idUser;
    private String otherData;
    private boolean enabled;
    private ListEvaluations list;

    

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, String otherData, ListEvaluations list, boolean enabled) {

        this.idFarm = idFarm;
        this.farmName = farmName;
        this.address = address;
        this.farmerName = farmerName;
        this.dniFarmer = dniFarmer;
        this.cowNumber = cowNumber;
        this.idUser = idUser;
        this.list = list;
        this.otherData = otherData;
        this.farmIdentifier = farmIdentifier;
        this.enabled = enabled;
    }

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, String otherData, ListEvaluations list) {
        this(idFarm, farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser, otherData, list, true);
    }

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser) {
        this(idFarm, farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser, "", new ListEvaluations(idFarm), true);
    }

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, String otherData) {
        this(idFarm, farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser, otherData, new ListEvaluations(idFarm), true);
    }

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, ListEvaluations list) {
        this(idFarm, farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser, "", list, true);
    }

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, boolean enabled) {
        this(idFarm, farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser, "", new ListEvaluations(idFarm), enabled);
    }

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, String otherData, boolean enabled) {
        this(idFarm, farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser, otherData, new ListEvaluations(idFarm), enabled);
    }

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, ListEvaluations list, boolean enabled) {
        this(idFarm, farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser, "", list, enabled);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getAddress() {
        return address;
    }

    public String getOtherData() {
        return otherData;
    }

    public int getCowNumber() {
        return cowNumber;
    }

    public String getFarmIdentifier() {
        return farmIdentifier;
    }

    public String getDniFarmer() {
        return dniFarmer;
    }

    public String getFarmName() {
        return farmName;
    }

    public String getFarmerName() {
        return farmerName;
    }

    public IdHandler getIdFarm() {
        return idFarm;
    }

    public String getNameUser() {
        return idUser.getValue();
    }

    public IdHandler getIdUser() {
        return idUser;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCowNumber(int cowNumber) {
        this.cowNumber = cowNumber;
    }

    public void setDniFarmer(String dniFarmer) {
        this.dniFarmer = dniFarmer;
    }

    public void setFarmName(String farmName) {
        this.farmName = farmName;
    }

    public void setFarmerName(String farmerName) {
        this.farmerName = farmerName;
    }

    public void setOtherData(String otherData) {
        this.otherData = otherData;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void addEvaluation(InformationEvaluation evaluation) {
        list.addEvaluation(evaluation);
    }

    public void removeEvaluation(IdHandler evaluation) {
        list.removeEvaluation(evaluation);
    }

    public InformationEvaluation getEvaluation(IdHandler evaluation) {
        return list.getEvaluation(evaluation);
    }

    public LinkedList<InformationEvaluation> getListEvaluation() {
        return list.getListEvaluation();
    }

    public String getInformation() {
        return "Granja: " + this.getFarmName()
                + " - " + this.getFarmIdentifier() + "<br>"
                + "Granjero: " + this.getFarmerName() + " - "
                + this.getDniFarmer() + "<br>"
                + "Dirección: " + this.getAddress() + "<br>"
                + "Número de vacas actual: " + this.getCowNumber() + "<br>"
                + "Otros datos: " + this.getOtherData() + "<br>"
                + "Número de evaluaciones: " + list.size() + "<br>"
                + "<br>";
    }

    @Override
    public String toString() {
        return "IdFarm = " + idFarm.getValue()+ " FarmIdentifier = " + farmIdentifier + " NombreGranja = " + farmName;
    }
    
    
    private void readObject(java.io.ObjectInputStream stream)
            throws IOException, ClassNotFoundException {
        idFarm=(IdHandler)stream.readObject();
        farmName=(String)stream.readObject();
        farmIdentifier=(String)stream.readObject();
        address=(String)stream.readObject();
        farmerName=(String)stream.readObject();
        dniFarmer=(String)stream.readObject();
        cowNumber=stream.readInt();
        idUser=(IdHandler)stream.readObject();
        otherData=(String)stream.readObject();
        enabled=stream.readBoolean();
    }

    private void writeObject(java.io.ObjectOutputStream stream)
            throws IOException {
        stream.writeObject(idFarm);
        stream.writeObject(farmName);
        stream.writeObject(farmIdentifier);
        stream.writeObject(address);
        stream.writeObject(farmerName);
        stream.writeObject(dniFarmer);
        stream.writeInt(cowNumber);
        stream.writeObject(idUser);
        stream.writeObject(otherData);
        stream.writeBoolean(enabled);
    }
}
