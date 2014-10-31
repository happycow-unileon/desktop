package es.unileon.happycow.controller;

import es.unileon.happycow.gui.JFrameApplication;
import es.unileon.happycow.gui.PanelReport;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.procedures.Report;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import javax.swing.JOptionPane;

/**
 *
 * @author dorian
 */
public class ReportController implements InterfaceController, Printable{
    private PanelReport panel;
    private String content;
    private IdHandler idFarm;

    public ReportController(PanelReport panel, String content, IdHandler idFarm) {
        this.panel = panel;
        this.content = content;
        this.idFarm=idFarm;
        panel.getEditor().setText(content);
    }

    public ReportController(PanelReport panel, Report report, IdHandler idFarm) {
        this(panel, report.toString(), idFarm);
    }
    
    public void print() {
        JOptionPane.showMessageDialog(JFrameApplication.getInstance(), 
                "Paciencia, funcionalidad todavía no implementada", 
                "No implementado", JOptionPane.INFORMATION_MESSAGE);
//        PrinterJob job = PrinterJob.getPrinterJob();
//        job.setPrintable(panel.getEditor());
//        // Diálogo para elegir el formato de impresión
//        PageFormat pageFormat = new PageFormat();
//        pageFormat=job.pageDialog(pageFormat);
//
//        // Diálogo para confirmar impresion.
//        // Devuelve true si el usuario decide imprimir.
//        if (job.printDialog())
//           try {
//               job.print();
//        } catch (PrinterException ex) {
//            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
    public void listFarms(){
        JFrameController.getInstance().seeListFarm();
    }
    
    public void farm(){
        JFrameController.getInstance().manageFarm(idFarm);
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        switch (pageIndex){
            case 0:
                return PAGE_EXISTS;
            default:
                return NO_SUCH_PAGE;
        }
    }
    
}
