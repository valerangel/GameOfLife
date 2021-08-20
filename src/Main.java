public class Main {

    public static void main(String[] args) {

        GameOfLife gameOfLife = new GameOfLife(100, 30);
        Painter visual = new Painter(gameOfLife);

        for (int i = 0; i < 1000; i++) {
            gameOfLife.iterate();
            visual.paintCells();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
