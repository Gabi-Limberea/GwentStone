package cards.minions.special;

import cards.minions.MinionCard;
import cards.minions.SpecialMinionCard;
import fileio.CardInput;

public final class Miraj extends MinionCard implements SpecialMinionCard {
    /**
     * @param source
     */
    public Miraj(final CardInput source) {
        super(source);
    }

    /**
     * @param source
     */
    public Miraj(final MinionCard source) {
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
        int temp = this.getHealth();

        this.setHealth(target.getHealth());
        target.setHealth(temp);
    }
}
