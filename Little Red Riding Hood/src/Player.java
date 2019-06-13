import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class Player extends GamePiece {

    private long lastRegen;

    public Player(Color color, int moves) {

        super(color, moves);
    }

    public void moveInDirection(KeyCode direction, World world) {
        if (movesLeft <= 0) return;
        switch (direction) {
            case W:
                move(posI - 1, posJ, world);
                break;
            case S:
                move(posI + 1, posJ, world);
                break;
            case A:
                move(posI, posJ - 1, world);
                break;
            case D:
                move(posI, posJ + 1, world);
                break;
        }
    }

    public void regenMoves(int timeOfRound) {
        if (movesLeft >= moves) return;
        if (System.currentTimeMillis() - lastRegen >= Math.floorDiv(timeOfRound, moves)) {
            movesLeft++;
            lastRegen = System.currentTimeMillis();
        }
    }

    @Override
    void draw(GraphicsContext gc, int centerX, int centerY, int tileSize) {
        gc.setFill(color);
        gc.fillOval(centerX, centerY, tileSize * .9f, tileSize * .9f);
    }
}
