package es.unileon.happycow.controller;

import es.unileon.happycow.application.Parameters;
import es.unileon.happycow.database.Database;
import es.unileon.happycow.gui.PanelReport;
import es.unileon.happycow.handler.IdEvaluation;
import es.unileon.happycow.handler.IdFarm;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.evaluation.EvaluationCriterionModel;
import es.unileon.happycow.model.evaluation.IEvaluationModel;
import es.unileon.happycow.procedures.Printer;
import es.unileon.happycow.procedures.Report;
import es.unileon.happycow.strategy.AverageEvaluation;
import es.unileon.happycow.strategy.EvaluationAlgorithm;
import es.unileon.happycow.windows.Window;
import javax.print.PrintService;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public class ReportController extends Controller {

    /**
     * Panel concreto
     */
    private final PanelReport panelReport;

    private IdHandler farm;

    public ReportController(PanelReport report) {
        this.panelReport = report;
    }

    public void imprimir() {
        Printer print = new Printer();
        if (print.hasServices()) {
            PrintService service = print.showPrintDialog();
            if (service != null) {
                print.printJEditorPane(panelReport.getPanelReport(), service);
            }
        }else{
            JOptionPane.showMessageDialog(null, 
                "No tiene ninguna impresora predeterminada", 
                "Impresoras", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void listFarms() {
        controller.clearParameters();
        controller.setState(Window.LIST_FARMS);
    }

    public void farm() {
        controller.clearParameters();
        controller.addParameter("id", farm.toString());
        controller.setState(Window.MANAGE_FARM);
    }

    @Override
    public void onResume(Parameters parameters) {
        //guardo la granja
        farm = new IdFarm(parameters.getString("idFarm"));
        //saco el identificador de la evaluación para el reporte
        IdHandler evaluation = new IdEvaluation(parameters.getString("idEvaluation"));
        IEvaluationModel model = new EvaluationCriterionModel(Database.getInstance().getEvaluation(evaluation));
        //obtengo la forma de calculo preferida (TODO mirar en opciones del usuario)
        EvaluationAlgorithm algorithm = new AverageEvaluation();
        //creo el reporte
        Report rep = new Report(algorithm, model);
        //escribo el reporte
        panelReport.setReport(rep.toString());
    }
}
