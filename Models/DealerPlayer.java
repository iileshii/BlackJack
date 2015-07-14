package Models;

/**
 * Class for Dealer
 * Consists of constructor, and getBlindHandToString method that returns hand with one blind card
 *
 * @author Alexey Kuznetsov
 * @version 1.0
 */

public class DealerPlayer extends Player {

    public DealerPlayer() {
        super("Dealer", PlayerType.DEALER);
    }

    /**
     * @return String of the player's hand where the first card is not showed.
     */

    public String getBlindHandToString() {
        String stringHand = "\tX X\t";
        for (int i = 1; i < getHand().size(); i++) {
            Card card = getHand().get(i);
            stringHand = stringHand + card.toString() + "\t";
        }
        stringHand = stringHand + "|";
        return stringHand;
    }
}
