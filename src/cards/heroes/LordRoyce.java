package cards.heroes;

import cards.minions.MinionCard;
import cards.minions.MinionStates;
import fileio.CardInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class LordRoyce extends HeroCard {
    /**
     * @param source
     */
    public LordRoyce(final CardInput source) {
        super(source);
    }

    /**
     * @param targetRow
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
