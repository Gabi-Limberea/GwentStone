package board;

import cards.CardAttackStatus;
import cards.heroes.HeroCard;
import cards.minions.MinionCard;
import cards.minions.MinionStates;

import java.util.ArrayList;

public final class Board {
    public static final int                              ROWS     = 4;
    public static final int                              COLUMNS  = 5;
    private static      Board                            instance = null;
    private final       ArrayList<ArrayList<MinionCard>> cardsOnBoard;

    /**
     * Constructs the Board object.
     */
    private Board() {
        cardsOnBoard = new ArrayList<>(ROWS);

        for (int i = 0; i < ROWS; i++) {
            ArrayList<MinionCard> cardsRow = new ArrayList<>(COLUMNS);
            cardsOnBoard.add(cardsRow);
        }
    }

    /**
     * @return the Board instance
     */
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    /**
     * Get all the cards that are currently placed on the board.
     *
     * @return an array of all the cards on the board
     */
    public ArrayList<ArrayList<MinionCard>> getCardsOnBoard() {
        return cardsOnBoard;
    }

    /**
     * Add a card on a specified row.
     *
     * @param card card to be added
     * @param row  row where the card will be added
     */
    public void addCardOnRow(final MinionCard card, final int row) {
        cardsOnBoard.get(row).add(card);
    }

    /**
     * Fully clear the board of all cards.
     */
    public void resetBoard() {
        for (int i = 0; i < ROWS; i++) {
            cardsOnBoard.get(i).clear();
            cardsOnBoard.get(i).ensureCapacity(COLUMNS);
        }
    }

    /**
     * Create an array comprised of all the frozen cards on the board.
     *
     * @return the frozen cards array.
     */
    public ArrayList<MinionCard> getFrozen() {
        ArrayList<MinionCard> frozen = new ArrayList<>();

        for (ArrayList<MinionCard> row : cardsOnBoard) {
            for (MinionCard card : row) {
                if (card.getState() == MinionStates.FROZEN) {
                    frozen.add(card);
                }
            }
        }

        return frozen;
    }

    /**
     * Unfreeze all minions on the board that have been frozen for a turn.
     */
    public void unfreeze(final int turnCounter) {
        for (ArrayList<MinionCard> row : cardsOnBoard) {
            row.forEach(card -> {
                if (card.getState() == MinionStates.FROZEN
                    && card.getState().getFreezeTimeStamp() == turnCounter - 2) {
                    card.setState(MinionStates.ACTIVE);
                }
            });
        }
    }

    /**
     * Reset all minions on the board that have attacked.
     */
    public void resetAttackStatus(HeroCard HeroOne, HeroCard HeroTwo) {
        for (ArrayList<MinionCard> row : cardsOnBoard) {
            row.forEach(card -> card.setAttackStatus(CardAttackStatus.HAS_NOT_ATTACKED));
        }

        HeroOne.setAttackStatus(CardAttackStatus.HAS_NOT_ATTACKED);
        HeroTwo.setAttackStatus(CardAttackStatus.HAS_NOT_ATTACKED);
    }
}
