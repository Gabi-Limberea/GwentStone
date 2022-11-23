package games;

import board.Board;
import cards.minions.MinionCard;
import cards.minions.MinionStates;
import errors.Errors;
import fileio.ActionsInput;
import fileio.GameInput;
import org.jetbrains.annotations.NotNull;
import output.Output;
import player.Player;

import java.util.ArrayList;
import java.util.Random;

public final class Game {
    private final GamePrep gamePrep;
    private final ArrayList<ActionsInput> actions;

    /**
     * @param source
     */
    public Game(final @NotNull GameInput source) {
        this.gamePrep = new GamePrep(source.getStartGame());
        this.actions = source.getActions();
    }

    /**
     * @param currentTurn
     * @return
     */
    public static int endTurn(final int currentTurn) {
        return currentTurn == 1 ? 2 : 1;
    }

    /**
     * @param playerOne
     * @param playerTwo
     * @return
     */
    public ArrayList<String> startGame(
            final @NotNull Player playerOne, final @NotNull Player playerTwo
                                      ) {
        ArrayList<String> gameOutput = new ArrayList<>();
        Board currentBoard = Board.getInstance();

        int currentTurn = gamePrep.getStartingPlayer();
        int turnCounter = 1;

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
                case PLACE_CARD -> {
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
                case USE_ENV -> {
                    Errors error = Errors.NONE;

                    if (currentTurn == 1) {
                        error = playerOne.useEnvCard(action.getHandIdx(), action.getAffectedRow(),
                                                     turnCounter
                                                    );
                    } else if (currentTurn == 2) {
                        error = playerTwo.useEnvCard(action.getHandIdx(), action.getAffectedRow(),
                                                     turnCounter
                                                    );
                    }

                    if (error != Errors.NONE) {
                        gameOutput.add(Output.useEnvCardError(action, error.getError()));
                    }
                }
                case CARD_ATTACK -> {
                    Errors error = Errors.NONE;

                    if (currentTurn == 1) {
                        error = playerOne.cardAttack(action.getCardAttacker(),
                                                     action.getCardAttacked()
                                                    );
                    } else if (currentTurn == 2) {
                        error = playerTwo.cardAttack(
                                action.getCardAttacker(), action.getCardAttacked());
                    }

                    if (error != Errors.NONE) {
                        gameOutput.add(Output.cardAttackError(action, error.getError()));
                    }
                }
                case CARD_SPECIAL -> {
                    Errors error = Errors.NONE;

                    if (currentTurn == 1) {
                        error = playerOne.cardSpecialAttack(action.getCardAttacker(),
                                                            action.getCardAttacked()
                                                           );
                    } else if (currentTurn == 2) {
                        error = playerTwo.cardSpecialAttack(action.getCardAttacker(),
                                                            action.getCardAttacked()
                                                           );
                    }

                    if (error != Errors.NONE) {
                        gameOutput.add(Output.cardSpecialAttackError(action, error.getError()));
                    }
                }
                case DAMAGE_HERO -> {
                    Errors error = Errors.NONE;

                    if (currentTurn == 1) {
                        error = playerOne.damageHero(
                                action.getCardAttacker(), playerTwo.getCurrentHero());
                    } else if (currentTurn == 2) {
                        error = playerTwo.damageHero(
                                action.getCardAttacker(), playerOne.getCurrentHero());
                    }

                    if (error != Errors.NONE) {
                        gameOutput.add(Output.damageHeroError(action, error.getError()));
                    }

                    if (playerOne.getCurrentHero().getHealth() <= 0
                        || playerTwo.getCurrentHero().getHealth() <= 0) {
                        gameOutput.add(Output.getWinner(playerOne, playerTwo));
                    }
                }
                case HERO_ATTACK -> {
                    Errors error = Errors.NONE;

                    if (currentTurn == 1) {
                        System.out.println("HERO ATTACK: " + playerOne.getCurrentHero().getName());
                        error = playerOne.useHeroAbility(action.getAffectedRow());
                    } else if (currentTurn == 2) {
                        System.out.println("HERO ATTACK: " + playerTwo.getCurrentHero().getName());
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
                case GET_GAMES -> gameOutput.add(Output.getGames(action, playerOne.getWins() + playerTwo.getWins()));
                case GET_WINS_ONE ->
                        gameOutput.add(Output.getPlayerWins(action, playerOne.getWins()));
                case GET_WINS_TWO ->
                        gameOutput.add(Output.getPlayerWins(action, playerTwo.getWins()));
                case END_TURN -> {
                    System.out.println(currentTurn);
                    currentTurn = endTurn(currentTurn);

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

                        currentBoard.resetAttackStatus(
                                playerOne.getCurrentHero(), playerTwo.getCurrentHero());
                    }

                    currentBoard.unfreeze(turnCounter);
                    turnCounter++;
                }
                default -> {

                }
            }
        }

        currentBoard.resetBoard();
        return gameOutput;
    }
}
