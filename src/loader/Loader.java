package loader;

import fileio.DecksInput;
import fileio.Input;
import games.Game;
import player.Player;

import java.util.ArrayList;

public final class Loader {
	private final Player playerOne;
	private final Player playerTwo;

	private final ArrayList<Game> games;

	public Loader(final Input sessionInput) {
		DecksInput dummyOne = sessionInput.getPlayerOneDecks();
		DecksInput dummyTwo = sessionInput.getPlayerTwoDecks();

		this.playerOne = new Player(dummyOne);
		this.playerTwo = new Player(dummyTwo);

		this.games = new ArrayList<>(sessionInput.getGames().size());

		for (int i = 0; i < sessionInput.getGames().size(); i++) {
			this.games.add(new Game(sessionInput.getGames().get(i)));
		}
	}

	public void startSession() {

	}
}
