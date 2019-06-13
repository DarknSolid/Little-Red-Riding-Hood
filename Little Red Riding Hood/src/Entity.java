import javafx.scene.paint.Color;

abstract class Entity extends GamePiece {

    public Entity(Color color, int moves) {
        super(color, moves);
    }

    abstract void move(World world);
}
