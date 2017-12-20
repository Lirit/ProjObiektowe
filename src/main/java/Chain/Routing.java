package Chain;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;


public class Routing {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);
        System.out.println("server started at port 9000" );
        server.createContext("/main", (HttpHandler) new MainHandler());
        //server.createContext("/get", (HttpHandler) new JSONRequestHandler());
        server.createContext("/json", (HttpHandler) new JSONRequestHandler());
        server.createContext("/error", (HttpHandler) new ErrorHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
}