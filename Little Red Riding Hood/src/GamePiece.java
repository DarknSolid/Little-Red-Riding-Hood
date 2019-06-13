import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

abstract public class GamePiece {

    protected int posI, posJ;
    protected Color color;
    protected int moves, movesLeft;

    public GamePiece(Color color, int moves) {
        this.color = color;
        this.moves = moves;
        movesLeft = moves;
    }

    protected boolean move(int iTo, int jTo, World world) {
        if (!world.isFreeTile(iTo, jTo)) return false;
        Tile current = world.getTileAtPosition(posI, posJ);
        current.setGamePiece(null);
        movesLeft -= current.getCost();
        current = world.getTileAtPosition(iTo, jTo);
        current.setGamePiece(this);
        posI = iTo;
        posJ = jTo;

        return true;
    }

    public boolean hasFinishedTurn(World world) {
        return movesLeft <= 0 || movesLeft < world.getTileAtPosition(posI, posJ).getCost();
    }

    public void resetMovesLeft() {
        movesLeft = moves;
    }

    abstract void draw(GraphicsContext gc, int centerX, int centerY, int tileSize);

    public int getPosI() {
        return posI;
    }

    public int getPosJ() {
        return posJ;
    }

    public void setPosition(int posI, int posJ) {
        this.posI = posI;
        this.posJ = posJ;
    }
}
