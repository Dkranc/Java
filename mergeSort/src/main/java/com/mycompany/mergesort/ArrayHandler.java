package com.mycompany.mergesort;

import java.util.ArrayList;
public class ArrayHandler extends Thread{ 
    private ArrayCollection _theArrays;
    
 public  ArrayHandler(ArrayCollection theArrays){
  this._theArrays=theArrays;
 }  
    
@Override
    public void run()
    {
         ArrayList<ArrayList<Integer>> pair=_theArrays.getCoupleAndDeleteFromList();
         while(pair!=null){
           ArrayList<Integer> newArr=new ArrayList();
           _theArrays.merge(pair.remove(0),pair.remove(0), newArr);
           _theArrays.putInTheList(newArr);
           pair=_theArrays.getCoupleAndDeleteFromList();
        }
    }

    }
     


