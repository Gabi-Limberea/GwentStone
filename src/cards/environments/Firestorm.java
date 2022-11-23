package cards.environments;

import cards.minions.MinionCard;
import fileio.CardInput;

import java.util.ArrayList;

public final class Firestorm extends EnvironmentCard {
    /**
     * @param source
     */
    public Firestorm(final CardInput source) {
        super(source);
    }

    /**
     * @param source
     */
    public Firestorm(final EnvironmentCard source) {
        super(source);
    }

    /**
     * @param targetRow
     */
    public void use(final ArrayList<MinionCard> targetRow) {
        for (MinionCard minion : targetRow) {
            minion.setHealth(minion.getHealth() - 1);
        }

        targetRow.removeIf(minion -> minion.getHealth() <= 0);
    }
}
