package games;

import board.Board;
import fileio.ActionsInput;
import fileio.GameInput;
import org.jetbrains.annotations.NotNull;
import output.Output;
import player.Player;

import java.util.ArrayList;
import java.util.Random;

public final class Game {
    private final GamePrep gamePrep;
    private final ArrayList<ActionsInput> actions;

    /**
     * @param source
     */
    public Game(final @NotNull GameInput source) {
        this.gamePrep = new GamePrep(source.getStartGame());
        this.actions = source.getActions();
    }

    /**
     * @param playerOne
     * @param playerTwo
     * @return
     */
    public ArrayList<String> startGame(
            final @NotNull Player playerOne, final @NotNull Player playerTwo
                                      ) {
        ArrayList<String> gameOutput = new ArrayList<>();
        Board currentBoard = Board.getInstance();
        //        Random shuffle = new Random(gamePrep.getShuffleSeed());
        int currentTurn = gamePrep.getStartingPlayer();
        int turnCounter = 1;

        playerOne.setCurrentDeck(
                gamePrep.getPlayerOneDeckIndex(), new Random(gamePrep.getShuffleSeed()));
        playerOne.setCurrentHero(gamePrep.getPlayerOneHero());

        playerTwo.setCurrentDeck(
                gamePrep.getPlayerTwoDeckIndex(), new Random(gamePrep.getShuffleSeed()));
        playerTwo.setCurrentHero(gamePrep.getPlayerTwoHero());

        playerOne.addToHand();
        playerOne.increaseMana(turnCounter);

        playerTwo.addToHand();
        playerTwo.increaseMana(turnCounter);

        for (ActionsInput action : actions) {
            if (playerOne.getCurrentHero().getHealth() <= 0
                || playerTwo.getCurrentHero().getHealth() <= 0) {
                break;
            }

            switch (Actions.getCommand(action.getCommand())) {
                case GET_HERO -> gameOutput.add(Output.getHero(action, playerOne, playerTwo));
                case GET_DECK -> gameOutput.add(Output.getDeck(action, playerOne, playerTwo));
                case GET_HAND -> gameOutput.add(Output.getHand(action, playerOne, playerTwo));
                case GET_TURN -> gameOutput.add(Output.getTurn(action, currentTurn));
                case GET_TABLE -> gameOutput.add(Output.getBoard(action));
                case GET_CARD -> gameOutput.add(Output.getCard(action));
                case END_TURN -> {
                    currentTurn = Output.endTurn(currentTurn);
                    turnCounter++;

                    playerOne.addToHand();
                    playerOne.increaseMana(turnCounter);

                    playerTwo.addToHand();
                    playerTwo.increaseMana(turnCounter);
                }
                default -> {

                }
            }

            System.out.println(gameOutput);
        }

        currentBoard.resetBoard();
        return gameOutput;
    }
}
