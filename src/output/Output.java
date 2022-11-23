package output;

import board.Board;
import errors.Errors;
import fileio.ActionsInput;
import player.Player;

public final class Output {
    private Output() {

    }

    /**
     * Generate output for getting info about a player's hero in a JSON formatted string.
     *
     * @param action    the action that was performed
     * @param playerOne the first player
     * @param playerTwo the second player
     * @return the output of the action
     */
    public static String getHero(
            final ActionsInput action, final Player playerOne, final Player playerTwo
                                ) {
        if (action.getPlayerIdx() == 1) {
            return "{\"command\":\"" + action.getCommand() + "\",\"playerIdx\":"
                   + action.getPlayerIdx() + ",\"output\":" + playerOne.getCurrentHero() + "}";
        } else if (action.getPlayerIdx() == 2) {
            return "{\"command\":\"" + action.getCommand() + "\",\"playerIdx\":"
                   + action.getPlayerIdx() + ",\"output\":" + playerTwo.getCurrentHero() + "}";
        }

        return null;
    }

    /**
     * Generate output for getting a player's decks in a JSON formatted string.
     *
     * @param action    the action that was performed
     * @param playerOne the first player
     * @param playerTwo the second player
     * @return the output of the action
     */
    public static String getDeck(
            final ActionsInput action, final Player playerOne, final Player playerTwo
                                ) {
        if (action.getPlayerIdx() == 1) {
            return "{\"command\":\"" + action.getCommand() + "\",\"playerIdx\":"
                   + action.getPlayerIdx() + ",\"output\":" + playerOne.getCurrentDeck() + "}";
        } else if (action.getPlayerIdx() == 2) {
            return "{\"command\":\"" + action.getCommand() + "\",\"playerIdx\":"
                   + action.getPlayerIdx() + ",\"output\":" + playerTwo.getCurrentDeck() + "}";
        }

        return null;
    }

    /**
     * Generate output for getting which player's turn it is in a JSON formatted string.
     *
     * @param action      the action that was performed
     * @param currentTurn the current turn
     * @return the output of the action
     */
    public static String getTurn(final ActionsInput action, final int currentTurn) {
        return "{\"command\":\"" + action.getCommand() + "\",\"output\":" + currentTurn + "}";
    }

    /**
     * Generate output for getting a player's hand in a JSON formatted string.
     *
     * @param action    the action that was performed
     * @param playerOne the first player
     * @param playerTwo the second player
     * @return the output of the action
     */
    public static String getHand(
            final ActionsInput action, final Player playerOne, final Player playerTwo
                                ) {
        if (action.getPlayerIdx() == 1) {
            return "{\"command\":\"" + action.getCommand() + "\",\"playerIdx\":"
                   + action.getPlayerIdx() + ",\"output\":" + playerOne.getHand() + "}";
        } else if (action.getPlayerIdx() == 2) {
            return "{\"command\":\"" + action.getCommand() + "\",\"playerIdx\":"
                   + action.getPlayerIdx() + ",\"output\":" + playerTwo.getHand() + "}";
        }

        return null;
    }

    /**
     * Generate output for getting the cards on the board in a JSON formatted string. The cards are
     * printed by row, from left to right.
     *
     * @param action the action that was performed
     * @return the output of the action
     */
    public static String getBoard(final ActionsInput action) {
        return "{\"command\":\"" + action.getCommand() + "\",\"output\":"
               + Board.getInstance().getCardsOnBoard() + "}";
    }

    /**
     * Generate output for getting a card from a certain position on the board in a JSON formatted
     * string.
     *
     * @param action the action that was performed
     * @return the output of the action
     */
    public static String getCard(final ActionsInput action) {
        if (Board.getInstance().getCardsOnBoard().get(action.getX()).isEmpty()
            || Board.getInstance().getCardsOnBoard().get(action.getX()).size() <= action.getY()
            || Board.getInstance().getCardsOnBoard().get(action.getX()).get(action.getY())
               == null) {
            return "{\"command\":\"" + action.getCommand() + "\",\"x\":" + action.getX() + ","
                   + "\"y\":" + action.getY() + ",\"output\":\"" + Errors.NO_CARD.getError()
                   + "\"}";
        }

        return "{\"command\":\"" + action.getCommand() + "\",\"x\":" + action.getX() + ","
               + "\"y\":" + action.getY() + ",\"output\":"
               + Board.getInstance().getCardsOnBoard().get(action.getX()).get(action.getY()) + "}";
    }

    /**
     * Generate output for getting the error that was encountered when a player tried to place a
     * card on the board in a JSON formatted string.
     *
     * @param action the action that was performed
     * @param error  the error that occurred
     * @return the output of the action
     */
    public static String placeCardError(final ActionsInput action, final String error) {
        return "{\"command\":\"" + action.getCommand() + "\",\"handIdx\":" + action.getHandIdx()
               + ",\"error" + "\":\"" + error + "\"}";
    }

    /**
     * Generate output for getting how much mana a player has in a JSON formatted string.
     *
     * @param action    the action that was performed
     * @param playerOne the first player
     * @param playerTwo the second player
     * @return the output of the action
     */
    public static String getMana(
            final ActionsInput action, final Player playerOne, final Player playerTwo
                                ) {
        if (action.getPlayerIdx() == 1) {
            return "{\"command\":\"" + action.getCommand() + "\",\"output\":" + playerOne.getMana()
                   + ",\"playerIdx\":" + action.getPlayerIdx() + "}";
        } else if (action.getPlayerIdx() == 2) {
            return "{\"command\":\"" + action.getCommand() + "\",\"output\":" + playerTwo.getMana()
                   + ",\"playerIdx\":" + action.getPlayerIdx() + "}";
        }

        return null;
    }

    /**
     * Generate output for getting the error that was encountered when a player tried using an
     * environment card in a JSON formatted string.
     *
     * @param action the action that was performed
     * @param error  the error that occurred
     * @return the output of the action
     */
    public static String useEnvCardError(final ActionsInput action, final String error) {
        return "{\"command\":\"" + action.getCommand() + "\",\"handIdx\":" + action.getHandIdx()
               + ",\"affectedRow\":" + action.getAffectedRow() + ",\"error" + "\":\"" + error
               + "\"}";
    }

    /**
     * Generate output for getting all the environment cards a player has in their hand in a JSON
     * formatted string.
     *
     * @param action    the action that was performed
     * @param playerOne the first player
     * @param playerTwo the second player
     * @return the output of the action
     */
    public static String getEnv(
            final ActionsInput action, final Player playerOne, final Player playerTwo
                               ) {
        if (action.getPlayerIdx() == 1) {
            return "{\"command\":\"" + action.getCommand() + "\",\"output\":"
                   + playerOne.getEnvCardsInHand() + ",\"playerIdx\":" + action.getPlayerIdx()
                   + "}";
        } else if (action.getPlayerIdx() == 2) {
            return "{\"command\":\"" + action.getCommand() + "\",\"output\":"
                   + playerTwo.getEnvCardsInHand() + ",\"playerIdx\":" + action.getPlayerIdx()
                   + "}";
        }

        return null;
    }

    /**
     * Generate output for getting all the frozen cards on the board in a JSON formatted string.
     *
     * @param action the action that was performed
     * @return the output of the action
     */
    public static String getFrozen(final ActionsInput action) {
        return "{\"command\":\"" + action.getCommand() + "\",\"output\":"
               + Board.getInstance().getFrozen() + "}";
    }

    /**
     * Generate output for getting the error that was encountered when a player tried to make a
     * minion card use their basic attack in a JSON formatted string.
     *
     * @param action the action that was performed
     * @param error  the error that occurred
     * @return the output of the action
     */
    public static String cardAttackError(final ActionsInput action, final String error) {
        return "{\"command\":\"" + action.getCommand() + "\",\"cardAttacker\":{\"x\":"
               + action.getCardAttacker().getX() + ",\"y\":" + action.getCardAttacker().getY()
               + "},\"cardAttacked\":{\"x\":" + action.getCardAttacked().getX() + ",\"y\":"
               + action.getCardAttacked().getY() + "},\"error\":\"" + error + "\"}";
    }

    /**
     * Generate output for getting  the error that was encountered when a player tried to make a
     * minion card use their special ability in a JSON formatted string.
     *
     * @param action the action that was performed
     * @param error  the error that occurred
     * @return the output of the action
     */
    public static String cardSpecialAttackError(final ActionsInput action, final String error) {
        return "{\"command\":\"" + action.getCommand() + "\",\"cardAttacker\":{\"x\":"
               + action.getCardAttacker().getX() + ",\"y\":" + action.getCardAttacker().getY()
               + "},\"cardAttacked\":{\"x\":" + action.getCardAttacked().getX() + ",\"y\":"
               + action.getCardAttacked().getY() + "},\"error\":\"" + error + "\"}";
    }

    /**
     * Generate output for getting the error that was encountered when a player tried to make a
     * minion card attack the enemy hero in a JSON formatted string.
     *
     * @param action the action that was performed
     * @param error  the error that occurred
     * @return the output of the action
     */
    public static String damageHeroError(final ActionsInput action, final String error) {
        return "{\"command\":\"" + action.getCommand() + "\",\"cardAttacker\":{\"x\":"
               + action.getCardAttacker().getX() + ",\"y\":" + action.getCardAttacker().getY()
               + "},\"error\":\"" + error + "\"}";
    }

    /**
     * Generate output for getting the winner of the game in a JSON formatted string.
     *
     * @param playerOne the first player
     * @param playerTwo the second player
     * @return the output of the action
     */
    public static String getWinner(final Player playerOne, final Player playerTwo) {
        if (playerOne.getCurrentHero().getHealth() <= 0) {
            playerTwo.increaseWins();

            return "{\"gameEnded\":\"Player two killed the enemy hero.\"}";
        } else if (playerTwo.getCurrentHero().getHealth() <= 0) {
            playerOne.increaseWins();

            return "{\"gameEnded\":\"Player one killed the enemy hero.\"}";
        }

        return null;
    }

    /**
     * Generate output for getting the error that was encountered when a player tried to use their
     * hero's ability in a JSON formatted string.
     *
     * @param action the action that was performed
     * @param error  the error that occurred
     * @return the output of the action
     */
    public static String useHeroAbilityError(final ActionsInput action, final String error) {
        return "{\"command\":\"" + action.getCommand() + "\",\"affectedRow\":"
               + action.getAffectedRow() + ",\"error" + "\":\"" + error + "\"}";
    }

    /**
     * Generate output for getting how many games have been played in a JSON formatted string.
     *
     * @param action the action that was performed
     * @param games  the number of games played
     * @return the output of the action
     */
    public static String getGames(final ActionsInput action, final int games) {
        return "{\"command\":\"" + action.getCommand() + "\",\"output\":" + games + "}";
    }

    /**
     * Generate output for getting how many wins a player has in a JSON formatted string.
     *
     * @param action     the action that was performed
     * @param playerWins the number of wins for the player
     * @return the output of the action
     */
    public static String getPlayerWins(final ActionsInput action, final int playerWins) {
        return "{\"command\":\"" + action.getCommand() + "\",\"output\":" + playerWins + "}";
    }
}
