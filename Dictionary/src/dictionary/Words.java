
package dictionary;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Words {
    
 private TreeMap<String, String> map;
    
    //default constructor
    public Words(){
        map=new TreeMap(); //initializing new data structure
    }
    
    //method getwords returns a string array with all the words in the dictonary
    public String[] getWords(){
        String[] words=new String[map.size()];
        int i=0;
        for(Map.Entry<String, String> entry: map.entrySet()){//loop throught the map and turn every key into a normal string and then input it into array
            String key=entry.getKey();
            words[i]=key;
            i++;
        }
        return words;
    }
    
    //the method returns a string array with all the definitions in the dictonary
    public String[] getDefinition(){
        String[] def=new String[map.size()];
        int i=0;
        for(Map.Entry<String, String> entry: map.entrySet()){//loop throught the map and turn every key into a normal string and then input it into array
            String val=entry.getValue();
            def[i]=val;
            i++;
        }
        return def;
    }
    
    //method adds a word into dictonary. first we check if number is valid and then we add it into the map
    public boolean add(String word, String def){
        boolean validWord=true;
        if(word.length()==0) validWord=false;//if we didnt get a word
        for(int i=0; i<word.length();i++){
            if(!Character.isLetter(word.charAt(i))) validWord=false; //if word isnt valid
        }
        if(validWord) map.put(word, def);//if the word is valid then add it into  map
        
      return validWord;    
    }
    
    //method returns the value of the key from the map if it exists or null if the ke isnt in the map
    public String containsKey(String key){
        return map.get(key);
    }
    
    //method deletes a word and its definition from the map
    //first we get the key then we remove it from map
    public void delete(String key){
        int j =0;
        String keyToDelete="";
        while(key.charAt(j)!= ':') j++;//we want to reach the end of the word
        keyToDelete=key.substring(0,j);
        map.remove(keyToDelete);//using the remove method of the map 
    }
    
  //the method gets a Scanner that points to a file and adds the words from it in to the map.
  //we only read from .txt files and we assume that the words are build in the pattern "home: a place where one lives permanently"
  //when every word has its own line
    public void getDictionary(Scanner in){
        String word="";
        String def="";
      while(in.hasNext()){ //while we still have more lines in file
          String line=in.nextLine(); //we get the next line in the file
          word="";
          def="";
          int i=0;
          int j=0;
          while(line.charAt(i)!= ' ' && line.charAt(i)!= '\t') i++;//we want to reach the end of the word
          word=line.substring(0,i);
          System.out.println(word);
          j=i+1;//we put j at the beggining of the definition
          while(i<line.length()) i++;//we want to reach the end og the definition
          def=line.substring(j,i);//we get the definition from the text
          this.add(word, def);//adding only valid contacts using the add method
          
      }
    }
    
}
