package cards.minions.special;

import cards.minions.MinionCard;
import cards.minions.SpecialMinionCard;
import fileio.CardInput;

public final class Disciple extends MinionCard implements SpecialMinionCard {
    /**
     * @param source the card to be created
     */
    public Disciple(final CardInput source) {
        super(source);
    }

    /**
     * @param source the card to be copied
     */
    public Disciple(final MinionCard source) {
        super(source);
    }

    /**
     * Disciple is not a tank
     *
     * @return false
     */
    @Override
    public boolean isTank() {
        return false;
    }

    /**
     * Use the special ability of the Disciple on the given target.s
     *
     * @param target the target of the special ability
     */
    @Override
    public void specialAttack(final MinionCard target) {
        int newHealth = target.getHealth() + 2;
        target.setHealth(newHealth);
    }
}
