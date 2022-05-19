
package com.mycompany.flightsimulator;

import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
/*
this is the main class for our program. it is used to test the flights that are runnable objects
*/
public class Main {
    public static void main(String[] args) {
        AirPort ap1=new AirPort("Ben-gurion",3);//creating new airports
        AirPort ap2=new AirPort("JFK",3);
        SecureRandom sc=new SecureRandom();//we use the random to decide what direction the flight will fly in
         try
		{
                 Flight[] flights=new Flight[10];//creating 10 new fligths in an array
		 ExecutorService exe = Executors.newFixedThreadPool(10);// creating a pool of 10 threads to run the flights
                 for (int i = 0; i < 10; i++) {
                   int direction=sc.nextInt(2);//selecting a number 0 or 1
                   if(direction==0){
                   flights[i]=new Flight(i, ap1, ap2);
                   }
                   else  flights[i]=new Flight(i, ap2, ap1);           
                 }	
                  for (int i = 0; i < 10; i++) {
                  exe.execute(flights[i]);//excecuting all 10 fligths
                          }
	        exe.shutdown();
	        exe.awaitTermination(Long.MAX_VALUE,TimeUnit.SECONDS);// we wait for all threads to end before closing
		}
		catch(InterruptedException e)
		{	
		 e.printStackTrace();
		 System.exit(1);
		}
    }

}


