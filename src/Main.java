import static java.awt.desktop.UserSessionEvent.Reason.LOCK;

public class Main {

    public static void main(String[] args) {

        GameOfLife gameOfLife = new GameOfLife(100, 80);
        Painter visual = new Painter(gameOfLife);

        int timeSleeping = 500;

        for (int i = 0; i < 10000; i++) {
            gameOfLife.iterate();
            visual.paintCells();
            try {
                Thread.sleep(timeSleeping);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
