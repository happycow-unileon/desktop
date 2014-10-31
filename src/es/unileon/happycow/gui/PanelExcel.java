package es.unileon.happycow.gui;

import es.unileon.happycow.controller.ExcelController;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import org.japura.gui.model.DefaultListCheckModel;

/**
 *
 * @author dorian
 */
public class PanelExcel extends javax.swing.JPanel implements InterfaceExcelPanel {

    private ExcelController controller;
    private final boolean isAdmin;
    private String user;

    /**
     * Creates new form PanelExcel
     *
     * @param users
     */
    public PanelExcel(LinkedList<User> users) {
        if (users.size() > 1) {
            isAdmin = true;
        } else {
            isAdmin = false;
            user = users.get(0).getName();
        }
        initComponents(users);
    }

    private void initComponents(LinkedList<User> users) {
        createComponents(users);
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents(LinkedList<User> users) {
        if (isAdmin) {
            comboUsers = new javax.swing.JComboBox();
            for (User us : users) {
                comboUsers.addItem(us.getName());
            }
        }

        buttonExport = new javax.swing.JButton("Exportar");
        panel = new PanelUserExcel();
        buttonHelp = new javax.swing.JButton(
                new javax.swing.ImageIcon(
                        getClass().getResource("/images/help.png")));
    }

    private void configureComponents() {
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);

        setLayout(new java.awt.GridBagLayout());
    }

    private void addEvents() {
        JFrameApplication.getInstance().getHelp().setHelpOnButton(buttonHelp, "AyudaListaGranjas");
        JFrameApplication.getInstance().getHelp().setHelp(this, "AyudaListaGranjas");

        buttonExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.export();
                }
            }
        });
        
        if(isAdmin){
            comboUsers.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.changeUser();
                }
            });
        }
    }

    private void addLayout() {
        java.awt.GridBagConstraints gridBagConstraints;

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonExport, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(panel, gridBagConstraints);

        if (isAdmin) {
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.insets = new java.awt.Insets(15, 15, 5, 15);
            add(comboUsers, gridBagConstraints);
        }

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonHelp, gridBagConstraints);
    }

    @Override
    public String getUser() {
        String result = "";
        if (isAdmin) {
            int selected = comboUsers.getSelectedIndex();
            if (selected != -1) {
                result = (String) comboUsers.getSelectedItem();
            }
        } else {
            result = user;
        }
            return result;
    }

    @Override
    public void changeUser(IdHandler user) {
        if (isAdmin) {
            comboUsers.setSelectedItem(user.toString());
        }
    }

    @Override
    public DefaultListCheckModel getModelListFarm() {
        return panel.getModelListFarm();
    }

    @Override
    public DefaultListCheckModel getModelListEvaluations() {
        return panel.getModelListEvaluations();
    }

    @Override
    public void setController(ExcelController controller) {
        this.controller = controller;
        panel.setController(controller);
    }

    @Override
    public void updateInformation() {
        //??????????????????????
    }

    @Override
    public String getFarm() {
        return panel.getFarm();
    }

    @Override
    public void changeFarms(DefaultListCheckModel farms) {
        panel.changeFarms(farms);
    }

    @Override
    public void changeEvaluations(DefaultListCheckModel evaluations) {
        panel.changeEvaluations(evaluations);
    }

    private javax.swing.JButton buttonExport;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JComboBox comboUsers;
    private PanelUserExcel panel;
}
