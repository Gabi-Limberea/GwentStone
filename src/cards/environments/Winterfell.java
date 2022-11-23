package cards.environments;

import cards.minions.MinionCard;
import cards.minions.MinionStates;
import fileio.CardInput;

import java.util.ArrayList;

public final class Winterfell extends EnvironmentCard {
    /**
     * @param source
     */
    public Winterfell(final CardInput source) {
        super(source);
    }

    /**
     * @param source
     */
    public Winterfell(final EnvironmentCard source) {
        super(source);
    }

    /**
     * @param targetRow
     */
    @Override
    public void use(final ArrayList<MinionCard> targetRow) {
        for (MinionCard minion : targetRow) {
            minion.setState(MinionStates.FROZEN);
        }
    }
}
