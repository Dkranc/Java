
package chatapplication;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
the read thread read messages from the server and shows them on the users app.
 */
public class ReadThread extends Thread {
    private BufferedReader reader; //the reader buffer we use to get the text 
    private Socket socket;//the socket thats connected to the server 
    private Controller client;//we have a controller object that is the client. this is how we send the text we read to the GUI
 
    public ReadThread(Socket socket, Controller client) {
        this.socket = socket;
        this.client = client;
 
        try {
            //creting the reader object to read from the server
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 /*
    the run method runs as the read thread. it reads text from the server and if its valid text then it sends it to the user(controller ) to show on his screen
    */
    public void run() {
        while (client.userConnected()) {//as long as the user is connected
            
                try {
                    String response = reader.readLine();//we read the line on the server 
                   // System.out.println(response);
                   if(null!=response)
                    client.addText(response);//adding the message t othe app if its valid
                    
                } catch (IOException ex) {
                    System.out.println("Error reading from server: " + ex.getMessage());
                    ex.printStackTrace();
                    break;
                }
                
        }
        
        try {
            socket.close();
            } catch (IOException ex) {
                Logger.getLogger(ReadThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
}
