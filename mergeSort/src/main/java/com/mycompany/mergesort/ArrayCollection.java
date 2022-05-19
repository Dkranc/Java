
package com.mycompany.mergesort;

import java.util.ArrayList;

public class ArrayCollection {
 private ArrayList<ArrayList<Integer>> _lists;
 private int threadsWaiting;
 private int maxThreads;
public  ArrayCollection(int m){
 _lists=new ArrayList();
 threadsWaiting=0;
 maxThreads=m;
}
public synchronized ArrayList<ArrayList<Integer>> getCoupleAndDeleteFromList(){
  ArrayList<ArrayList<Integer>> list=new ArrayList();
  if(threadsWaiting<maxThreads-1){
   if(_lists.size()<2){ 
  try{
       threadsWaiting++;
       wait();
       threadsWaiting--;
     
        
  }catch(InterruptedException e){ 
           
   }
    
  } 
  else{
       list.add(_lists.remove(0));
       list.add(_lists.remove(0));
       return list;
    }
  }
  else{
      print();
      return null;   
  }
  return null;
}
public synchronized void putInTheList(ArrayList<Integer> list){
 _lists.add(list);
 notifyAll();
}

public int size(){
 return _lists.size();
}

public void print(){
  ArrayList<Integer> list= _lists.remove(0); 
  while(list.size()>0){
      System.out.print(list.remove(0)+" ");
}
     System.out.println();
//    System.out.println(_lists.get(0));
}



public synchronized void merge(ArrayList<Integer> firstHalf,ArrayList<Integer> seconedHalf,ArrayList<Integer> arr){
      while(firstHalf.size()>0&&seconedHalf.size()>0){
       if(firstHalf.get(0)<seconedHalf.get(0)){
        arr.add(firstHalf.remove(0));
       }
       else{
         arr.add(seconedHalf.remove(0));
       }
      }
      while(firstHalf.size()>0){
       arr.add(firstHalf.remove(0));  
      }
      while(seconedHalf.size()>0){
        arr.add(seconedHalf.remove(0));
      }
       notifyAll();
     }

    

}

