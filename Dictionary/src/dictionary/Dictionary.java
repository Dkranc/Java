package dictionary;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
this class is the main class.we import the fxml file and load it to the scene.
 */
public class Dictionary extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root=FXMLLoader.load( getClass().getResource("Dictionary.fxml"));//getting the fxml file
        Scene scene = new Scene(root);
        primaryStage.setTitle("Dictonary");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}