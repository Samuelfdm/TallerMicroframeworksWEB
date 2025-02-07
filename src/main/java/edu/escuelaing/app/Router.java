package edu.escuelaing.app;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Router {
    private static final Map<String, BiFunction<Request, Response, String>> routes = new HashMap<>();

    public static void get(String path, BiFunction<Request, Response, String> handler) {
        routes.put(path, handler);
    }

    public static String handleRequest(String path, Request req, Response res) {
        BiFunction<Request, Response, String> handler = routes.get(path);
        if (handler != null) {
            return handler.apply(req, res);
        }
        return null;
    }
}