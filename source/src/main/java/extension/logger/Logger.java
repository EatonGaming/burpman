package extension.logger;

import burp.IExtensionStateListener;

import java.io.OutputStream;
import java.io.PrintWriter;

import static extension.logger.LogLevel.ERROR;
import static java.lang.String.format;

public class Logger implements IExtensionStateListener {
    private static Logger instance;

    private boolean initialised = false;
    private PrintWriter logWriter = null;
    private PrintWriter errorWriter = null;

    private Logger(){}

    public static Logger getInstance()
    {
        if (instance == null)
        {
            instance = new Logger();
        }

        return instance;
    }

    public void initialise(OutputStream stdOut, OutputStream stdErr)
    {
        logWriter = new PrintWriter(stdOut, true);
        errorWriter = new PrintWriter(stdErr, true);
        initialised = true;
    }

    public static void log(LogLevel logLevel, String message, Object ... arguments)
    {
        Logger logger = getInstance();
        logger.checkLoggerIsInitialised();

        String messageWithArguments = format(message, arguments);

        logger.log(logLevel, messageWithArguments);
    }

    public static void logError(String message, Exception exception, Object ... arguments)
    {
        Logger logger = getInstance();
        logger.checkLoggerIsInitialised();

        String messageWithArguments = format(message, arguments);

        logger.logError(messageWithArguments, exception);
    }

    private void checkLoggerIsInitialised()
    {
        if (!initialised)
        {
            throw new IllegalStateException("You must initialise the logger before using it.");
        }
    }

    private void log(LogLevel logLevel, String message)
    {
        writeMessage(logLevel, logWriter, message);
    }

    private void logError(String message, Exception exception)
    {
        String messageToLog = format("%s - %s", message, exception.getMessage());

        writeMessage(ERROR, errorWriter, messageToLog);
    }

    private void writeMessage(LogLevel logLevel, PrintWriter printWriter, String message)
    {
        if (!logLevel.canLog())
        {
            return;
        }

        String messageToLog = format("%s => %s", logLevel, message);

        printWriter.println(messageToLog);
    }

    @Override
    public void extensionUnloaded() {
        if (initialised)
        {
            logWriter.close();
            errorWriter.close();
            initialised = false;
        }
    }
}
