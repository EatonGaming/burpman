package extension.editor.tabs.queryparams;

import extension.logger.LogLevel;
import extension.logger.Logger;

import javax.swing.*;
import java.awt.event.*;
import java.util.function.BiConsumer;

import static extension.logger.Logger.log;

public class AddQueryParamsPanel extends JPanel {
    private final BiConsumer<String, String> addQueryParameterConsumer;

    public AddQueryParamsPanel(BiConsumer<String, String> addQueryParameterConsumer) {
        this.addQueryParameterConsumer = addQueryParameterConsumer;

        initComponents();

        setupComponentListeners();
    }

    private void setupComponentListeners() {
        addButton.addActionListener(e -> addQueryParameter());

        EnterKeyListener enterKeyListener = new EnterKeyListener(this::addQueryParameter);
        nameTextField.addKeyListener(enterKeyListener);
        valueTextField.addKeyListener(enterKeyListener);

        nameTextField.addFocusListener(new SelectTextFocusListener(nameTextField));
        valueTextField.addFocusListener(new SelectTextFocusListener(valueTextField));
    }

    private void addQueryParameter() {
        this.addQueryParameterConsumer.accept(nameTextField.getText(), valueTextField.getText());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        valueLabel = new javax.swing.JLabel();
        valueTextField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();

        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWidths = new int[] {0, 5, 0, 5, 0};
        layout.rowHeights = new int[] {0, 0, 0};
        setLayout(layout);

        nameLabel.setText("Name");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        add(nameLabel, gridBagConstraints);

        nameTextField.setFocusCycleRoot(true);
        nameTextField.setNextFocusableComponent(valueTextField);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        add(nameTextField, gridBagConstraints);

        valueLabel.setText("Value");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        add(valueLabel, gridBagConstraints);

        valueTextField.setNextFocusableComponent(addButton);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        add(valueTextField, gridBagConstraints);

        addButton.setText("Add");
        addButton.setNextFocusableComponent(nameTextField);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        add(addButton, gridBagConstraints);
    }// </editor-fold>


    // Variables declaration - do not modify
    private javax.swing.JButton addButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel valueLabel;
    private javax.swing.JTextField valueTextField;
    // End of variables declaration

    private static class EnterKeyListener implements KeyListener {
        private final Runnable addQueryParamAction;

        private EnterKeyListener(Runnable addQueryParamAction) {
            this.addQueryParamAction = addQueryParamAction;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
            {
                addQueryParamAction.run();
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
            // Do nothing.
        }

        @Override
        public void keyReleased(KeyEvent e) {
            // Do nothing.
        }
    }

    private static class SelectTextFocusListener implements FocusListener
    {
        private final JTextField textField;

        private SelectTextFocusListener(JTextField textField) {
            this.textField = textField;
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (e.getCause() == FocusEvent.Cause.TRAVERSAL_FORWARD)
            {
                textField.selectAll();
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (e.getCause() == FocusEvent.Cause.TRAVERSAL_FORWARD)
            {
                textField.select(0, 0);
            }
        }
    }
}
