package Models;

/**
 * Class for Real Players.
 * Consists of constructor only
 *
 * @author Alexey Kuznetsov
 * @version 1.0
 */

public class RealPlayer extends Player {

    public RealPlayer(String name) {
        super(name, PlayerType.REAL);
    }

}
