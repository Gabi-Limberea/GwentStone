package games;

import cards.heroes.HeroCard;
import cards.heroes.HeroTypes;
import fileio.CardInput;
import fileio.StartGameInput;

public final class GamePrep {
    private final int      playerOneDeckIndex;
    private final int      playerTwoDeckIndex;
    private final int      shuffleSeed;
    private final HeroCard playerOneHero;
    private final HeroCard playerTwoHero;
    private final int      startingPlayer;

    /**
     * @param source the input for creating the game preparations.
     */
    public GamePrep(final StartGameInput source) {
        this.playerOneDeckIndex = source.getPlayerOneDeckIdx();
        this.playerTwoDeckIndex = source.getPlayerTwoDeckIdx();
        this.shuffleSeed = source.getShuffleSeed();
        this.startingPlayer = source.getStartingPlayer();

        CardInput dummyHeroOne = source.getPlayerOneHero();
        CardInput dummyHeroTwo = source.getPlayerTwoHero();

        switch (HeroTypes.getHeroType(dummyHeroOne.getName())) {
            case ROYCE -> {
                cards.heroes.HeroCard hero = new cards.heroes.LordRoyce(dummyHeroOne);
                this.playerOneHero = hero;
            }
            case THORINA -> {
                cards.heroes.HeroCard hero = new cards.heroes.EmpressThorina(dummyHeroOne);
                this.playerOneHero = hero;
            }
            case MUDFACE -> {
                cards.heroes.HeroCard hero = new cards.heroes.KingMudface(dummyHeroOne);
                this.playerOneHero = hero;
            }
            case KOCIORAW -> {
                cards.heroes.HeroCard hero = new cards.heroes.GeneralKocioraw(dummyHeroOne);
                this.playerOneHero = hero;
            }
            default ->
                    throw new IllegalStateException("Unexpected value: " + dummyHeroOne.getName());
        }

        switch (HeroTypes.getHeroType(dummyHeroTwo.getName())) {
            case ROYCE -> {
                cards.heroes.HeroCard hero = new cards.heroes.LordRoyce(dummyHeroTwo);
                this.playerTwoHero = hero;
            }
            case THORINA -> {
                cards.heroes.HeroCard hero = new cards.heroes.EmpressThorina(dummyHeroTwo);
                this.playerTwoHero = hero;
            }
            case MUDFACE -> {
                cards.heroes.HeroCard hero = new cards.heroes.KingMudface(dummyHeroTwo);
                this.playerTwoHero = hero;
            }
            case KOCIORAW -> {
                cards.heroes.HeroCard hero = new cards.heroes.GeneralKocioraw(dummyHeroTwo);
                this.playerTwoHero = hero;
            }
            default ->
                    throw new IllegalStateException("Unexpected value: " + dummyHeroTwo.getName());
        }
    }

    /**
     * Get the index of the first player's deck.
     *
     * @return the index of the first player's deck.
     */
    public int getPlayerOneDeckIndex() {
        return this.playerOneDeckIndex;
    }

    /**
     * Get the index of the second player's deck.
     *
     * @return the index of the second player's deck.
     */
    public int getPlayerTwoDeckIndex() {
        return playerTwoDeckIndex;
    }

    /**
     * Get the seed used for shuffling the players' decks.
     *
     * @return the seed used for shuffling the decks.
     */
    public int getShuffleSeed() {
        return shuffleSeed;
    }

    /**
     * Get the first player's hero.
     *
     * @return the hero of the first player.
     */
    public HeroCard getPlayerOneHero() {
        return playerOneHero;
    }

    /**
     * Get the second player's hero.
     *
     * @return the hero of the second player.
     */
    public HeroCard getPlayerTwoHero() {
        return playerTwoHero;
    }

    /**
     * Get the player who starts the game.
     *
     * @return the index of the starting player
     */
    public int getStartingPlayer() {
        return startingPlayer;
    }
}
