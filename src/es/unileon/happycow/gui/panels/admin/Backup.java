package es.unileon.happycow.gui.panels.admin;

import es.unileon.happycow.controller.admin.BackupController;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author dorian
 */
public class Backup extends javax.swing.JPanel implements InterfaceTabs{
    private BackupController controller;
    private File export;
    private File importBackup;
    private final FileNameExtensionFilter filter;
    private JFileChooser fileChooser;

    /**
     * Creates new form Backup
     */
    public Backup() {
        this(null);
    }

    public Backup(BackupController controller) {
        initComponents();
        filter = new FileNameExtensionFilter("Ficheros backup", "backup");
        this.controller=controller;
    }

    private void initComponents() {
        createComponents();
        configureComponents();
        addEvents();
        addLayout();
    }
    
    private void createComponents(){
        textExport = new javax.swing.JTextField();
        textImport = new javax.swing.JTextField();
        buttonBackup = new javax.swing.JButton("Realizar backup");
        buttonSelectExport=new javax.swing.JButton("Seleccionar carpeta");
        buttonSelectImport=new javax.swing.JButton("Seleccionar fichero");
        buttonImport = new javax.swing.JButton("Restaurar base de datos");
        lblTitleRecover = new javax.swing.JLabel("Recuperar backup");
        lblTitleBackup = new javax.swing.JLabel("Realizar backup");
        lblInfor = new javax.swing.JLabel();
        buttonHelp = new javax.swing.JButton(new javax.swing.ImageIcon
                        (getClass().getResource("/images/help.png")));
    }
    
    private void configureComponents(){
        setLayout(new java.awt.GridBagLayout());
        lblTitleBackup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitleRecover.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonHelp.setBorderPainted(false);
        buttonHelp.setContentAreaFilled(false);
        buttonHelp.setFocusPainted(false);
    }
    
    private void addEvents(){
//        JFrameApplication.getInstance().getHelp().setHelpOnButton(buttonHelp, "AyudaAdminBackup");
//        JFrameApplication.getInstance().getHelp().setHelp(this, "AyudaAdminBackup");
        
        buttonSelectExport.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectExport();
            }
        });
        
        buttonSelectImport.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectImport();
            }
        });
        
        buttonBackup.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBackupActionPerformed();
            }
        });
        
        buttonImport.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImportActionPerformed();
            }
        });
        
    }
    
    private void addLayout(){
        java.awt.GridBagConstraints gridBagConstraints;
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 16);
        add(textExport, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth=2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 16);
        add(textImport, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 16);
        add(buttonBackup, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 16);
        add(buttonSelectExport, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 16);
        add(buttonSelectImport, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(5, 16, 5, 16);
        add(buttonImport, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHEAST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(buttonHelp, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblTitleBackup, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.4;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblTitleRecover, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.PAGE_END;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(lblInfor, gridBagConstraints);
    }
    
    public void setController(BackupController controller){
        this.controller=controller;
    }
    
    public void clearInfo(){
        textExport.setText("");
        textImport.setText("");
        lblInfor.setText("");
    }
    
    public void setInfo(String text){
        lblInfor.setText(text);
    }

    private void buttonBackupActionPerformed() {
        if(controller!=null){
            controller.export(export);
        }
        
    }
   

    private void buttonImportActionPerformed() {
        if(controller!=null){
            controller.importBackup(importBackup);
        }
    }

    private void selectExport() {
        buttonBackup.requestFocus();
        if(fileChooser==null){
            fileChooser = new JFileChooser();
            fileChooser.setFileFilter(filter);
        }
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int seleccion = fileChooser.showSaveDialog(this);
        
        if(seleccion==JFileChooser.APPROVE_OPTION){
            export=fileChooser.getSelectedFile();
            textExport.setText(export.getAbsolutePath());
        }
    }

    private void selectImport() {
        buttonImport.requestFocus();
        if(fileChooser==null){
            fileChooser = new JFileChooser();
            fileChooser.setFileFilter(filter);
        }
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = fileChooser.showOpenDialog(this);
        
        if(seleccion==JFileChooser.APPROVE_OPTION){
            importBackup=fileChooser.getSelectedFile();
            textImport.setText(importBackup.getAbsolutePath());
        }
    }


    private javax.swing.JButton buttonBackup;
    private javax.swing.JButton buttonHelp;
    private javax.swing.JButton buttonSelectImport;
    private javax.swing.JButton buttonSelectExport;
    private javax.swing.JButton buttonImport;
    private javax.swing.JLabel lblInfor;
    private javax.swing.JLabel lblTitleBackup;
    private javax.swing.JLabel lblTitleRecover;
    private javax.swing.JTextField textExport;
    private javax.swing.JTextField textImport;

    @Override
    public void updateInformation() {
       
    }

}
