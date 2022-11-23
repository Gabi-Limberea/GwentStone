package cards.environments;

import cards.minions.MinionCard;
import fileio.CardInput;

import java.util.ArrayList;

public final class Firestorm extends EnvironmentCard {
    /**
     * @param source the card to be created
     */
    public Firestorm(final CardInput source) {
        super(source);
    }

    /**
     * @param source the card to be copied
     */
    public Firestorm(final EnvironmentCard source) {
        super(source);
    }

    /**
     * Use the Firestorm effect on the given row.
     *
     * @param targetRow the row to be affected by the card
     */
    public void use(final ArrayList<MinionCard> targetRow) {
        for (MinionCard minion : targetRow) {
            minion.setHealth(minion.getHealth() - 1);
        }

        targetRow.removeIf(minion -> minion.getHealth() <= 0);
    }
}
