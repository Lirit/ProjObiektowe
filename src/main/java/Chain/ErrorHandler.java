package Chain;

import Builder.Test;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class ErrorHandler implements HttpHandler {
    HttpHandler successor;

    public void setSuccessor(HttpHandler successor){
        this.successor = successor;
    }

    public void handle(HttpExchange t) throws IOException {
        String req = t.getRequestMethod();
        System.out.println("Error: none of the available handlers can handle this request: "+req);
        Headers h = t.getResponseHeaders();
        h.add("Content-Type", "text/html");
            Builder.Test tst = new Test();
            String html = tst.getTemp().returnHTML();
        String response =  html;
        t.sendResponseHeaders(200, response.length());
        MainHandler.lastResponse = response;
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
