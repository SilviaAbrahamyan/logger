package com.egs.training.logger;

public enum Level {
    OFF("OFF", Integer.MAX_VALUE),
    ERROR("ERROR", 40),
    WARNING("WARNING", 30),
    INFO("INFO", 20),
    TRACE("TRACE", 10),
    ALL("ALL", Integer.MIN_VALUE),
    ;

    private String name;
    private int value;

    Level(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static Level getByName(final String name) {
        for (Level level : values()) {
            if (level.name.equals(name)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Level with name " + name + " not found");
    }
}
