package cards.environments;

import cards.minions.MinionCard;
import cards.minions.MinionStates;
import fileio.CardInput;

import java.util.ArrayList;

public final class Winterfell extends EnvironmentCard {
    /**
     * @param source the card to be created
     */
    public Winterfell(final CardInput source) {
        super(source);
    }

    /**
     * @param source the card to be copied
     */
    public Winterfell(final EnvironmentCard source) {
        super(source);
    }

    /**
     * Use the Winterfell effect on the given row.
     *
     * @param targetRow the row to be affected by the card
     */
    @Override
    public void use(final ArrayList<MinionCard> targetRow) {
        for (MinionCard minion : targetRow) {
            minion.setState(MinionStates.FROZEN);
        }
    }
}
