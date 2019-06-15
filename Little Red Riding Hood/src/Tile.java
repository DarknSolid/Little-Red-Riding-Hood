import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Tile {
    public enum TileTypes {
        GRASS, FOREST, ROCK, WATER
    }

    private TileTypes tileType;
    private int posI, posJ;
    private int tileSize;
    private int cost;
    private Color color;
    private GamePiece gamePiece;
    private Image texture;

    public Tile(int posI, int posJ, int tileSize, Image texture, TileTypes tileType, int cost) {
        this.posI = posI;
        this.posJ = posJ;
        this.tileSize = tileSize;
        this.texture = texture;
        this.tileType = tileType;
        this.cost = cost;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(texture, posJ * tileSize, posI * tileSize, tileSize, tileSize);
        if (gamePiece == null) return;
        gamePiece.draw(gc, posI, posJ, tileSize);
    }

    public void drawDot(GraphicsContext gc) {
        gc.setFill(Color.PURPLE);
        gc.fillRect(posJ * tileSize, posI * tileSize, tileSize * .1, tileSize * .1);
    }

    public GamePiece getGamePiece() {
        return gamePiece;
    }

    public void setGamePiece(GamePiece gamePiece) {
        this.gamePiece = gamePiece;
    }

    public TileTypes getTileType() {
        return tileType;
    }

    public int getCost() {
        return cost;
    }

    public int getPosI() {
        return posI;
    }

    public int getPosJ() {
        return posJ;
    }
}
