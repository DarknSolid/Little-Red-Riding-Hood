import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class WorldCreator {

    private TileCreator tileCreator;

    public WorldCreator(int tileSize) {
        tileCreator = new TileCreator(tileSize);
    }

    public World generateRandomWorld(int worldHeight, int worldWidth) {
        RandomWorldGenerator randomWorldGenerator = new RandomWorldGenerator(worldHeight, worldWidth, tileCreator.getTileSize());
        return randomWorldGenerator.generateWorld();
    }

    public World loadFile(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String[] dimensions = br.readLine().split(" ");
            Tile[][] world = new Tile[Integer.parseInt(dimensions[0])][Integer.parseInt(dimensions[1])];

            for (int i = 0; i < world.length; i++) {
                String sequence = br.readLine();
                for (int j = 0; j < sequence.length(); j++) {
                    world[i][j] = getTile(sequence.charAt(j), i, j);
                }
            }

            return new World(world);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Tile getTile(char c, int i, int j) {
        switch (c) {
            case 'G':
                return tileCreator.createGrassTile(i, j);
            case 'F':
                return tileCreator.createForestTile(i, j);
            case 'R':
                return tileCreator.createRockTile(i, j);
            case 'W':
                return tileCreator.createWaterTile(i, j);
        }
        return null;
    }
}
