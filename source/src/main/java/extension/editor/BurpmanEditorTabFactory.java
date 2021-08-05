package extension.editor;

import burp.IMessageEditorController;
import burp.IMessageEditorTab;
import burp.IMessageEditorTabFactory;
import extension.request.RequestModel;

public class BurpmanEditorTabFactory implements IMessageEditorTabFactory
{
    @Override
    public IMessageEditorTab createNewInstance(IMessageEditorController controller, boolean editable) {
        RequestModel requestModel = new RequestModel();

        return new BurpmanEditorTab(requestModel, controller, editable);
    }
}
