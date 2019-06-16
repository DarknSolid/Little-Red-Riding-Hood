import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

abstract public class GamePiece {

    protected int posI, posJ;
    protected int moves, movesLeft;
    private Image img;


    public GamePiece(int moves, String pathToTexture) {
        this.moves = moves;
        this.img = new Image(getClass().getResourceAsStream(pathToTexture));
        movesLeft = moves;
    }

    protected boolean move(int iTo, int jTo, World world) {
        if (!world.isFreeTile(iTo, jTo)) return false;
        Tile current = world.getTileAtPosition(posI, posJ);
        current.setGamePiece(null);
        movesLeft -= current.getCost();
        current = world.getTileAtPosition(iTo, jTo);
        current.setGamePiece(this);
        this.posI = iTo;
        this.posJ = jTo;

        return true;
    }

    public boolean hasFinishedTurn(World world) {
        return movesLeft <= 0 || movesLeft < world.getTileAtPosition(posI, posJ).getCost();
    }

    public void resetMovesLeft() {
        movesLeft = moves;
    }

    public void draw(GraphicsContext gc, int centerX, int centerY, int tileSize) {
        gc.drawImage(img, posJ*tileSize, posI * tileSize, tileSize, tileSize);
    }

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
