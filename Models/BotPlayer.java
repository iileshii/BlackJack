package Models;

/**
 * Class for Bots
 * Consists of constructor, level of risk and its getter.
 *
 * @author Alexey Kuznetsov
 * @version 1.0
 */

public class BotPlayer extends Player {

    private double riskLevel;

    public BotPlayer(String name) {
        super(name, PlayerType.BOT);
        riskLevel = Math.random();
    }

    /**
     * @return riskLevel of player
     */

    public double getRiskLevel() {
        return riskLevel;
    }
}
