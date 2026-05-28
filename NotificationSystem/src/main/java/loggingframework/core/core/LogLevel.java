package loggingframework.core.core;

public enum LogLevel {
    debug(1),
    info(2),
    warning(3),
    error(4),
    fatal(5);

    private final int level;

    LogLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
