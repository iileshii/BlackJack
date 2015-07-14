package Models;

import java.util.ArrayList;

/**
 * Abstract class for players
 * Consists of Player type, name, cards in a hand, current points and account
 *
 * @author Alexey Kuznetsov
 * @version 1.0
 */

public abstract class Player {
    private final String name;
    private final PlayerType type;
    private ArrayList<Card> hand;
    private int points;
    private int account;

    public Player(String name, PlayerType type) {
        this.name = name;
        hand = new ArrayList<>();
        points = 0;
        this.type = type;
        account = 1000;
    }

    /**
     * @param card adds to user's player
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * @return player points
     */

    public int getPoints() {
        return points;
    }

    /**
     * @param points sets to player
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return player name
     */

    public String getName() {
        return name;
    }

    /**
     * @return player type
     */

    public PlayerType getType() {
        return type;
    }

    /**
     * @param i number of Card
     * @return string card
     */

    public String getCardToString(int i) {
        return hand.get(i).toString();
    }

    /**
     * @return ArrayList of cards in the player's hand
     */

    public ArrayList<Card> getHand() {
        return hand;
    }

    /**
     * @return String of the player's hand
     */

    public String getHandToString() {
        String stringHand = "\t";
        for (Card card : hand) {
            stringHand = stringHand + card.toString() + "\t";
        }
        return stringHand;
    }

    /**
     * Method resets the player's hand and sets points to zero
     */

    public void resetHand() {
        hand.clear();
        points = 0;
    }

    /**
     * @return account amount
     */

    public int getAccount() {
        return account;
    }

    /**
     * @param amount is adding to player's account
     */

    public void increaseAccount(int amount) {
        account = account + amount;
    }

    /**
     * @param amount is removing from player's account
     */

    public void decreaseAccount(int amount) {
        account = account - amount;
    }
}
