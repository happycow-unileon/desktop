/*
 * 
 */
package es.unileon.happycow.gui.evaluation.criterion;

import es.unileon.happycow.controller.evaluation.IEvaluationController;
import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.japura.gui.TitlePanel;

/**
 *
 * @author dorian
 */
public class PanelFileList extends TitlePanel {
    private IEvaluationController controller;
    private LinkedList<PanelFile> filePanels;

    public PanelFileList(JComponent[] components) {
        super("", components);
        initComponents();
        
        filePanels=new LinkedList<>();
    }

    public void setController(IEvaluationController controller) {
        this.controller = controller;
    }

    public void addFileList(List<String> files) {
        for (String file : files) {
            addFile(file);
        }
    }
    
    public void removeFileList(){
        filePanels.clear();
        list.removeAll();
    }
    
    public void addComponentTitle(JComponent component){
        this.add(component);
    }

    public void addFile(String file) {
        PanelFile panel = new PanelFile();
        panel.setFileName(file);
        panel.setController(controller);

        filePanels.add(panel);
        list.add(panel);
        list.revalidate();
    }

    public void removeFile(String file) {
        boolean removed=false;
        for (Iterator<PanelFile> iterator = filePanels.iterator(); iterator.hasNext() && !removed;) {
            PanelFile next = iterator.next();
            if(next.getFileName().compareTo(file)==0){
                //remove the list from panel
                list.remove(next);
                //remove from list
                iterator.remove();
                
                
                this.revalidate();
                removed=true;
                
            }
        }
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents() {
        scrollList = new JScrollPane();
        list=new JPanel();
    }

    private void configureComponents() {
        list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
        scrollList.setViewportView(list);
        scrollList.setBorder(null);
        scrollList.setViewportBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
    }

    private void addEvents() {

    }

    private void addLayout() {
        setLayout(new BorderLayout());
        add(scrollList, BorderLayout.CENTER);
    }

    private JScrollPane scrollList;
    private JPanel list;

}
