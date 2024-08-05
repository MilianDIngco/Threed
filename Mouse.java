/**
 * The Mouse class keeps track of the x and y coordinate of the mouse+
 */

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseMotionListener {

    double x, y;

    @Override
    public void mouseDragged(MouseEvent e) {
      // TODO document why this method is empty
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }

}
