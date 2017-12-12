import jdk.nashorn.internal.ir.debug.JSONWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DBConfig {
final String CONFIG = "conf.json";
String url;
String username;
String password;
int port;
String database;

    public void writeconfig(){
        FileWriter configFile = null;
        JSONObject config = new JSONObject();
        config.put("url","sqlite://...");
        config.put("port",3166);
        config.put("username","root");
        config.put("password", "root");
        config.put("database","Baza_1");
try {
    configFile = new FileWriter("conf.json");
    configFile.write(config.toJSONString());
    }
catch(IOException e){
    e.printStackTrace();
    }
finally{
    try{
        configFile.close();
        }
    catch(Exception e){}
    }
}

    public void readConfig() throws IOException{
        JSONParser parser = new JSONParser();
        FileReader configFile = null;


        try{
            configFile = new FileReader(CONFIG);
        //Object obj = parser.parse(configFile);
         JSONObject obj = (JSONObject) parser.parse(configFile);

        url = (String)obj.get("url");
        username = (String)obj.get("username");
        password = (String)obj.get("password");
        port = (Integer) obj.get("port");

        System.out.println(url);
        System.out.println(username);
        System.out.println(password);
        System.out.println(port);
        }
        catch(Exception e){

        }
        finally{
            configFile.close();
        }
    }
    public static void main(String[] args) throws IOException{
        DBConfig dbc = new DBConfig();
        dbc.writeconfig();
        dbc.readConfig();
    }
}
