package Strategy;

import main.Person;
import org.json.simple.JSONArray;

import java.util.Map;

public class Test {

    public static void main(String[] args){
        Singleton s = Singleton.getInstance("postgresql");
        StrategyDBHandler str = s.database;
        Map<Integer, Person> mp = str.getAll();
        JSONArray a = str.toJson(mp);
        System.out.println(a.toString());
    }
}
