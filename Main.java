public class Main {

    /**
     * The main class, create game controller and view, then launch it.
     */

    public static void main(String[] args) {

        GameController gameController = new GameController();
        GameView gameView = new GameView(gameController);

        gameView.gameStart();
    }
}
