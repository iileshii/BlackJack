package Models;

import java.util.ArrayList;
import java.util.Random;

/**
 * Card deck, consist of constructor, Array Lists of remain cards, used cards, getters and reset method.
 *
 * @author Alexey Kuznetsov
 * @version 1.0
 */

public class CardDeck {
    private ArrayList<Card> cardList;
    private ArrayList<Card> usedCardList;

    public CardDeck() {
        reset();
    }

    /**
     * @return random card from the remain cards in the deck
     */

    public Card getRandomCard() {
        Random random = new Random();
        int i = random.nextInt(getRemainAmount());
        Card card = cardList.get(i);
        cardList.remove(i);
        usedCardList.add(card);
        return card;
    }

    /**
     * @return amount of remain cards
     */

    public int getRemainAmount() {
        return cardList.size();
    }

    /**
     * @return amount of used cards
     */

    public int getUsedAmount() {
        return usedCardList.size();
    }

    /**
     * Method resets card deck
     */

    public void reset() {
        cardList = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Front front : Front.values()) {
                Card card = new Card(suit, front);
                cardList.add(card);
            }
        }
        usedCardList = new ArrayList<>();
    }
}
