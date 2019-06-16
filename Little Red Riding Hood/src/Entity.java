import java.util.*;

abstract class Entity extends GamePiece {

    protected int viewRange;

    public Entity(int moves, int viewRange, String pathToTexture) {
        super(moves, pathToTexture);
        this.viewRange = viewRange;
    }

    abstract void move(World world);

    protected List<GamePiece> findGamepiecesInRange(World world) {
        List<GamePiece> gamePieces = new ArrayList<>();

        LinkedList<Tile> neighbours = new LinkedList<>();
        HashSet<Tile> markedNeighbours = new HashSet<>();

        neighbours.add(world.getTileAtPosition(posI,posJ));
        markedNeighbours.add(world.getTileAtPosition(posI,posJ));

        int previousListSize;
        for (int i = 0; i < viewRange; i++) { // search range iterations

            previousListSize = neighbours.size();

            for (int j = 0; j < previousListSize; j++) {

                for (Tile tile : world.getNeighbours(neighbours.removeFirst())) {
                    if (markedNeighbours.contains(tile)) continue;
                    if (tile == null) continue;
                    if (tile.getGamePiece() != null) {
                        gamePieces.add(tile.getGamePiece());
                    }
                    neighbours.add(tile);
                    markedNeighbours.add(tile);
                }
            }
        }
        return gamePieces;
    }
}
