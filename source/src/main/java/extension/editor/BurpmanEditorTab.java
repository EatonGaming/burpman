package extension.editor;

import burp.IMessageEditorController;
import burp.IMessageEditorTab;

import java.awt.*;

public class BurpmanEditorTab implements IMessageEditorTab
{
    private final IMessageEditorController controller;
    private final boolean editable;
    private final BurpmanEditor burpmanEditor;

    public BurpmanEditorTab(IMessageEditorController controller, boolean editable)
    {
        this.controller = controller;
        this.editable = editable;
        this.burpmanEditor = new BurpmanEditor();
    }

    @Override
    public String getTabCaption() {
        return "Burpman";
    }

    @Override
    public Component getUiComponent() {
        return burpmanEditor;
    }

    @Override
    public boolean isEnabled(byte[] content, boolean isRequest) {
        return true;
    }

    @Override
    public void setMessage(byte[] content, boolean isRequest) {

    }

    @Override
    public byte[] getMessage() {
        return new byte[0];
    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public byte[] getSelectedData() {
        return new byte[0];
    }
}
