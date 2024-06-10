package application;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClueTest {

    @Test
    void testCrosswordClue() {
        // Creamos una instancia de CrosswordClue
        CrosswordClue clue = new CrosswordClue("Pista de prueba", "Respuesta de prueba", 1, 1, true);
        
        // Verificamos que los valores se establecieron correctamente
        assertEquals("Pista de prueba", clue.getClue(), "La pista no coincide");
        assertEquals("Respuesta de prueba", clue.getAnswer(), "La respuesta no coincide");
        assertEquals(1, clue.getRow(), "La fila no coincide");
        assertEquals(1, clue.getCol(), "La columna no coincide");
        assertTrue(clue.isHorizontal(), "La dirección no coincide");
    }

    @Test
    void testCheckAnswer() {
        // Creamos una instancia de CrosswordClue
        CrosswordClue clue = new CrosswordClue("Pista de prueba", "Respuesta de prueba", 1, 1, true);
        
        // Obtenemos la respuesta y la comparamos directamente con la respuesta esperada
        String expectedAnswer = "Respuesta de prueba";
        assertEquals(expectedAnswer, clue.getAnswer(), "La respuesta no coincide");
    }
}
