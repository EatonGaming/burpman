package extension;

import static java.lang.String.format;

public class ExtensionMetaData
{
    private ExtensionMetaData(){}

    public static final String EXTENSION_NAME = "Burpman";

    private static final int MAJOR_VERSION = 0;
    private static final int MINOR_VERSION = 1;
    private static final int REVISION = 1;
    public static final String VERSION = format("%d.%d.%d", MAJOR_VERSION, MINOR_VERSION, REVISION);
}
