import java.util.Comparator;
import java.util.HashMap;

public class TileComparator implements Comparator<Tile> {

    private HashMap<Tile, Integer> travelCostToTile;

    public TileComparator(HashMap<Tile, Integer> travelCostToTile) {
        this.travelCostToTile = travelCostToTile;
    }

    @Override
    public int compare(Tile o1, Tile o2) {
        if (travelCostToTile.get(o1) < travelCostToTile.get(o2)) return -1;
        if (travelCostToTile.get(o1) > travelCostToTile.get(o2)) return 1;
        return 0;
    }
}