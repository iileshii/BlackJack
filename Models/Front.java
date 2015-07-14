package Models;

/**
 * Enumeration of Fronts
 * Consists of Front and Front value (ACE value is 11)
 *
 * @author Alexey Kuznetsov
 * @version 1.0
 */

public enum Front {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10),
    ACE(11);

    private int value;

    Front(int value) {
        this.value = value;
    }

    /**
     * @return value of this front
     */

    public int getValue() {
        return value;
    }


}
