// HuffmanNode.java
public class HuffmanNode implements Comparable<HuffmanNode> {
    public char character;
    public int frequency;
    public HuffmanNode left, right;

    public HuffmanNode(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
    }

    public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
        this.character = '\0'; // Nodo 
        this.frequency = frequency;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.frequency, other.frequency);
    }

    public boolean isLeaf() {
        return (left == null && right == null);
    }
}
