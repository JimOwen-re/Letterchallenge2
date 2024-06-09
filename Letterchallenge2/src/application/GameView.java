package application;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class GameView {
    private GridPane gridPane;

    public GameView(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void displayGrid(CrosswordGrid crosswordGrid) {
        gridPane.getChildren().clear();
        char[][] grid = crosswordGrid.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                Text cell = new Text(Character.toString(grid[i][j]));
                gridPane.add(cell, j, i);
            }
        }
    }

    public void displayClues(java.util.List<CrosswordClue> clues) {
        // Lógica para mostrar las pistas (podría ser en un ListView u otra parte de la interfaz)
    }

    public void updateGrid(String answer, int row, int col, boolean isHorizontal) {
        // Lógica para actualizar la cuadrícula con la respuesta correcta
        for (int i = 0; i < answer.length(); i++) {
            Text cell = new Text(Character.toString(answer.charAt(i)));
            if (isHorizontal) {
                gridPane.add(cell, col + i, row);
            } else {
                gridPane.add(cell, col, row + i);
            }
        }
    }

    public void displayMessage(String message) {
        // Lógica para mostrar un mensaje al usuario (podría ser en un Label o Alert)
    }
}
