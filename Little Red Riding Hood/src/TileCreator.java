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

    public Tile createTileByType(int posI, int posJ, Tile.TileTypes type) {
        switch (type) {
            case GRASS:
                return createGrassTile(posI, posJ);
            case FOREST:
                return createForestTile(posI, posJ);
            case ROCK:
                return createRockTile(posI, posJ);
            case WATER:
                return createWaterTile(posI, posJ);
        }
        System.err.println("ERROR! createTileByType was given a type that can't be created!");
        return null;
    }


    public int getTileSize() {
        return tileSize;
    }
}
