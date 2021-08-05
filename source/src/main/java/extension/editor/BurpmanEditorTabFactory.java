package extension.editor;

import burp.IMessageEditorController;
import burp.IMessageEditorTab;
import burp.IMessageEditorTabFactory;

public class BurpmanEditorTabFactory implements IMessageEditorTabFactory
{
    @Override
    public IMessageEditorTab createNewInstance(IMessageEditorController controller, boolean editable) {
        return new BurpmanEditorTab(controller, editable);
    }
}
