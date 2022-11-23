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
     * @param source
     */
    public TheRipper(final MinionCard source) {
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
    public void specialAttack(final MinionCard target) {
        int newAttackDamage = target.getAttackDamage() - 2;
        if (newAttackDamage < 0) {
            newAttackDamage = 0;
        }

        target.setAttackDamage(newAttackDamage);
    }
}
