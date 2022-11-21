package player;

import board.BoardRows;
import cards.Card;
import cards.environments.EnvironmentTypes;
import cards.heroes.HeroCard;
import cards.minions.MinionTypes;
import fileio.CardInput;
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
    private              ArrayList<Card>            currentHand;
    private              ArrayList<Card>            currentDeck;
    private              HeroCard                   currentHero;
    private              int                        mana;

    /**
     * @param decks
     */
    public Player(final @NotNull DecksInput decks, final BoardRows front, final BoardRows back) {
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
     * @return
     */
    public ArrayList<ArrayList<Card>> getDecks() {
        return decks;
    }

    /**
     * @return
     */
    public ArrayList<Card> getCurrentDeck() {
        return this.currentDeck;
    }

    /**
     * @param currentDeckIndex
     * @param shuffle
     */
    public void setCurrentDeck(final int currentDeckIndex, final Random shuffle) {
        this.currentDeck = new ArrayList<>(decks.get(currentDeckIndex));
        Collections.shuffle(this.currentDeck, shuffle);
    }

    /**
     * @return
     */
    public HeroCard getCurrentHero() {
        return currentHero;
    }

    /**
     * @param hero
     */
    public void setCurrentHero(final HeroCard hero) {
        this.currentHero = hero;
    }

    /**
     *
     */
    public void addToHand() {
        if (this.currentDeck.size() == 0) {
            return;
        }

        this.currentHand.add(this.currentDeck.remove(0));
    }

    /**
     * @return
     */
    public ArrayList<Card> getHand() {
        return this.currentHand;
    }

    /**
     * @param increase
     */
    public void increaseMana(final int increase) {
        if (this.mana == MAX_MANA) {
            return;
        }

        this.mana += increase;
    }

    /**
     * @return
     */
    public BoardRows getFrontRow() {
        return frontRow;
    }

    /**
     * @return
     */
    public BoardRows getBackRow() {
        return backRow;
    }
}
