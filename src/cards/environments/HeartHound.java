package cards.environments;

import cards.minions.MinionCard;
import fileio.CardInput;

import java.util.ArrayList;

public final class HeartHound extends EnvironmentCard {
    /**
     * @param source
     */
    public HeartHound(final CardInput source) {
        super(source);
    }

    /**
     * @param targetRow
     */
    @Override
    public void use(ArrayList<MinionCard> targetRow) {

    }
}
