package cards.minions.special;

import cards.minions.MinionCard;
import cards.minions.SpecialMinionCard;
import fileio.CardInput;

public final class TheRipper extends MinionCard implements SpecialMinionCard {
    /**
     * @param source the card to be created
     */
    public TheRipper(final CardInput source) {
        super(source);
    }

    /**
     * @param source the card to be copied
     */
    public TheRipper(final MinionCard source) {
        super(source);
    }

    /**
     * The Ripper is not a tank.
     *
     * @return false
     */
    @Override
    public boolean isTank() {
        return false;
    }

    /**
     * Use the special ability of The Ripper on the given target.
     *
     * @param target the target of the special ability
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
