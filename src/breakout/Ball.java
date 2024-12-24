package breakout;


import java.util.List;
import java.awt.Color;
import java.awt.Paint;
import java.util.ArrayList;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.Point;


public class Ball extends Ellipse {

   private double x;
   private double y;
   private int xDirection = 1;
   private int yDirection = 1;
   private Paddle paddle;
   private CanvasWindow canvas;
   private List<Point> corners = new ArrayList<Point>();
   private BrickManager brickManager;
   private int speed = 4;
   private int bricksDestroyed = 0;

   
    Ball(double x, double y, int width,int height, CanvasWindow canvas, Paddle paddle, BrickManager brickManager) {
        super(x, y, width, height);
        this.canvas = canvas;
        this.paddle = paddle;
        this.brickManager = brickManager;
    }

    public int getBricksDestroyed() {
        return bricksDestroyed;
    }

    public int getXDirection() {
        return xDirection;
    }

    public int getYDirection() {
        return yDirection;
    }

    public void setXDirection(int xDirection) {
        this.xDirection = xDirection;
    }

    public void flipYDirection() {
        this.yDirection *= -1;
    }

    public void move() {

        x = this.getPosition().getX();
        y = this.getPosition().getY();


        changeDirection();
        

        this.setPosition(x -= speed * xDirection, y -= speed * yDirection);

    }

    public void checkIntersection() {

        Point topLeft = new Point(this.getX(), this.getY());
        Point topRight = new Point(this.getX() + this.getWidth(), this.getY());
        Point bottomLeft = new Point(this.getX(), this.getY() + this.getHeight());
        Point bottomRight = new Point(this.getX() + this.getWidth(), this.getY() + this.getHeight());


        corners.add(bottomLeft);
        corners.add(bottomRight);
        corners.add(topLeft);
        corners.add(topRight);


        //check each brick
        for (Brick b: brickManager.getBricks()) {
            //check each corner of the balls bounding box against each brick
            for (Point p : corners) {
                if (canvas.getElementAt(p.getX(), p.getY()) == b) {
                    if (b.checkSide(p) == "flipBoth") {
                        xDirection *= -1;
                        yDirection *= -1;
                    }
                    else if (b.checkSide(p) == "flipX") {
                        xDirection *= -1;
                    } else if (b.checkSide(p) == "flipY") {
                        yDirection *= -1;
                    }
                    canvas.remove(b);
                    changeSpeed(b.getFillColor());
                    bricksDestroyed++;
                }

            }
        }

        corners.removeAll(corners);
        
    }

    public void changeDirection() {
        
        //checks for wall intersection
        if (x >= canvas.getWidth() || x <= 0) {
            xDirection *= -1;
        }

        //checks for ceiling intersection
        if (y <= 0) {
            // switch Y direction to go the opposite way
            yDirection *= -1;
        }

        //checks for paddle intersection
        if (canvas.getElementAt(this.getPosition()) == paddle) {
            yDirection *= -1;
        }


        //checks for brick intersections
        checkIntersection();


    }

    //change ball speed based on color of brick it last interacted with
    public void changeSpeed(Paint color) {
        if (color == Color.BLUE) {
            speed = 2;
        } else if (color == Color.GREEN) {
            speed = 4;
        } else if (color == Color.YELLOW) {
            speed = 6;
        } else if (color == Color.RED) {
            speed = 8;
        }
    }


    
}

