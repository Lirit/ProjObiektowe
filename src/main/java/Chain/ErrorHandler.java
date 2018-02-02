package Chain;

import Builder.Test;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import static Chain.MainHandler.lastResp;
import static Chain.MainHandler.writeResponse;

public class ErrorHandler implements HttpHandler {
    HttpHandler successor;

    public void setSuccessor(HttpHandler successor){
        this.successor = successor;
    }

    public void handle(HttpExchange t) throws IOException {
            String req = t.getRequestMethod();
            System.out.println("Error: none of the available handlers can handle this request: " + req);
            //Headers h = t.getResponseHeaders();
            //h.add("Content-Type", "text/html");
           /* Builder.Test tst = new Test();
            String html = tst.getTemp().returnHTML();
            String response = html;
            MainHandler m = new MainHandler();
        //parsingJSON("main.json");
            MainHandler.writeResponse(t, response, "html");
            /*t.sendResponseHeaders(404, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();*/
            Builder.Test tst = new Test();
            String response = tst.getTemp().returnHTML();
            MainHandler mh = new MainHandler();
            lastResp = response;
                //parsingJSON("main.json");
            mh.writeResponse(t, response, "html");
    }
}
