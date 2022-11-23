package games;

import board.Board;
import cards.minions.MinionCard;
import cards.minions.MinionStates;
import errors.Errors;
import fileio.ActionsInput;
import fileio.GameInput;
import output.Output;
import player.Player;

import java.util.ArrayList;
import java.util.Random;

public final class Game {
    private final GamePrep gamePrep;
    private final ArrayList<ActionsInput> actions;

    /**
     * @param source the game input file
     */
    public Game(final GameInput source) {
        this.gamePrep = new GamePrep(source.getStartGame());
        this.actions = source.getActions();
    }

    /**
     * Update which player's turn it is
     *
     * @param currentTurn which player's turn it is
     * @return the next player's turn
     */
    private static int updateTurn(final int currentTurn) {
        return currentTurn == 1 ? 2 : 1;
    }

    /**
     * Take care of the command handling logic to play a game.
     *
     * @return the output of the game in a JSON formatted string
     */
    public ArrayList<String> playGame(
            final Player playerOne, final Player playerTwo
                                     ) {
        ArrayList<String> gameOutput = new ArrayList<>();
        Board currentBoard = Board.getInstance();

        int currentTurn = gamePrep.getStartingPlayer();
        int turnCounter = 1;

        gameInit(playerOne, playerTwo, turnCounter);

        for (ActionsInput action : actions) {
            switch (Actions.getCommand(action.getCommand())) {
                case GET_HERO -> gameOutput.add(Output.getHero(action, playerOne, playerTwo));
                case GET_DECK -> gameOutput.add(Output.getDeck(action, playerOne, playerTwo));
                case GET_HAND -> gameOutput.add(Output.getHand(action, playerOne, playerTwo));
                case GET_TURN -> gameOutput.add(Output.getTurn(action, currentTurn));
                case GET_TABLE -> gameOutput.add(Output.getBoard(action));
                case GET_CARD -> gameOutput.add(Output.getCard(action));
                case GET_ENV -> gameOutput.add(Output.getEnv(action, playerOne, playerTwo));
                case GET_MANA -> gameOutput.add(Output.getMana(action, playerOne, playerTwo));
                case GET_FROZEN -> gameOutput.add(Output.getFrozen(action));
                case PLACE_CARD -> placeCard(currentTurn, playerOne, playerTwo, action, gameOutput);
                case USE_ENV ->
                        useEnv(currentTurn, playerOne, playerTwo, action, gameOutput, turnCounter);
                case CARD_ATTACK ->
                        cardAttack(currentTurn, playerOne, playerTwo, action, gameOutput);
                case CARD_SPECIAL ->
                        cardSpecial(currentTurn, playerOne, playerTwo, action, gameOutput);
                case DAMAGE_HERO ->
                        damageHero(currentTurn, playerOne, playerTwo, action, gameOutput);
                case HERO_ATTACK -> {
                    Errors error = Errors.NONE;

                    if (currentTurn == 1) {
                        error = playerOne.useHeroAbility(action.getAffectedRow());
                    } else if (currentTurn == 2) {
                        error = playerTwo.useHeroAbility(action.getAffectedRow());
                    }

                    if (error != Errors.NONE) {
                        gameOutput.add(Output.useHeroAbilityError(action, error.getError()));
                    }

                    if (playerOne.getCurrentHero().getHealth() <= 0
                        || playerTwo.getCurrentHero().getHealth() <= 0) {
                        gameOutput.add(Output.getWinner(playerOne, playerTwo));
                    }
                }
                case GET_GAMES -> gameOutput.add(
                        Output.getGames(action, playerOne.getWins() + playerTwo.getWins()));
                case GET_WINS_ONE ->
                        gameOutput.add(Output.getPlayerWins(action, playerOne.getWins()));
                case GET_WINS_TWO ->
                        gameOutput.add(Output.getPlayerWins(action, playerTwo.getWins()));
                case END_TURN -> {
                    currentTurn = updateTurn(currentTurn);
                    turnCounter = endTurn(turnCounter, playerOne, playerTwo, currentBoard);
                }
                default -> {

                }
            }
        }

        currentBoard.resetBoard();

        playerOne.setMana(0);
        playerOne.getHand().clear();

        playerTwo.setMana(0);
        playerTwo.getHand().clear();

        return gameOutput;
    }

    /**
     * Do the initial setup for the game.
     *
     * @param turnCounter the counter that keeps track of how many turns have passed
     */
    private void gameInit(final Player playerOne, final Player playerTwo, final int turnCounter) {
        playerOne.setCurrentDeck(gamePrep.getPlayerOneDeckIndex(),
                                 new Random(gamePrep.getShuffleSeed())
                                );
        playerOne.setCurrentHero(gamePrep.getPlayerOneHero());

        playerTwo.setCurrentDeck(gamePrep.getPlayerTwoDeckIndex(),
                                 new Random(gamePrep.getShuffleSeed())
                                );
        playerTwo.setCurrentHero(gamePrep.getPlayerTwoHero());

        playerOne.addToHand();
        playerOne.increaseMana(turnCounter);

        playerTwo.addToHand();
        playerTwo.increaseMana(turnCounter);
    }

    /**
     * Do the cleanup after a turn.
     *
     * @param turnCounter the counter that keeps track of how many turns have passed
     * @return the updated turn counter
     */
    private int endTurn(
            final int turnCounter, final Player playerOne, final Player playerTwo,
            final Board currentBoard
                       ) {
        for (ArrayList<MinionCard> row : Board.getInstance().getCardsOnBoard()) {
            for (MinionCard card : row) {
                if (card.getState() == MinionStates.ACTIVE) {
                    card.getState().setTimeStamp(turnCounter);
                }
            }
        }

        if (turnCounter % 2 == 0) {
            playerOne.addToHand();
            playerOne.increaseMana(turnCounter / 2 + 1);

            playerTwo.addToHand();
            playerTwo.increaseMana(turnCounter / 2 + 1);

            currentBoard.resetAttackStatus(playerOne.getCurrentHero(), playerTwo.getCurrentHero());
        }

        currentBoard.unfreeze(turnCounter);
        return turnCounter + 1;
    }

    /**
     * Execute the place card action.
     */
    private void placeCard(
            final int currentTurn, final Player playerOne, final Player playerTwo,
            final ActionsInput action, final ArrayList<String> gameOutput
                          ) {
        Errors error = Errors.NONE;

        if (currentTurn == 1) {
            error = playerOne.placeCard(action.getHandIdx());
        } else if (currentTurn == 2) {
            error = playerTwo.placeCard(action.getHandIdx());
        }

        if (error != Errors.NONE) {
            gameOutput.add(Output.placeCardError(action, error.getError()));
        }
    }

    /**
     * Execute the use environment action.
     */
    private void useEnv(
            final int currentTurn, final Player playerOne, final Player playerTwo,
            final ActionsInput action, final ArrayList<String> gameOutput, final int turnCounter
                       ) {
        Errors error = Errors.NONE;

        if (currentTurn == 1) {
            error = playerOne.useEnvCard(action.getHandIdx(), action.getAffectedRow(), turnCounter);
        } else if (currentTurn == 2) {
            error = playerTwo.useEnvCard(action.getHandIdx(), action.getAffectedRow(), turnCounter);
        }

        if (error != Errors.NONE) {
            gameOutput.add(Output.useEnvCardError(action, error.getError()));
        }
    }

    /**
     * Execute the card attack action.
     */
    private void cardAttack(
            final int currentTurn, final Player playerOne, final Player playerTwo,
            final ActionsInput action, final ArrayList<String> gameOutput
                           ) {
        Errors error = Errors.NONE;

        if (currentTurn == 1) {
            error = playerOne.cardAttack(action.getCardAttacker(), action.getCardAttacked());
        } else if (currentTurn == 2) {
            error = playerTwo.cardAttack(action.getCardAttacker(), action.getCardAttacked());
        }

        if (error != Errors.NONE) {
            gameOutput.add(Output.cardAttackError(action, error.getError()));
        }
    }

    /**
     * Execute the card special attack action.
     */
    private void cardSpecial(
            final int currentTurn, final Player playerOne, final Player playerTwo,
            final ActionsInput action, final ArrayList<String> gameOutput
                            ) {
        Errors error = Errors.NONE;

        if (currentTurn == 1) {
            error = playerOne.cardSpecialAttack(action.getCardAttacker(), action.getCardAttacked());
        } else if (currentTurn == 2) {
            error = playerTwo.cardSpecialAttack(action.getCardAttacker(), action.getCardAttacked());
        }

        if (error != Errors.NONE) {
            gameOutput.add(Output.cardSpecialAttackError(action, error.getError()));
        }
    }

    /**
     * Execute the damage hero action.
     */
    private void damageHero(
            final int currentTurn, final Player playerOne, final Player playerTwo,
            final ActionsInput action, final ArrayList<String> gameOutput
                           ) {
        Errors error = Errors.NONE;

        if (currentTurn == 1) {
            error = playerOne.damageHero(action.getCardAttacker(), playerTwo.getCurrentHero());
        } else if (currentTurn == 2) {
            error = playerTwo.damageHero(action.getCardAttacker(), playerOne.getCurrentHero());
        }

        if (error != Errors.NONE) {
            gameOutput.add(Output.damageHeroError(action, error.getError()));
        }

        if (playerOne.getCurrentHero().getHealth() <= 0
            || playerTwo.getCurrentHero().getHealth() <= 0) {
            gameOutput.add(Output.getWinner(playerOne, playerTwo));
        }
    }
}
