
package weatherapp;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/*
this class is the client side app for the weather app. we import the fxml file to have  GUI. all logic is in the controller class.
*/
public class WeatherApp extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root=FXMLLoader.load( getClass().getResource("weather.fxml"));//getting the file from directory
        Scene scene = new Scene(root);//setting the scene we will use
        primaryStage.setTitle("Weather App");
        primaryStage.setScene(scene);//setting the stages scene to the scene we created
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
