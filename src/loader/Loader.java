package loader;

import board.BoardRows;
import fileio.DecksInput;
import fileio.Input;
import games.Game;
import org.jetbrains.annotations.NotNull;
import player.Player;

import java.util.ArrayList;

public final class Loader {
    private final Player playerOne;
    private final Player playerTwo;

    private final ArrayList<Game> games;

    /**
     * @param sessionInput
     */
    public Loader(final @NotNull Input sessionInput) {
        DecksInput dummyOne = sessionInput.getPlayerOneDecks();
        DecksInput dummyTwo = sessionInput.getPlayerTwoDecks();

        this.playerOne = new Player(
                dummyOne, BoardRows.PLAYER_ONE_FRONT, BoardRows.PLAYER_ONE_BACK);
        this.playerTwo = new Player(
                dummyTwo, BoardRows.PLAYER_TWO_FRONT, BoardRows.PLAYER_TWO_BACK);

        this.games = new ArrayList<>(sessionInput.getGames().size());

        for (int i = 0; i < sessionInput.getGames().size(); i++) {
            this.games.add(new Game(sessionInput.getGames().get(i)));
        }
    }

    /**
     *
     */
    public ArrayList<String> startSession() {
        ArrayList<String> sessionOutput = new ArrayList<>();

        for (Game game : this.games) {
            sessionOutput.addAll(game.startGame(this.playerOne, this.playerTwo));

            this.playerOne.setMana(0);
            this.playerOne.getHand().clear();

            this.playerTwo.setMana(0);
            this.playerTwo.getHand().clear();
        }

        return sessionOutput;
    }
}
