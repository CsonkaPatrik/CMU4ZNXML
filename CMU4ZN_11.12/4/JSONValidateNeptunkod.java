package CMU4ZN1112;

import org.everit.json.schema.*;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONValidateNeptunkod {
    public static void main(String[] args) {
        try {
            // Load JSON schema
            String schemaContent = new String(Files.readAllBytes(Paths.get("orarendCMU4ZNSchema.json")));
            JSONObject jsonSchema = new JSONObject(schemaContent);
            Schema schema = SchemaLoader.load(jsonSchema);

            // Load JSON document
            String jsonContent = new String(Files.readAllBytes(Paths.get("orarendCMU4ZN.json")));
            JSONObject jsonObject = new JSONObject(jsonContent);

            // Validate JSON document against schema
            schema.validate(jsonObject);
            System.out.println("JSON dokumentum érvényes a sémával szemben.");

        } catch (ValidationException e) {
            System.out.println("JSON dokumentum nem érvényes. Hibák:");
            e.getAllMessages().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
