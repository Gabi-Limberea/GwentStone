package board;

import cards.Card;

import java.util.ArrayList;

public final class Board {
    public static final int                        ROWS     = 4;
    public static final int                        COLUMNS  = 5;
    private static      Board                      instance = null;
    private final       ArrayList<ArrayList<Card>> cardsOnBoard;

    /**
     *
     */
    private Board() {
        cardsOnBoard = new ArrayList<>(ROWS);

        for (int i = 0; i < ROWS; i++) {
            ArrayList<Card> cardsRow = new ArrayList<>(COLUMNS);
            cardsOnBoard.add(cardsRow);
        }
    }

    /**
     * @return
     */
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    /**
     * @return
     */
    public ArrayList<ArrayList<Card>> getCardsOnBoard() {
        return cardsOnBoard;
    }

    /**
     * @param card
     * @param row
     */
    public void addCardOnRow(final Card card, final int row) {
        cardsOnBoard.get(row).add(card);
    }

    /**
     *
     */
    public void resetBoard() {
        for (int i = 0; i < ROWS; i++) {
            cardsOnBoard.get(i).clear();
            cardsOnBoard.get(i).ensureCapacity(COLUMNS);
        }
    }

    /**
     * @return
     */
    public ArrayList<String> genOutput() {
        ArrayList<String> output = new ArrayList<>();

        for (int i = 0; i < ROWS; i++) {
            for (Card card : cardsOnBoard.get(i)) {
                output.add(card.toString());
            }
        }

        return output;
    }
}
