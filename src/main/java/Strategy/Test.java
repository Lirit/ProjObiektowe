package Strategy;

import main.Person;
import org.json.simple.JSONArray;

import java.util.Map;

public class Test {

    public static void main(String[] args){
        Singleton s = Singleton.getInstance("sqlite");
        StrategyDBHandler str = s.database;
        //Person p2 = str.getbyId(2);
        Map<Integer, Person> mp = str.getAll();
        JSONArray a = str.toJson(mp);
        System.out.println(a.toString());
        //System.out.println(p2.getName());
       /* StrategyDBHandler str = new StartegyDBsqlite();
        str.connect();
        Map<Integer, Person> mp = str.getAll();

        System.out.println(mp.get(2).getId()+" "+mp.get(2).getSurname());
        */

    }
}
