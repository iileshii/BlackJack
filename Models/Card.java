package Models;

/**
 * Card class consist of suit and front, its getters, checker isACE, and toString override method.
 *
 * @author Alexey Kuznetsov
 * @version 1.0
 */

public class Card {
    private Suit suit;
    private Front front;

    public Card(Suit suit, Front front) {
        this.suit = suit;
        this.front = front;
    }

    /**
     * @return card's suit
     */

    public Suit getSuit() {
        return suit;
    }

    /**
     * @return card's front
     */

    public Front getFront() {
        return front;
    }

    /**
     * @return card's value
     */

    public int getValue() {
        return front.getValue();
    }

    /**
     * @return true if it's ACE
     */
    public boolean isAce() {
        return front == Front.ACE;
    }

    /**
     * @return string card
     */
    @Override
    public String toString() {
        return suit + " " + front;
    }
}
