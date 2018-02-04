package Chain;

import Builder.HTMLConfig;
import Decorator.Editor;
import Strategy.Singleton;
import Strategy.StrategyDBHandler;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.Person;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class MainHandler implements HttpHandler {
    HttpHandler successor;
    public void setSuccessor(HttpHandler successor){
        this.successor = successor;
    }

    public static String lastResp = null;

    public void handle(HttpExchange t) throws IOException {
        String req = t.getRequestMethod();
        System.out.println(req);
        if(req.equals("GET")){
            String url = "http://localhost:9000/main";
            System.out.println("MainHandler handling request: " + req);
            String request = t.getRequestURI().getQuery();
            String r = t.getRequestURI().toString();
            String response = "";
            if(r.equals("/main")){
                response = parsingHTML("temp.html");
                writeResponse(t, response, "html");
            }
            else if(r.equals("/add")){
                response = parsingHTML("add.html");
                writeResponse(t, response, "html");
            }
            else if(r.equals("/remove")){
                response = parsingHTML("remove.html");
                writeResponse(t, response, "html");
            }
            else if(r.equals("/error")){
                Thread thread = new Thread(){
                    public void run(){
                        String response = doOptions(url);
                        try{
                            writeResponse(t, response, "html");
                        }
                        catch(Exception ex){}
                    }
                };
                thread.start();
            }
            else if(r.equals("/listall")){
                Thread thread = new Thread(){
                    public void run(){
                        String response = doPost(url);
                        try{
                            writeResponse(t, response, "json");
                        }
                        catch(Exception ex){}
                    }
                };
                thread.start();
            }
            else if(r.equals("/edittemplate")){
                Editor ed = new Editor(new HTMLConfig());
                ed.readConfig("special_config.json", "comments");
                Thread thread = new Thread(){
                    public void run(){
                        String response = doPost(url);
                        try{
                            writeResponse(t, response, "json");
                        }
                        catch(Exception ex){}
                    }
                };
                thread.start();
            }
            else if(r.matches("/removerequest.*")){
                Thread thread = new Thread(){
                    public void run(){
                        Map<String, String> mp = queryToMap(t.getRequestURI().getQuery());
                        int id = parseInt(mp.get("id"));
                        String response = doDelete(url, id);
                        try{
                            writeResponse(t, response, "json");
                        }
                        catch(Exception ex){}
                    }
                };
                thread.start();
            }
            else if(r.matches("/addrequest.*")){
                Thread thread = new Thread(){
                    public void run(){
                        Map<String, String> mp = queryToMap(t.getRequestURI().getQuery());
                        Person person = new Person();
                        person.setName(mp.get("name"));
                        person.setSurname(mp.get("surname"));
                        person.setTelephone(parseInt(mp.get("telephone")));
                        person.setMail(mp.get("mail"));
                        String response = doPut(url, person);
                        try{
                            writeResponse(t, response, "json");
                        }
                        catch(Exception ex){
                            ex.printStackTrace();
                        }
                    }
                };
                thread.start();
            }

        }
        else{
            System.out.println("MainHandler can't handle this request: "+req);
            successor.handle(t);
        }
    }

    public static void writeResponse(HttpExchange httpExchange, String response, String type) throws IOException {
        Headers headers = httpExchange.getResponseHeaders();
        if(type=="json") {
            headers.set("Content-Type", String.format("application/json; charset=%s", StandardCharsets.UTF_8));
        }
        final byte[] rawResponseBody = response.getBytes(StandardCharsets.UTF_8);
        httpExchange.sendResponseHeaders(200, rawResponseBody.length);
        httpExchange.getResponseBody().write(rawResponseBody);
        httpExchange.close();
    }

    String parsingJSON(String file){
        String json = "";
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(file));
            JSONObject jsonObject = (JSONObject) obj;
            json = obj.toString();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return json;
    }

    String parsingHTML(String file){
        StringBuilder HTMLparse = new StringBuilder();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null) {
                HTMLparse.append(str);
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
            in.close();}
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        return HTMLparse.toString();
    }


public String doOptions(String url){

    StringBuffer buffer = null;
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("OPTIONS");
            String line;
            buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while(reader.ready()){
            line = reader.readLine();
            buffer.append(line);
            }
            reader.close();
            conn.disconnect();
        }
        catch(Exception e){

        }
    return buffer.toString();
}

    public String doPost(String url){

        StringBuffer buffer = null;
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            String line;
            buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while(reader.ready()){
                line = reader.readLine();
                buffer.append(line);
            }
            reader.close();
            conn.disconnect();
        }
        catch(Exception e){

        }
        return buffer.toString();
    }


    public String doDelete(String url, int id){
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
        str.remove(id);
        StringBuffer buffer = null;
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("DELETE");
            String line;
            buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while(reader.ready()){
                line = reader.readLine();
                buffer.append(line);
            }
            reader.close();
            conn.disconnect();
        }
        catch(Exception e){
        }
        return buffer.toString();
    }

    public String doPut(String url, Person p){
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
        str.add(p);

        StringBuffer buffer = null;
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            String line;
            buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while(reader.ready()){
                line = reader.readLine();
                buffer.append(line);
            }
            reader.close();
            conn.disconnect();
        }
        catch(Exception e){
        }
        return buffer.toString();
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

}
