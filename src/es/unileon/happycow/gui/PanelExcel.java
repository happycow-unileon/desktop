package es.unileon.happycow.gui;

import es.unileon.happycow.controller.ExcelController;
import es.unileon.happycow.gui.admin.InterfaceTabs;
import es.unileon.happycow.handler.IdHandler;
import es.unileon.happycow.help.HelpSystem;
import es.unileon.happycow.help.HelpTheme;
import es.unileon.happycow.model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import org.japura.gui.model.DefaultListCheckModel;

/**
 * Panel del login del programa
 *
 * @author dorian
 */
public class PanelExcel extends javax.swing.JPanel implements InterfaceTabs {

    /**
     * Controlador del panel
     */
    private ExcelController controller;

    /**
     * Creates new form PanelLogin
     */
    public PanelExcel() {
        //inicializar componentes
        initComponents();
    }

    /**
     * establece un controlador al panel
     *
     * @param controller un LoginController como controlador
     */
    public void setController(ExcelController controller) {
        this.controller = controller;
        panel.setController(controller);
    }

    public void setComboUsers(LinkedList<User> list) {
        comboUsers.removeAllItems();
        for (User user : list) {
            comboUsers.addItem(user.getName());
        }
    }

    public void hideComboUser(boolean hide) {
        comboUsers.setVisible(!hide);
    }

    public String getUser() {
        String result = "";
        int selected = comboUsers.getSelectedIndex();
        if (selected != -1) {
            result = (String) comboUsers.getSelectedItem();
        }
        return result;
    }

    public void changeUser(IdHandler user) {
        comboUsers.setSelectedItem(user.getValue());
    }

    public DefaultListCheckModel getModelListFarm() {
        return panel.getModelListFarm();
    }

    public DefaultListCheckModel getModelListEvaluations() {
        return panel.getModelListEvaluations();
    }

    public String getFarm() {
        return panel.getFarm();
    }

    public void changeFarms(DefaultListCheckModel farms) {
        panel.changeFarms(farms);
    }

    public void changeEvaluations(DefaultListCheckModel evaluations) {
        panel.changeEvaluations(evaluations);
    }

    /**
     * Inicializa los componentes
     */
    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }

    private void createComponents() {
        comboUsers = new javax.swing.JComboBox();

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
        buttonExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (controller != null) {
                    controller.export();
                }
            }
        });

        comboUsers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.changeUser();
            }
        });

        buttonHelp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (comboUsers.isVisible()) {
                    HelpSystem.getInstance().seeHelp(HelpTheme.ExcelAdmin);
                } else {
                    HelpSystem.getInstance().seeHelp(HelpTheme.Excel);
                }
            }
        });

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

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LAST_LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(15, 15, 5, 15);
        add(comboUsers, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonHelp, gridBagConstraints);
    }

    private javax.swing.JButton buttonExport;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JComboBox comboUsers;
    private PanelUserExcel panel;

    @Override
    public void updateInformation() {
        controller.update();
    }
}
