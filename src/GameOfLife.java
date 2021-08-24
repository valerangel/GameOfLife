public class GameOfLife {
    private final double CONSTANT_FILL = 0.5;
    private CellStatus[][] board;
    private int height;
    private int width;

    public GameOfLife(int width, int height) {
        this.width = width;
        this.height = height;

        this.board = new CellStatus[width][height];

        this.fillBoard();
    }

    public void iterate() {
        CellStatus[][] newBoard = copyBoard(this.board);
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (isAlive(i, j) == true) newBoard[i][j] = CellStatus.ALIVE;
                else newBoard[i][j] = CellStatus.DEAD;
            }
        }
        this.board = newBoard;
    }

    private CellStatus[][] copyBoard(CellStatus[][] board) {
        CellStatus[][] newBoard = new CellStatus[width][height];

        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    private void fillBoard() {
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board[i].length; j++) {
                if (Math.random() < CONSTANT_FILL) {
                    board[i][j] = CellStatus.ALIVE;
                } else {
                    board[i][j] = CellStatus.DEAD;
                }
            }
        }
    }

    private boolean isAlive(int i, int j) {
        int neighboursAlive = 0;
        for (int k = -1; k <= 1; k++) {
            for (int l = -1; l <= 1; l++) {
                if ((i + k >= 0) &&
                        (i + k < width) &&
                        (j + l >= 0) &&
                        (j + l < height) &&
                        !(k == 0 && l == 0)) {
                    if (board[i + k][j + l] == CellStatus.ALIVE) neighboursAlive++;
                }
            }
        }

        if (neighboursAlive == 3) {
            return true;
        }
        if (neighboursAlive == 2 && this.board[i][j] == CellStatus.ALIVE) {
            return true;
        }
        return false;
    }

    public CellStatus[][] getBoard() {
        return this.board;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
