package extension.editor;

import burp.IMessageEditorController;
import burp.IMessageEditorTab;
import extension.request.RequestModel;

import java.awt.*;

public class BurpmanEditorTab implements IMessageEditorTab
{
    private final RequestModel requestModel;
    private final IMessageEditorController controller;
    private final boolean editable;
    private final BurpmanEditor burpmanEditor;

    public BurpmanEditorTab(RequestModel requestModel, IMessageEditorController controller, boolean editable)
    {
        this.requestModel = requestModel;
        this.controller = controller;
        this.editable = editable;

        this.burpmanEditor = new BurpmanEditor(requestModel);

        requestModel.bindTo(burpmanEditor);
        requestModel.notifyAllProperties();
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
