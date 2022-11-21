package cards.minions;

import cards.Card;
import cards.heroes.HeroCard;
import fileio.CardInput;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class MinionCard extends Card {
    private final MinionRow    row;
    private       int          attackDamage;
    private       MinionStates state;
    private       int          health;

    /**
     * @param source
     */
    public MinionCard(final CardInput source) {
        super(source);
        this.attackDamage = source.getAttackDamage();
        this.state = MinionStates.ACTIVE;
        this.health = source.getHealth();

        switch (Objects.requireNonNull(MinionTypes.getMinionType(source.getName()))) {
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
    }

    /**
     * @param target
     */
    public void basicAttack(@NotNull MinionCard target) {
        target.health -= this.attackDamage;
    }

    /**
     * @param target
     */
    public void heroAttack(@NotNull HeroCard target) {
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
}
