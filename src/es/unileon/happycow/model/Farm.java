package es.unileon.happycow.model;


import es.unileon.happycow.handler.IdHandler;
import java.io.Serializable;
import java.util.Iterator;
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
    private LinkedList<InformationEvaluation> list;
    
    private boolean listLoaded;

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, String otherData, LinkedList<InformationEvaluation> list, boolean enabled) {
        this.idFarm = idFarm;
        this.farmName = farmName;
        this.address = address;
        this.farmerName = farmerName;
        this.dniFarmer = dniFarmer;
        this.cowNumber = cowNumber;
        this.idUser = idUser;
        if(list==null){
            listLoaded=false;
            list=new LinkedList<>();
        }else{
            listLoaded=true;
        }
        this.list = list;
        this.otherData = otherData;
        this.farmIdentifier = farmIdentifier;
        this.enabled=enabled;
    }
    
    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, String otherData, LinkedList<InformationEvaluation> list) {
        this(idFarm, farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser, otherData, list, true);
    }

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser) {
        this(idFarm, farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser, "", null, true);
    }

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, String otherData) {
        this(idFarm, farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser, otherData, null, true);
    }

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, LinkedList<InformationEvaluation> list) {
        this(idFarm, farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser, "", list, true);
    }
    
    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, boolean enabled) {
        this(idFarm, farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser, "", null, enabled);
    }

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, String otherData, boolean enabled) {
        this(idFarm, farmName, farmIdentifier, address, farmerName, dniFarmer, cowNumber, idUser, otherData, null, enabled);
    }

    public Farm(IdHandler idFarm, String farmName, String farmIdentifier,
            String address, String farmerName, String dniFarmer, int cowNumber,
            IdHandler idUser, LinkedList<InformationEvaluation> list, boolean enabled) {
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
        return idUser.toString();
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
        if(!listLoaded){
            getListEvaluation();
        }
        if (!getListEvaluation().contains(evaluation)) {
            list.add(evaluation);
        }
    }

    public void removeEvaluation(IdHandler evaluation) {
        InformationEvaluation target = null;
        if (!listLoaded) {
            getListEvaluation();
        }
        for (Iterator<InformationEvaluation> it = list.iterator(); it.hasNext() && target == null;) {
            InformationEvaluation eval = it.next();
            if (eval.getIdEvaluation().compareTo(evaluation) == 0) {
                target = eval;
            }
        }
        list.remove(target);
    }

    public InformationEvaluation getEvaluation(IdHandler evaluation) {
        int target = -1;
        if (!listLoaded) {
            getListEvaluation();
        }
        int index = 0;
        for (Iterator<InformationEvaluation> it = list.iterator(); it.hasNext() && target <= -1;) {
            InformationEvaluation eval = it.next();
            if (eval.getIdEvaluation().compareTo(evaluation) == 0) {
                target = index;
            }
            index++;
        }
        if (target >= 0) {
            return list.get(target);
        } else {
            return null;
        }
    }

    public LinkedList<InformationEvaluation> getListEvaluation() {
        if (!listLoaded) {
            //TODO
           //list = Database.getInstance().getListEvaluations(idFarm);
        }
        listLoaded=true;
        return list;
    }

    public String getInformation() {
        return "Granja: " + this.getFarmName()
                + " - " + this.getFarmIdentifier() + "<br>"
                + "Granjero: " + this.getFarmerName() + " - "
                + this.getDniFarmer() + "<br>"
                + "Dirección: " + this.getAddress() + "<br>"
                + "Número de vacas actual: " + this.getCowNumber() + "<br>"
                + "Otros datos: " + this.getOtherData() + "<br>"
                + "Número de evaluaciones: " + this.getListEvaluation().size() + "<br>"
                + "<br>";
    }
    
    @Override
    public String toString(){
        return "IdFarm = "+idFarm.toString() + " FarmIdentifier = "+ farmIdentifier +  " NombreGranja = "+farmName;
    }
}
