package cards.minions.basic;

import cards.minions.MinionCard;
import fileio.CardInput;

public final class Sentinel extends MinionCard {
    /**
     * @param source the card to be created
     */
    public Sentinel(final CardInput source) {
        super(source);
    }

    /**
     * @param source the card to be copied
     */
    public Sentinel(final MinionCard source) {
        super(source);
    }

    /**
     * Sentinel is not a tank.
     *
     * @return false
     */
    @Override
    public boolean isTank() {
        return false;
    }
}
