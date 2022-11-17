package board;

import cards.Card;

import java.util.ArrayList;

public class Board {
	private static       Board                      instance = null;
	private              ArrayList<ArrayList<Card>> cardsOnBoard;
	private static final int                        ROWS     = 4;
	private static final int                        COLUMNS  = 5;

	private Board() {
		cardsOnBoard = new ArrayList<>(ROWS);

		for (int i = 0; i < ROWS; i++) {
			ArrayList<Card> cardsRow = new ArrayList<>(COLUMNS);
			cardsOnBoard.set(i, cardsRow);
		}
	}

	public static Board getInstance() {
		if (instance == null) {
			instance = new Board();
		}
		return instance;
	}
}
