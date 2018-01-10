package Chain;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.net.ssl.HttpsURLConnection;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;


public class Routing {

    public static void main(String[] args) throws Exception {
        MainHandler mh = new MainHandler();
        JSONRequestHandler rh = new JSONRequestHandler();
        ErrorHandler eh = new ErrorHandler();
        mh.setSuccessor(rh);
        rh.setSuccessor(eh);
        HttpServer server = HttpServer.create(new InetSocketAddress(9000), 0);
        System.out.println("server started at port 9000" );
        server.createContext("/main", (HttpHandler) mh);
        server.setExecutor(null); // creates a default executor
        server.start();

    }
}