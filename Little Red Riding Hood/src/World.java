import java.util.Stack;

public class World {
    Tile[][] world;
    PathFinder pathFinder;

    public World(Tile[][] world) {
        this.world = world;
        pathFinder = new PathFinder(this);
    }

    public Stack<Tile> getPathFromTo(int iFrom, int jFrom, int iTo, int jTo) {
        return pathFinder.findPath(world[iFrom][jFrom], world[iTo][jTo], false);
    }

    public Stack<Tile> getPathFromCloseTo(int iFrom, int jFrom, int iTo, int jTo) {
        return pathFinder.findPath(world[iFrom][jFrom], world[iTo][jTo], true);
    }

    public boolean isFreeTile(int posI, int posJ) {
        try {
            Tile tile = getTileAtPosition(posI, posJ);
            return !isObstacle(tile) && tile.getGamePiece() == null;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public boolean isObstacle(Tile tile) {
        return tile.getTileType() == Tile.TileTypes.ROCK || tile.getTileType() == Tile.TileTypes.WATER;
    }

    public Tile getTileAtPosition(int posI, int posJ) {
        try {
            return world[posI][posJ];
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    public boolean spawnGamePiece(GamePiece gamePiece, int posI, int posJ) {
        if (isFreeTile(posI, posJ)) {
            getTileAtPosition(posI, posJ).setGamePiece(gamePiece);
            gamePiece.setPosition(posI, posJ);
            return true;
        }
        return false;
    }

    public Tile[][] getTiles() {
        return world;
    }
}
