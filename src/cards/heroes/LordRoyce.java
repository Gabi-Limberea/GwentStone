package cards.heroes;

import cards.minions.MinionCard;
import cards.minions.MinionStates;
import fileio.CardInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class LordRoyce extends HeroCard {
    /**
     * @param source the card to be created
     */
    public LordRoyce(final CardInput source) {
        super(source);
    }

    /**
     * Use the ability of Lord Royce on the given row.
     *
     * @param targetRow the row to be affected by the ability
     */
    public void useAbility(final ArrayList<MinionCard> targetRow) {
        MinionCard maxAttackCard = Collections.max(targetRow, Comparator.comparingInt(
                MinionCard::getAttackDamage));

        int currentTimeStamp = maxAttackCard.getState().getTimeStamp();

        if (maxAttackCard.getState() != MinionStates.FROZEN) {
            maxAttackCard.setState(MinionStates.FROZEN);
        }

        maxAttackCard.getState().setFreezeTimeStamp(currentTimeStamp);
    }
}
