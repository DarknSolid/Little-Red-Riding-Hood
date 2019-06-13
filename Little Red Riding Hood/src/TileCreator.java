import javafx.scene.paint.Color;

public class TileCreator {

    private int tileSize;

    public TileCreator(int tileSize) {
        this.tileSize = tileSize;
    }

    public Tile createGrassTile(int posI, int posJ) {
        return new Tile(posI, posJ, tileSize, Color.GREEN, Tile.TileTypes.GRASS, 1);
    }
    public Tile createForestTile(int posI, int posJ) {
        return new Tile(posI, posJ, tileSize, Color.DARKGREEN, Tile.TileTypes.FOREST, 2);
    }
    public Tile createRockTile(int posI, int posJ) {
        return new Tile(posI, posJ, tileSize, Color.GREY, Tile.TileTypes.ROCK, 0);
    }
    public Tile createWaterTile(int posI, int posJ) {
        return new Tile(posI, posJ, tileSize, Color.BLUE, Tile.TileTypes.WATER, 5);
    }
}
