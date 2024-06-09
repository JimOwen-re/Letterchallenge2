package application;

import java.util.ArrayList;
import java.util.List;

public class CrosswordGame {
    private CrosswordGrid grid;
    private List<Player> players;

    public CrosswordGame(int rows, int cols) {
        this.grid = new CrosswordGrid(rows, cols);
        this.players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public CrosswordGrid getGrid() {
        return grid;
    }

    public List<Player> getPlayers() {
        return players;
    }

    // Métodos adicionales para manejar la lógica del juego
    public boolean checkAnswer(String answer, int row, int col, boolean isHorizontal) {
        // Implementar lógica para verificar respuesta
        return true; // Placeholder
    }

    public boolean isGameComplete() {
        // Implementar lógica para verificar si el juego está completo
        return false; // Placeholder
    }
}
