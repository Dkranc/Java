
package dictionary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/*
the controler class is in chrage of the functionality of the GUI created by the fxml file.
in the controller class we have all of the FXML elemnets and all of the functions that connect to the buttons and text fields
when we want to change the actual dictionary and not just the GUI we use the Words class and its methods
 */
public class Controller {

   // declaring all the fxml properties
    @FXML
    private Text errorTextBox;
    @FXML
    private ToggleGroup main;
    
    @FXML
    private Button searchBtn;
    
    @FXML
    private Button clearSearchBtn;
    
    @FXML
    private TextField importText;

    @FXML
    private Button importBtn;
    
     @FXML
    private Button saveBtn;

    @FXML
    private TextField serachTextBox;

    @FXML
    private Button addBtn;

    @FXML
    private TextField addWordText;

    @FXML
    private TextField addDefText;

    @FXML
    private Button updateBtn;

    @FXML
    private TextField updateDefinitionText;

    @FXML
    private Button deleteBtn;

    @FXML
    private ScrollPane scrollMenu;
    
    private String importFile="";
    
    private Words words;//we use this to connects the dictionary to the GUI
    
 
    // the initialize method is calles in the building of the app so we use it to start some important values
    @FXML
    public void initialize(){
    main=new ToggleGroup();//we have to have a group that contains all the radio buttons we add
    words=new Words();//we have to have an objects of the Word class to use the methods from there so we can deal with requests from the user
    }
    
    
    // the clear search function clears the search from the screen and shows the whole Dictionary again.
    @FXML
    void clearSearchClicked(ActionEvent event) {
      serachTextBox.setText("");//cleraing the old search
      errorTextBox.setText("");//clearing the old error 
      String[] wordArray=words.getWords();//getting all the words
      String[] definitions=words.getDefinition();//getting all the definitions
      VBox root= new VBox();
      for(int i=0;i<wordArray.length;i++){  //we use the for loop to add all of the contacts to the root 
       RadioButton rb = new RadioButton(wordArray[i]+": "+definitions[i]);
       rb.setToggleGroup(main);           
       root.getChildren().add(rb); 
      }
     scrollMenu.setContent(root);//we add the root with all of the buttons back into the scroll menu
     scrollMenu.setPannable(true);
    }
 
    //the addClicked function is called when we click on the add button.
    //we get the text from the input fields and then send it to the words add function to add to the dictionary.
    //we then show all the words in a sorted list with radio buttons
    @FXML
    void addClicked(ActionEvent event) {
     errorTextBox.setText("");//clearing the error box from previous error
     String name=addWordText.getText();//grabing the text from the input filelds
     String number=addDefText.getText();
     addWordText.setText("");//clearing the input fields
     addDefText.setText("");
     boolean added=words.add(name,number);//we add the words but save the value of the returnd value as well to see if it was added or not
     if(added){//if the word was added succesfullly
          String []wordArray=words.getWords();//getting all the names
          String []definitions=words.getDefinition();//getting all the numbers
          VBox root= new VBox();
          for(int i=0;i<wordArray.length;i++){ //we use the for loop to add all of the words to the root  
           RadioButton rb = new RadioButton(wordArray[i]+": "+definitions[i]);
           rb.setToggleGroup(main);           
           root.getChildren().add(rb); 
           }
     scrollMenu.setContent(root);//we add the root with all of the buttons back into the scroll menu
     scrollMenu.setPannable(true); // it means that the user should be able to pan the viewport by using the mouse.
     }
     else {//if the woed wasnt added 
      errorTextBox.setText("you must input valid values");
     }
    }

    //the delete method delets a word and its definition if one was selected. if not it will show an error
    @FXML
    void deleteClicked(ActionEvent event) {
         errorTextBox.setText("");//clear error filed
         RadioButton selectedRadioButton = (RadioButton) main.getSelectedToggle();//we need to get the selected button so we know which word to delete
         if(selectedRadioButton!=null){//if a button was selected
          String keyToDelete = selectedRadioButton.getText();//we get the text from the button that was selected    
          this.words.delete(keyToDelete);//we call the delete function from the words class
         String []wordArray=words.getWords();//getting all the words
         String []definitions=words.getDefinition();//getting all the definitions
         VBox root= new VBox();
          for(int i=0;i<wordArray.length;i++){  //we use the for loop to add all of the words to the root 
           RadioButton rb = new RadioButton(wordArray[i]+": "+definitions[i]);
           rb.setToggleGroup(main);           
           root.getChildren().add(rb); 
           }
           scrollMenu.setContent(root);//we add the root with all of the buttons back into the scroll menu
           scrollMenu.setPannable(true); // it means that the user should be able to pan the viewport by using the mouse.
        }
         else {//if no one was selected
         errorTextBox.setText("no word seleted. please select one before deleting");
         }
    }
    
    
    //the importClicked method imports a file after the user gives the name of the file.
    //if no file name has been inputed we show an error message
    @FXML
    void importClicked(ActionEvent event) throws FileNotFoundException{
        errorTextBox.setText("");//clear the old error
        FileInputStream file;//we create a new file input stream
        if(importText.getText().length()!=0){//if the user gave a files name
         try{ 
             file=new FileInputStream(importText.getText());//we try and load the file that user gave us. if the file wasnt found then we throw an error  
         }catch(FileNotFoundException e){//if the file wasnt found
           errorTextBox.setText("you must input a valid file name");// we show an error to the user
           file=null;
         }
         if (file!=null&&!this.importText.getText().equals(importFile)){//if the file exists and the file wasnt allready imported before. we dont want to have duplicates
          importFile=this.importText.getText();//we update the import file so we know what file was last imported
          Scanner input=new Scanner(file); //we create a new scanner to read the text from the file
          this.words.getDictionary(input);//we call the getDictionary function from the words class. more explenation in other class
          String []wordArray=words.getWords();//after we added all of the new words to the data strucrture we get all the words
          String []definitions=words.getDefinition();//we get all the definitions
          VBox root= new VBox();
          for(int i=0;i<wordArray.length;i++){ //the loop is for adding all the words to the root  
           RadioButton rb = new RadioButton(wordArray[i]+": "+definitions[i]);
           rb.setToggleGroup(main);           
           root.getChildren().add(rb); //adding the new button to the root
           }
           scrollMenu.setContent(root);//addint the new root in to the scroll menu
           scrollMenu.setPannable(true); // it means that the user should be able to pan the viewport by using the mouse.
          }
        }
       else {//if the input text fields was empty
        errorTextBox.setText("you must input a file name");
       }
    }
    
    //this method saves the current stage of the dictionary to a file. the user must first provide a name of a file that exist and only then save to it.
    //the file can be empty but needs to excist. 
    @FXML
    void saveClicked(ActionEvent event) {
        errorTextBox.setText("");//clear old error box
          if (importFile.length()!=0)//if the import file has beed used already
         try{      
         FileWriter fw=new FileWriter(this.importFile);//we create a file writer object that writes to the same file that we imported. it writes over the old file
         String []wordArray=words.getWords();
          String []definitions=words.getDefinition();
          for(int i=0;i<wordArray.length;i++){ 
           fw.write(wordArray[i]+" "+definitions[i]+'\n');//we add the words in a way that is easy to use afterwords
          }
          fw.close();//closing the filewriter
      } catch(IOException e){//if the file wasnt found 
          errorTextBox.setText("file not found");//we show an error message
         }
        
        else {//if the import file hasnt been changed , meaning no file has been importd yet
          errorTextBox.setText("no file to save to, import first");
        }
    }

    //method finds the words with the key that the user gives and shows only those words on the screen.
    // if no word is found nothing changes
    @FXML
    void searchClicked(ActionEvent event) {
        errorTextBox.setText("");//clearing the old text error
        String key=serachTextBox.getText();
        String def=words.containsKey(key);
        if (def!=null){//if the key exist in the Dictionary
          VBox root=new VBox();
          RadioButton rb=new RadioButton(key+": "+def);//creating a new button with the word
          rb.setToggleGroup(main);
          root.getChildren().add(rb);//adding the word to the root
          scrollMenu.setContent(root);//showing only the words that were found
          scrollMenu.setPannable(true); 
        }
        else {//if the word was not found
        errorTextBox.setText("no such word in dictionary"); 
        }
    }
    
    //this method updates a words definition but only if a word was selected. if not it shows an error
    @FXML
    void updateClicked(ActionEvent event) {
        errorTextBox.setText(""); //clearing old error
        int j=0;
        char ch=' ';
        String updatedDefinition=updateDefinitionText.getText();
        boolean isDef=true;
        for(int i=0;i<updatedDefinition.length();i++){//we check if the input definition is valid- if all characters are letters
            ch=updatedDefinition.charAt(i);
            if(!Character.isLetter(ch) && ch != ' ')  isDef=false;
        }//end of for loop
        
        if(isDef){//if we have a valid definition
          RadioButton selectedRadioButton = (RadioButton) main.getSelectedToggle();// we get the selected radio button
          if(selectedRadioButton!=null){//if a button was selected
           String definitionToUpdate = selectedRadioButton.getText(); //we get the text from the button
           while(definitionToUpdate.charAt(j)!=':') j++;//we want to reach the end of the key name
           selectedRadioButton.setText(definitionToUpdate.substring(0, j+2)+updatedDefinition);//we update the text to be the key and add the new definition after the space
           words.add(definitionToUpdate.substring(0,j), updatedDefinition);//we add the word to the map. this value will replace the older one
          }
          else {//if no button was selected
          errorTextBox.setText("no word was seleted. please select one before updating");
          }
        }
        else{//if the input wasnt  a definition
             errorTextBox.setText("not a definition. can not update"); 
        }
        updateDefinitionText.setText("");//cleraing the new definition
        main.selectToggle(null);//clear selection of radio button
    }
    
}


