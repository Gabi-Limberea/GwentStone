package cards.minions;

import cards.Card;
import cards.CardAttackStatus;
import cards.heroes.HeroCard;
import fileio.CardInput;
import org.jetbrains.annotations.NotNull;

public abstract class MinionCard extends Card {
    private final MinionRow          row;
    private int          attackDamage;
    private MinionStates state;
    private int              health;
    private CardAttackStatus attackStatus;

    /**
     * @param source
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
     * @param source
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
     * @param target
     */
    public void basicAttack(final @NotNull MinionCard target) {
        target.health -= this.attackDamage;
    }

    /**
     * @param target
     */
    public void heroAttack(final @NotNull HeroCard target) {
        target.takeDamage(this.attackDamage);
    }

    /**
     * @return
     */
    public abstract boolean isTank();

    /**
     * @return
     */
    public MinionStates getState() {
        return this.state;
    }

    /**
     * @param state
     * @return
     */
    public void setState(final MinionStates state) {
        this.state = state;
    }

    /**
     * @return
     */
    public MinionRow getRow() {
        return this.row;
    }

    /**
     * @return
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * @return
     */
    public int getAttackDamage() {
        return this.attackDamage;
    }

    /**
     * @param attackDamage
     */
    public void setAttackDamage(final int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     * @return
     */
    @Override
    protected String genOutput() {
        return "{\"mana\":" + this.getMana() + ",\"attackDamage\":" + this.attackDamage
               + ",\"health\":" + this.health + ",\"description\":\"" + this.getDescription()
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

    /**
     * @return
     */
    public CardAttackStatus getAttackStatus() {
        return attackStatus;
    }

    /**
     * @param attackStatus
     */
    public void setAttackStatus(final CardAttackStatus attackStatus) {
        this.attackStatus = attackStatus;
    }
}
