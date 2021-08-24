import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class Painter {
    private final int LENGTH = 10;

    private JFrame jframe;
    private JPanel jpanel1;
    private JPanel jpanel2;
    private Graphics g1;
    private Graphics g2;
    private GameOfLife gameOfLife;
    private NumberOfLayer numberOfLayer;

    public Painter(GameOfLife gameOfLife) {
        this.gameOfLife = gameOfLife;

        this.numberOfLayer = NumberOfLayer.ONE;

        this.jframe = new JFrame("Game of Life");
        this.jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.jframe.setSize(gameOfLife.getWidth() * LENGTH + 15,
                gameOfLife.getHeight() * LENGTH + 45);
        this.jframe.setVisible(true);


        this.jpanel1 = new JPanel(new BorderLayout());
        this.jpanel1.setSize(gameOfLife.getWidth() * LENGTH + 15,
                gameOfLife.getHeight() * LENGTH + 45);

        this.jpanel2 = new JPanel(new BorderLayout());
        this.jpanel2.setSize(gameOfLife.getWidth() * LENGTH + 15,
                gameOfLife.getHeight() * LENGTH + 45);

        this.jframe.add(this.jpanel1);
        this.jframe.add(this.jpanel2);

        this.jframe.setResizable(false);

        this.g1 = jpanel1.getGraphics();
        this.g2 = jpanel2.getGraphics();
        paintCells();
    }

    public void paintCells() {
        ArrayList<Position> cellsAlive = new ArrayList<>();

        Graphics g;
        if (this.numberOfLayer == NumberOfLayer.ONE) {
            g = g1;
        } else {
            g = g2;
        }


        for (int i = 0; i < gameOfLife.getWidth(); i++) {
            for (int j = 0; j < gameOfLife.getHeight(); j++) {
                if (gameOfLife.getBoard()[i][j] == CellStatus.ALIVE) {
                    cellsAlive.add(new Position(i, j));
                }
            }
        }

        allBlack(g);

        long startTime = System.nanoTime();

        paintCells(g, cellsAlive);

        long endTime = System.nanoTime();

        long duration = (endTime - startTime)/1000000;
        System.out.println("Tardo en pintar los círculos" + duration + " ms");

        //long startTime = System.nanoTime();

        changeJFrame();

        //long endTime = System.nanoTime();

        //long duration = (endTime - startTime)/1000000;
        //System.out.println("Tardo en pintar " + duration + " ms");

    }

    private void allBlack(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0,
                this.gameOfLife.getWidth() * LENGTH,
                this.gameOfLife.getHeight() * LENGTH);
    }

    private void paintCells(Graphics g, ArrayList<Position> cellsAlive){
        g.setColor(Color.WHITE);
        for (Position p : cellsAlive) {
            g.drawOval((int) ((p.getCoorX() + 0.33) * LENGTH),
                    (int) ((p.getCoorY() + 0.33) * LENGTH),
                    (int) (0.67 * LENGTH),
                    (int) (0.67 * LENGTH));
        }
    }

    private void changeJFrame (){
        if (this.numberOfLayer == NumberOfLayer.ONE) {
            this.numberOfLayer = NumberOfLayer.TWO;
            //jframe.removeAll();
            this.jpanel1.setVisible(true);

        } else if (this.numberOfLayer == NumberOfLayer.TWO) {
            this.numberOfLayer = NumberOfLayer.ONE;
            //jframe.removeAll();
            this.jpanel2.setVisible(true);
        }
    }
}
