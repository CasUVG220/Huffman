// HuffmanDecompressor.java
import java.io.*;
import java.util.*;
import java.nio.file.Files;


public class HuffmanDecompressor {
    public static void decompress(String inputPath, String outputPath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(inputPath))) {
            Map<Character, String> codes = (Map<Character, String>) ois.readObject();
            String encoded = (String) ois.readObject();

            Map<String, Character> reverseCodes = new HashMap<>();
            for (Map.Entry<Character, String> entry : codes.entrySet()) {
                reverseCodes.put(entry.getValue(), entry.getKey());
            }

            StringBuilder decoded = new StringBuilder();
            StringBuilder temp = new StringBuilder();

            for (char bit : encoded.toCharArray()) {
                temp.append(bit);
                if (reverseCodes.containsKey(temp.toString())) {
                    decoded.append(reverseCodes.get(temp.toString()));
                    temp.setLength(0);
                }
            }

            Files.write(new File(outputPath).toPath(), decoded.toString().getBytes());
        }
    }
}
