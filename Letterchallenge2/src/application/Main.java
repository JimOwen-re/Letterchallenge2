package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * La clase principal de la aplicación, que extiende Application y se encarga de iniciar y gestionar
 * la interfaz gráfica de usuario usando JavaFX.
 */
public class Main extends Application {

    private static final int[] NUM_ROWS = {6, 9, 13}; // Filas para cada nivel
    private static final int[] NUM_COLS = {5, 9, 13}; // Columnas para cada nivel
    private Button[][] buttons;
    private int activeRow = 0; // Fila activa que se rellenará
    private int currentPlayer = 1; // Jugador actual (1 o 2)
    private Text turnText; // Texto para mostrar el turno del jugador
    private Label currentPlayerLabel; // Etiqueta para mostrar el nombre del jugador actual
    private Text levelText; // Texto para mostrar el nivel actual
    private int currentLevel = 1; // Nivel actual
    private String[] targetWords = { "SWEET", "NEWSPAPER", "OVERPROTECTED" }; // Palabras objetivo para cada nivel
    private final Color correctColor = Color.YELLOW; // Color para letras correctas
    private final Color guessedWordColor = Color.ORANGE; // Color para palabra adivinada
    private GridPane gridPane;
    private int[] playerScores = {0, 0}; // Puntajes de los jugadores
    private Text player1ScoreText;
    private Text player2ScoreText;

    private int selectedRow = -1; // Fila seleccionada
    private int selectedCol = -1; // Columna seleccionada

    @Override
    public void start(Stage primaryStage) {
        try {
            VBox root = new VBox();
            root.setPadding(new Insets(10));
            root.setSpacing(10);

            // Crear la escena
            Scene scene = createScene();

            // Mostrar la ventana
            primaryStage.setScene(scene);
            primaryStage.setTitle("Letter Challenge");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método para crear la escena principal de la aplicación.
     * @return La escena principal.
     */
    private Scene createScene() {
        VBox root = new VBox();
        root.setPadding(new Insets(10));
        root.setSpacing(10);

        // Crear la cuadrícula del crucigrama
        gridPane = new GridPane();
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        createGridPane(currentLevel);

        // Controles para seleccionar letras
        HBox controls = new HBox();
        controls.setSpacing(10);
        for (char c = 'A'; c <= 'Z'; c++) {
            Button letterButton = new Button(String.valueOf(c));
            letterButton.setOnAction(event -> {
                Button sourceButton = (Button) event.getSource();
                // Obtener la letra seleccionada y colocarla en el cuadro activo del crucigrama
                String letter = sourceButton.getText();
                placeLetterInSelectedCell(letter);
            });
            controls.getChildren().add(letterButton);
        }

        // Texto para mostrar el turno del jugador
        turnText = new Text("Turno del Jugador " + currentPlayer);
        turnText.setFill(getPlayerColor(currentPlayer));

        // Etiqueta para mostrar el nombre del jugador actual
        currentPlayerLabel = new Label("Jugador " + currentPlayer);
        currentPlayerLabel.setBackground(new Background(new BackgroundFill(getPlayerColor(currentPlayer), null, null)));

        // Texto para mostrar el nivel actual
        levelText = new Text("Nivel " + currentLevel);

        // Botón para reiniciar el juego
        Button restartButton = new Button("Reiniciar Juego");
        restartButton.setOnAction(event -> resetGame());

        // HBox para el turno del jugador
        HBox turnBox = new HBox(turnText);

        // HBox para el nombre del jugador
        HBox playerBox = new HBox(currentPlayerLabel);

        // VBox para el nombre del jugador actual y el botón de reiniciar el juego
        VBox playersBox = new VBox(playerBox, restartButton);
        playersBox.setSpacing(10);

        // HBox para el tablero y los jugadores
        HBox gameArea = new HBox(gridPane, playersBox);

        // Crear etiquetas de texto para mostrar los puntajes
        player1ScoreText = new Text("Puntaje Jugador 1: " + playerScores[0]);
        player2ScoreText = new Text("Puntaje Jugador 2: " + playerScores[1]);

        // Agregar la cuadrícula, los controles, y el área de juego a la raíz
        VBox scoreBox = new VBox(player1ScoreText, player2ScoreText);
        scoreBox.setSpacing(10);
        root.getChildren().addAll(gameArea, controls, turnBox, levelText, scoreBox);

        // Crear y devolver la escena
        return new Scene(root);
    }

    /**
     * Método para reiniciar el juego, restableciendo todos los parámetros y la interfaz gráfica.
     */
    private void resetGame() {
        currentLevel = 1;
        currentPlayer = 1;
        playerScores[0] = 0;
        playerScores[1] = 0;
        activeRow = 0;
        createGridPane(currentLevel);
        turnText.setText("Turno del Jugador " + currentPlayer);
        turnText.setFill(getPlayerColor(currentPlayer));
        currentPlayerLabel.setText("Jugador " + currentPlayer);
        currentPlayerLabel.setBackground(new Background(new BackgroundFill(getPlayerColor(currentPlayer), null, null)));
        levelText.setText("Nivel " + currentLevel);
        player1ScoreText.setText("Puntaje Jugador 1: " + playerScores[0]);
        player2ScoreText.setText("Puntaje Jugador 2: " + playerScores[1]);
        resetBoard(currentLevel);
    }

    /**
     * Método para reiniciar el tablero al cambiar de nivel.
     * @param level El nivel actual del juego.
     */
    private void resetBoard(int level) {
        activeRow = 0;
        createGridPane(level);
        // Limpiar el contenido de todas las casillas
        for (int i = 0; i < NUM_ROWS[level - 1]; i++) {
            for (int j = 0; j < NUM_COLS[level - 1]; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    /**
     * Método para crear la cuadrícula de acuerdo al nivel actual.
     * @param level El nivel actual del juego.
     */
    private void createGridPane(int level) {
        gridPane.getChildren().clear();
        buttons = new Button[NUM_ROWS[level - 1]][NUM_COLS[level - 1]];
        for (int i = 0; i < NUM_ROWS[level - 1]; i++) {
            for (int j = 0; j < NUM_COLS[level - 1]; j++) {
                Button button = new Button();
                button.setPrefSize(getButtonSize(level), getButtonSize(level));
                button.setStyle("-fx-border-color: black; -fx-border-width: 1px;"); // Establecer borde negro
                buttons[i][j] = button;
                gridPane.add(button, j, i);

                // Añadir evento para seleccionar el botón
                int row = i;
                int col = j;
                button.setOnAction(event -> {
                    selectedRow = row;
                    selectedCol = col;
                });
            }
        }
    }

    /**
     * Método para obtener el tamaño de los botones según el nivel.
     * @param level El nivel actual del juego.
     * @return El tamaño de los botones.
     */
    private double getButtonSize(int level) {
        if (level == 2) {
            return 40; // Tamaño para el nivel 2 (9x9)
        } else if (level == 3) {
            return 30; // Tamaño para el nivel 3 (13x13)
        } else {
            return 30; // Tamaño predeterminado para otros niveles
        }
    }

    /**
     * Método para colocar una letra en la celda seleccionada.
     * @param letter La letra seleccionada.
     */
    private void placeLetterInSelectedCell(String letter) {
        if (selectedRow >= 0 && selectedCol >= 0) {
            buttons[selectedRow][selectedCol].setText(letter);
            highlightMatchingLetters(selectedRow, selectedCol);
            
            // Verificar si la letra colocada coincide con alguna letra de las palabras objetivo
            String targetWord = getTargetWord();
            if (targetWord.contains(letter)) {
                // Otorgar puntos al jugador actual
                playerScores[currentPlayer - 1] += 5;
                updatePlayerScores(); // Actualizar los puntajes mostrados en la interfaz
            }
            
            // Verificar si se completó la fila activa
            if (selectedCol == NUM_COLS[currentLevel - 1] - 1 && isRowFilled(selectedRow)) {
                int correctLetters = countCorrectLetters(selectedRow);
                playerScores[currentPlayer - 1] += correctLetters * 5; // Aumentar el puntaje del jugador actual
                updatePlayerScores(); // Actualizar los puntajes mostrados en la interfaz
                changePlayer();
            }
        }
    }

    /**
     * Método para actualizar los puntajes mostrados en la interfaz.
     */
    private void updatePlayerScores() {
        player1ScoreText.setText("Puntaje Jugador 1: " + playerScores[0]);
        player2ScoreText.setText("Puntaje Jugador 2: " + playerScores[1]);
    }

    /**
     * Método para contar la cantidad de letras correctas en una fila.
     * @param row La fila a verificar.
     * @return La cantidad de letras correctas.
     */
    private int countCorrectLetters(int row) {
        String word = getWordFromRow(row);
        String targetWord = getTargetWord();
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == targetWord.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Método para obtener el color del jugador.
     * @param player El jugador actual.
     * @return El color del jugador.
     */
    private Color getPlayerColor(int player) {
        return (player == 1) ? Color.LIGHTGREEN : Color.LIGHTBLUE;
    }

    /**
     * Método para resaltar las letras que coinciden con la palabra objetivo.
     * @param row La fila de la letra seleccionada.
     * @param col La columna de la letra seleccionada.
     */
    private void highlightMatchingLetters(int row, int col) {
        String letter = buttons[row][col].getText();
        if (letter.length() == 1 && getTargetWord().contains(letter)) {
            buttons[row][col].setBackground(new Background(new BackgroundFill(correctColor, null, null)));
        } else {
            buttons[row][col].setBackground(null);
        }
    }

    /**
     * Método para verificar si la palabra formada es correcta y resaltar las letras con el color del jugador.
     */
    private void checkWordAndHighlight() {
        String word = getWordFromRow(activeRow);
        if (word.equals(getTargetWord())) {
            for (int j = 0; j < NUM_COLS[currentLevel - 1]; j++) {
                buttons[activeRow][j].setBackground(new Background(new BackgroundFill(guessedWordColor, null, null)));
            }
            if (currentLevel < targetWords.length) {
                showAlertWordCompleted(); // Mostrar mensaje de palabra completada
                currentLevel++; // Cambiar al siguiente nivel
                levelText.setText("Nivel " + currentLevel); // Actualizar el texto del nivel
                resetBoard(currentLevel); // Reiniciar el tablero para el siguiente nivel
            } else {
                determineWinner(); // Determinar al ganador cuando se completa el juego
            }
        }
    }

    /**
     * Método para mostrar una alerta cuando se completa una palabra.
     */
    private void showAlertWordCompleted() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Palabra Completada");
        alert.setHeaderText(null);
        alert.setContentText("¡Felicidades! Has completado la palabra. Avanzando al siguiente nivel.");
        alert.showAndWait();
    }

    /**
     * Método para cambiar al siguiente jugador.
     */
    private void changePlayer() {
        // Verificar si la fila actual coincide con la palabra objetivo antes de cambiar el turno
        checkWordAndHighlight();
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
        turnText.setText("Turno del Jugador " + currentPlayer);
        turnText.setFill(getPlayerColor(currentPlayer));
        currentPlayerLabel.setText("Jugador " + currentPlayer);
        currentPlayerLabel.setBackground(new Background(new BackgroundFill(getPlayerColor(currentPlayer), null, null)));

        // Asegurarse de que la fila activa cambia solo si la fila actual está llena
        if (isRowFilled(activeRow)) {
            activeRow = (activeRow + 1) % NUM_ROWS[currentLevel - 1];
            // Mostrar mensaje si se han llenado todas las filas sin adivinar la palabra
            if (activeRow == 0) {
                if (currentLevel < targetWords.length) {
                    showAlertNoWinner();
                    resetBoard(currentLevel); // Reiniciar el nivel
                } else {
                    determineWinner(); // Determinar al ganador cuando se completa el juego
                }
            }
        }
    }

    /**
     * Método para mostrar una alerta cuando nadie adivina la palabra.
     */
    private void showAlertNoWinner() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Nivel Completo");
        alert.setHeaderText(null);
        alert.setContentText("Nadie adivinó la palabra. Reiniciando el nivel.");
        alert.showAndWait();
    }

    /**
     * Método para determinar al ganador al final del juego.
     */
    private void determineWinner() {
        if (playerScores[0] > playerScores[1]) {
            showAlertWinner(1);
        } else if (playerScores[1] > playerScores[0]) {
            showAlertWinner(2);
        } else {
            showAlertTie();
        }
    }

    /**
     * Método para mostrar una alerta cuando el juego termina con un ganador.
     * @param winner El jugador ganador.
     */
    private void showAlertWinner(int winner) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fin del Juego");
        alert.setHeaderText(null);
        alert.setContentText("¡Felicidades, Jugador " + winner + "! ¡Eres el ganador con un puntaje de " + playerScores[winner - 1] + " puntos!");
        alert.showAndWait();
    }

    /**
     * Método para mostrar una alerta cuando el juego termina en empate.
     */
    private void showAlertTie() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Fin del Juego");
        alert.setHeaderText(null);
        alert.setContentText("¡Es un empate! Ambos jugadores tienen la misma puntuación.");
        alert.showAndWait();
    }

    /**
     * Método para obtener la palabra objetivo del nivel actual.
     * @return La palabra objetivo.
     */
    private String getTargetWord() {
        return targetWords[currentLevel - 1];
    }

    /**
     * Método para obtener la palabra formada en una fila específica.
     * @param row La fila de la que se obtiene la palabra.
     * @return La palabra formada en la fila.
     */
    private String getWordFromRow(int row) {
        StringBuilder word = new StringBuilder();
        for (int col = 0; col < NUM_COLS[currentLevel - 1]; col++) {
            word.append(buttons[row][col].getText());
        }
        return word.toString();
    }

    /**
     * Método para verificar si una fila está completamente llena.
     * @param row La fila a verificar.
     * @return true si la fila está llena, false en caso contrario.
     */
    private boolean isRowFilled(int row) {
        for (int col = 0; col < NUM_COLS[currentLevel - 1]; col++) {
            if (buttons[row][col].getText().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
