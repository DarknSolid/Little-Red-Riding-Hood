import javafx.concurrent.Task;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class Model {

    private World world;
    private Runnable observer;
    private ArrayList<Entity> entities;
    private Player player;
    private boolean shouldRunGame;
    private Thread gameLoopThread;

    public Model() {
        newGame();
    }

    private void newGame() {
        entities = new ArrayList<>();
        createWorld();
//        populateWorld();
        runGame();
    }

    private void createWorld() {
        WorldCreator worldCreator = new WorldCreator(50);
        world = worldCreator.createWorldFromFile("maps/map1.txt");
        
    }

    private void populateWorld() {
        player = new Player(Color.RED, 2);
        world.spawnGamePiece(player, 0, 0);
        Wolf wolf1 = new Wolf(player);
        Wolf wolf2 = new Wolf(player);
        world.spawnGamePiece(wolf1, 9, 9);
        world.spawnGamePiece(wolf2, 9, 8);
        entities.add(wolf1);
        entities.add(wolf2);
    }

    private void runGame() {
        shouldRunGame = true;
        if (gameLoopThread != null && gameLoopThread.isAlive()) return;

        gameLoopThread = new Thread(new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (shouldRunGame) {
                    nextRound();
                }
                return null;
            }
        });
        gameLoopThread.setDaemon(true);
        gameLoopThread.start();
    }

    public void nextRound() {
        try {
            int timePrRound = 1000; //in ms
            int timeUsagePrEntity = timePrRound / entities.size(); // in ms.

            for (Entity entity : entities) {

                int timeBetweenSteps = timeUsagePrEntity / entity.moves;
                long start = System.currentTimeMillis();
                while (!entity.hasFinishedTurn(world)) {
                    Thread.sleep(timeBetweenSteps);
                    player.regenMoves(timePrRound);
                    moveEntity(entity);
                    runObserver();
                }
                long end = System.currentTimeMillis();
                if (end - start < timeUsagePrEntity) Thread.sleep(end - start);
                entity.resetMovesLeft();
            }
        } catch (InterruptedException e) {
            System.out.println("Thread id " + Thread.currentThread().getId() + " was interrupted");
        }
    }

    private void moveEntity(Entity entity) {
        entity.move(world);
        runObserver();
    }

    public void movePlayer(KeyCode keyCode) {
        player.moveInDirection(keyCode, world);
        runObserver();
    }

    public void setObserver(Runnable observer) {
        this.observer = observer;
    }

    private void runObserver() {
        observer.run();
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public Tile[][] getTiles() {
        return world.getTiles();
    }

    public void reset() {
        shouldRunGame = false;
        gameLoopThread.interrupt();
        newGame();
        runObserver();
    }
}
