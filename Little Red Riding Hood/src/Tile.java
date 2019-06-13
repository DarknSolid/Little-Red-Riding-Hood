import javafx.scene.canvas.GraphicsContext;
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

    public Tile(int posI, int posJ, int tileSize, Color color, TileTypes tileType, int cost) {
        this.posI = posI;
        this.posJ = posJ;
        this.tileSize = tileSize;
        this.color = color;
        this.tileType = tileType;
        this.cost = cost;
    }

    public void draw(GraphicsContext gc) {
        //Border
        gc.setFill(Color.BLACK);
        gc.fillRect(posJ * tileSize, posI * tileSize, tileSize, tileSize);

        //Tile
        gc.setFill(color);
        gc.fillRect(posJ * tileSize, posI * tileSize, tileSize - 2, tileSize - 2);

        //GamePiece
        if (gamePiece != null) {
            gamePiece.draw(gc, posJ * tileSize, posI * tileSize, tileSize);
        }
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
