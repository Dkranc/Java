
package chatapplication;

import java.io.*;
import java.net.*;
import java.util.Scanner;
 
/**
the writer thread gets text from the client and sends it to the server so the other user can read it
 */
public class WriteThread extends Thread {
    private PrintWriter writer;//the object that writes to the server
    private Socket socket;
    private Controller client;
 
    public WriteThread(Socket socket, Controller client) {
        this.socket = socket;
        this.client = client;
 
        try {
            //creating the writer object to send messages t othe server
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    
 /*
    the run method is ran when the writer thread starts. it reads from the client(Controller class) and sends it to the server
    */
    public void run() {
        
        String msg;
        while(client.userConnected()){
            //read the line from the GUI
            msg = client.readLine();
            System.out.println(msg);
            
       if(msg!=null){
           //send the message to the server
           writer.println(msg);
           }
        }
            
 
        try {
            //after the user disconects we send the bye message to close the user thread
            writer.println("bye");
            socket.close();
        } catch (IOException ex) {
 
            System.out.println("Error writing to server: " + ex.getMessage());
        }
    }
}