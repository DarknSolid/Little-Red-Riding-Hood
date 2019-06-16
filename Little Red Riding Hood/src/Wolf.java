import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Wolf extends Entity {

    private GamePiece target;
    private Stack<Tile> path;

    public Wolf() {
        super(2, 5, "textures/wolfhead.png");
    }

    @Override
    void move(World world) {
        findTarget(world);
        if (target != null) chaseTarget(world);
        else wander(world);
    }

    private void wander(World world) {
        ArrayList<Tile> neighbours = world.getNeighbours(world.getTileAtPosition(posI, posJ));
        Random r = new Random();
        while (true) {

            Tile neighbour = neighbours.get(r.nextInt(neighbours.size()));
            if (neighbour == null) continue;

            if (move(neighbour.getPosI(), neighbour.getPosJ(), world)) break;
        }

    }

    private void chaseTarget(World world) {
        path = world.getPathFromCloseTo(posI, posJ, target.getPosI(), target.getPosJ());
        if (movesLeft >= 0 && path.size() > 0) {
            Tile tile = path.pop();
            move(tile.getPosI(), tile.getPosJ(), world);
        } else movesLeft = 0;
    }

    private void findTarget(World world) {
        for (GamePiece gamePiece : findGamepiecesInRange(world)) {
            if (gamePiece instanceof Player) {
                target = gamePiece;
            }
        }
    }

    public List<Tile> getPath() {
        return path;
    }
}
