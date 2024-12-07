package CMU4ZN1112;

import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONReadNeptunkod {
    public static void main(String[] args) {
        try {
            String content = new String(Files.readAllBytes(Paths.get("orarendCMU4ZN.json")));
            JSONObject jsonObj = new JSONObject(content);

            JSONObject orarend = jsonObj.getJSONObject("orarend");
            JSONArray oraArray = orarend.getJSONArray("ora");

            for (int i = 0; i < oraArray.length(); i++) {
                JSONObject ora = oraArray.getJSONObject(i);
                System.out.println("Óra " + (i + 1) + ":");
                for (String key : ora.keySet()) {
                    if (ora.get(key) instanceof JSONObject) {
                        System.out.println("  " + key + ":");
                        JSONObject nested = ora.getJSONObject(key);
                        for (String nestedKey : nested.keySet()) {
                            System.out.println("    " + nestedKey + ": " + nested.get(nestedKey));
                        }
                    } else {
                        System.out.println("  " + key + ": " + ora.get(key));
                    }
                }
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
