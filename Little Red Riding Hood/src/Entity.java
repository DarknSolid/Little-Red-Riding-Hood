import javafx.scene.paint.Color;

abstract class Entity extends GamePiece {

    public Entity(Color color, int moves, String pathToTexture) {
        super(color, moves, pathToTexture);
    }

    abstract void move(World world);
}
