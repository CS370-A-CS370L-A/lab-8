/**
 * Basic HTTP Request Handler
 * @author Your Name
 * @version 1.0
 */
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;

public class SimpleHandler implements HttpHandler {
    public void handle(HttpExchange exchange) throws IOException {
        try {
            // Always serve index.html
            File file = new File("index.html");
            
            // Check if file exists
            if (!file.exists()) {
                sendResponse(exchange, 404, "File not found");
                return;
            }
            
            // Read file and send response
            byte[] response = readFile(file);
            exchange.sendResponseHeaders(200, response.length);
            OutputStream os = exchange.getResponseBody();
            os.write(response);
            os.close();
            
        } catch (Exception e) {
            sendResponse(exchange, 500, "Server error");
        }
    }
    
    private byte[] readFile(File file) throws IOException {
        // Simple file reading
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        fis.read(data);
        fis.close();
        return data;
    }
    
    private void sendResponse(HttpExchange exchange, int code, String message) 
            throws IOException {
        byte[] response = message.getBytes();
        exchange.sendResponseHeaders(code, response.length);
        OutputStream os = exchange.getResponseBody();
        os.write(response);
        os.close();
    }
}