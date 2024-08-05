import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DPanel extends JPanel implements Runnable {

    static final int FPS = 120;
    Thread gameThread;
    KeyHandler keyh;
    Mouse mouse;
    ObjectManager om;

    public DPanel(ObjectManager om) {
        gameThread = new Thread(this);
        keyh = new KeyHandler();
        mouse = new Mouse();
        this.om = om;

        this.setFocusable(true);
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);

        this.addKeyListener(keyh);
        this.addMouseMotionListener(mouse);
        
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();
        
            delta += (currentTime - lastTime) / drawInterval;
        
            lastTime = currentTime;
        
            if (delta >= 1) {
                update();
            
                repaint();
            
                delta--;
            }
        }  
    }
    
    public void update() {
        om.updateModels(keyh, mouse);
    }

    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        g2.translate(App.screenSize.getWidth() / 2, App.screenSize.getHeight() / 2);
        g2.setColor(Color.white);

        om.drawModels(g2);

        g2.dispose();

    }
}
