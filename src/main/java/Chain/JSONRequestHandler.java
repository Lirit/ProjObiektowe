package Chain;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class JSONRequestHandler implements HttpHandler {

    HttpHandler successor;

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
        String response = "PUT";
        MainHandler.lastResponse = response;
        MainHandler.writeResponse(t, response);
    }
    else if(req.equals("POST")){
        System.out.println("JSONRequestHandler handling request: "+req);
        String response = "POST";
        MainHandler.lastResponse = response;
        MainHandler.writeResponse(t, response.toString());

    }
    else if(req.equals("DELETE")){
        System.out.println("JSONRequestHandler handling request: "+req);
        String response = "DELETE";
        MainHandler.lastResponse = response;
        MainHandler.writeResponse(t, response.toString());
    }
    else{
        System.out.println("JSONRequestHandler can't handle this request: "+req);
        successor.handle(t);
    }

}



}
