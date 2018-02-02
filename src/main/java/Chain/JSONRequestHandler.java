package Chain;

import Strategy.Singleton;
import Strategy.StrategyDBHandler;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.Main;
import main.Person;
import org.json.simple.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class JSONRequestHandler implements HttpHandler {

    HttpHandler successor;
    String response = "['testing json']";
    public void setSuccessor(HttpHandler successor){
        this.successor = successor;
    }

    public Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("&")) {
            String pair[] = param.split("=");
            if (pair.length>1) {
                result.put(pair[0], pair[1]);
            }else{
                result.put(pair[0], "");
            }
        }
        return result;
    }

    public void handle(HttpExchange t) throws IOException {
    String req = t.getRequestMethod();
    if(req.equals("PUT")){
        System.out.println("JSONRequestHandler handling request: "+req);
        Singleton s = Singleton.getInstance("sqlite");
        String request = t.getRequestURI().getQuery();
        System.out.println(request);
        Map<String, String> tmp = queryToMap(request);
        StrategyDBHandler str = s.database;
        Person p = new Person();
        p.setId(3);
        p.setName("Bartłomiej");
        p.setSurname("Zieliński");
        p.setMail("barze@example.com");
        p.setTelephone(222333444);
        str.add(p);
        Map<Integer, Person> mp = str.getAll();
        JSONArray a = str.toJson(mp);
        System.out.println(a.toString());
        response = a.toString();
        MainHandler.writeResponse(t, response,"json");
    }
    else if(req.equals("POST")){
        System.out.println("JSONRequestHandler handling request: "+req);
        Singleton s = Singleton.getInstance("sqlite");
        StrategyDBHandler str = s.database;
        Map<Integer, Person> mp = str.getAll();
        JSONArray a = str.toJson(mp);
        System.out.println(a.toString());
        response = a.toString();
        MainHandler.writeResponse(t, response,"json");

    }
    else if(req.equals("DELETE")){
        System.out.println("JSONRequestHandler handling request: "+req);
        Singleton s = Singleton.getInstance("sqlite");
        StrategyDBHandler str = s.database;
        str.remove(2);
        Map<Integer, Person> mp = str.getAll();
        JSONArray a = str.toJson(mp);
        System.out.println(a.toString());
        response = a.toString();
        MainHandler.writeResponse(t, response,"json");
    }
    else{
        System.out.println("JSONRequestHandler can't handle this request: "+req);
        successor.handle(t);
    }

}



}
