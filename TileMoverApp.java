import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class TileMoverApp extends Application
{
    Game game;
    GraphicsContext gc;
    Animation animation;

    final double GRIDSIZE = 30;

    public void start(Stage stage)
    {
        game = new Game();

        Canvas canvas = new Canvas(game.width * GRIDSIZE, game.height * GRIDSIZE);
        gc = canvas.getGraphicsContext2D();

        VBox vbox = new VBox();
        vbox.getChildren().add(canvas);

        Scene scene = new Scene(vbox);
        scene.setOnKeyPressed(this::handleKey);

        stage.setScene(scene);
        stage.setTitle("Tile Mover");
        stage.show();

        animation = new Animation();
        animation.start();
    }

    void handleKey(KeyEvent e)
    {
        KeyCode key = e.getCode();

        if (key == KeyCode.P) // Press P to pause/unpause
        {
            animation.togglePause();
        }
        else if (key == KeyCode.W)
        {
            game.mover.move(0, -1);
        }
        else if (key == KeyCode.A)
        {
            game.mover.move(-1, 0);
        }
        else if (key == KeyCode.S)
        {
            game.mover.move(0, 1);
        }
        else if (key == KeyCode.D)
        {
            game.mover.move(1, 0);
        }
        else if (key == KeyCode.R)
        {
            game = new Game();
        }
    }

    void renderMover()
    {
        gc.setFill(Color.SEAGREEN);
        double x = game.mover.x * GRIDSIZE;
        double y = game.mover.y * GRIDSIZE;
        double w = GRIDSIZE;
        double h = GRIDSIZE;
        gc.fillOval(x, y, w, h);
    }

    void renderTile(Tile t)
    {
        gc.setFill(Color.DARKGRAY);
        double x = t.x * GRIDSIZE;
        double y = t.y * GRIDSIZE;
        double w = GRIDSIZE;
        double h = GRIDSIZE;
        gc.fillRect(x, y, w, h);
    }

    void renderTiles()
    {
        for (Tile t : game.tiles)
        {
            renderTile(t);
        }
    }

    void renderGame()
    {
        // Draw a white rectangle over everything.
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, game.width * GRIDSIZE, game.height * GRIDSIZE);

        renderTiles();
        renderMover();
    }

    class Animation extends AnimationTimer
    {
        private boolean isPaused = false;
        long lastUpdate = 0;

        /**
         * The main game loop.
         * 
         * This method will be called 60 times per second while SnakeApp runs.
         */
        public void handle(long t)
        {
            renderGame();
        }

        /**
         * Toggles between pausing and unpausing.
         */
        public void togglePause()
        {
            if (isPaused)
            {
                start();
                isPaused = false;
            }
            else
            {
                stop();
                isPaused = true;
            }
        }
    }
}
