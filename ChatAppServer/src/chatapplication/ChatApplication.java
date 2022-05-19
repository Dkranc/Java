
package chatapplication;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
the chat appliction class starts a new GUI from the fxml file. all logic is in the other classes. 
this is a basic javafx app.
*/
public class ChatApplication extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("interface.fxml"));   //loading the fxml file    
        Scene scene = new Scene(root);  //loading the root into the scene    
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
