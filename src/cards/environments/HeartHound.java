package cards.environments;

import board.Board;
import cards.minions.MinionCard;
import fileio.CardInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class HeartHound extends EnvironmentCard {
    /**
     * @param source
     */
    public HeartHound(final CardInput source) {
        super(source);
    }

    /**
     * @param source
     */
    public HeartHound(final EnvironmentCard source) {
        super(source);
    }

    /**
     * @param targetRow
     */
    @Override
    public void use(final ArrayList<MinionCard> targetRow) {
        MinionCard maxHealthMinion = Collections.max(
                targetRow, Comparator.comparingInt(MinionCard::getHealth));
        int column = 0, row = 0;

        for (int i = 0; i < Board.ROWS; i++) {
            ArrayList<MinionCard> minionRow = Board.getInstance().getCardsOnBoard().get(i);

            for (int j = 0; j < minionRow.size(); j++) {
                if (minionRow.get(j) == maxHealthMinion) {
                    column = j;
                    row = i;
                }
            }
        }

        Board.getInstance().getCardsOnBoard().get(Board.ROWS - row - 1).add(column,
                                                                            maxHealthMinion
                                                                           );
        Board.getInstance().getCardsOnBoard().get(row).remove(maxHealthMinion);
    }
}
