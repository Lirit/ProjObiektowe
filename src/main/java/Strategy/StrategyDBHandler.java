package Strategy;

import javafx.util.Pair;
import main.Person;
import org.json.simple.JSONArray;

import java.util.List;
import java.util.Map;

public interface StrategyDBHandler {

    public void connect();
    public void add(Person p);
    public void remove(int id);
    public Person getbyId(int id);
    public Map<Integer, Person> getAll();
    public JSONArray toJson(Map<Integer, Person> map);
}
