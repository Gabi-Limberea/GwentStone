package cards.environments;

import cards.Card;
import cards.minions.MinionCard;
import fileio.CardInput;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class EnvironmentCard extends Card {
    /**
     * @param source
     */
    public EnvironmentCard(final @NotNull CardInput source) {
        super(source);
    }

    /**
     * @param targetRow
     */
    abstract void use(ArrayList<MinionCard> targetRow);

    /**
     * @return
     */
    @Override
    protected String genOutput() {
        return "{\"mana\":" + this.getMana() + ",\"description\":\"" + this.getDescription()
               + "\",\"colors\":" + this.genColorsOutput() + ",\"name\":\"" + this.getName()
               + "\"}";
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return this.genOutput();
    }
}
