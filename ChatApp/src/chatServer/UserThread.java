
package chatServer;

import java.io.*;
import java.net.*;

/*the user thread runs and listens on the server to recieve and send messeges*/
public class UserThread extends Thread {
    private Socket socket;//this is what connected to the server
    private ChatServer server;//this is the server object that from whom we where sent from
    private PrintWriter writer;//an elemnt that can write meseeges to the server 
    private int userNum;//the number of the user
 
    public UserThread(Socket socket, ChatServer server, int num) {
        this.socket = socket;
        this.server = server;
        this.userNum=num;
    }
 
    /*
    the run function runs when we start the user tread.
    it creats a reader and writer that are used to read and write messges to and from the server.
    all users send and recieve from the server.
    */
    public void run() {
        try {
            //creating the reader element so we can get messeges from the server 
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            //creating the writer element so we can send messeges to the server 
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

 
             String serverMessage;
 
            do {
                //we read the line from the server and send it out the other user. the messege came from the user before the server held it
                serverMessage = "user "  + userNum +" says: " + reader.readLine();
                server.broadcast(serverMessage, userNum);//sending out the messege to the other user
 
            } while (!serverMessage.equals("bye"));

            socket.close();
 
        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

 
    /**
     * Sends a message to the client.
     */
    void sendMessage(String message) {
        writer.println(message);//write the messege to the server 
    }
}

