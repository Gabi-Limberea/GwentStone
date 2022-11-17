package games;

import cards.Hero;
import fileio.CardInput;
import fileio.StartGameInput;

public final class GamePrep {
	private final int  playerOneDeckIndex;
	private final int  playerTwoDeckIndex;
	private final int  shuffleSeed;
	private final Hero playerOneHero;
	private final Hero playerTwoHero;
	private final int  startingPlayer;

	public GamePrep(final StartGameInput source) {
		this.playerOneDeckIndex = source.getPlayerOneDeckIdx();
		this.playerTwoDeckIndex = source.getPlayerTwoDeckIdx();
		this.shuffleSeed = source.getShuffleSeed();
		this.startingPlayer = source.getStartingPlayer();

		CardInput dummyHeroOne = source.getPlayerOneHero();
		CardInput dummyHeroTwo = source.getPlayerTwoHero();

		this.playerOneHero = new Hero(dummyHeroOne);
		this.playerTwoHero = new Hero(dummyHeroTwo);
	}

	public int getPlayerOneDeckIndex() {
		return playerOneDeckIndex;
	}

	public int getPlayerTwoDeckIndex() {
		return playerTwoDeckIndex;
	}

	public int getShuffleSeed() {
		return shuffleSeed;
	}

	public Hero getPlayerOneHero() {
		return playerOneHero;
	}

	public Hero getPlayerTwoHero() {
		return playerTwoHero;
	}

	public int getStartingPlayer() {
		return startingPlayer;
	}
}
