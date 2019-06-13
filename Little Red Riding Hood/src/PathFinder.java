import java.util.*;

public class PathFinder {
    private World world;
    private PriorityQueue<Tile> pq;
    private HashMap<Tile, Integer> travelCostToTile;
    private HashMap<Tile, Tile> pathToTile;
    private HashSet<Tile> isMarked;

    private boolean shouldFindPathCloseToDestination;
    private Tile destination;

    public PathFinder(World world) {
        this.world = world;
        prepareNextSearch();
    }

    private void prepareNextSearch() {
        travelCostToTile = new HashMap<>();
        pathToTile = new HashMap<>();
        isMarked = new HashSet<>();
        pq = new PriorityQueue<>(new TileComparator(travelCostToTile));
    }

    public Stack<Tile> findPath(Tile start, Tile destination, boolean shouldFindPathCloseToDestination) {
        this.shouldFindPathCloseToDestination = shouldFindPathCloseToDestination;
        this.destination = destination;

        pq.add(start);
        travelCostToTile.put(start, 0);

        while (!pq.isEmpty()) {
            Tile current = pq.remove();
            if (current == destination) {
                break;
            }
            isMarked.add(current);
            visitNeighbours(current);
        }
        Stack<Tile> path = buildPath();
        prepareNextSearch();
        return path;
    }

    private Stack<Tile> buildPath() {
        Stack<Tile> path = new Stack<>();
        Tile current;

        if (shouldFindPathCloseToDestination) {
            current = pathToTile.get(destination);
        } else current = destination;

        while (pathToTile.containsKey(current)) {
            path.add(current);
            current = pathToTile.get(current);
        }

        return path;
    }

    private void visitNeighbours(Tile current) {
        for (Tile neighbour : getNeighbours(current)) {
            if (isMarked.contains(neighbour) || neighbour == null) continue;

            if (travelCostToTile.containsKey(neighbour)) {
                if (travelCostToTile.get(neighbour) <= neighbour.getCost() + travelCostToTile.get(current)) {
                    continue;
                }
            }
            updateTile(neighbour, current);
        }

    }

    private void updateTile(Tile tile, Tile previous) {
        pathToTile.put(tile, previous);
        travelCostToTile.put(tile, tile.getCost() + travelCostToTile.get(previous));
        pq.add(tile);
    }

    private ArrayList<Tile> getNeighbours(Tile current) {
        ArrayList<Tile> neighbours = new ArrayList<>();
        int posI = current.getPosI();
        int posJ = current.getPosJ();
        neighbours.add(getNeighbour(posI - 1, posJ));
        neighbours.add(getNeighbour(posI + 1, posJ));
        neighbours.add(getNeighbour(posI, posJ - 1));
        neighbours.add(getNeighbour(posI, posJ + 1));
        return neighbours;
    }

    private Tile getNeighbour(int i, int j) {
        Tile neighbour = world.getTileAtPosition(i, j);
        if (neighbour == null) return null;
        if (shouldFindPathCloseToDestination) {
            if (neighbour == destination) {
                if (!world.isObstacle(neighbour)) return neighbour;
            } else if (world.isFreeTile(i, j)) return neighbour;

        } else if (world.isFreeTile(i, j)) return neighbour;
        return null;
    }
}
