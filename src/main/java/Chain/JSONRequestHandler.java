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

    //PUT, DELETE oraz POST

public void handle(HttpExchange t) throws IOException {
    String req = t.getRequestMethod();
    System.out.println(req);
    if(req.equals("PUT")){
        StringBuilder response = new StringBuilder();
        Map <String,String>parms = MainHandler.queryToMap(t.getRequestURI().getQuery());
        response.append("<html><body>");
        response.append("hello : " + parms.get("hello") + "<br/>");
        response.append("foo : " + parms.get("foo") + "<br/>");
        response.append("</body></html>");
        MainHandler.writeResponse(t, response.toString());
    }
    else if(req.equals("POST")){
        String response = "POST";
        MainHandler.writeResponse(t, response.toString());
    }
    else if(req.equals("DELETE")){
        String response = "DELETE";
        MainHandler.writeResponse(t, response.toString());
    }
    else{
        ErrorHandler e = new ErrorHandler();
        e.handle(t);
    }

}

}
