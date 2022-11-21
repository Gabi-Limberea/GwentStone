package cards.minions.basic;

import cards.minions.MinionCard;
import fileio.CardInput;

public final class Goliath extends MinionCard {
    /**
     * @param source
     */
    public Goliath(final CardInput source) {
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
