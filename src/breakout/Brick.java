package breakout;

import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Point;

public class Brick extends Rectangle {
    Brick(Point position, int width, int height) {
        super(position,new Point(width, height));   
    }

    public String checkSide(Point p) {

        

        if (p.getY() > this.getPosition().getY() && p.getY() < this.getPosition().getY() + this.getHeight() &&
            p.getX() == this.getPosition().getX() || p.getX() == this.getPosition().getX() + this.getWidth()) {
                return "flipBoth";
            }

        if (p.getY() > this.getPosition().getY() && p.getY() < this.getPosition().getY() + this.getHeight()) {
            return "flipY";
        }


        
        if (p.getX() == this.getPosition().getX() || p.getX() == this.getPosition().getX() + this.getWidth()) {
            return "flipX";
        }

        
        return "";

    }

    
   
}
