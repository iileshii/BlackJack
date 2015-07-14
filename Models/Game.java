package Models;

import java.util.ArrayList;

/**
 * Game model, consists of card deck, player list, and finish checker.
 *
 * @author Alexey Kuznetsov
 * @version 1.0
 */

public class Game {
    private CardDeck deck;
    private ArrayList<Player> players;
    private boolean finish;

    public Game() {
        deck = new CardDeck();
        players = new ArrayList<>();
        finish = false;
    }

    /**
     * @return is game finished
     */

    public boolean isFinish() {
        return finish;
    }

    /**
     * @param finish set true if game was finished, otherwise set false;
     */

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /**
     * @return Deck
     */

    public CardDeck getDeck() {
        return deck;
    }

    /**
     * @param player to add to game.
     */

    public void addPlayer(Player player) {
        players.add(player);
    }

    /**
     * @return amount of players
     */

    public int getNumberOfPlayers() {
        return players.size();
    }

    /**
     * @return array list of players in the game
     */

    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @return random card from the deck
     */

    public Card getRandomCardFromDeck() {
        return deck.getRandomCard();
    }

    /**
     * @return checks how many cards are in the deck
     */

    public boolean areCardsInDeck() {
        return deck.getRemainAmount() >= (1.0 / 3) * (deck.getRemainAmount() + deck.getUsedAmount());
    }

    /**
     * Method resets the deck
     */

    public void resetDeck() {
        deck.reset();
    }

    /**
     * Method resets players
     */

    public void resetPlayers() {
        players.forEach(Models.Player::resetHand);
    }
}
