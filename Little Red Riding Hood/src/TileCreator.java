import javafx.scene.image.Image;
import java.util.Random;

public class TileCreator {

    private int tileSize;
    private String grassPath, grassFlowerPath, forestPath, rockPath, waterPath;
    private Image grassTex, grassFlowerTex, forestTex, rockTex, waterTex;

    public TileCreator(int tileSize) {
        this.tileSize = tileSize;
        grassPath = "textures/grass_no_flowers.jpg";
        grassFlowerPath = "textures/grass_flowers.jpg";
        forestPath = "textures/forest1.jpg";
        rockPath = "textures/rock.jpg";
        waterPath = "textures/water1.jpg";
        grassTex = getImageFromFileLocation(grassPath);
        grassFlowerTex = getImageFromFileLocation(grassFlowerPath);
        forestTex = getImageFromFileLocation(forestPath);
        rockTex = getImageFromFileLocation(rockPath);
        waterTex = getImageFromFileLocation(waterPath);
    }

    public Tile createGrassTile(int posI, int posJ) {
        Random r = new Random();
        int n = r.nextInt(4)+1;
        Image texture;
        if (n < 4) texture = grassTex;
        else texture = grassFlowerTex;
        return new Tile(posI, posJ, tileSize, texture, Tile.TileTypes.GRASS, 1);
    }

    public Tile createForestTile(int posI, int posJ) {
        return new Tile(posI, posJ, tileSize, forestTex, Tile.TileTypes.FOREST, 2);
    }

    public Tile createRockTile(int posI, int posJ) {
        return new Tile(posI, posJ, tileSize, rockTex, Tile.TileTypes.ROCK, 0);
    }

    public Tile createWaterTile(int posI, int posJ) {
        return new Tile(posI, posJ, tileSize, waterTex, Tile.TileTypes.WATER, 5);
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

    private Image getImageFromFileLocation(String loc) {
        return new Image(getClass().getResourceAsStream(loc));
    }


    public int getTileSize() {
        return tileSize;
    }
}
