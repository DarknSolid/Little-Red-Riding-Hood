import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane borderPane = new BorderPane();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("game.fxml"));
        borderPane.setCenter(loader.load());

        GameController gameController = loader.getController();
        gameController.init(new Model());

        Scene scene = new Scene(borderPane);

        stage.setScene(scene);
        stage.setTitle("Little Red Riding Hood");
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();
    }
}
