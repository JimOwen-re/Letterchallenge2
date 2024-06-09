package application;

import java.util.ArrayList;
import java.util.List;

public class CrosswordGrid {
    private int rows;
    private int cols;
    private char[][] grid;
    private List<CrosswordClue> clues;

    public CrosswordGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        this.clues = new ArrayList<>();
        initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = ' ';
            }
        }
    }

    public void addClue(CrosswordClue clue) {
        clues.add(clue);
        placeClueOnGrid(clue);
    }

    private void placeClueOnGrid(CrosswordClue clue) {
        String answer = clue.getAnswer();
        int row = clue.getRow();
        int col = clue.getCol();
        if (clue.isHorizontal()) {
            for (int i = 0; i < answer.length(); i++) {
                grid[row][col + i] = answer.charAt(i);
            }
        } else {
            for (int i = 0; i < answer.length(); i++) {
                grid[row + i][col] = answer.charAt(i);
            }
        }
    }

    public char[][] getGrid() {
        return grid;
    }

    public List<CrosswordClue> getClues() {
        return clues;
    }
}
