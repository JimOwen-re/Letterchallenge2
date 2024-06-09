package application;

public class GameController {
    private CrosswordGame game;
    private GameView view;

    public GameController(CrosswordGame game, GameView view) {
        this.game = game;
        this.view = view;
        initializeView();
    }

    private void initializeView() {
        // Inicializa la vista con datos del juego
        view.displayGrid(game.getGrid());
        view.displayClues(game.getGrid().getClues());
    }

    // Método para manejar la acción de un jugador
    public void handleGuess(String answer, int row, int col, boolean isHorizontal) {
        if (game.checkAnswer(answer, row, col, isHorizontal)) {
            view.updateGrid(answer, row, col, isHorizontal);
            view.displayMessage("Correct!");
        } else {
            view.displayMessage("Try again!");
        }
        checkGameStatus();
    }

    // Método para verificar el estado del juego
    private void checkGameStatus() {
        if (game.isGameComplete()) {
            view.displayMessage("Congratulations! You completed the crossword!");
        }
    }
}