package games;

public enum Actions {
    GET_HAND("getCardsInHand"), GET_DECK("getPlayerDeck"), GET_TABLE("getCardsOnTable"),
    GET_TURN("getPlayerTurn"), GET_HERO("getPlayerHero"), GET_CARD("getCardAtPosition"),
    GET_MANA("getPlayerMana"), GET_ENV("getEnvironmentCardsInHand"),
    GET_FROZEN("getFrozenCardsOnTable"), GET_GAMES("getTotalGamesPlayed"),
    GET_WINS_ONE("getPlayerOneWins"), GET_WINS_TWO("getPlayerTwoWins"), END_TURN("endPlayerTurn"),
    PLACE_CARD("placeCard"), CARD_ATTACK("cardUsesAttack"), CARD_SPECIAL("cardUsesAbility"),
    DAMAGE_HERO("useAttackHero"), HERO_ATTACK("useHeroAbility"), USE_ENV("useEnvironmentCard"),
    NOTHING("nothing");
    private final String name;

    Actions(final String name) {
        this.name = name;
    }

    /**
     * Get the action corresponding to the given command.
     *
     * @param command the command to be checked
     * @return the action that corresponds to the command
     */
    public static Actions getCommand(final String command) {
        for (Actions action : Actions.values()) {
            if (action.name.equals(command)) {
                return action;
            }
        }

        return NOTHING;
    }
}
