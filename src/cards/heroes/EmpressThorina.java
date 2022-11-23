package cards.heroes;

import cards.minions.MinionCard;
import fileio.CardInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class EmpressThorina extends HeroCard {
    /**
     * @param source the card to be created
     */
    public EmpressThorina(final CardInput source) {
        super(source);
    }

    /**
     * Use the ability of Empress Thorina on the given row.
     *
     * @param targetRow the row to be affected by the ability
     */
    @Override
    public void useAbility(final ArrayList<MinionCard> targetRow) {
        MinionCard maxHealthMinion = Collections.max(
                targetRow, Comparator.comparingInt(MinionCard::getHealth));

        targetRow.remove(maxHealthMinion);
    }
}
