package output;

import board.Board;
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
     * @param currentTurn
     * @return
     */
    public static int endTurn(final int currentTurn) {
        return currentTurn == 1 ? 2 : 1;
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
               + Board.getInstance().genOutput() + "}";
    }

    public static String getCard(final ActionsInput action) {
        if (Board.getInstance().getCardsOnBoard().get(action.getX()).isEmpty() ||
            Board.getInstance().getCardsOnBoard().get(action.getX()).get(action.getY()) == null) {
            return "{\"command\":\"" + action.getCommand() + "\",\"x\":" + action.getX() + ","
                   + "\"y\":" + action.getY() + ",\"output\":\"No card available at "
                   + "that position.\"}";
        }

        return "{\"command\":\"" + action.getCommand() + "\",\"x\":" + action.getX() + ","
               + "\"y\":" + action.getY() + ",\"output\":"
               + Board.getInstance().getCardsOnBoard().get(action.getX()).get(action.getY()) + "}";
    }
}
