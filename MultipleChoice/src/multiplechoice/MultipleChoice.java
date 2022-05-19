
package multiplechoice;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *This Application is a multiple Choice test. we have 4 answers and only one is correct.
 * the user must choose an answer for each question. 
 * at the end of the application the user will get a message that shows his score
 */


/*
the class MultipleChoice is the main class of this app and we use it to rewrite the start method
*/
public class MultipleChoice extends Application {
    /*
    the start method gets the fxml file that we create in SceneBuilder and uses it to create our scene that is put onto the stage.
    */
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root=FXMLLoader.load( getClass().getResource("multy.fxml"));//getting the file from directory
        Scene scene = new Scene(root);//setting the scene we will use
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);//setting the stages scene to the scene we created
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

   
    
}
