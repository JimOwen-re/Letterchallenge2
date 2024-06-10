package application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CrosswordGridtest {

    private CrosswordGrid crosswordGrid;

    @Before
    public void setUp() {
        crosswordGrid = new CrosswordGrid(5, 5);
    }

    @Test
    public void testAddClue() {
        CrosswordClue clue = new CrosswordClue("Test clue", "WORD", 1, 1, true);
        crosswordGrid.addClue(clue);
        List<CrosswordClue> clues = crosswordGrid.getClues();
        assertNotNull(clues);
        assertEquals(1, clues.size());
    }

    @Test
    public void testGetGrid() {
        char[][] grid = crosswordGrid.getGrid();
        assertNotNull(grid);
        assertEquals(5, grid.length);
        assertEquals(5, grid[0].length);
    }
}
