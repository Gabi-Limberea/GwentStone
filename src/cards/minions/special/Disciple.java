package cards.minions.special;

import cards.minions.MinionCard;
import cards.minions.SpecialMinionCard;
import fileio.CardInput;

public final class Disciple extends MinionCard implements SpecialMinionCard {
    /**
     * @param source
     */
    public Disciple(final CardInput source) {
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
