import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class GameController {

    private View view;
    private Model model;

    @FXML
    private Canvas canvas;
    @FXML
    private Pane pane;

    public void init(Model model) {
        this.view = new View(model, canvas, pane);
        this.model = model;
        canvas.setFocusTraversable(true);
    }

    @FXML
    private void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case W:
            case S:
            case A:
            case D:
                model.movePlayer(keyEvent.getCode());
                break;
            case R:
                model.reset();
        }
    }
}
