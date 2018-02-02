package Chain;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

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
            String response = "";
            System.out.println(request);
            if(request.equals("options")){
                Thread thread = new Thread(){
                    public void run(){
                        String response = doOptions(url);
                        System.out.println(response);
                        try{
                            writeResponse(t, response, "html");
                        }
                        catch(Exception ex){}
                    }
                };
                thread.start();
            }
            else if(request.equals("post")){
                Thread thread = new Thread(){
                    public void run(){
                        String response = doPost(url);
                        System.out.println(response);
                        try{
                            writeResponse(t, response, "json");
                        }
                        catch(Exception ex){}
                    }
                };
                thread.start();
            }
            else if(request.equals("delete")){
                Thread thread = new Thread(){
                    public void run(){
                        String response = doDelete(url);
                        System.out.println(response);
                        try{
                            writeResponse(t, response, "json");
                        }
                        catch(Exception ex){}
                    }
                };
                thread.start();
            }
            else if(request.equals("put")){
                Thread thread = new Thread(){
                    public void run(){
                        String response = doPut(url);
                        System.out.println(response);
                        try{
                            writeResponse(t, response, "json");
                        }
                        catch(Exception ex){}
                    }
                };
                thread.start();
            }
            else if(request.matches("name=(.*)")){

                    Thread thread = new Thread(){
                        public void run(){
                            String response = doPut(url);
                            System.out.println(response);
                            try{
                                writeResponse(t, response, "json");
                            }
                            catch(Exception ex){}
                        }
                    };
                    thread.start();

            }
            else{
                    response = parsingHTML("temp.html");
                    writeResponse(t, response, "html");

            }

        }
        else{
            System.out.println("MainHandler can't handle this request: "+req);
            successor.handle(t);
        }
    }

    public static void writeResponse(HttpExchange httpExchange, String response, String type) throws IOException {
        //httpExchange.sendResponseHeaders(200, response.length());
        Headers headers = httpExchange.getResponseHeaders();
        if(type=="json") {
            headers.set("Content-Type", String.format("application/json; charset=%s", StandardCharsets.UTF_8));
        }
        final byte[] rawResponseBody = response.getBytes(StandardCharsets.UTF_8);
        httpExchange.sendResponseHeaders(200, rawResponseBody.length);
        httpExchange.getResponseBody().write(rawResponseBody);
        //OutputStream os = httpExchange.getResponseBody();
        httpExchange.close();
        //os.write(response.getBytes());
        //os.close();
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
    System.out.println(buffer.toString());
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
        System.out.println(buffer.toString());
        return buffer.toString();
    }


    public String doDelete(String url){

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
        System.out.println(buffer.toString());
        return buffer.toString();
    }

    public String doPut(String url){

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
        System.out.println(buffer.toString());
        return buffer.toString();
    }

    public Map<String, String> queryToMap(String query){
        Map<String, String> result = new HashMap<String, String>();
        for (String param : query.split("?")[1].split("&")) {
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
