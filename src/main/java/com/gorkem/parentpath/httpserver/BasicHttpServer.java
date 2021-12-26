package com.gorkem.parentpath.httpserver;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class BasicHttpServer {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger("src/main/java/com/gorkem/parentpath/httpserver");

    private static String _logLevel = "";
    private static String _loggerName = "";

    private static HttpServer server;
    private static int port;
    private static int var1;

    public static void main(String[] args) {
        port = 8080;
        var1 = 0;
        server = serverBuilder(port, var1);
        System.out.println("Server started at : " + port + ".");
        server.createContext("/", new RootHandler());
        server.createContext("/log/change-level", new LogLevelPostHandler());
        server.setExecutor(null);
        server.start();
    }

    private static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) {
            String response = "This is root handler.";
            try {
                writeSuccessResponse(httpExchange, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class LogLevelPostHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) {
            try {
                StringBuilder response = new StringBuilder();

                Map<String, String> parameters = createKeyValuePairs(httpExchange);

                for (String key : parameters.keySet()) {
                    if (!parameters.isEmpty()) {
                        if (key.equals("loggerName")) {
                            _loggerName = parameters.get(key);
                        } else if (key.equals("logLevel")) {
                            _logLevel = parameters.get(key);
                        }
                    }
                }
                changedLogLevel(_loggerName, _logLevel);
                response.append(_loggerName).append("'s level successfully changed to ").append(_logLevel).append(".");

                writeSuccessResponse(httpExchange, String.valueOf(response));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                closeServer(server, var1);
            }
        }
    }

    private static void closeServer(HttpServer server, int var1) {
        if (server != null) {
            server.stop(var1);
        }
    }

    public static void changedLogLevel(String loggerName, String logLevel) {
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        Logger parentLogger = loggerName.equalsIgnoreCase("root") ? loggerContext.getLogger(loggerName) : loggerContext.exists(loggerName);

        if (parentLogger != null) {
            parentLogger.setLevel(Level.toLevel(logLevel));
        }
    }

    public static Map<String, String> createKeyValuePairs(HttpExchange httpExchange) {
        URI requestedUri = httpExchange.getRequestURI();
        String query = requestedUri.getRawQuery();

        return queryToMap(query);
    }

    public static void writeSuccessResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static Map<String, String> queryToMap(String query) {
        if (query == null) {
            return null;
        }
        Map<String, String> result = new HashMap<>();
        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
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

    public static void printLogPerSpecificTime(int sec, String loggerName) {
        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (log.isErrorEnabled()) { log.error("Logger name : {}. ERROR LOG", loggerName); }
                if (log.isWarnEnabled()) { log.warn("Logger name : {}. WARN LOG", loggerName); }
                if (log.isInfoEnabled()) { log.info("Logger name : {}. INFO LOG", loggerName); }
                if (log.isDebugEnabled()) { log.debug("Logger name : {}. DEBUG LOG", loggerName); }
                if (log.isTraceEnabled()) { log.trace("Logger name : {}. TRACE LOG", loggerName); }
            }
        };
        timer.schedule(task, 100, sec);
    }
}
