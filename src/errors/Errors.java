package errors;

public enum Errors {
    NONE(""), ENV_CARD_ON_TABLE("Cannot place environment card on table."),
    NO_MANA_PLACE_CARD("Not enough mana to place card on table."),
    ROW_FULL("Cannot place card on table since row is full."),
    NO_CARD("No card available at that position."),
    NOT_ENV_CARD("Chosen card is not of type environment."),
    NOT_ENEMY_ROW("Chosen row does not belong to the enemy."),
    ROW_FULL_STEAL("Cannot steal enemy card since the player's row is full."),
    NO_MANA_USE_ENV("Not enough mana to use environment card."),
    NOT_ENEMY_CARD("Attacked card does not belong to the enemy."),
    CARD_ALREADY_ATTACKED("Attacker card has already attacked this turn."),
    CARD_FROZEN("Attacker card is frozen."), CARD_NOT_TANK("Attacked card is not of type 'Tank'."),
    NOT_CURRENT_PLAYER_ATTACKED_CARD("Attacked card does not belong to the current player."),
    NO_MANA_HERO_ABILITY("Not enough mana to use hero's ability."),
    HERO_ALREADY_ATTACKED("Hero has already attacked this turn."),
    NOT_CURRENT_PLAYER_ROW_HERO_ABILITY("Selected row does not belong to the current player."),
    NOT_ENEMY_ROW_HERO_ABILITY("Selected row does not belong to the enemy.");

    private final String error;

    Errors(final String error) {
        this.error = error;
    }

    /**
     * @return the error string
     */
    public String getError() {
        return error;
    }
}
