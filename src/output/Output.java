package output;

import board.Board;
import errors.Errors;
import fileio.ActionsInput;
import player.Player;

public final class Output {
    private Output() {

    }

    /**
     * @param action
     * @param playerOne
     * @param playerTwo
     * @return
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
     * @param action
     * @param playerOne
     * @param playerTwo
     * @return
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
     * @param action
     * @param currentTurn
     * @return
     */
    public static String getTurn(final ActionsInput action, final int currentTurn) {
        return "{\"command\":\"" + action.getCommand() + "\",\"output\":" + currentTurn + "}";
    }

    /**
     * @param action
     * @param playerOne
     * @param playerTwo
     * @return
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
     * @param action
     * @return
     */
    public static String getBoard(final ActionsInput action) {
        return "{\"command\":\"" + action.getCommand() + "\",\"output\":"
               + Board.getInstance().getCardsOnBoard() + "}";
    }

    /**
     * @param action
     * @return
     */
    public static String getCard(final ActionsInput action)     {
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
     * @param action
     * @param error
     * @return
     */
    public static String placeCardError(final ActionsInput action, final String error) {
        return "{\"command\":\"" + action.getCommand() + "\",\"handIdx\":" + action.getHandIdx()
               + ",\"error" + "\":\"" + error + "\"}";
    }

    /**
     * @param action
     * @param playerOne
     * @param playerTwo
     * @return
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
     * @param action
     * @param error
     * @return
     */
    public static String useEnvCardError(final ActionsInput action, final String error) {
        return "{\"command\":\"" + action.getCommand() + "\",\"handIdx\":" + action.getHandIdx()
               + ",\"affectedRow\":" + action.getAffectedRow() + ",\"error" + "\":\"" + error
               + "\"}";
    }

    /**
     * @param action
     * @param playerOne
     * @param playerTwo
     * @return
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
     * @param action
     * @return
     */
    public static String getFrozen(final ActionsInput action) {
        return "{\"command\":\"" + action.getCommand() + "\",\"output\":"
               + Board.getInstance().getFrozen() + "}";
    }

    /**
     * @param action
     * @param error
     * @return
     */
    public static String cardAttackError(final ActionsInput action, final String error) {
        return "{\"command\":\"" + action.getCommand() + "\",\"cardAttacker\":{\"x\":"
               + action.getCardAttacker().getX() + ",\"y\":" + action.getCardAttacker().getY()
               + "},\"cardAttacked\":{\"x\":" + action.getCardAttacked().getX() + ",\"y\":"
               + action.getCardAttacked().getY() + "},\"error\":\"" + error + "\"}";
    }

    /**
     * @param action
     * @param error
     * @return
     */
    public static String cardSpecialAttackError(final ActionsInput action, final String error) {
        return "{\"command\":\"" + action.getCommand() + "\",\"cardAttacker\":{\"x\":"
               + action.getCardAttacker().getX() + ",\"y\":" + action.getCardAttacker().getY()
               + "},\"cardAttacked\":{\"x\":" + action.getCardAttacked().getX() + ",\"y\":"
               + action.getCardAttacked().getY() + "},\"error\":\"" + error + "\"}";
    }

    /**
     * @param action
     * @param error
     * @return
     */
    public static String damageHeroError(final ActionsInput action, final String error) {
        return "{\"command\":\"" + action.getCommand() + "\",\"cardAttacker\":{\"x\":"
               + action.getCardAttacker().getX() + ",\"y\":" + action.getCardAttacker().getY()
               + "},\"error\":\"" + error + "\"}";
    }

    /**
     * @param playerOne
     * @param playerTwo
     * @return
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
     * @param action
     * @param error
     * @return
     */
    public static String useHeroAbilityError(final ActionsInput action, final String error) {
        return "{\"command\":\"" + action.getCommand() + "\",\"affectedRow\":"
               + action.getAffectedRow() + ",\"error" + "\":\"" + error + "\"}";
    }

    /**
     * @param action
     * @param games
     * @return
     */
    public static String getGames(final ActionsInput action, final int games) {
        return "{\"command\":\"" + action.getCommand() + "\",\"output\":" + games + "}";
    }

    /**
     * @param action
     * @param playerWins
     * @return
     */
    public static String getPlayerWins(final ActionsInput action, final int playerWins) {
        return "{\"command\":\"" + action.getCommand() + "\",\"output\":" + playerWins + "}";
    }
}
