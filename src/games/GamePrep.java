package games;

import cards.heroes.HeroCard;
import cards.heroes.HeroTypes;
import fileio.CardInput;
import fileio.StartGameInput;
import org.jetbrains.annotations.NotNull;

public final class GamePrep {
    private final int      playerOneDeckIndex;
    private final int      playerTwoDeckIndex;
    private final int      shuffleSeed;
    private final HeroCard playerOneHero;
    private final HeroCard playerTwoHero;
    private final int      startingPlayer;

    /**
     * @param source
     */
    public GamePrep(final @NotNull StartGameInput source) {
        this.playerOneDeckIndex = source.getPlayerOneDeckIdx();
        this.playerTwoDeckIndex = source.getPlayerTwoDeckIdx();
        this.shuffleSeed = source.getShuffleSeed();
        this.startingPlayer = source.getStartingPlayer();

        CardInput dummyHeroOne = source.getPlayerOneHero();
        CardInput dummyHeroTwo = source.getPlayerTwoHero();


        cards.heroes.HeroCard heroTwo = null;

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
     * @return
     */
    public int getPlayerOneDeckIndex() {
        return this.playerOneDeckIndex;
    }

    /**
     * @return
     */
    public int getPlayerTwoDeckIndex() {
        return playerTwoDeckIndex;
    }

    /**
     * @return
     */
    public int getShuffleSeed() {
        return shuffleSeed;
    }

    /**
     * @return
     */
    public HeroCard getPlayerOneHero() {
        return playerOneHero;
    }

    /**
     * @return
     */
    public HeroCard getPlayerTwoHero() {
        return playerTwoHero;
    }

    /**
     * @return
     */
    public int getStartingPlayer() {
        return startingPlayer;
    }
}
