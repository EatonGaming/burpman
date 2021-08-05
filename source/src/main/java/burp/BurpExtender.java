package burp;

import extension.editor.BurpmanEditorTabFactory;
import extension.logger.Logger;

import java.io.OutputStream;

import static extension.ExtensionMetaData.EXTENSION_NAME;
import static extension.ExtensionMetaData.VERSION;
import static extension.logger.LogLevel.INFO;
import static extension.logger.Logger.log;

public class BurpExtender implements IBurpExtender{
    @Override
    public void registerExtenderCallbacks(IBurpExtenderCallbacks callbacks)
    {
        callbacks.setExtensionName(EXTENSION_NAME);

        initialiseLogger(callbacks);

        IMessageEditorTabFactory burpmanEditorTabFactory = new BurpmanEditorTabFactory();
        callbacks.registerMessageEditorTabFactory(burpmanEditorTabFactory);

        log(INFO, "%s version %s has been loaded successfully.", EXTENSION_NAME, VERSION);
    }

    private void initialiseLogger(IBurpExtenderCallbacks callbacks) {
        OutputStream stdout = callbacks.getStdout();
        OutputStream stderr = callbacks.getStderr();
        Logger logger = Logger.getInstance();

        logger.initialise(stdout, stderr);
        callbacks.registerExtensionStateListener(logger);
    }
}
