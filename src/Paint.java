import javax.swing.*;
import java.awt.*;

public class Paint {
    private final int LENGTH = 60;

    private JFrame jframe;
    private JPanel jpanel;
    private Graphics g;
    private GameOfLife gameOfLife;

    public Paint(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;

        this.jpanel = new JPanel(new BorderLayout());

        this.jframe = new JFrame("Game of Life");
        this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jframe.setSize(gameOfLife.getWidth() * LENGTH + 15,
                gameOfLife.getHeight() * LENGTH + 45);
        this.jframe.setVisible(true);

        this.jpanel.setSize(gameOfLife.getWidth() * LENGTH + 15,
                gameOfLife.getHeight() * LENGTH + 45);
        this.jframe.add(this.jpanel);

        this.jframe.setResizable(false);

        this.g = jpanel.getGraphics();
        paintCells();
    }

    public void paintCells() {
        allBlack();
        for (int i = 0; i < gameOfLife.getWidth(); i++) {
            for (int j = 0; j < gameOfLife.getHeight(); j++) {
                if (gameOfLife.getBoard()[i][j] == CellStatus.ALIVE) {
                    g.setColor(Color.white);
                    g.drawOval((int) ((i + 0.33) * LENGTH),
                            (int) ((j + 0.33) * LENGTH),
                            (int) (0.67 * LENGTH),
                            (int) (0.67 * LENGTH));
                }
            }
        }
    }

    private void allBlack() {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.gameOfLife.getWidth() * LENGTH,
                this.gameOfLife.getHeight() * LENGTH);
    }
}
