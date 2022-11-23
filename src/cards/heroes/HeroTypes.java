package cards.heroes;

public enum HeroTypes {
    ROYCE("Lord Royce"), THORINA("Empress Thorina"), MUDFACE("King Mudface"),
    KOCIORAW("General " + "Kocioraw"), NOTHING("Nothing");

    private final String name;

    HeroTypes(final String name) {
        this.name = name;
    }

    /**
     * Get the hero type associated with the given name.
     *
     * @param name the name of the hero
     * @return the hero type
     */
    public static HeroTypes getHeroType(final String name) {
        for (HeroTypes heroType : HeroTypes.values()) {
            if (heroType.name.equals(name)) {
                return heroType;
            }
        }
        return NOTHING;
    }
}
