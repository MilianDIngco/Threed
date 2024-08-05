/**
 * The KeyHandler class keeps track of the keys pressed
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean w, a, s, d, left, right, up, down;

    @Override
    public void keyTyped(KeyEvent e) {
      // TODO document why this method is empty
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W)
            w = true;
        if (code == KeyEvent.VK_A)
            a = true;
        if (code == KeyEvent.VK_S)
            s = true;
        if(code == KeyEvent.VK_D) 
            d = true;
        if(code == 37)
            left = true;
        if(code == 39)
            right = true;
        if(code == 38)
            up = true;
        if(code == 40)
            down = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W)
            w = false;
        if (code == KeyEvent.VK_A)
            a = false;
        if (code == KeyEvent.VK_S)
            s = false;
        if(code == KeyEvent.VK_D) 
            d = false;
        if(code == 37)
            left = false;
        if(code == 39)
            right = false;
        if(code == 38)
            up = false;
        if(code == 40)
            down = false;
    }

}
