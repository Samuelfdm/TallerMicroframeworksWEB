package edu.escuelaing.app;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private final Map<String, String> queryParams = new HashMap<>();

    public void setQueryParams(String query) {
        if (query != null) {
            for (String param : query.split("&")) {
                String[] keyValue = param.split("=");
                if (keyValue.length > 1) {
                    queryParams.put(keyValue[0], keyValue[1]);
                }
            }
        }
    }

    public String getValues(String key) {
        return queryParams.get(key);
    }
}