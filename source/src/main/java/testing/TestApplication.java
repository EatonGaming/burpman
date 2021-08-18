package testing;

import burp.IMessageEditorTab;
import extension.editor.BurpmanEditorTabFactory;

import javax.swing.*;
import java.awt.*;

public class TestApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TestApplication::createTestFrame);
    }

    private static void createTestFrame() {
        JFrame testFrame = new JFrame("Test harness");
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setPreferredSize(new Dimension(400, 400));

        BurpmanEditorTabFactory burpmanEditorTabFactory = new BurpmanEditorTabFactory();
        IMessageEditorTab iMessageEditorTab = burpmanEditorTabFactory.createNewInstance(null, true);
        Component editorTabUiComponent = iMessageEditorTab.getUiComponent();

        testFrame.getContentPane().add(editorTabUiComponent);

        testFrame.pack();
        testFrame.setVisible(true);
    }
}
