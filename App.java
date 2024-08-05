import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class App {

    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public static void main(String[] args) {
        JFrame frame = new JFrame("THNEED");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);

        Matrix.initPersProjMatrix(40, 100, 10);

        int x = -50, y = -50, z = 100, length = 100;

        Model model = new Model(new Vertex[] {
                new Vertex(x, y, z),
                new Vertex(x + length, y, z),
                new Vertex(x, y + length, z),
                new Vertex(x + length, y + length, z),
                new Vertex(x, y, z + length),
                new Vertex(x + length, y, z + length),
                new Vertex(x, y + length, z + length),
                new Vertex(x + length, y + length, z + length)
        }, new int[][] {
                { 0, 1, 3 }, { 0, 3, 2 },
                { 1, 5, 7 }, { 7, 3, 1 },
                { 5, 4, 6 }, { 5, 6, 7 },
                { 4, 0, 2 }, { 4, 2, 6 },
                { 4, 5, 1 }, { 4, 1, 0 },
                { 7, 3, 2 }, { 7, 2, 6 }
        });
        model.setCenter(new Vertex(x + length / 2, y + length / 2, z + length / 2));

        ObjectManager om = new ObjectManager();
        om.addModel(model);

        DPanel dp = new DPanel(om);
        frame.add(dp);
        dp.gameThread.start();
        dp.grabFocus();

        frame.setVisible(true);
    }

}