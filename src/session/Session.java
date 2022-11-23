package session;

import board.BoardRows;
import fileio.DecksInput;
import fileio.Input;
import games.Game;
import player.Player;

import java.util.ArrayList;

public final class Session {
    private final Player playerOne;
    private final Player playerTwo;

    private final ArrayList<Game> games;

    /**
     * @param sessionInput the input associated for a session of games
     */
    public Session(final Input sessionInput) {
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
     * Play a session of multiple games
     *
     * @return the output of a game session.
     */
    public ArrayList<String> playSession() {
        ArrayList<String> sessionOutput = new ArrayList<>();

        for (Game game : this.games) {
            sessionOutput.addAll(game.playGame(this.playerOne, this.playerTwo));
        }

        return sessionOutput;
    }
}
