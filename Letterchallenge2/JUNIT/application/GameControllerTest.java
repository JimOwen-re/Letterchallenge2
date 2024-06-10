package application;

import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.Test;

public class GameControllerTest {

    @Test
    public void testHandleGuess_CorrectAnswer() {
        // Arrange
        CrosswordGame game = new CrosswordGame(10, 10);
        GridPane gridPane = new GridPane(); // Importa GridPane aquí
        GameView view = new GameView(gridPane);
        GameController gameController = new GameController(game, view);
        String answer = "WORD";
        int row = 0;
        int col = 0;
        boolean isHorizontal = true;

        // Act
        gameController.handleGuess(answer, row, col, isHorizontal);

        // Assert
        // Agregar afirmaciones para verificar que el estado del juego o de la vista haya cambiado según lo esperado
    }

    @Test
    public void testHandleGuess_IncorrectAnswer() {
        // Arrange
        CrosswordGame game = new CrosswordGame(10, 10);
        GridPane gridPane = new GridPane(); // Importa GridPane aquí
        GameView view = new GameView(gridPane);
        GameController gameController = new GameController(game, view);
        String answer = "NOT";
        int row = 0;
        int col = 0;
        boolean isHorizontal = true;

        // Act
        gameController.handleGuess(answer, row, col, isHorizontal);

        // Assert
        // Agregar afirmaciones para verificar que el estado del juego o de la vista haya cambiado según lo esperado
    }
}
