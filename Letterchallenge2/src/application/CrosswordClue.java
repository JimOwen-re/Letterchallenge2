package application;

public class CrosswordClue {
    private String clue;
    private String answer;
    private int row;
    private int col;
    private boolean isHorizontal;

    public CrosswordClue(String clue, String answer, int row, int col, boolean isHorizontal) {
        this.clue = clue;
        this.answer = answer;
        this.row = row;
        this.col = col;
        this.isHorizontal = isHorizontal;
    }

    // Getters y setters
    public String getClue() {
        return clue;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public void setHorizontal(boolean isHorizontal) {
        this.isHorizontal = isHorizontal;
    }
}
