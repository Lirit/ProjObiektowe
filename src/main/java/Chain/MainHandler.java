package Chain;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class MainHandler implements HttpHandler {
    HttpHandler successor;
    static String lastResponse = "No responses yet.";
    public void setSuccessor(HttpHandler successor){
        this.successor = successor;
    }

    public void handle(HttpExchange t) throws IOException {
        String req = t.getRequestMethod();
        if(req.equals("GET")){
            System.out.println("MainHandler handling request: "+req);
            String response = lastResponse;
            writeResponse(t,response);
        }
        else{
            System.out.println("MainHandler can't handle this request: "+req);
            successor.handle(t);
        }
    }

    public static void writeResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }



}
