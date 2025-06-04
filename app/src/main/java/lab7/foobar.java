import java.util.HashMap;
import java.util.Map;
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
  }

  Heap<Character, Integer> stack = new Heap<>();

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
    
    for(Map.Entry<String, HashMap> entry : counts.entrySet()) {

        char c = entry.getKey();

        int count = entry.getValue();

        Node node = new Node(c, count, null, null);

        stack.put(node, count);
    }
  }

  /** Build the huffman tree from the frequency list**/
  private static void buildTree() {
  
    if (stack.size() <= 1) return;

    // take lowest two freqies

    Node h = stack.poll();
    Node k = stack.poll();

    // cobine into a new node with cobined frequencies

    Node fresh = new Node(null, h.count + k.count, h, k);

    // insert node into frequency list
    //
    stack.put(fresh, fresh.count);

    // repeat

    buildTree();
  }


}
