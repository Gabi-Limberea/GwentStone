package cards.minions.basic;

import cards.minions.MinionCard;
import fileio.CardInput;

public final class Sentinel extends MinionCard {
    /**
     * @param source
     */
    public Sentinel(final CardInput source) {
        super(source);
    }

    /**
     * @param source
     */
    public Sentinel(final MinionCard source) {
        super(source);
    }

    /**
     * @return
     */
    @Override
    public boolean isTank() {
        return false;
    }
}
