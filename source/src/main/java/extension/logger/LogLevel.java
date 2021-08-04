package extension.logger;

import extension.ExtensionMetaData;

import static extension.ExtensionMetaData.LOG_LEVEL;

public enum LogLevel {
    INFO(1),
    ERROR(2),
    DEBUG(4),
    VERBOSE(8);

    public final int weight;

    LogLevel(int weight) {
        this.weight = weight;
    }

    public boolean canLog()
    {
        return LOG_LEVEL.weight >= weight;
    }
}
