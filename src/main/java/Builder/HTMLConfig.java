package Builder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

public class HTMLConfig {
String file;

    public String readConfig(String naem) throws IOException {
        JSONParser parser = new JSONParser();
        FileReader configFile = null;

        try{
            configFile = new FileReader(naem);
            //Object obj = parser.parse(configFile);
            JSONObject obj = (JSONObject) parser.parse(configFile);

            file = (String)obj.get("fileName");

            System.out.println(file);
        }
        catch(Exception e){

        }
        finally{
            configFile.close();
        }

        return file;
    }
}
