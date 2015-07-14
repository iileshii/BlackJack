import Models.Action;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Primary view is to interact to user
 *
 * @author Alexey Kuznetsov
 * @version (a version number or a date)
 */

public class GameView {
    private final int MAX_PLAYERS = 6; // maximum value of players
    private final int MIN_PLAYERS = 2; // minimum value of players
    private GameController gameController;

    public GameView(GameController gameController) {
        this.gameController = gameController;
        gameController.setGameView(this);
    }

    /**
     * Method asks user how many players and how many real players will play. After that method launch game controller
     * to set and start game.
     */

    public void gameStart() {
        System.out.println("How many players will play? (" + MIN_PLAYERS + " - " + MAX_PLAYERS + ")");
        Scanner scanner = new Scanner(System.in);
        int number = 0;
        while (number < MIN_PLAYERS || number > MAX_PLAYERS) {
            number = scanner.nextInt();
        }
        System.out.println("How many REAL players will play? (0 - " + (number - 1) + ")");
        int numberRealPlayers = -1;
        while (numberRealPlayers < 0 || numberRealPlayers > (number - 1)) {
            numberRealPlayers = scanner.nextInt();
        }
        gameController.setGameAndLaunch(getPlayersNames(numberRealPlayers), number);
    }

    /**
     * @param numberRealPlayers number of real players to create
     * @return Array List of names
     */

    public ArrayList<String> getPlayersNames(int numberRealPlayers) {
        ArrayList<String> namesList = new ArrayList<>();
        for (int number = 0; number < numberRealPlayers; number++) {
            System.out.println("Type name for Player " + (number + 1));
            Scanner scanner = new Scanner(System.in);
            String name = scanner.next();
            namesList.add(name);
        }
        return namesList;
    }

    /**
     * Method prints out head of the end table
     */

    public void tableResultsHead() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("\nTHE END");
        System.out.println("\n--------------------------------------------------");
        System.out.println("|\tName\t|\tPoints\t|\tStatus\t|\tAccount\t\t|\tCards");
    }

    /**
     * Method prints out a line of the end table
     *
     * @param playerName    Name of player
     * @param points        Points of player
     * @param isWinner      true if player is winner, otherwise false
     * @param playerAccount Player's account
     * @param stringHand    Player's card
     */

    public void tableResultsLine(String playerName, int points, boolean isWinner, int playerAccount, String stringHand) {
        System.out.print("|\t" + playerName + "\t|\t" + points + "\t\t|\t");
        if (isWinner) {
            System.out.print("Winner");
        } else {
            System.out.print("Loser");
        }
        System.out.print("\t|\t" + playerAccount + "\t\t|" + stringHand);
        System.out.println();
    }

    /**
     * Method prints out head of an intermediate result table
     *
     * @param prize current prize in the game
     */

    public void tableCheckHead(int prize) {
        System.out.println("\n--------------------------------------------------");
        System.out.println("\nBank: " + prize + "\t|\tPlayers have");
        System.out.println("\n--------------------------------------------------");
        System.out.println("|\tName\t|\tPoints\t|\tCards");
    }

    /**
     * Method prints out a line of an intermediate result table
     *
     * @param playerName Name of player
     * @param points     Points of player
     * @param stringHand Player's card
     * @param isDealer   true if player is a dealer, otherwise false
     */

    public void tableCheckLine(String playerName, int points, String stringHand, boolean isDealer) {
        if (isDealer) {
            System.out.print("|\t" + playerName + "\t|\t??\t\t|" + stringHand);
            System.out.println();
        } else {
            System.out.print("|\t" + playerName + "\t|\t" + points + "\t\t|" + stringHand);
            System.out.println();
        }
    }

    /**
     * @param name       Name of current Real player
     * @param stringHand Current cards in his hand
     * @return Action is chosen by player
     */

    public Action getTurnAction(String name, String stringHand) {
        System.out.println("\n--------------------------------------------------");
        System.out.println(name + "'s turn.");
        System.out.println("You has:");
        System.out.println(stringHand);
        System.out.println("Take a card? (0 - No, 1 - Yes)");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        if (i == 1) {
            return Action.TAKE_CARD;
        } else {
            return Action.STOP;
        }
    }

    /**
     * Method asks user if he wants to play again
     */

    public void wantContinue() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("Do you want to play again? (0 - No, 1 - Yes)");
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        if (i == 1) {
            gameController.startAgain();
        } else {
            System.out.println("\n--------------------------------------------------");
            System.out.println("\nGOOD BYE!");
        }
    }

}
