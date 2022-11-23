package player;

import board.Board;
import board.BoardRows;
import cards.Card;
import cards.CardAttackStatus;
import cards.environments.EnvironmentCard;
import cards.environments.EnvironmentTypes;
import cards.heroes.HeroCard;
import cards.heroes.HeroTypes;
import cards.minions.MinionCard;
import cards.minions.MinionRow;
import cards.minions.MinionStates;
import cards.minions.MinionTypes;
import cards.minions.SpecialMinionCard;
import errors.Errors;
import fileio.CardInput;
import fileio.Coordinates;
import fileio.DecksInput;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class Player {
    private static final int                        MAX_MANA = 10;
    private final        ArrayList<ArrayList<Card>> decks;
    private final        BoardRows                  frontRow;
    private final        BoardRows                  backRow;
    private final        ArrayList<Card>            currentHand;
    private              ArrayList<Card>            currentDeck;
    private              HeroCard                   currentHero;
    private              int                        mana;
    private              int                        wins;


    /**
     * @param decks the decks of the player
     * @param front The index of the front row associated with the player
     * @param back  The index of the back row associated with the player
     */
    public Player(final @NotNull DecksInput decks, final BoardRows front, final BoardRows back) {
        this.wins = 0;
        this.decks = new ArrayList<>(decks.getNrDecks());

        for (int i = 0; i < decks.getNrDecks(); i++) {
            CardInput dummyCard;

            this.decks.add(new ArrayList<>(decks.getNrCardsInDeck()));

            for (int j = 0; j < decks.getNrCardsInDeck(); j++) {
                dummyCard = decks.getDecks().get(i).get(j);

                switch (MinionTypes.getMinionType(dummyCard.getName())) {
                    case SENTINEL -> {
                        this.decks.get(i).add(new cards.minions.basic.Sentinel(dummyCard));
                        continue;
                    }
                    case BERSERKER -> {
                        this.decks.get(i).add(new cards.minions.basic.Berserker(dummyCard));
                        continue;
                    }
                    case GOLIATH -> {
                        this.decks.get(i).add(new cards.minions.basic.Goliath(dummyCard));
                        continue;
                    }
                    case WARDEN -> {
                        this.decks.get(i).add(new cards.minions.basic.Warden(dummyCard));
                        continue;
                    }
                    case RIPPER -> {
                        this.decks.get(i).add(new cards.minions.special.TheRipper(dummyCard));
                        continue;
                    }
                    case MIRAJ -> {
                        this.decks.get(i).add(new cards.minions.special.Miraj(dummyCard));
                        continue;
                    }
                    case DISCIPLE -> {
                        this.decks.get(i).add(new cards.minions.special.Disciple(dummyCard));
                        continue;
                    }
                    case CURSED_ONE -> {
                        this.decks.get(i).add(new cards.minions.special.TheCursedOne(dummyCard));
                        continue;
                    }
                    default -> {
                    }
                }

                switch (EnvironmentTypes.getEnvironmentType(dummyCard.getName())) {
                    case WINTERFELL ->
                            this.decks.get(i).add(new cards.environments.Winterfell(dummyCard));
                    case FIRESTORM ->
                            this.decks.get(i).add(new cards.environments.Firestorm(dummyCard));
                    case HEART_HOUND ->
                            this.decks.get(i).add(new cards.environments.HeartHound(dummyCard));
                    default -> {
                    }
                }
            }
        }

        this.currentDeck = null;
        this.currentHero = null;
        this.currentHand = new ArrayList<>();
        this.frontRow = front;
        this.backRow = back;
        this.mana = 0;
    }

    /**
     * Increase te number of wins for the player by 1
     */
    public void increaseWins() {
        this.wins++;
    }

    /**
     * Get how many games the player has won
     *
     * @return the number of wins of the player
     */
    public int getWins() {
        return wins;
    }

    /**
     * Get all the decks that the player has
     *
     * @return the array of decks belonging to the player
     */
    public ArrayList<ArrayList<Card>> getDecks() {
        return decks;
    }

    /**
     * @return the current deck of the player
     */
    public ArrayList<Card> getCurrentDeck() {
        return this.currentDeck;
    }

    /**
     * Set the current deck of the player.
     *
     * @param currentDeckIndex the index of the deck that the player wants to use
     * @param shuffle          the Random object used to shuffle the deck
     */
    public void setCurrentDeck(final int currentDeckIndex, final Random shuffle) {
        this.currentDeck = new ArrayList<>(decks.get(currentDeckIndex).size());

        for (int i = 0; i < decks.get(currentDeckIndex).size(); i++) {
            Card dummyCard = decks.get(currentDeckIndex).get(i);

            switch (MinionTypes.getMinionType(dummyCard.getName())) {
                case SENTINEL -> {
                    this.currentDeck.add(new cards.minions.basic.Sentinel((MinionCard) dummyCard));
                    continue;
                }
                case BERSERKER -> {
                    this.currentDeck.add(new cards.minions.basic.Berserker((MinionCard) dummyCard));
                    continue;
                }
                case GOLIATH -> {
                    this.currentDeck.add(new cards.minions.basic.Goliath((MinionCard) dummyCard));
                    continue;
                }
                case WARDEN -> {
                    this.currentDeck.add(new cards.minions.basic.Warden((MinionCard) dummyCard));
                    continue;
                }
                case RIPPER -> {
                    this.currentDeck.add(
                            new cards.minions.special.TheRipper((MinionCard) dummyCard));
                    continue;
                }
                case MIRAJ -> {
                    this.currentDeck.add(new cards.minions.special.Miraj((MinionCard) dummyCard));
                    continue;
                }
                case DISCIPLE -> {
                    this.currentDeck.add(
                            new cards.minions.special.Disciple((MinionCard) dummyCard));
                    continue;
                }
                case CURSED_ONE -> {
                    this.currentDeck.add(
                            new cards.minions.special.TheCursedOne((MinionCard) dummyCard));
                    continue;
                }
                default -> {
                }
            }

            switch (EnvironmentTypes.getEnvironmentType(dummyCard.getName())) {
                case WINTERFELL -> this.currentDeck.add(
                        new cards.environments.Winterfell((EnvironmentCard) dummyCard));
                case FIRESTORM -> this.currentDeck.add(
                        new cards.environments.Firestorm((EnvironmentCard) dummyCard));
                case HEART_HOUND -> this.currentDeck.add(
                        new cards.environments.HeartHound((EnvironmentCard) dummyCard));
                default -> {
                }
            }
        }

        Collections.shuffle(this.currentDeck, shuffle);

        System.out.println(this.currentDeck == decks.get(currentDeckIndex));
    }

    /**
     * Get the hero that the player is using in the current game
     *
     * @return the hero that the player is currently using
     */
    public HeroCard getCurrentHero() {
        return currentHero;
    }

    /**
     * Set the hero that the player wants to use for the current game
     *
     * @param hero the hero that the player wants to use in the current game
     */
    public void setCurrentHero(final HeroCard hero) {
        this.currentHero = hero;
    }

    /**
     * Draw a card from the deck and add it to the player's hand.
     */
    public void addToHand() {
        if (this.currentDeck.size() == 0) {
            return;
        }

        this.currentHand.add(this.currentDeck.remove(0));
    }

    /**
     * Get the cards that the player has in their hand.
     *
     * @return the current hand of the player
     */
    public ArrayList<Card> getHand() {
        return this.currentHand;
    }

    /**
     * Get how much mana the player has.
     *
     * @return how much mana the player has
     */
    public int getMana() {
        return mana;
    }

    /**
     * Set how much mana the player has.
     *
     * @param mana the amount of mana the player will have
     */
    public void setMana(int mana) {
        this.mana = mana;
    }


    /**
     * Increase how much mana the player has by a given amount.
     *
     * @param increase with how much the player's mana will be increased
     */
    public void increaseMana(final int increase) {
        if (increase >= MAX_MANA) {
            this.mana = MAX_MANA;
            return;
        }

        this.mana += increase;
    }

    /**
     * Place a card from the player's hand on the board.
     *
     * @param cardIndex the index of the card (in hand) that the player wants to play
     * @return an error if one was encountered
     */
    public Errors placeCard(final int cardIndex) {
        Card card = this.currentHand.get(cardIndex);

        if (EnvironmentTypes.getEnvironmentType(card.getName()) != EnvironmentTypes.NOTHING) {
            return Errors.ENV_CARD_ON_TABLE;
        }

        MinionCard minion = (MinionCard) card;

        if (card.getMana() > this.mana) {
            System.out.println("Not enough mana");
            return Errors.NO_MANA_PLACE_CARD;
        }

        int row;

        if (minion.getRow() == MinionRow.FRONT) {
            row = this.frontRow.ordinal();
        } else {
            row = this.backRow.ordinal();
        }

        if (Board.getInstance().getCardsOnBoard().get(row).size() == Board.COLUMNS) {
            return Errors.ROW_FULL;
        }

        Board.getInstance().addCardOnRow(minion, row);
        this.currentHand.remove(cardIndex);
        this.mana -= card.getMana();

        return Errors.NONE;
    }

    /**
     * Use an environment card from the player's hand on one of the enemy's rows.
     *
     * @param cardIndex   the index of the environment card (in hand) that the player wants to play
     * @param affectedRow the row that the environment card will affect
     * @return an error if one was encountered
     */
    public Errors useEnvCard(final int cardIndex, final int affectedRow, final int turnCounter) {
        ArrayList<ArrayList<MinionCard>> cardsOnBoard = Board.getInstance().getCardsOnBoard();

        Card card = this.currentHand.get(cardIndex);

        if (EnvironmentTypes.getEnvironmentType(card.getName()) == EnvironmentTypes.NOTHING) {
            return Errors.NOT_ENV_CARD;
        }

        EnvironmentCard envCard = (EnvironmentCard) card;

        if (card.getMana() > this.mana) {
            System.out.println("Not enough mana to use environment card.");
            return Errors.NO_MANA_USE_ENV;
        }

        if (affectedRow == this.frontRow.ordinal() || affectedRow == this.backRow.ordinal()) {
            return Errors.NOT_ENEMY_ROW;
        }

        if (EnvironmentTypes.getEnvironmentType(card.getName()) == EnvironmentTypes.HEART_HOUND) {
            if (cardsOnBoard.get(affectedRow).size() == Board.COLUMNS) {
                return Errors.ROW_FULL_STEAL;
            }
        }

        this.mana -= card.getMana();
        envCard.use(cardsOnBoard.get(affectedRow));
        this.currentHand.remove(cardIndex);

        cardsOnBoard.get(affectedRow).forEach(
                (minionCard -> minionCard.getState().setFreezeTimeStamp(turnCounter)));

        return Errors.NONE;
    }

    /**
     * Get all the environment cards that the player has in their hand.
     *
     * @return a list of environment cards that the player has in their hand
     */
    public ArrayList<EnvironmentCard> getEnvCardsInHand() {
        ArrayList<EnvironmentCard> envCards = new ArrayList<>();

        for (Card card : this.currentHand) {
            if (EnvironmentTypes.getEnvironmentType(card.getName()) != EnvironmentTypes.NOTHING) {
                envCards.add((EnvironmentCard) card);
            }
        }

        return envCards;
    }


    /**
     * Make a card from the board attack another card.
     *
     * @param cardAttacker the board coordinates for the card that is attacking
     * @param cardAttacked the board coordinates for the card that is being attacked
     * @return an error if one was encountered
     */
    public Errors cardAttack(final Coordinates cardAttacker, final Coordinates cardAttacked) {
        ArrayList<ArrayList<MinionCard>> cardsOnBoard = Board.getInstance().getCardsOnBoard();
        int attackerRow = cardAttacker.getX();
        int attackedRow = cardAttacked.getX();
        int attackerColumn = cardAttacker.getY();
        int attackedColumn = cardAttacked.getY();

        if (this.frontRow.ordinal() == attackedRow || this.backRow.ordinal() == attackedRow) {
            return Errors.NOT_ENEMY_CARD;
        }

        Errors error = checkAttackConditionsStatusFirst(attackerRow, attackerColumn);
        if (error != Errors.NONE) {
            return error;
        }

        error = checkForTank(attackedRow, attackedColumn);
        if (error != Errors.NONE) {
            return error;
        }

        MinionCard attacker = cardsOnBoard.get(attackerRow).get(attackerColumn);
        MinionCard attacked = cardsOnBoard.get(attackedRow).get(attackedColumn);

        attacker.basicAttack(attacked);

        attackCleanup(attackedRow, attackedColumn, attackerRow, attackerColumn);

        return Errors.NONE;
    }

    /**
     * Make a card from the board use their special attack on another card.
     *
     * @param cardAttacker the board coordinates for the card that is attacking
     * @param cardAttacked the board coordinates for the card that is being attacked
     * @return an error if one was encountered
     */
    public Errors cardSpecialAttack(
            final Coordinates cardAttacker, final Coordinates cardAttacked
                                   ) {
        ArrayList<ArrayList<MinionCard>> cardsOnBoard = Board.getInstance().getCardsOnBoard();
        int attackerRow = cardAttacker.getX();
        int attackedRow = cardAttacked.getX();
        int attackerColumn = cardAttacker.getY();
        int attackedColumn = cardAttacked.getY();

        Errors error = checkAttackConditionsFrozenFirst(attackerRow, attackerColumn);
        if (error != Errors.NONE) {
            return error;
        }

        MinionCard attacker = cardsOnBoard.get(attackerRow).get(attackerColumn);
        MinionCard attacked = cardsOnBoard.get(attackedRow).get(attackedColumn);

        if (MinionTypes.getMinionType(attacker.getName()) == MinionTypes.DISCIPLE) {
            if (attackedRow != this.frontRow.ordinal() && attackedRow != this.backRow.ordinal()) {
                return Errors.NOT_CURRENT_PLAYER_ATTACKED_CARD;
            }
        } else {
            if (this.frontRow.ordinal() == attackedRow || this.backRow.ordinal() == attackedRow) {
                return Errors.NOT_ENEMY_CARD;
            }

            error = checkForTank(attackedRow, attackedColumn);
            if (error != Errors.NONE) {
                return error;
            }
        }

        SpecialMinionCard specialAttacker = (SpecialMinionCard) attacker;

        specialAttacker.specialAttack(attacked);

        attackCleanup(attackedRow, attackedColumn, attackerRow, attackerColumn);
        return Errors.NONE;
    }

    /**
     * Check if the card that is attacking has already attacked or frozen (in this order).
     *
     * @param attackerRow    the row on which the attacker card is located
     * @param attackerColumn the column on which the attacker card is located
     * @return an error if one was encountered
     */
    private Errors checkAttackConditionsStatusFirst(
            final int attackerRow, final int attackerColumn
                                                   ) {
        if (Board.getInstance().getCardsOnBoard().get(attackerRow).get(
                attackerColumn).getAttackStatus() == CardAttackStatus.HAS_ATTACKED) {
            return Errors.CARD_ALREADY_ATTACKED;
        }

        if (Board.getInstance().getCardsOnBoard().get(attackerRow).get(attackerColumn).getState()
            == MinionStates.FROZEN) {
            return Errors.CARD_FROZEN;
        }

        return Errors.NONE;
    }

    /**
     * Check if the card that is attacking is frozen or has already attacked (in this order).
     *
     * @param attackerRow    the row on which the attacker card is located
     * @param attackerColumn the column on which the attacker card is located
     * @return an error if one was encountered
     */
    private Errors checkAttackConditionsFrozenFirst(
            final int attackerRow, final int attackerColumn
                                                   ) {
        if (Board.getInstance().getCardsOnBoard().get(attackerRow).get(attackerColumn).getState()
            == MinionStates.FROZEN) {
            return Errors.CARD_FROZEN;
        }

        if (Board.getInstance().getCardsOnBoard().get(attackerRow).get(
                attackerColumn).getAttackStatus() == CardAttackStatus.HAS_ATTACKED) {
            return Errors.CARD_ALREADY_ATTACKED;
        }

        return Errors.NONE;
    }

    /**
     * Make sure that if the attacked card is dead, it is removed from the board, and set that
     * the attacker card has been used this round.
     *
     * @param attackedRow    the row on which the attacked card is located
     * @param attackedColumn the column on which the attacked card is located
     * @param attackerRow    the row on which the attacker card is located
     * @param attackerColumn the column on which the attacker card is located
     */
    private void attackCleanup(
            final int attackedRow, final int attackedColumn, final int attackerRow,
            final int attackerColumn
                              ) {
        if (Board.getInstance().getCardsOnBoard().get(attackedRow).get(attackedColumn).getHealth()
            <= 0) {
            Board.getInstance().getCardsOnBoard().get(attackedRow).remove(attackedColumn);
        }

        Board.getInstance().getCardsOnBoard().get(attackerRow).get(attackerColumn).setAttackStatus(
                CardAttackStatus.HAS_ATTACKED);
    }

    /**
     * Check if there are tanks on the board and the attacked card is ones.
     *
     * @param attackedRow    the row on which the attacked card is located
     * @param attackedColumn the column on which the attacked card is located
     * @return an error if one was encountered
     */
    private Errors checkForTank(final int attackedRow, final int attackedColumn) {
        ArrayList<ArrayList<MinionCard>> cardsOnBoard = Board.getInstance().getCardsOnBoard();

        boolean foundADifferentTank = false;

        for (int i = 0;
             i < cardsOnBoard.get(Board.ROWS - this.frontRow.ordinal() - 1).size(); i++) {
            if (cardsOnBoard.get(Board.ROWS - this.frontRow.ordinal() - 1).get(i).isTank()) {
                if (Board.ROWS - this.frontRow.ordinal() - 1 != attackedRow || i != attackedColumn)
                    foundADifferentTank = true;
                else return Errors.NONE;
            }
        }

        if (foundADifferentTank) {
            return Errors.CARD_NOT_TANK;
        }

        return Errors.NONE;
    }

    /**
     * Attack the enemy hero with one of the player's cards on the board.
     *
     * @param cardAttacker the coordinates of the card that is attacking
     * @param enemyHero    the enemy hero
     * @return an error if one was encountered
     */
    public Errors damageHero(final Coordinates cardAttacker, HeroCard enemyHero) {
        ArrayList<ArrayList<MinionCard>> cardsOnBoard = Board.getInstance().getCardsOnBoard();
        int attackerRow = cardAttacker.getX();
        int attackerColumn = cardAttacker.getY();

        Errors error = checkAttackConditionsFrozenFirst(attackerRow, attackerColumn);
        if (error != Errors.NONE) {
            return error;
        }

        error = checkForTank(attackerRow, attackerColumn);
        if (error != Errors.NONE) {
            return error;
        }

        MinionCard attacker = cardsOnBoard.get(attackerRow).get(attackerColumn);
        attacker.heroAttack(enemyHero);

        attacker.setAttackStatus(CardAttackStatus.HAS_ATTACKED);

        return Errors.NONE;
    }

    /**
     * Use the player's current hero's ability on an enemy card row.
     *
     * @param affectedRow the row that will be affected by the hero's ability
     * @return an error if one was encountered
     */
    public Errors useHeroAbility(final int affectedRow) {
        if (this.mana < this.currentHero.getMana()) {
            return Errors.NO_MANA_HERO_ABILITY;
        }

        if (this.currentHero.getAttackStatus() == CardAttackStatus.HAS_ATTACKED) {
            return Errors.HERO_ALREADY_ATTACKED;
        }

        HeroTypes heroType = HeroTypes.getHeroType(this.currentHero.getName());
        if (heroType == HeroTypes.THORINA || heroType == HeroTypes.ROYCE) {
            if (affectedRow == this.frontRow.ordinal() || affectedRow == this.backRow.ordinal()) {
                return Errors.NOT_ENEMY_ROW_HERO_ABILITY;
            }
        } else if (heroType == HeroTypes.KOCIORAW || heroType == HeroTypes.MUDFACE) {
            if (!(affectedRow == this.frontRow.ordinal()
                  || affectedRow == this.backRow.ordinal())) {
                return Errors.NOT_CURRENT_PLAYER_ROW_HERO_ABILITY;
            }
        }

        ArrayList<MinionCard> cardsOnRow = Board.getInstance().getCardsOnBoard().get(affectedRow);

        this.mana -= this.currentHero.getMana();
        this.currentHero.useAbility(cardsOnRow);
        this.currentHero.setAttackStatus(CardAttackStatus.HAS_ATTACKED);

        return Errors.NONE;
    }
}
