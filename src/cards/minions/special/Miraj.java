package cards.minions.special;

import cards.minions.MinionCard;
import cards.minions.SpecialMinionCard;
import fileio.CardInput;

public final class Miraj extends MinionCard implements SpecialMinionCard {
    /**
     * @param source the card to be created
     */
    public Miraj(final CardInput source) {
        super(source);
    }

    /**
     * @param source the card to be copied
     */
    public Miraj(final MinionCard source) {
        super(source);
    }

    /**
     * Miraj is not a tank.
     *
     * @return false
     */
    @Override
    public boolean isTank() {
        return false;
    }

    /**
     * Use the special ability of Miraj on the given target.
     *
     * @param target the target of the special ability
     */
    @Override
    public void specialAttack(final MinionCard target) {
        int temp = this.getHealth();

        this.setHealth(target.getHealth());
        target.setHealth(temp);
    }
}
