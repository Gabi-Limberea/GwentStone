package cards.minions;

import cards.Card;
import cards.CardAttackStatus;
import cards.heroes.HeroCard;
import fileio.CardInput;

public abstract class MinionCard extends Card {
    private final MinionRow        row;
    private       int              attackDamage;
    private       MinionStates     state;
    private       int              health;
    private       CardAttackStatus attackStatus;

    /**
     * @param source the card to be created
     */
    public MinionCard(final CardInput source) {
        super(source);
        this.attackDamage = source.getAttackDamage();
        this.state = MinionStates.ACTIVE;
        this.health = source.getHealth();

        switch (MinionTypes.getMinionType(source.getName())) {
            case BERSERKER, SENTINEL, CURSED_ONE, DISCIPLE -> {
                MinionRow dummyRow = MinionRow.BACK;
                this.row = dummyRow;
            }
            case RIPPER, MIRAJ, GOLIATH, WARDEN -> {
                MinionRow dummyRow = MinionRow.FRONT;
                this.row = dummyRow;
            }
            default -> throw new IllegalStateException("Unexpected value: " + source.getName());
        }

        this.attackStatus = CardAttackStatus.HAS_NOT_ATTACKED;
    }

    /**
     * @param source the card to be copied
     */
    public MinionCard(final MinionCard source) {
        super(source);
        this.attackDamage = source.getAttackDamage();
        this.state = source.getState();
        this.health = source.getHealth();
        this.row = source.getRow();
        this.attackStatus = source.getAttackStatus();
    }

    /**
     * Make the card attack an enemy card.
     *
     * @param target the card to be attacked
     */
    public void basicAttack(final MinionCard target) {
        target.health -= this.attackDamage;
    }

    /**
     * Make the card attack an enemy hero.
     *
     * @param target the hero to be attacked
     */
    public void heroAttack(final HeroCard target) {
        target.takeDamage(this.attackDamage);
    }

    /**
     * Get if a minion is a tank or not
     *
     * @return if a minion is a tank
     */
    public abstract boolean isTank();

    /**
     * Get the state of the minion.
     *
     * @return the state of the minion
     */
    public MinionStates getState() {
        return this.state;
    }

    /**
     * Set the state of the minion.
     *
     * @param state the state to be set
     */
    public void setState(final MinionStates state) {
        this.state = state;
    }

    /**
     * Get the row on which the minion should be placed.
     *
     * @return the row of the minion
     */
    public MinionRow getRow() {
        return this.row;
    }

    /**
     * Get how much health the minion has.
     *
     * @return the health of the minion
     */
    public int getHealth() {
        return health;
    }

    /**
     * Set the health of the minion.
     *
     * @param health the health to be set
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * Get the minion's attack damage.
     *
     * @return te minion's attack damage
     */
    public int getAttackDamage() {
        return this.attackDamage;
    }

    /**
     * Set a minion's attack damage.
     *
     * @param attackDamage the attack damage to be set
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * Generate the output for getting a minion in a JSON formatted string.
     *
     * @return the minion in string format
     */
    @Override
    protected String genOutput() {
        return "{\"mana\":" + this.getMana() + ",\"attackDamage\":" + this.attackDamage
               + ",\"health\":" + this.health + ",\"description\":\"" + this.getDescription()
               + "\",\"colors\":" + this.genColorsOutput() + ",\"name\":\"" + this.getName()
               + "\"}";
    }

    /**
     * @return the string version of the minion
     */
    @Override
    public String toString() {
        return this.genOutput();
    }

    /**
     * Get the attack status of the minion.
     *
     * @return the attack status of the minion
     */
    public CardAttackStatus getAttackStatus() {
        return attackStatus;
    }

    /**
     * Set the attack status of the minion.
     *
     * @param attackStatus the attack status to be set
     */
    public void setAttackStatus(final CardAttackStatus attackStatus) {
        this.attackStatus = attackStatus;
    }
}
