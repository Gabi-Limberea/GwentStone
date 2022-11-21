package cards.minions.special;

import cards.minions.MinionCard;
import cards.minions.SpecialMinionCard;
import fileio.CardInput;

public final class TheCursedOne extends MinionCard implements SpecialMinionCard {
    /**
     * @param source
     */
    public TheCursedOne(final CardInput source) {
        super(source);
    }

    /**
     * @return
     */
    @Override
    public boolean isTank() {
        return false;
    }

    /**
     * @param target
     */
    @Override
    public void specialAttack(MinionCard target) {

    }
}
