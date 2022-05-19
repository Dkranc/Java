
package weatherapp;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

/*
the Controller class is the client side of the app.
 we use the buttons to send and recieve data from server.
*/
public class Controller {
    private InetAddress IPAddress;
    
    @FXML
    private RadioButton jerusalemRB;

    @FXML
    private ToggleGroup Cities;

    @FXML
    private RadioButton washingtonRB;

    @FXML
    private RadioButton berlinRB;

    @FXML
    private RadioButton amsterdamRB;

    @FXML
    private RadioButton parisRB;

    @FXML
    private TextField hostText;

    @FXML
    private Button connectBtn;

    @FXML
    private Button getBtn;

    @FXML
    private Text todayText;

    @FXML
    private Text tommorow;

    @FXML
    private Text tommorowText;

    @FXML
    private Text afterTommorow;

    @FXML
    private Text afterTommorowText;

/* 
 the Initialize method sets the user data of the radio butoons to the rigth value so we can recieve them in the server as a number    
 */
    public void initialize(){
     jerusalemRB.setUserData(1);
     washingtonRB.setUserData(2);
     berlinRB.setUserData(3);
     amsterdamRB.setUserData(4);
     parisRB.setUserData(5);
    }
    
    /*
    ConnectClicked is incharge of getting the host name from the user and seting the ip address to the rigth value.
    then we clear the button and text field so the user can not change in the middle
    */
    @FXML
    private void ConnectClicked(javafx.event.ActionEvent event) {
         try { 
            if(hostText.getText()!=null){//if tthe user said what host he wants
            connectBtn.setVisible(false);//hide connect button
            String hostname=hostText.getText();
            this.IPAddress = InetAddress.getByName(hostname);
            hostText.setVisible(false);
            System.out.println("Connected");
            }
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   /*
    getClicked function sends a request to the server with the number of city that the user wants, and recieves 3 lines of text from the server to display n GUI
    */
    @FXML
    private void getClicked(javafx.event.ActionEvent event) {
             System.out.println("weatherapp.Controller.getClicked()");
        try {
        //  Create the socket object for carrying the data.
            DatagramSocket ds = new DatagramSocket();
        
            byte buf[] = null;
            
            //getting the button selected
            String num=Cities.getSelectedToggle().getUserData().toString();
            buf = num.getBytes();//preparing num for sending 
            
            //Create the datagramPacket for sending the data.
            DatagramPacket Dp = new DatagramPacket(buf, buf.length, IPAddress, 3333);
            
            //invoke the send call to actually send the data. the server will catch this data
            ds.send(Dp);
            
            
             buf=new byte[65536];//creating a big buffer for the text
             for(int i=0;i<3;i++){
                 
             //we want a new Datagram Packet   to recieve the dat from the dat from server
             Dp = new DatagramPacket(buf, buf.length);
             ds.receive(Dp);
             
             //we have to turn the data in to a normal string to display it
             StringBuilder ret=get(buf);
                 
                 
             // deciding where to put the line 
             if(i==0) todayText.setText(ret.toString());
             else if(i==1) tommorowText.setText(ret.toString());
             else afterTommorowText.setText(ret.toString());
             
             }
            
            
            
            
        }catch (UnknownHostException  ex) {
            System.out.println("unknown host error");
        } catch (SocketException ex) {
            System.out.println("socekt error");
        } catch (IOException ex) {
            System.out.println("bad connection . IO error");;
        }
        
    }
    
    
    /*
    the get function turns a byte array in to a string builder object that can be read normaly
    */
  public static StringBuilder get(byte [] a){
      StringBuilder ret = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
                ret.append((char) a[i]);//adding the chars to the string
        }
        return ret;
    }
}




