package cards.environments;

import cards.minions.MinionCard;
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
     * @param targetRow
     */
    @Override
    public void use(ArrayList<MinionCard> targetRow) {

    }
}
