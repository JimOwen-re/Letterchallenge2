package application;

import javafx.scene.layout.GridPane;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameViewTest {

    @Test
    public void testConstructor() {
        // Arrange
        GridPane gridPane = new GridPane();

        // Act
        GameView gameView = new GameView(gridPane);

        // Assert
        assertNotNull(gameView);
    }

    
}
