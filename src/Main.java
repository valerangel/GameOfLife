public class Main {

    public static void main(String[] args) {

        GameOfLife gameOfLife = new GameOfLife(20, 20);
        Paint paint = new Paint(gameOfLife);

        for (int i = 0; i < 1000; i++) {
            paint.paintCells();
            gameOfLife.iterate();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
