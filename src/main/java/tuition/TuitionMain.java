package tuition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Main class to set the primary stage and scene for the application.
 *
 * @author Harsh Kumawat, Wayne Huang
 */
public class TuitionMain extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(TuitionMain.class.getResource("tuition-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 700);
        stage.setTitle("Tuition Manager");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Launch method.
     *
     * @param args arguments.
     */
    public static void main(String[] args) {
        launch();
    }
}