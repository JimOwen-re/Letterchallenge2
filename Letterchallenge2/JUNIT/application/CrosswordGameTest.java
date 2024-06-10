package application;

import org.junit.Test;
import static org.junit.Assert.*;

public class CrosswordGameTest {

    @Test
    public void testInitialization() {
        CrosswordGame game = new CrosswordGame(5, 5);
        assertNotNull(game);
    }
}
