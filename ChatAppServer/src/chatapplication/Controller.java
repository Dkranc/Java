package chatapplication;


import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
the Controller class is the client class. this controller allows us to read text from the client and to
* display messages on the clients screen
 */
public class Controller{ 
     
    @FXML
    private Button sendBtn;
    @FXML
    private TextField messageTxt;
    @FXML
    private Button connect;
    @FXML
    private TextField hostNameTxt;
    @FXML
    private TextField portTxt;
    @FXML
    private Button quit;
    @FXML
    private Text log;
    
    private boolean canSend=false;
    private boolean userConnected=true;
   
    
   @FXML
   public void initialize(){
   sendBtn.setVisible(false);//hiding the send button untill the client is connected
   messageTxt.setVisible(false);//hiding the text input field untill the client is connected
   }

   /*
   connectClicked gets the info about the host name and the port and creats a socket that is connected to the sever.
   then we create and start read and write threads which are in charge of reading and writing messages to and from the server
   */
    @FXML
     void connectClicked(ActionEvent event) {
        String hostName;
        int port;
        //if we hace data in both fields
        if(hostNameTxt.getText()!=null&&hostNameTxt.getText().length()>0&&portTxt.getText()!=null&&portTxt.getText().length()>0){
            try{
            //get the data from text fileds
            hostName=hostNameTxt.getText();
            port=Integer.parseInt(portTxt.getText());
            
            //hide the connection fileds and button
            hostNameTxt.setVisible(false);
            portTxt.setVisible(false);
            connect.setVisible(false);
            
            //show the send btn and the text field
            sendBtn.setVisible(true);
            messageTxt.setVisible(true);
            
            //create a new socket with the port and server name  we got
            Socket socket = new Socket(hostName, port);
 
            //starting the read and write threads
             new ReadThread(socket,this).start(); 
             new WriteThread(socket,this).start();
             System.out.println("Connected to the chat server");
        
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O Error: " + ex.getMessage());
        } catch (NumberFormatException e){
                log.setText("you must input a number in to port");
             }
        }
        else{//if non valid text was received
            log.setText("you must input host name and port number");
        
        }
 
    }

     
     /*
     this method closes the wondow and diconnectes the user
     */
    @FXML
    void quitClicked(ActionEvent event) {
      userConnected=false;
      
      Stage stage=(Stage) quit.getScene().getWindow();
      stage.close();
    }

    /*
    this messege changes the cansend field so the writer thread knows if he can send the message
    */
    @FXML
    void sendClicked(ActionEvent event){
        if(messageTxt.getText()!=null&&messageTxt.getText().length()>0) canSend=true;
    }

    /*
    addText adds text to the GUI. it shows the messge recived from the server
    */
    public void addText(String text) {
        log.setText(log.getText()+text+"\n");
    }

    /*
    readLine reads a line from the text field that the user inputed and sends it to the writer thread
    */
    public String readLine() {
     String msg=null;
     if(canSend){//if we can send the message
         msg=messageTxt.getText();  

           addText("you: "+msg);//adding the text to the filesd with all messages

               messageTxt.setText("");//clearing the message
    
     }
      canSend=false;//setting the send booleand to false so we know we have nothing to send.
      return msg;
    }
  /*
    returns wether the user is connected or not
    */
    boolean userConnected() {
        return userConnected;
    }

}
