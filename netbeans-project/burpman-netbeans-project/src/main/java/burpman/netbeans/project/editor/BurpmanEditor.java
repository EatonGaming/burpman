/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package burpman.netbeans.project.editor;

/**
 *
 * @author mike.eaton
 */
public class BurpmanEditor extends javax.swing.JPanel {

    /**
     * Creates new form BurpmanEditor
     */
    public BurpmanEditor() {
        initComponents();
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

        contentPanel = new javax.swing.JPanel();
        methodComboBox = new javax.swing.JComboBox<>();
        pathTextField = new javax.swing.JTextField();
        requestAttributesTabbedPane = new javax.swing.JTabbedPane();
        queryParamsTab = new burpman.netbeans.project.editor.QueryParamsTab();

        setLayout(new java.awt.GridBagLayout());

        contentPanel.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        contentPanel.add(methodComboBox, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        contentPanel.add(pathTextField, gridBagConstraints);

        requestAttributesTabbedPane.addTab("Query Params", queryParamsTab);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 0, 0, 0);
        contentPanel.add(requestAttributesTabbedPane, gridBagConstraints);
        requestAttributesTabbedPane.getAccessibleContext().setAccessibleName("Request Metadata tabs");

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        add(contentPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JComboBox<String> methodComboBox;
    private javax.swing.JTextField pathTextField;
    private burpman.netbeans.project.editor.QueryParamsTab queryParamsTab;
    private javax.swing.JTabbedPane requestAttributesTabbedPane;
    // End of variables declaration//GEN-END:variables
}
