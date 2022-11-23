package cards.heroes;

import cards.minions.MinionCard;
import fileio.CardInput;

import java.util.ArrayList;

public final class GeneralKocioraw extends HeroCard {
    /**
     * @param source the card to be created
     */
    public GeneralKocioraw(final CardInput source) {
        super(source);
    }

    /**
     * Use the ability of General Kocioraw on the given row.
     *
     * @param targetRow the row to be affected by the ability
     */
    @Override
    public void useAbility(final ArrayList<MinionCard> targetRow) {
        for (MinionCard minionCard : targetRow) {
            int minionAttackDamage = minionCard.getAttackDamage() + 1;
            minionCard.setAttackDamage(minionAttackDamage);
        }
    }
}
