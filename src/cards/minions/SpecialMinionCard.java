package cards.minions;

public interface SpecialMinionCard {
    /**
     * Use a minion's special attack on an enemy minion.
     *
     * @param target the target of the special ability
     */
    void specialAttack(MinionCard target);
}
