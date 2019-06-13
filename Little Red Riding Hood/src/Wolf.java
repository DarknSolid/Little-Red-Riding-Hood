import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Stack;

public class Wolf extends Entity {

    Player player;
    Stack<Tile> path;

    public Wolf(Player player) {
        super(Color.BLACK, 2);
        this.player = player;
    }

    @Override
    void move(World world) {
        path = world.getPathFromCloseTo(posI, posJ, player.getPosI(), player.getPosJ());
        if (movesLeft >= 0 && path.size() > 0) {
            Tile tile = path.pop();
            move(tile.getPosI(), tile.getPosJ(), world);
        } else movesLeft = 0;
    }

    @Override
    void draw(GraphicsContext gc, int centerX, int centerY, int tileSize) {
        gc.setFill(color);
        gc.fillOval(centerX, centerY, tileSize * .9f, tileSize * .9f);
    }

    public List<Tile> getPath() {
        return path;
    }
}
