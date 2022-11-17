package player;

import cards.Card;
import cards.Hero;
import fileio.CardInput;
import fileio.DecksInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class Player {
	private final ArrayList<ArrayList<Card>> decks;
	private       ArrayList<Card>            currentDeck;
	private       Hero                       currentHero;
	private       ArrayList<Card>            currentHand;

	public Player(final DecksInput decks) {
		this.decks = new ArrayList<>(decks.getNrDecks());

		for (int i = 0; i < decks.getNrDecks(); i++) {
			CardInput dummyCard;

			this.decks.add(new ArrayList<>(decks.getNrCardsInDeck()));

			for (int j = 0; j < decks.getNrCardsInDeck(); j++) {
				dummyCard = decks.getDecks().get(i).get(j);

				this.decks.get(i).add(new Card(dummyCard));
			}
		}

		this.currentDeck = null;
		this.currentHero = null;
		this.currentHand = null;
	}

	public ArrayList<ArrayList<Card>> getDecks() {
		return decks;
	}

	public ArrayList<Card> getCurrentDeck() {
		return this.currentDeck;
	}

	public void setCurrentDeck(final int currentDeckIndex, final Random shuffle) {
		this.currentDeck = new ArrayList<>(decks.get(currentDeckIndex));
		Collections.shuffle(this.currentDeck, shuffle);
	}

	public Hero getCurrentHero() {
		return currentHero;
	}

	public void setCurrentHero(final Hero currentHero) {
		this.currentHero = currentHero;
	}

	public void addToHand() {
		this.currentHand.add(this.currentDeck.remove(0));
	}

	public ArrayList<Card> getHand() {
		return this.currentHand;
	}
}
