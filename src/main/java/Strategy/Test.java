package Strategy;

import Builder.HTMLConfig;
import main.Person;
import org.json.simple.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args){
        HTMLConfig c = new HTMLConfig();
        String data = "";
        try {
            data = c.readConfig("database_conf.json", "current_database");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        Singleton s = Singleton.getInstance(data);
        StrategyDBHandler str = s.database;
        Map<Integer, Person> mp = str.getAll();
        JSONArray a = str.toJson(mp);
        System.out.println(a.toString());
    }
}
