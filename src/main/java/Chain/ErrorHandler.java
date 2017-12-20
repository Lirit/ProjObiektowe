package Chain;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

public class ErrorHandler implements HttpHandler {

    public void handle(HttpExchange t) throws IOException {

        Headers h = t.getResponseHeaders();
        h.add("Content-Type", "text/html");
        String response =  "Error";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
