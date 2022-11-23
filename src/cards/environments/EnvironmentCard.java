package cards.environments;

import cards.Card;
import cards.minions.MinionCard;
import fileio.CardInput;

import java.util.ArrayList;

public abstract class EnvironmentCard extends Card {
    /**
     * @param source the card to create
     */
    public EnvironmentCard(final CardInput source) {
        super(source);
    }

    /**
     * @param source the card to copy
     */
    public EnvironmentCard(final EnvironmentCard source) {
        super(source);
    }

    /**
     * Use the environment card's ability
     *
     * @param targetRow the row to apply the effect to
     */
    public abstract void use(ArrayList<MinionCard> targetRow);

    /**
     * Generate the string output for the card.
     *
     * @return the string version of the card
     */
    @Override
    protected String genOutput() {
        return "{\"mana\":" + this.getMana() + ",\"description\":\"" + this.getDescription()
               + "\",\"colors\":" + this.genColorsOutput() + ",\"name\":\"" + this.getName()
               + "\"}";
    }

    /**
     * @return the string version of the card
     */
    @Override
    public String toString() {
        return this.genOutput();
    }
}
