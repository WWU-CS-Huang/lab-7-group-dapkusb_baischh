package lab7;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.NoSuchElementException;

import heap.Heap;

public final class HuffmanTree {

  public class Node {

    public char character;
    public int count;
    public Node left;
    public Node right;

    /** constructor: gives default values to all fields */
    public Node() { }

    /** constructor: sets only word */
    public Node(char c) {
      character = c;
    }

    /** constructor: sets word and parent fields */
    public Node(char c, int p) {
      character = c;
      count = p;
    }

    /** constructor: sets all fields */
    public Node(char c, int p, Node l, Node r) {
      character = c;
      count = p;
      left = l;
      right = r;
    }

    public Node(int p, Node l, Node r) {
      count = p;
      left = l;
      right = r;
    }
  }

  Heap<Node, Integer> stack = new Heap<>();

  Map<Character, Integer> counts = new HashMap<>();

  Hashtable<Character, String> storage = new Hashtable<>();

  Node origin; // Head Node in Huffman Tree

  

  /** Count the chars in a the input string**/
  private void countChars(String s) {
    for (char c : s.toCharArray()) {
      counts.put(c, counts.getOrDefault(c, 0) + 1);
    }
  }

  /** Populate a priority queue with chars from frequency list**/
  private void enqueue() {
    // for feach pair char and count 
    // add the pair to a heap with the char as the value and the count 
    // as the priority

    char c;
    int count;
    
    for(Map.Entry<Character, Integer> entry : counts.entrySet()) {

        c = entry.getKey();

        count = entry.getValue();

        Node node = new Node(c, count, null, null);

        stack.add(node, count);
    }
  }

  /** Build the huffman tree from the frequency list**/
  private void buildTree() {
    // countChars(s);
    // enqueue();
  
    if (stack.size() <= 1) return;

    // take lowest two freqies

    Node h = stack.poll();
    Node k = stack.poll();

    // cobine into a new node with cobined frequencies

    Node fresh = new Node(h.count + k.count, h, k);

    origin = fresh;

    // insert node into frequency list
    //
    stack.add(fresh, fresh.count);

    // repeat

    buildTree();
  }

  /** Encodes a String into values that are able to be read by the Huffman Tree **/
  public String encode(String s) throws NoSuchElementException{ // If person tries to put a value thats not in the tree to encode
    countChars(s);
    enqueue();
    buildTree();
    walkTree();


    String returnString = "";
    // String temp;
    for (int i = 0; i < s.length(); i++){
      // temp = storage.get(input.charAt(i));
      returnString += storage.get(s.charAt(i));;
    }
    return returnString;
  }

  /** Decodes a String of zeros and ones into text **/
  public String decode(String s){
    Node cur = origin;
    String returnString = "";
    for (int i = 0; i < s.length(); i++){
      if (cur.left == null && cur.right == null){
        returnString += cur.character;
        cur = origin;
      }
      if (s.charAt(i) == '0'){
        cur = cur.left;
      } else {
        cur = cur.right;
      }
    }
    returnString += cur.character; // Adds the last letter as the for loop would omit it otherwise
    return returnString;
  }

  // public void test(){
  //   origin = new Node();
  //   origin.left = new Node('i');
  //   origin.right = new Node();
  //   origin.right.right = new Node('f');
  //   origin.right.left = new Node('g');

  //   walkTree();
  //   System.out.println(storage.entrySet());
  // }


  /** Finds the paths to each character in the Huffman Tree and adds them to a hashtable for storage **/
  public void walkTree(){ 
    String path = "";
    walkTree(origin, path);
  }


  /** Recursive method that finds paths and adds them to hashtable
      Invariant: path will always contain the path of the node head **/
  private void walkTree(Node head, String path){
    //System.out.println(path);
    if (head.left == null && head.right == null){
      storage.put(head.character, path);
      return;
    }
    if (head.left != null){
      walkTree(head.left, path + "0");
    }
    if (head.right != null){
      walkTree(head.right, path + "1");
    }
  }
}
