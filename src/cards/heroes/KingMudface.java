package cards.heroes;

import cards.minions.MinionCard;
import fileio.CardInput;

import java.util.ArrayList;

public final class KingMudface extends HeroCard {
    /**
     * @param source
     */
    public KingMudface(final CardInput source) {
        super(source);
    }

    /**
     * @param targetRow
     */
    @Override
    public void useAbility(final ArrayList<MinionCard> targetRow) {
        for (MinionCard minion : targetRow) {
            minion.setHealth(minion.getHealth() + 1);
        }

        targetRow.removeIf(minion -> minion.getHealth() <= 0);
    }
}
