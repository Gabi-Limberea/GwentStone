package cards.environments;

public enum EnvironmentTypes {
    FIRESTORM("Firestorm"), WINTERFELL("Winterfell"), HEART_HOUND("Heart Hound"),
    NOTHING("Nothing");
    private final String name;

    EnvironmentTypes(final String name) {
        this.name = name;
    }

    /**
     * @param name
     * @return
     */
    public static EnvironmentTypes getEnvironmentType(final String name) {
        for (EnvironmentTypes environmentType : EnvironmentTypes.values()) {
            if (environmentType.name.equals(name)) {
                return environmentType;
            }
        }
        return NOTHING;
    }
}
