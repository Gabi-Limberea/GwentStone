package cards.minions.special;

import cards.minions.MinionCard;
import cards.minions.SpecialMinionCard;
import fileio.CardInput;

public final class TheRipper extends MinionCard implements SpecialMinionCard {
    /**
     * @param source
     */
    public TheRipper(final CardInput source) {
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
