package games;

import fileio.ActionsInput;
import fileio.GameInput;

import java.util.ArrayList;

public class Game {
	private final GamePrep                gamePrep;
	private final ArrayList<ActionsInput> actions;

	public Game(final GameInput source) {
		this.gamePrep = new GamePrep(source.getStartGame());
		this.actions = source.getActions();
	}
}
