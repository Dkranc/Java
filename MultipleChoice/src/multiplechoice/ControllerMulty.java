package multiplechoice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import java.security.SecureRandom;
import javafx.application.Platform;
import javafx.scene.Node;

 /*
This is the Controller class for our test application.
we have all the EventListeners and handlers set up in this class
*/
public abstract class ControllerMulty {
/*
    initializing all the fxml components we have i the App
    */
    @FXML
    private Label question;
    
     @FXML
     private ToggleGroup Answers;

    @FXML
    private RadioButton ans1;

    @FXML
    private RadioButton ans2;

    @FXML
    private RadioButton ans3;

    @FXML
    private RadioButton ans4;

    @FXML
    private Button restartBtn;

    @FXML
    private Button nextBtn;

    @FXML
    private Label yesOrNo;
    
    /*
    initializing the java variables we use in the app
    */
    private int correct;
    private Scanner input;
    private RadioButton [] arr=new RadioButton[4];
    private double score;
    private double questionCnt;
    
    /*
    the initialize method is in charge of setting all the defualt setting on the first screen. we use this method to display the 
    first question and set the data of all the diferent components
    */
    public void initialize() throws FileNotFoundException{
         FileInputStream file =getfile();   //getting the file from the directory
       if(file !=null){
        yesOrNo.setText("");//clearing the text from the yesOrNo label
        nextBtn.setVisible(false);//hiding the next and restart button button
        restartBtn.setVisible(false);
        /*
        we put each radioButton in the array so there easy to reach
        */
       arr[0] =ans1; 
       arr[1] =ans2; 
       arr[2] =ans3; 
       arr[3] =ans4; 
       
      
        input = new Scanner(file);//assigning the Scanner we have with the file we got
        /*
        getting a random number form 0 to 3 indicating the right number
        */
        SecureRandom rand= new SecureRandom();
        correct=rand.nextInt(4);
        
        question.setText(input.nextLine());//putting the first line from the file into the question label
        arr[correct].setText(input.nextLine());//putting the correct answer in the array in the random spot we gor from the random number
        arr[correct].setUserData(correct);//setting the data of the correct answer
        for(int i=0; i<4; i++){
        if(i!=correct) {//as long as were not at the correct answer cell we set the data and set the text to the the answer number and next line coresponditly
            arr[i].setUserData(i);
            arr[i].setText(input.nextLine());
        }//end of if
          }//end of for
       }//end of big if
       else Platform.exit();//terminate app if the file wasnt found
    }//end of method

      @FXML
            /*
            this method is used when an answer is chosen.
            we check first if the answer is correct and then print a message telling the user if he was right or wrong
            */
    void answerSelected(ActionEvent event) {
        questionCnt++;
         nextBtn.setVisible(true);//we show the next button
          String ansStr=Answers.getSelectedToggle().getUserData().toString();//we get the data from the button we chose 
          int ans=Integer.valueOf(ansStr);//converting the number to an int
          if (ans==correct) {
              yesOrNo.setText("correct!");
          score++;
          }
          else yesOrNo.setText("incorrect!");
          /*
          we want to disable the buttons so that the user wont be able to change his answer.
          we us e a inline method and set the diable property of each RadioButton to true.
          */
         Answers.getToggles().forEach(toggle -> {
         Node node = (Node) toggle ;
         node.setDisable(true);
         });
    }

    @FXML
        /*
            we use this method when the net button is clicked. this meand the user chose an answer and he wants to get the next question. 
            if there are no more questions the score will show up
            */
    void nextLClicked(ActionEvent event) {
         yesOrNo.setText("");//clearing the text form the answer label
         /*
         this time we want to let the user to make a disition so we enable the RadioButtons 
         */
         Answers.getToggles().forEach(toggle -> {
         Node node = (Node) toggle ;
         node.setDisable(false);
         });
        SecureRandom rand= new SecureRandom();//getting a random number for the correct answer
        correct=rand.nextInt(4);
        
         Answers.getSelectedToggle().setSelected(false);//clearing the previous answer from the screen
        if(input.hasNextLine()){ //if we have another line in the text
        question.setText(input.nextLine());//putting the question and the correct answer their place
        arr[correct].setText(input.nextLine());
        arr[correct].setUserData(correct);//setting the data to the right int
        for(int i=0; i<4; i++){//same as initialize method. we put the text ing the text property and the number of the answer in the data property
         if(i!=correct) {
            arr[i].setUserData(i);
            arr[i].setText(input.nextLine());
         }//small if
        }//for
        }//big if
        else {//meaning we dont have any more questions
            nextBtn.setVisible(false);//hide the next button
            yesOrNo.setText("Your score is: "+ (score/questionCnt)*100 +"%\nClick RESTART to redo");//setting the score string to be displayed on screen
        Answers.getToggles().forEach(toggle -> {//now we disable the buttons because we are done 
         Node node = (Node) toggle ;
         node.setDisable(true);
         });
        restartBtn.setVisible(true);//we show the restart button becaue we want the user to be able to take the text again
        }
    }

    @FXML
            /*
            this method is callled when the restart button is clicked.
            we use this to restart the test by using the same commands we used in initialize method
            */
    void restartCLicked(ActionEvent event) throws FileNotFoundException  {
         FileInputStream file =getfile(); //getting the same file as before 
        if(file !=null){
        //we use the exact same code as initialize to restart the test.but we cear the old score
         yesOrNo.setText("");
         score=0;
         questionCnt=0;
        Answers.getToggles().forEach(toggle -> {//enabling the radio buttons
         Node node = (Node) toggle ;
         node.setDisable(false);
         }); 
      nextBtn.setVisible(false);//hididng the next button
      /*
      putting the buttons in an array so we can reach them with ease
      */
      
       arr[0] =ans1; 
       arr[1] =ans2; 
       arr[2] =ans3; 
       arr[3] =ans4; 
       restartBtn.setVisible(false);
      
        input = new Scanner(file);//oasigning the file to the scanner
        //creating a random number
        SecureRandom rand= new SecureRandom();
        correct=rand.nextInt(4);
        /*
        like in initialize we set the text of each component to the his line form the test and we set the data of the buttons to match there placing
        */
        question.setText(input.nextLine());
        arr[correct].setText(input.nextLine());
        arr[correct].setUserData(correct);
        for(int i=0; i<4; i++){
        if(i!=correct) {
            arr[i].setUserData(i);
            arr[i].setText(input.nextLine());
        }
          }
        }//end of if   
         else Platform.exit();//terminate app if the file wasnt found
    }//end of method
    /*
    the get file return a FileInputStream object that is used for the questions. we throw an exception if the file wasnt found
    */
 private FileInputStream getfile() throws FileNotFoundException {
    
        try{
        FileInputStream file=new FileInputStream("test.txt");

        return file;
    }
        catch (FileNotFoundException e) {
            System.out.println("file not found");
             return null;
        }
    }
}
