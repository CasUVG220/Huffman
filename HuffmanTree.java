// HuffmanTree.java
import java.util.*;

public class HuffmanTree {
    private HuffmanNode root;
    private Map<Character, String> huffmanCodes = new HashMap<>();

    public HuffmanTree(Map<Character, Integer> frequencies) {
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (var entry : frequencies.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);
            pq.add(parent);
        }

        root = pq.poll();
        buildCodes(root, "");
    }

    private void buildCodes(HuffmanNode node, String code) {
        if (node.isLeaf()) {
            huffmanCodes.put(node.character, code);
            return;
        }
        buildCodes(node.left, code + "0");
        buildCodes(node.right, code + "1");
    }

    public Map<Character, String> getCodes() {
        return huffmanCodes;
    }

    public HuffmanNode getRoot() {
        return root;
    }
}
