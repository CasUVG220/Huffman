// HuffmanCompressor.java
import java.io.*;
import java.util.*;
import java.nio.file.Files;



public class HuffmanCompressor {
    public static void compress(String inputPath, String outputPath) throws IOException {
        String text = new String(Files.readAllBytes(new File(inputPath).toPath()));
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        HuffmanTree tree = new HuffmanTree(frequencies);
        Map<Character, String> codes = tree.getCodes();

        // Guardar códigos en el archivo
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputPath))) {
            oos.writeObject(codes);
            StringBuilder encoded = new StringBuilder();
            for (char c : text.toCharArray()) {
                encoded.append(codes.get(c));
            }
            oos.writeObject(encoded.toString());
        }
    }
}
