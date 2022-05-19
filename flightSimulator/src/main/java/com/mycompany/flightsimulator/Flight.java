
package com.mycompany.flightsimulator;

import java.security.SecureRandom;
/*
this class is a runnable class. that means each object from this class is excecutable.
 we use this class to simulate a flight from one airport to another.
*/
public class Flight implements Runnable{
   private int _flightNumber;
   private AirPort _takeOffFrom;
   private AirPort _landIn;
    /*
   the basic constructor. gets the take off airport , landing airport and flight number.
   */
   public Flight (int flightNumber,AirPort takeOff, AirPort land){
    this._flightNumber=flightNumber;
    this._takeOffFrom=takeOff;
    this._landIn=land;
  }  
   /*
   the run method is overriden so we can implement our own.
   we use random numbers to decide how long the fligth will be,
   then we take off and wait till runway is clear 
   we clear the runway with the method from the airport class and we land the plin in its destination.
    after landing we clear the runway again.
   */
   @Override
   public void run(){
    SecureRandom sc=new SecureRandom();//we use this to decide how long the fligt will be
    int takeOffRunway=_takeOffFrom.depart(_flightNumber);//we recieve the flight number from the airport
       try {
         Thread.sleep(sc.nextInt(3000)+2000); //we send the flight to sleep for 2-5 seconds. this ilustrates the takeoff time
         System.out.println("flight: "+_flightNumber+" took off from runway: "+takeOffRunway+" in the "+_takeOffFrom.getName()+" airport");
       } catch (Exception e) {
         }  
    _takeOffFrom.freeRunway(takeOffRunway,_flightNumber);//we free the runway from the airport
     try {
         Thread.sleep(sc.nextInt(8000)+2000);  //we send the flight to sleep for 8-10 seconds. this ilustrates the flight time
       } catch (Exception e) {
         }
     int landRunway=_landIn.land(_flightNumber);//we get the landing runway
       try {
         Thread.sleep(sc.nextInt(3000)+2000);  //we send the flight to sleep for 8-10 seconds. this ilustrates the landing time
         System.out.println("flight: "+_flightNumber+" landed on runway: "+landRunway+" in the "+_landIn.getName()+" airport");
       } catch (Exception e) {
         }
    
    _landIn.freeRunway(landRunway,_flightNumber);//we free the runway from the airport so other fligth can land
   }
}
