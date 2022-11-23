package cards.heroes;

import cards.Card;
import cards.CardAttackStatus;
import cards.minions.MinionCard;
import fileio.CardInput;

import java.util.ArrayList;

public abstract class HeroCard extends Card {
    private static final int              HEALTH = 30;
    private              int              health;
    private              CardAttackStatus attackStatus;

    /**
     * @param source the card to be created
     */
    public HeroCard(final CardInput source) {
        super(source);
        this.health = HEALTH;
        this.attackStatus = CardAttackStatus.HAS_NOT_ATTACKED;
    }

    /**
     * Use the hero's ability on the given row.
     *
     * @param targetRow the row to be targeted by the ability
     */
    public abstract void useAbility(ArrayList<MinionCard> targetRow);

    /**
     * Make the hero take the given amount of damage.
     *
     * @param damage the amount of damage to be dealt to the hero
     */
    public void takeDamage(final int damage) {
        this.health -= damage;
    }

    /**
     * Get the hero's current health.
     *
     * @return the hero's health
     */
    public int getHealth() {
        return this.health;
    }

    /**
     * Get the hero's attack status.
     *
     * @return the hero's attack status
     */
    public CardAttackStatus getAttackStatus() {
        return this.attackStatus;
    }

    /**
     * Set the hero's attack status.
     *
     * @param attackStatus the new attack status of the hero
     */
    public void setAttackStatus(final CardAttackStatus attackStatus) {
        this.attackStatus = attackStatus;
    }

    /**
     * Generate the output for getting the hero in a JSON formatted string.
     *
     * @return the hero in string format
     */
    @Override
    protected String genOutput() {
        return "{\"mana\":" + this.getMana() + ",\"description\":\"" + this.getDescription()
               + "\",\"colors\":" + this.genColorsOutput() + ",\"name\":\"" + this.getName()
               + "\",\"health\":" + this.getHealth() + "}";
    }

    /**
     * @return the string version of the hero
     */
    @Override
    public String toString() {
        return this.genOutput();
    }
}
