package cards.minions.basic;

import cards.minions.MinionCard;
import fileio.CardInput;

public final class Warden extends MinionCard {
    /**
     * @param source
     */
    public Warden(final CardInput source) {
        super(source);
    }

    /**
     * @param source
     */
    public Warden(final MinionCard source) {
        super(source);
    }

    /**
     * @return
     */
    @Override
    public boolean isTank() {
        return true;
    }
}
