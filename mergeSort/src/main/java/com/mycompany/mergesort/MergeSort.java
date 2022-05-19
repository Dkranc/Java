
package com.mycompany.mergesort;
import java.util.Scanner;
import java.security.SecureRandom;
import java.util.ArrayList;
public class MergeSort {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        System.out.println("please enter the length of the array");
        int n=scan.nextInt();
        System.out.println("please enter the amount of threads");
        int m=scan.nextInt();
        SecureRandom sr=new SecureRandom();
        ArrayCollection arrays=new ArrayCollection(m);

        for(int i=0;i<n;i++)
        {
         ArrayList<Integer> arr=new ArrayList();
         arr.add((sr.nextInt(100)+1)); 
         arrays.putInTheList(arr);
        }  
        for(int i=0;i<m;i++){
        new ArrayHandler(arrays).start();   
        }          
        scan.close();
    }
}

     