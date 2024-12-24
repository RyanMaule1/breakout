package breakout;


import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import java.awt.Color;


/**
 * The game of Breakout.
 */
public class BreakoutGame {
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 800;
    private int lives = 4;

    private CanvasWindow canvas;
    private Paddle paddle;
    private Ball ball;
    private BrickManager brickManager;


    public BreakoutGame() {
        canvas = new CanvasWindow("Breakout!", CANVAS_WIDTH, CANVAS_HEIGHT);

        canvas.setBackground(Color.BLACK);
        brickManager = new BrickManager(canvas);

        addPaddle();
        canvas.onMouseMove(e -> {
            
            paddle.move(e);
        });

        addBall();
        canvas.animate(() -> {
            resetBall();
            ball.move();
            
            
            
            if (brickManager.getBricks().size() == ball.getBricksDestroyed()) {
                youWin();
            }

        });

        brickManager.generateBricks();

    }

    private void addPaddle() {
        paddle = new Paddle(new Point(0,0), CANVAS_WIDTH / 5, CANVAS_HEIGHT / 50);
        paddle.setCenter(CANVAS_WIDTH / 2, CANVAS_HEIGHT - 25);
        paddle.setFillColor(Color.WHITE);
        canvas.add(paddle);
    }

    private void addBall() {
        ball = new Ball(0, 0, CANVAS_WIDTH / 50, CANVAS_WIDTH / 50, canvas, paddle, brickManager);
        ball.setCenter(CANVAS_WIDTH / 2, CANVAS_HEIGHT - 25 - paddle.getHeight());
        ball.setFillColor(Color.white);
        canvas.add(ball);
    }

    private void resetBall() {
        if (ball.getY() >= CANVAS_HEIGHT) {
            canvas.pause(3000);
            //yDirection should not be a public variable
            ball.flipYDirection();
            ball.setPosition(CANVAS_WIDTH / 2, CANVAS_HEIGHT - 30 - paddle.getHeight());
            lives--;
            if (lives == 0) {
                youLose();
            }
        }
    }

    public void youLose() {
       canvas.removeAll();
       GraphicsText gameOverText = new GraphicsText("You Lost! Exit out and Reopen to Play Again");
       gameOverText.setFillColor(Color.WHITE);
       canvas.add(gameOverText, CANVAS_WIDTH/2 - gameOverText.getWidth(), CANVAS_HEIGHT/2);
       canvas.draw();
    }

    public void youWin() {
        canvas.removeAll();
        GraphicsText gameOverText = new GraphicsText("You Won! Exit out and Reopen to Play Again");
        gameOverText.setFillColor(Color.WHITE);
        canvas.add(gameOverText, CANVAS_WIDTH / 2 - gameOverText.getWidth(), CANVAS_HEIGHT / 2);
        canvas.draw();
    }
    

    public static void main(String[] args){
        new BreakoutGame();
    }
}
