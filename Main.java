// Main.java
import java.io.File;
import java.util.Map;
import java.util.Scanner;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Comprimir archivo");
        System.out.println("2. Descomprimir archivo");
        System.out.println("3. Ver contenido .huff");
        System.out.print("Seleccione una opción: ");
        int opcion = sc.nextInt();
        sc.nextLine(); 

        try {
            if (opcion == 1) {
                System.out.print("Ruta del archivo original 'arhivo.txt': ");
                String entrada = sc.nextLine();
                System.out.print("Ruta para guardar archivo comprimido 'arhico.huff': ");
                String salida = sc.nextLine();
                HuffmanCompressor.compress(entrada, salida);
                System.out.println("Archivo comprimido.");
            } else if (opcion == 2) {
                System.out.print("Ruta del archivo comprimido (.huff): ");
                String entrada = sc.nextLine();
                System.out.print("Ruta para guardar archivo descomprimido (.txt): ");
                String salida = sc.nextLine();
                HuffmanDecompressor.decompress(entrada, salida);
                System.out.println("Archivo descomprimido");
            } else if (opcion == 3) {
                System.out.print("Ruta del archivo .huff: ");
                String ruta = sc.nextLine();
                mostrarContenidoHuff(ruta);
            } else {
                System.out.println("No se puede");
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        sc.close();
    }

    private static void mostrarContenidoHuff(String ruta) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta))) {
            Map<Character, String> codes = (Map<Character, String>) ois.readObject();
            String encoded = (String) ois.readObject();

            System.out.println("\nLista de letras en Huffman:");
            for (Map.Entry<Character, String> entry : codes.entrySet()) {
                char c = entry.getKey();
                String val = entry.getValue();
                String display = (c == '\n') ? "\\n" : (c == ' ') ? "' '" : String.valueOf(c);
                System.out.println("'" + display + "' → " + val);
            }

            System.out.println("\n Primeros 200 bits:");
            System.out.println(encoded.substring(0, Math.min(200, encoded.length())) + "...");

            File f = new File(ruta);
            System.out.println("\nTamaño del archivo en bytes: " + f.length() + " bytes");
        } catch (Exception e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
