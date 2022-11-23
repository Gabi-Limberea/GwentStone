package cards.minions.special;

import cards.minions.MinionCard;
import cards.minions.SpecialMinionCard;
import fileio.CardInput;

public final class TheCursedOne extends MinionCard implements SpecialMinionCard {
    /**
     * @param source the card to be created
     */
    public TheCursedOne(final CardInput source) {
        super(source);
    }

    /**
     * @param source the card to be copied
     */
    public TheCursedOne(final MinionCard source) {
        super(source);
    }

    /**
     * The Cursed One is not a tank.
     *
     * @return false
     */
    @Override
    public boolean isTank() {
        return false;
    }

    /**
     * Use the special ability of The Cursed One on the given target.
     *
     * @param target the target of the special ability
     */
    @Override
    public void specialAttack(final MinionCard target) {
        int temp = target.getAttackDamage();

        target.setAttackDamage(target.getHealth());
        target.setHealth(temp);
    }
}
