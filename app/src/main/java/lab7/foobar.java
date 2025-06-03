import java.util.HashMap;
import java.util.Map;

import heap.Heap;
import avl.AVL;

public final class foobar {

  Map<Character, Integer> counts = new HashMap<>();

  /** Count the chars in a the input string**/
  private static void countChars(String s) {

    for (char c : s.toCharArray()) {
      counts.put(c, counts.getOrDefault(c, 0) + 1);
    }
  }

  /** Populate a priority queue with chars from frequency list**/
  private static void enqueue() {
    // for feach pair char and count 
    // add the pair to a heap with the char as the value and the count 
    // as the priority
    

  }

  /** Build the huffman tree from the frequency list**/
  private static void buildTree() {
    // take lowest two freqies
    //
    // cobine into a new node with cobined frequencies
    //
    // insert node into frequency list
    //
    // repeat
  }



  public String encode(String input){
    String returnString = "";
    for (int i = 0; i < input.length(); i++){
      returnString = (String) counts.get(input.charAt(i)); // Need to know actual list name
    }
  }

  public String decode(String input){
    int cur; //avlTreeOrigin;
    String returnString;
    for (int i = 0; i < input.length(); i++){
      if (cur.isLeaf()){
        //returnString = cur.getValue();
        //cur = avlTreeOrigin
        continue;
      }
      if (counts.get(input.charAt(i)) == '0'){
        //cur = cur.goLeft()
      } else {
        //cur = cur.goRight();
      }
    }
    return returnString;
  }
}
