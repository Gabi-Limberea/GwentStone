package cards.heroes;

import cards.minions.MinionCard;
import fileio.CardInput;

import java.util.ArrayList;

public final class KingMudface extends HeroCard {
    /**
     * @param source the card to be created
     */
    public KingMudface(final CardInput source) {
        super(source);
    }

    /**
     * Use the ability of King Mudface on the given row.
     *
     * @param targetRow the row affected by the ability
     */
    @Override
    public void useAbility(final ArrayList<MinionCard> targetRow) {
        for (MinionCard minion : targetRow) {
            minion.setHealth(minion.getHealth() + 1);
        }

        targetRow.removeIf(minion -> minion.getHealth() <= 0);
    }
}
