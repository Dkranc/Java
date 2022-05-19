
package calculater;

import java.lang.NumberFormatException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
/*
This is the controller class. here we have all the actionHandlers for all the buttons in the app.
most of the app is build out of buttons, we have some text fields but we dont have any event handler for them.
*/
public class Controller {
/*
    declaring all the fxml components
    */
    @FXML
    private Text screen;
    
    @FXML
    private Text minusSign;
    
    @FXML
    private Text action;

    @FXML
    private Button one;

    @FXML
    private Button two;

    @FXML
    private Button three;

    @FXML
    private Button plus;

    @FXML
    private Button four;

    @FXML
    private Button five;

    @FXML
    private Button six;

    @FXML
    private Button minus;

    @FXML
    private Button seven;

    @FXML
    private Button eight;

    @FXML
    private Button nine;

    @FXML
    private Button devide;

    @FXML
    private Button point;

    @FXML
    private Button zero;

    @FXML
    private Button equals;

    @FXML
    private Button times;

    @FXML
    private Button changeSign;
    /*
    declaring all the variables that we use in this class
    */
    private boolean pos=true;//keeps track of the +/- sign in from of the number
    private char act;//this stores the action that the user wants to do
    private Number num1;//keeps track of the first number in the calculation
    private Number num2;//keeps track of the second number in the calculation
    
     
    @FXML

    void clearBtn(ActionEvent event) {
    screen.setText(" "); //clearing the last number   
    minusSign.setText(" ");//clearing the sign before the number
    action.setText(" ");//clearing the action text box
    num1=null;//nulling the numbers so we can start fro mthe begining
    num2=null;
    enableBtns();
    }
    
    @FXML
            /*
            changes the sign of the number from + to - and the other way around
            */
    void changeBtn(ActionEvent event) {
    if (pos) {
    pos=false;
    minusSign.setText("-");
    }
    else {
    pos=true;
    minusSign.setText(" ");
    }
    }
    
    @FXML
            /*
            this method is in charge of suming the numbers and showing the answer.
            we use a try catch keywords to make sure we dont get an error from the input in the strings
            */
    void equalsBtn(ActionEvent event) {
     try{
     num2=new Number(Double.parseDouble(screen.getText()),pos); //creating the second number so we can use it in the calculation
     pos=true;//reseting the positive variable
     if (num1!=null){//if we already have a number that was enterd before
         /*
         we check what action need to be performed
         */
     switch(act){ 
         case '+': screen.setText(String.valueOf(num1.getNum()+num2.getNum()));
                   break;
         case '-': screen.setText( String.valueOf(num1.getNum()-num2.getNum()));
                   break;
         case '*': screen.setText( String.valueOf(num1.getNum()*num2.getNum()));
                   break;
         case '/': screen.setText( String.valueOf(num1.getNum()/num2.getNum()));
                   break;    
                    }//end of switch
     }//end of if
     else screen.setText(String.valueOf(num2.getNum()));//if we didnt have a number before we just show this number on the screen
     num1=null;//deleting the old numbers
     num2=null;//deleting the old numbers
     minusSign.setText(" ");//clearing the minus sign
     action.setText(" ");//clearing the action sign
     disableBtns();      }
     catch(NumberFormatException e){//if there wasnt a number in text box of the screeen we will get this exception. in this case we will ask the user to try again
       screen.setText("Error in Number, please try again");
       disableBtns();
       disableActions();//we only leave the clear button active so that way the user has to clear the screen
     }
    }
    /*
    in this next block of code we have all the action buttons. they are all builed in the same way.
    i will comment in the first one, and all the rest are the same.
    In these methods we also catch NumberFormatException if the screen has no string in it.
    */
    @FXML
    void devideBtn(ActionEvent event) {
      try{
      equals.fire();
      /*we call the equals function because we want to calculate numbers from the screen even before we get the next 
      number
      */
      enableBtns();//we have to enable the numbers because we disabled them in the equals function
      act='/';
      num1=new Number(Double.parseDouble(screen.getText()),pos);//creating the first number in the calculation
      pos=true;//resetting the positive sign
      screen.setText("");//we reset all the textfields
      minusSign.setText(" ");
      action.setText("/");
      }
     catch(NumberFormatException e){//if the text field was empty so we tell the user that there was an error
       screen.setText("Error in Number, please try again");
       disableBtns();
       disableActions();
     } 
    }

    @FXML
    void minusBtn(ActionEvent event) {
        try{
      equals.fire();      
      enableBtns();
      act='-';
      num1=new Number(Double.parseDouble(screen.getText()),pos);
      pos=true;
      screen.setText("");
      minusSign.setText(" ");
      action.setText("-");
        }
      catch(NumberFormatException e){
       screen.setText("Error in Number, please try again");
       disableBtns();
       disableActions();
     }  
    }
    
     @FXML
    void plusBtn(ActionEvent event) {
        try{
      equals.fire();
      enableBtns();
      act='+';
      num1=new Number(Double.parseDouble(screen.getText()),pos);
      pos=true;
      screen.setText("");
      minusSign.setText(" ");
      action.setText("+");
        }
      catch(NumberFormatException e){
       screen.setText("Error in Number, please try again");
       disableBtns();
       disableActions();
     }  
    }
    
     @FXML
    void timesBtn(ActionEvent event) {
        try{
       equals.fire();     
      enableBtns();
      act='*';      
      num1=new Number(Double.parseDouble(screen.getText()),pos);
      pos=true;
      screen.setText("");
      minusSign.setText(" ");
      action.setText("*");
      
    }
      catch(NumberFormatException e){
       screen.setText("Error in Number, please try again");
       disableBtns();
       disableActions();
     }  
    }
    /*
    these are the number buttons .all are the same. we just set the text pf the screen to the same ,plus the button
    that was pressed
    */
     @FXML
    void zeroBtn(ActionEvent event) {
    screen.setText( screen.getText()+"0");
    }
    
    @FXML
    void oneBtn(ActionEvent event) {
    screen.setText( screen.getText()+"1");
    }
    
     @FXML
    void twoBtn(ActionEvent event) {
    screen.setText( screen.getText()+"2");
    }
    
     @FXML
    void threeBtn(ActionEvent event) {
    screen.setText( screen.getText()+"3");
    }
    
     @FXML
    void fourBtn(ActionEvent event) {
    screen.setText( screen.getText()+"4");
    }
    
     @FXML
    void fiveBtn(ActionEvent event) {
    screen.setText( screen.getText()+"5");
    }
    
     @FXML
    void sixBtn(ActionEvent event) {
    screen.setText( screen.getText()+"6");
    }
    
    @FXML
    void sevenBtn(ActionEvent event) {
    screen.setText( screen.getText()+"7");
    }
    
    @FXML
    void eightBtn(ActionEvent event) {
    screen.setText( screen.getText()+"8");
    }

    @FXML
    void nineBtn(ActionEvent event) {
    screen.setText( screen.getText()+"9");
    }

     @FXML
    void pointBtn(ActionEvent event) {
    screen.setText( screen.getText()+".");
    }
    /*
    in this method we disable all the number methods
    */
     private void disableBtns(){
     one.setDisable(true);
     two.setDisable(true);
     three.setDisable(true);
     four.setDisable(true);
     five.setDisable(true);
     six.setDisable(true);
     seven.setDisable(true);
     eight.setDisable(true);
     nine.setDisable(true);
     zero.setDisable(true);
     point.setDisable(true);  
    }
     
     /*
     we enable all the buttons including the action buttons
     */
    private void enableBtns(){
     one.setDisable(false);
     two.setDisable(false);
     three.setDisable(false);
     four.setDisable(false);
     five.setDisable(false);
     six.setDisable(false);
     seven.setDisable(false);
     eight.setDisable(false);
     nine.setDisable(false);
     zero.setDisable(false);
     point.setDisable(false);
     plus.setDisable(false);
     minus.setDisable(false);
     times.setDisable(false);
     devide.setDisable(false);
     changeSign.setDisable(false);
     equals.setDisable(false);
    }
    /*
    in this method we disable the action buttons 
    */
    private void disableActions(){
     plus.setDisable(true);
     minus.setDisable(true);
     times.setDisable(true);
     devide.setDisable(true);
     changeSign.setDisable(true);
     equals.setDisable(true);
    }
}

