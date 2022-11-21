package cards.minions;

public enum MinionTypes {
    SENTINEL("Sentinel"), BERSERKER("Berserker"), GOLIATH("Goliath"), WARDEN("Warden"),
    RIPPER("The Ripper"), MIRAJ("Miraj"), DISCIPLE("Disciple"), CURSED_ONE("The Cursed One"),
    NOTHING("Nothing");
    private final String name;

    /**
     * @param name
     */
    MinionTypes(final String name) {
        this.name = name;
    }

    /**
     * @return
     */
    public static MinionTypes getMinionType(final String name) {
        for (MinionTypes minionType : MinionTypes.values()) {
            if (minionType.name.equals(name)) {
                return minionType;
            }
        }

        return NOTHING;
    }
}
