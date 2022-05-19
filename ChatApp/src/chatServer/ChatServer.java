
package chatServer;

import java.io.*;
import java.net.*;
import java.util.*;
 
/*
the chat server class has 2 atributes , port number and the userthreads list to keep track of the users connected
*/
public class ChatServer {
    private int port;
    ArrayList<UserThread> userThreads = new ArrayList<UserThread>(); 
    
    public ChatServer(int port) {
        this.port = port;
    }
 /*
  *this is the main function for this class. we create a server to listen on port 4444
    and wait for users to connect. we wait for 2 users to connect and start there chat.
    the server recieves each of the users messeges and send them to the othe user.
  */
    public void execute() {
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {//we conncet the server to the port we got 
            
            System.out.println("Chat Server is listening on port " + port);
            int userCounter=0;
            
            while (true) {
                Socket socket1 = serverSocket.accept();//wating for first user to connect to the server
                userCounter++;
                UserThread newUser1 = new UserThread(socket1, this,userCounter);//creating a user thread with the socket we recieved
                System.out.println("new user connected");
                
                Socket socket2 = serverSocket.accept();//wating for second user to connect to the server
                userCounter++;
                UserThread newUser2 = new UserThread(socket2, this,userCounter);//creating a user thread with the socket we recieved
                System.out.println("new user connected");
                
                //adding the users to the user list
                userThreads.add(newUser1);
                userThreads.add(newUser2);
                
                //starting the user threads
                newUser1.start();
                newUser2.start();
            }
        }
             catch (IOException ex) {
            
            System.out.println("Error in the server: " + ex.getMessage());
        }

    }
 /*
    the main method just calls the excute function with port number we provided
    */
    public static void main(String[] args) {
        int port = 4444;
        ChatServer server = new ChatServer(port);
        server.execute();
    }
   /*
    this method finds the users partner and send the message to him
    */ 
 void broadcast(String message, int user) throws IOException {
     int userPartner;
     if(user%2==0) userPartner=user-1;//if the user number is even then his partner has his number minus 1
     else userPartner=user+1;//if the user number is odd then his partner has his number plus 1
     userThreads.get(userPartner-1).sendMessage(message);       
    }
}


