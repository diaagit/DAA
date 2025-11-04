import java.util.*;

class HuffmanNode {
    char ch;
    int freq;
    HuffmanNode left, right;

    HuffmanNode(char ch, int freq) {
        this.ch = ch;
        this.freq = freq;
    }
}

public class HuffmanEncoding {

    static Map<Character, String> huffmanCodes = new HashMap<>();

    // Build Huffman Tree
    public static HuffmanNode buildTree(Map<Character, Integer> freqMap) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.freq));

        // Create leaf nodes
        for (var entry : freqMap.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Build the tree
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode newNode = new HuffmanNode('-', left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;
            pq.add(newNode);
        }

        return pq.poll();
    }

    // Generate Huffman Codes (Recursive)
    public static void generateCodes(HuffmanNode root, String code) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            huffmanCodes.put(root.ch, code);
            return;
        }
        generateCodes(root.left, code + "0");
        generateCodes(root.right, code + "1");
    }

    // Encode the text
    public static String encode(String text) {
        StringBuilder encoded = new StringBuilder();
        for (char ch : text.toCharArray()) {
            encoded.append(huffmanCodes.get(ch));
        }
        return encoded.toString();
    }

    // Decode the encoded text
    public static String decode(HuffmanNode root, String encoded) {
        StringBuilder decoded = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encoded.toCharArray()) {
            current = (bit == '0') ? current.left : current.right;
            if (current.left == null && current.right == null) {
                decoded.append(current.ch);
                current = root;
            }
        }
        return decoded.toString();
    }

    // ---------------- MAIN MENU ----------------
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String text = "", encoded = "";
        HuffmanNode root = null;

        while (true) {
            System.out.println("\n===== HUFFMAN ENCODING MENU =====");
            System.out.println("1. Enter Text");
            System.out.println("2. Show Huffman Codes");
            System.out.println("3. Encode Text");
            System.out.println("4. Decode Text");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Enter text: ");
                    text = sc.nextLine();
                    Map<Character, Integer> freqMap = new HashMap<>();
                    for (char c : text.toCharArray())
                        freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
                    root = buildTree(freqMap);
                    huffmanCodes.clear();
                    generateCodes(root, "");
                    System.out.println("Text stored successfully!");
                    break;

                case 2:
                    if (huffmanCodes.isEmpty())
                        System.out.println("Enter text first!");
                    else
                        System.out.println("Huffman Codes: " + huffmanCodes);
                    break;

                case 3:
                    if (text.isEmpty()) {
                        System.out.println("Enter text first!");
                        break;
                    }
                    encoded = encode(text);
                    System.out.println("Encoded Text: " + encoded);
                    break;

                case 4:
                    if (encoded.isEmpty()) {
                        System.out.println("Encode text first!");
                        break;
                    }
                    System.out.println("Decoded Text: " + decode(root, encoded));
                    break;

                case 5:
                    System.out.println("Exiting Program...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}


/*       (*,4)
     /     \
 (B,2)    (*,2)
          /   \
       (A,1) (C,1)

    input: "ABBC"
  
 */    
