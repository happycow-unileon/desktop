/*
 * 
 */
package es.unileon.happycow.gui.evaluation2.file;

import es.unileon.happycow.gui.evaluation2.EvaluationController;
import es.unileon.happycow.handler.IdHandler;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author dorian
 */
public class PanelFileList extends JPanel implements IFileButton {
    private EvaluationController controller;
    private LinkedList<PanelFile> filePanels;

    public PanelFileList() {
        super(null);
        initComponents();
        
        filePanels=new LinkedList<>();
    }

    public void addListFile(List<String> files) {
        for (String file : files) {
            addFile(file);
        }
    }

    public void addFile(String file) {
        PanelFile panel = new PanelFile();
        panel.setFileName(file);
        panel.setController(this);

        filePanels.add(panel);
        list.add(panel);
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
                removed=true;
            }
        }
    }
    
    @Override
    public void download(IdHandler id) {
        controller.downloadFile(id);
    }

    @Override
    public void remove(IdHandler id) {
        //remove in model
        controller.removeFile(id);
        //remove in gui
        removeFile(id.toString());
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
        list.setLayout(new GridLayout(0, 1));
        scrollList.setViewportView(list);
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
