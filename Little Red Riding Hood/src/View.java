import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.transform.Affine;

public class View {

    Model model;
    Canvas canvas;
    GraphicsContext gc;
    Affine transform;

    public View(Model model, Canvas canvas, Pane pane) {
        this.model = model;
        this.canvas = canvas;
        gc = canvas.getGraphicsContext2D();
        transform = new Affine();
        gc.setTransform(transform);

        pane.widthProperty().addListener((obj, oldvalue, newvalue) -> {
            canvas.setWidth(newvalue.doubleValue());
            repaint();
        });
        pane.heightProperty().addListener((obj, oldvalue, newvalue) -> {
            canvas.setHeight(newvalue.doubleValue());
            repaint();
        });
        model.setObserver(this::repaint);
        repaint();
    }

    public void repaint() {
        Platform.runLater (() -> {
            gc.setTransform(new Affine());
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gc.setTransform(transform);

            Tile[][] map = model.getTiles();
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    map[i][j].draw(gc);
                }
            }
            drawPath();
        });
    }

    public void drawPath() {
        try {


            Wolf wolf = (Wolf) model.getEntities().get(0);

            for (Tile tile : wolf.getPath()) {
                tile.drawDot(gc);
            }
        }catch (NullPointerException e) {

        }
    }

    public void pan(double dx, double dy) {
        transform.prependTranslation(dx, dy);
        repaint();
    }

    public void zoom(double deltaY, double x, double y) {
        double factor = Math.pow(1.001, deltaY);
        transform.prependScale(factor, factor, x, y);
        repaint();
    }
}
