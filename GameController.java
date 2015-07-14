import Models.*;

import java.util.ArrayList;

/**
 * Primary controller contains game logic.
 *
 * @author Alexey Kuznetsov
 * @version (a version number or a date)
 */

public class GameController {
    private final int BET = 50;
    private Game game;
    private GameView gameView;
    private int prize;

    public GameController() {
        game = new Game();
    }

    /**
     * Method sets current game view
     *
     * @param gameView current game view
     */

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Method creates players, and calls firstTurn() method
     *
     * @param playerNames   List of real player names
     * @param numberPlayers amount of all player
     */

    public void setGameAndLaunch(ArrayList<String> playerNames, int numberPlayers) {
        for (String name : playerNames) {
            game.addPlayer(new RealPlayer(name));
        }
        int numberBots = (numberPlayers - playerNames.size()) - 1;
        for (int bot = 0; bot < numberBots; bot++) {
            game.addPlayer(new BotPlayer("Bot #" + (bot + 1)));
        }
        game.addPlayer(new DealerPlayer());
        prize = 0;
        firstTurn();
    }

    /**
     * Method gets bets and makes the first turn, after that it calls checking for BlackJack
     */

    public void firstTurn() {
        for (Player player : game.getPlayers()) {
            prize = prize + BET;
            player.decreaseAccount(BET);
            addCardsToPlayer(player, 2);
        }

        tableCheckStatus();
        lookingForBlackJackOwners();

        if (!isFinish()) {
            makeTurns();
        }
    }

    /**
     * Method makes players turns
     */

    private void makeTurns() {
        for (Player player : game.getPlayers()) {
            switch (player.getType()) {
                case REAL: {
                    while (player.getPoints() < 21) {
                        boolean loopBreak = false;
                        switch (gameView.getTurnAction(player.getName(), player.getHandToString())) {
                            case TAKE_CARD: {
                                addCardsToPlayer(player, 1);
                                break;
                            }
                            case STOP: {
                                loopBreak = true;
                                break;
                            }
                        }
                        if (loopBreak) {
                            break;
                        }
                    }
                    break;
                }
                case DEALER: {
                    while (player.getPoints() < 17) {
                        addCardsToPlayer(player, 1);
                    }
                    break;
                }
                case BOT: {
                    while (1.0 - (player.getPoints() - 10.0) / 11.0 > ((BotPlayer) player).getRiskLevel()) {
                        addCardsToPlayer(player, 1);
                    }
                    break;
                }
            }
            tableCheckStatus();
        }
        checkWinners(false);
        setFinish();
    }

    /**
     * Method calls view methods to show checkStatus table
     */

    private void tableCheckStatus() {
        gameView.tableCheckHead(prize);
        for (Player player : game.getPlayers()) {
            switch (player.getType()) {
                case DEALER: {
                    gameView.tableCheckLine(
                            player.getName(),
                            player.getPoints(),
                            ((DealerPlayer) player).getBlindHandToString(),
                            true);
                    break;
                }
                default: {
                    gameView.tableCheckLine(player.getName(), player.getPoints(), player.getHandToString(), false);
                    break;
                }
            }
        }
    }

    /**
     * Method adds cards to player
     *
     * @param player      Player
     * @param numberCards number of cards to add
     */

    public void addCardsToPlayer(Player player, int numberCards) {
        for (int i = 0; i < numberCards; i++) {
            player.addCard(game.getRandomCardFromDeck());
        }
        checkPlayerPoints(player);
    }

    /**
     * Method looks for BlackJack in the game
     */

    public void lookingForBlackJackOwners() {
        for (Player player : game.getPlayers()) {
            if (player.getPoints() == 21) {
                checkWinners(false);
                setFinish();
                break;
            }
        }
    }

    /**
     * Method looks for winners and shows them
     *
     * @param blackJack true if there is blackjack
     */

    private void checkWinners(boolean blackJack) {
        int maxPoint = 0;
        int points;
        ArrayList<Player> winners = new ArrayList<>();
        ArrayList<Player> losers = new ArrayList<>();
        for (Player player : game.getPlayers()) {
            points = player.getPoints();
            if (maxPoint < points && points <= 21) {
                losers.addAll(winners);
                winners.clear();
                maxPoint = points;
                winners.add(player);
            } else if (points == maxPoint && points <= 21) {
                winners.add(player);
            } else {
                losers.add(player);
            }
        }
        if (blackJack && winners.size() > 1) {
            for (int i = 0; i < winners.size(); i++) {
                Player player = winners.get(i);
                if (player.getType() == PlayerType.DEALER) {
                    winners.remove(i);
                }
            }
        }
        int prizePerPlayer = prize / winners.size();
        gameView.tableResultsHead();
        for (Player winner : winners) {
            winner.increaseAccount(prizePerPlayer);
            gameView.tableResultsLine(winner.getName(), winner.getPoints(), true, winner.getAccount(), winner.getHandToString());
        }
        for (Player loser : losers) {
            gameView.tableResultsLine(loser.getName(), loser.getPoints(), false, loser.getAccount(), loser.getHandToString());
        }
        prize = prize - (prize / winners.size()) * winners.size();
        gameView.wantContinue();
    }

    /**
     * Method check point of player
     *
     * @param player Player to be executed
     */

    private void checkPlayerPoints(Player player) {
        ArrayList<Card> playerHand = player.getHand();
        int points = 0;
        int aces = 0;
        for (Card card : playerHand) {
            if (!card.isAce()) {
                points = points + card.getValue();
            } else {
                aces = aces + 1;
            }
        }
        if (aces > 1) {
            points = points + (aces - 1);
        }
        if (aces > 0) {
            if (points + 11 < 22) {
                points = points + 11;
            } else {
                points = points + 1;
            }
        }
        player.setPoints(points);
    }

    /**
     * @return true if game was finished
     */

    public boolean isFinish() {
        return game.isFinish();
    }

    /**
     * Method sets game finished
     */

    public void setFinish() {
        game.setFinish(true);
    }

    /**
     * Method resets deck if it's needed, resets players and launch game again
     */

    public void startAgain() {
        if (!game.areCardsInDeck()) {
            game.resetDeck();
        }
        game.resetPlayers();
        game.setFinish(false);
        firstTurn();
    }
}

