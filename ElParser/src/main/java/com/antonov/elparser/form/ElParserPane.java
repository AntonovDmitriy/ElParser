/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antonov.elparser.form;

import com.antonov.elparser.impl.domain.ElParser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Antonov
 */
public class ElParserPane extends javax.swing.JFrame {

    /**
     * Creates new form ElParserPane
     */
    private final Logger logger = LoggerFactory.getLogger(getClass().getCanonicalName());

    public ElParserPane() {
        initComponents();
        init();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        pojo = new com.antonov.elparser.pojo.PElParserParams();
        jLabel1 = new javax.swing.JLabel();
        edPathFile = new javax.swing.JTextField();
        btChoiceFile = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        btStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(546, 119));
        setPreferredSize(new java.awt.Dimension(546, 119));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jLabel1.setText("Файл");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        getContentPane().add(jLabel1, gridBagConstraints);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, pojo, org.jdesktop.beansbinding.ELProperty.create("${FILE_EXCEL_PATH}"), edPathFile, org.jdesktop.beansbinding.BeanProperty.create("text_ON_ACTION_OR_FOCUS_LOST"));
        bindingGroup.addBinding(binding);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        getContentPane().add(edPathFile, gridBagConstraints);

        btChoiceFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/list.png"))); // NOI18N
        btChoiceFile.setMargin(new java.awt.Insets(0, 0, 0, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.insets = new java.awt.Insets(5, 0, 0, 5);
        getContentPane().add(btChoiceFile, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(progressBar, gridBagConstraints);

        btStart.setText("СТАРТ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 0, 0);
        getContentPane().add(btStart, gridBagConstraints);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ElParserPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ElParserPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ElParserPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ElParserPane.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ElParserPane().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btChoiceFile;
    private javax.swing.JButton btStart;
    private javax.swing.JTextField edPathFile;
    private javax.swing.JLabel jLabel1;
    private com.antonov.elparser.pojo.PElParserParams pojo;
    private javax.swing.JProgressBar progressBar;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void init() {

        setTitle("ElParser 1.0.0");
        setLocationRelativeTo(null);
        initButtons();
    }

    private void initButtons() {

        btChoiceFile.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //Create a file chooser
                try {
                    final JFileChooser fc = new JFileChooser();
                    fc.setFileFilter(new OpenFileFilter("xlsx", "Файл Excel *.xlsx"));
                    int returnVal = fc.showOpenDialog(ElParserPane.this);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        pojo.setFILE_EXCEL_PATH(file.getCanonicalPath());
                        //This is where a real application would open the file.
                    }
                } catch (Throwable ex) {
                    String message = "При выборе файла произошла ошибка";
                    logger.error(message, ex);
                    handleException(new Exception(message, ex));
                }
            }
        }
        );

        btStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (pojo.getFILE_EXCEL_PATH() != null && pojo.getFILE_EXCEL_PATH().equalsIgnoreCase("AKIRARA")) {
                    AkiraraFrame.startCat();
                    return;
                }

                if (pojo.getFILE_EXCEL_PATH() != null && new File(pojo.getFILE_EXCEL_PATH()).exists()) {

                    try {

                        progressBar.setIndeterminate(true);
                        switchControls(false);

                        Thread startThread = new Thread(new Runnable() {

                            @Override
                            public void run() {
                                try {

                                    ElParser parser = new ElParser(pojo);
                                    parser.doAction();
                                    showFinalMessage();
                                } catch (Exception ex) {
                                    progressBar.setIndeterminate(false);
                                    handleException(ex);
                                } finally {
                                    progressBar.setIndeterminate(false);
                                    switchControls(true);

                                }
                            }
                        ;
                        });
                    startThread.start();

                    } catch (Throwable ex) {
                        handleException(new Exception(ex));
                    }
                } else {
                    String message = "Господа, вы не выбрали файл или он не существует.";
                    handleException(new Exception(message));
                }
            }
        });
    }

    private void switchControls(boolean flag) {
        btStart.setEnabled(flag);
        btChoiceFile.setEnabled(flag);
    }

    private void handleException(Throwable ex) {

        JOptionPane.showMessageDialog(this, ex.getMessage() + ". Детальную информацию смотрите в лог-файле", "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    private void showFinalMessage() {

        JOptionPane.showMessageDialog(this, "Процесса обработки данных завершен успешно");
    }
}
