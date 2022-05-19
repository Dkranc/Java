
package com.mycompany.weatherappserver;


import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
 

/*
this class is the server for the weather app. we use datagram sockets to transfer the data, and packets to recieve the data. 
*/
public class WeatherServer
{
    /*
    the main method reads the weather data from a file, then waits for client to select what city he wants to see the weather for.
     after that the server sends back 3 lines of information about the weather in the next few days.
    */
    public static void main(String args[]) throws IOException
    {
        //creating File instance to reference text file in Java
        File text = new File("Weather.txt");
     
        //Creating Scanner instance to read File in Java
        Scanner scnr = new Scanner(text);
        
        String [][]info= new String[5][3];//this will hold the lines of information about the weather
        //Reading each line of the file using Scanner class
        for(int i=0;i<5;i++){
            for(int j=0;j<3;j++){ 
              info[i][j]=scnr.nextLine();
            }  
        }
   try{

        //  Create a socket to listen at port 3333
        DatagramSocket ds = new DatagramSocket(3333);
        System.out.println("server ready");
        
        // creating buffer to hold the data in
        byte[] buf = new byte[65536];
 
        
        while (true)//we want to allways listen for another request
        {
 
            // create a DatgramPacket to receive the data.
            DatagramPacket DpReceive = new DatagramPacket(buf,buf.length);
 
            // revieve the data in byte buffer.
            ds.receive(DpReceive);
            
            //puting the data recievd in a string theat we can read
            StringBuilder ret = new StringBuilder();
            ret.append((char) buf[0]);
            
            //this is to make sure we got the right number from client
            System.out.println("Client: " + ret);
 
            int num=Integer.parseInt(ret.toString());
            
            String[] response=null;//response will hold an array of length 3 for each day needed.
            
            //we want to know what number we got from the user
            switch(num){
                case 1: response=info[0];
                        break;
                case 2: response=info[1];
                        break;
                case 3: response=info[2];
                        break;
                case 4: response=info[3];
                        break;
                case 5: response=info[4];
                        break;        
            }
             for(int i=0;i<3;i++){
                 buf = response[i].getBytes();//preparing the string for sending
           
                 //Create the datagramPacket for sending  the data.                 
                 InetAddress inet = DpReceive.getAddress();
                 int port=DpReceive.getPort();
                 DatagramPacket DpSend = new DatagramPacket(buf, buf.length, inet, port);
                 
                 //invoke the send call to actually send the data.
                   ds.send(DpSend);
 
                 // Clear the buffer after every message.
                   buf = new byte[65535];
            }
        }
        
   } catch(IOException e){
     
       System.out.println("error ");
   }
   
  }
    


}