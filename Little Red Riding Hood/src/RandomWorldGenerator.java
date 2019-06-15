import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomWorldGenerator {
    private Tile[][] world;
    private Tile[][] previousWorldState;
    private TileCreator tileCreator;
    private Map<Tile.TileTypes, TileGenerationStats> statsOfTileType;


    public RandomWorldGenerator(int worldHeight, int worldWidth, int tileSize) {
        this.world = new Tile[worldHeight][worldWidth];
        this.previousWorldState = new Tile[worldHeight][worldWidth];
        this.statsOfTileType = new HashMap<>();
        this.tileCreator = new TileCreator(tileSize);
        statsOfTileType.put(Tile.TileTypes.FOREST, new TileGenerationStats(55, 4, 5, 3));
        statsOfTileType.put(Tile.TileTypes.WATER, new TileGenerationStats(25, 3, 4, 2));
        statsOfTileType.put(Tile.TileTypes.ROCK, new TileGenerationStats(5, 1, 2, 1));

        fillWorldWithGrass();
    }

    public World generateWorld() {
        for (Tile.TileTypes type : statsOfTileType.keySet()) {
            int iterations = statsOfTileType.get(type).getIterations();
            fillRandomTiles(type);
            for (int i = 0; i < iterations; i++) {
                boolean isLastIteration = (i == iterations - 1);
                iterateWorld(type, isLastIteration);
            }
        }
        return new World(world);
    }

    private void iterateWorld(Tile.TileTypes type, boolean isLastIteration) {
        TileGenerationStats stats = statsOfTileType.get(type);

        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {

                Tile.TileTypes curTileType = world[i][j].getTileType();
                int neighbours = matchingNeighbours(type, i, j);

                if (curTileType == type) {
                    if (neighbours < stats.getSurviveConditionAmount()) {
                        world[i][j] = previousWorldState[i][j];
                    }
                } else if (neighbours >= stats.getSpawnConditionAmount()) {
                    world[i][j] = tileCreator.createTileByType(i, j, type);
                }

                if (isLastIteration) {
                    previousWorldState[i][j] = world[i][j];
                }
            }
        }
    }


    private int matchingNeighbours(Tile.TileTypes type, int posI, int posJ) {
        int neighbours = 0;

        for (int i = posI - 1; i <= posI + 1; i++) {

            for (int j = posJ - 1; j <= posJ + 1; j++) {
                try {
                    if (world[i][j].getTileType() == type) neighbours++;
                } catch (IndexOutOfBoundsException e) {
                }
            }
        }
        neighbours--; //subtract the tile looking from
        return neighbours;
    }

    private void fillRandomTiles(Tile.TileTypes type) {
        for (int i = 0; i < world.length; i++) {

            for (int j = 0; j < world[i].length; j++) {
                Tile randomTile = randomTile(i, j, type, statsOfTileType.get(type).getSpawnChance());
                if (randomTile == null) continue;
                world[i][j] = randomTile;
            }
        }
    }

    private Tile randomTile(int posI, int posJ, Tile.TileTypes type, int chance) {
        Random random = new Random();
        int i = random.nextInt(100) + 1;
        if (isBetween(1, chance, i)) return tileCreator.createTileByType(posI, posJ, type);
        else if (type == Tile.TileTypes.FOREST) return tileCreator.createGrassTile(posI, posJ);
        return null;
    }

    private boolean isBetween(int min, int max, int number) {
        return (number >= min && number <= max);
    }

    private void fillWorldWithGrass() {
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                world[i][j] = tileCreator.createGrassTile(i, j);
                previousWorldState[i][j] = world[i][j];
            }
        }
    }
}
