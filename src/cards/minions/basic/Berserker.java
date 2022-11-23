package cards.minions.basic;

import cards.minions.MinionCard;
import fileio.CardInput;

public final class Berserker extends MinionCard {
    /**
     * @param source
     */
    public Berserker(final CardInput source) {
        super(source);
    }

    /**
     * @param source
     */
    public Berserker(final MinionCard source) {
        super(source);
    }

    /**
     * @return
     */
    public boolean isTank() {
        return false;
    }
}
