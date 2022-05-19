
package com.mycompany.flightsimulator;

import java.util.LinkedList;
import java.util.Queue;
/*
the airport class ilustrates a real airport with a number of  runways.
we can takeoff land and free a runway from the airport.
all flights can use this class.
*/
public class AirPort {
    private String _name;
    private int _runWays;
    private int[] _freeRunways;
    private Queue<Integer> q;
  /*   this is the basic cunstroctr and it has:
the name of the airport,
the number of runways
    an array that keeps track of the free runways
    a queue to keep the flights in order
*/
    public AirPort(String name,int runWays){
     this._name=name;
     this._runWays=runWays;
     _freeRunways=new int[runWays];
     q=new LinkedList<>();
    }
/*
    the depart method ilustrates a take off,
    a flight that asks to depart gets a runway number that is free,
    if none are availble then we send the flight to wait untill a runway clears
    */
    public synchronized int depart(int fNumber){
        int freeRunway=-1;
        boolean runwayFound=false;
        while(!runwayFound){ //we want this while loop to never end untill we have a runway. the thread may sleep but it must eventually get a runway
        if((q.size()>0&&fNumber==q.peek())||q.isEmpty()){//we check if the queue is empty or if this flight is first in line. only then cna in take off..
        for (int i = 0; i < _runWays; i++) {//looking for a free runway
           if(_freeRunways[i]==0&&!runwayFound){//if we found a free runway
            runwayFound=true;
            _freeRunways[i]=1;//we set the value to 1 so we know this runway isnt available
            freeRunway=i;//this is the runway that will be returned
           }
        }
        }
        if(!runwayFound){//if we havent found a free runway
            try {
                if(!q.contains(fNumber))//if we havent already put this flight in the queue
                 q.add(fNumber);//add the flight to the queue
                wait();//the thread is in wait untill notified by the freeRunway method. only then will the fligth try to get a free runway again 
            } catch (Exception e) {      
            }
        }
        }
        
        return freeRunway;
    }
   /*
    the land method ilustrates a landing,
    a flight that asks to land gets a runway number that is free,
    if none are availble then we send the flight to wait untill a runway clears
    the method is just like the depart method so comments werre left out
    */ 
   public synchronized int land(int fNumber){
        int freeRunway=-1;
        boolean runwayFound=false;
        while(!runwayFound){        
        if((q.size()>0&&fNumber==q.peek())||q.isEmpty()){
        for (int i = 0; i < _runWays; i++) {
           if(_freeRunways[i]==0&&!runwayFound){
            runwayFound=true;
            _freeRunways[i]=1;
            freeRunway=i;
           }
        }
        }
        if(!runwayFound){
            try {
                 if(!q.contains(fNumber))
                  q.add(fNumber);
                 wait();
            } catch (Exception e) {      
            }
         }
        }
        
        return freeRunway;
    } 
   /*
   the freeRunway method gets a runway number and clers it for another landing
   */
   public synchronized void freeRunway(int runway,int flightNumber){
       _freeRunways[runway]=0;// we set the array to 0 so we know that this runway is clear
       System.out.println("flight: "+flightNumber+" has cleared runway "+ runway+" from Airport: "+_name );
       if(q.peek()!=null){
         q.remove(); //we remove the fligth from the queue because it landed
       }
        notifyAll();//notify all the thread that there is free runway. only the first flight in the line will be able to land
   }
   
    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }

    public int getRunWays() {
        return _runWays;
    }

    public void setRunWays(int _runWays) {
        this._runWays = _runWays;
    }
    
}
