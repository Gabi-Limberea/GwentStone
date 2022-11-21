package cards.heroes;

import cards.Card;
import cards.minions.MinionCard;
import fileio.CardInput;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class HeroCard extends Card {
    private static final int HEALTH = 30;
    private              int health;

    /**
     * @param source
     */
    public HeroCard(final @NotNull CardInput source) {
        super(source);
        this.health = HEALTH;
    }

    /**
     * @param targetRow
     */
    abstract void useAbility(ArrayList<MinionCard> targetRow);

    /**
     * @param damage
     */
    public void takeDamage(final int damage) {
        this.health -= damage;
    }

    /**
     * @return
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * @return
     */
    @Override
    protected String genOutput() {
        return "{\"mana\":" + this.getMana() + ",\"description\":\"" + this.getDescription()
               + "\",\"colors\":" + this.genColorsOutput() + ",\"name\":\"" + this.getName()
               + "\",\"health\":" + this.getHealth() + "}";
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return this.genOutput();
    }
}
