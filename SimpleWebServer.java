/**
 * Basic HTTP Server
 * @author Your Name
 * @version 1.0
 */
import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class SimpleWebServer {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting server...");
        
        // Create server on port 8000
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        
        // Set up request handler
        server.createContext("/", new SimpleHandler());
        
        // Start the server
        server.start();
        System.out.println("Server running at http://localhost:8000");
    }
}