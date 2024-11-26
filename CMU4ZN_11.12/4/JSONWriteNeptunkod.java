package neptunkod1112;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;

public class JSONWriteNeptunkod {
    public static void main(String[] args) {
        try {
            // Read the JSON file
            String content = new String(Files.readAllBytes(Paths.get("orarendNeptunkod.json")));
            JSONObject jsonObj = new JSONObject(content);

            // Print JSON in block format
            System.out.println("JSON blokk formában:");
            System.out.println(content);

            // Save JSON in structured format to a file
            try (FileWriter file = new FileWriter("orarendCMU4ZN1.json")) {
                file.write(jsonObj.toString(4)); // Indented with 4 spaces for readability
                System.out.println("\nStrukturált JSON mentve a fájlba: orarendCMU4ZN1.json");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
