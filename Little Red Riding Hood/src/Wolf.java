import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Stack;

public class Wolf extends Entity {

    private Player player;
    private Stack<Tile> path;

    public Wolf(Player player) {
        super(Color.BLACK, 2, "textures/wolfhead.png");
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

    public List<Tile> getPath() {
        return path;
    }
}
