package Builder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

public class HTMLConfig {


    public String readConfig(String naem, String parameter) throws IOException {
        String param= "";
        JSONParser parser = new JSONParser();
        FileReader configFile = null;
        try{
            configFile = new FileReader(naem);
            Object obj = parser.parse(configFile);
            JSONObject jsonobj = (JSONObject) obj;

            param = (String)jsonobj.get(parameter);

        }
        catch(Exception e){
e.printStackTrace();
        }
        finally{
            //configFile.close();
        }

        return param;
    }
}
