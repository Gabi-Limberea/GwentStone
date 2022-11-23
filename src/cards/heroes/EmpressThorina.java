package cards.heroes;

import cards.minions.MinionCard;
import fileio.CardInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class EmpressThorina extends HeroCard {
    /**
     * @param source
     */
    public EmpressThorina(final CardInput source) {
        super(source);
    }

    /**
     * @param targetRow
     */
    @Override
    public void useAbility(final ArrayList<MinionCard> targetRow) {
        MinionCard maxHealthMinion = Collections.max(
                targetRow, Comparator.comparingInt(MinionCard::getHealth));

        targetRow.remove(maxHealthMinion);
    }
}
