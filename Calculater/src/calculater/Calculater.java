
package calculater;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
This class is the main class of the application. the application is a simple calculator with the four basic math arithmetics.
we use the javafx framework for this app, and aditionaly the app was builed with sceneBuilder.
as you can see in this app we cann in the calc.fxml file as a root and then load the root into out scene.
*/
public class Calculater extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       Parent root=FXMLLoader.load( getClass().getResource("Calc.fxml"));//getting the fxml file
        Scene scene = new Scene(root);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
