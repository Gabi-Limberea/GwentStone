package cards.minions.basic;

import cards.minions.MinionCard;
import fileio.CardInput;

public final class Berserker extends MinionCard {
    /**
     * @param source the card to be created
     */
    public Berserker(final CardInput source) {
        super(source);
    }

    /**
     * @param source the card to be copied
     */
    public Berserker(final MinionCard source) {
        super(source);
    }

    /**
     * Berserker is not a tank.
     *
     * @return false
     */
    public boolean isTank() {
        return false;
    }
}
