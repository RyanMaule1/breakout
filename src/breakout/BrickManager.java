package breakout;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Point;

public class BrickManager extends GraphicsGroup {

    private static final int BRICK_LAYERS = 5;

    private CanvasWindow canvas;
    private Random rand = new Random();
    private List<Brick> bricks = new ArrayList<Brick>();
    ArrayList<Color> colors = new ArrayList<>(Arrays.asList(Color.BLUE, Color.GREEN, Color.YELLOW, Color.RED));


    BrickManager(CanvasWindow canvas) {
        this.canvas = canvas;
    }

    public List<Brick> getBricks() {
        return bricks;
    }

    public void generateBricks() {

        int y = 60;
        for (int i = 0; i < BRICK_LAYERS; i++) {
            int x = 0;
            y += 30;
            while (x < 600) {

                Brick brick = new Brick(new Point(x, y), rand.nextInt(40, 100) , 30);
                brick.setFillColor(colors.get(rand.nextInt(0, 4)));
                bricks.add(brick);
                canvas.add(brick);
                x += brick.getWidth();
            }
           
        }

    }


}
