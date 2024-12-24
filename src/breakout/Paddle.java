package breakout;


import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.events.MouseMotionEvent;

public class Paddle extends Rectangle {
    

    Paddle(Point position, int width, int height) {
        super(position,new Point(width, height));
       
    }

    public void move(MouseMotionEvent e) {
        this.setPosition(e.getPosition().getX(), this.getPosition().getY());
    }

    

}
