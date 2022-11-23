package cards.minions.basic;

import cards.minions.MinionCard;
import fileio.CardInput;

public final class Goliath extends MinionCard {
    /**
     * @param source the card to be created
     */
    public Goliath(final CardInput source) {
        super(source);
    }

    /**
     * @param source the card to be copied
     */
    public Goliath(final MinionCard source) {
        super(source);
    }

    /**
     * Goliath is a tank.
     *
     * @return true
     */
    @Override
    public boolean isTank() {
        return true;
    }
}
