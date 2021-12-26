package com.gorkem.parentpath.httpserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class BasicHttpServer {

    private static HttpServer server;
    private static int port;
    private static int var1;

    public static void main(String[] args) {
        port = 8080;
        var1 = 0;
        server = serverBuilder(port, var1);
        System.out.println("Server started at : " + port + ".");
        server.createContext("/", new RootHandler());
        server.setExecutor(null);
        server.start();
    }

    private static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String response = "This is root handler.";
            httpExchange.sendResponseHeaders(200, response.length());

            OutputStream os = httpExchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    private static HttpServer serverBuilder(int port, int var1) {
        if (server == null) {
            try {
                if (port < 1024 || port > 49151) {
                    return null;
                }
                server = HttpServer.create(new InetSocketAddress(port), var1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return server;
    }
}
