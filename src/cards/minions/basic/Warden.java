package cards.minions.basic;

import cards.minions.MinionCard;
import fileio.CardInput;

public final class Warden extends MinionCard {
    /**
     * @param source the card to be created
     */
    public Warden(final CardInput source) {
        super(source);
    }

    /**
     * @param source the card to be copied
     */
    public Warden(final MinionCard source) {
        super(source);
    }

    /**
     * Warden is a tank.
     *
     * @return true
     */
    @Override
    public boolean isTank() {
        return true;
    }
}
