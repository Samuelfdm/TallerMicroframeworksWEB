package edu.escuelaing.app;

import java.util.HashMap;
import java.util.Map;

public class App {
    private static final Map<String, String> users = new HashMap<>();

    public static void main(String[] args) {
        // Configurar la ubicación de los archivos estáticos
        StaticFileHandler.staticfiles("./src/main/resources/static");

        // Definir rutas REST
        Router.get("/hello", (req, res) -> "Hello " + req.getValues("name"));
        //EL SERVICIO ESTATICO prueba.html no responde correctamente, deberia imprimir el JSON basado en el input que escribe el usuario
        //Router.get("/app/hello", (req, res) -> "{\"name\":\"" + name + "\"}");
        Router.get("/pi", (req, res) -> String.valueOf(Math.PI));

        // Ruta para agregar un usuario usando GET
        Router.get("/addUser", (req, res) -> {
            String name = req.getValues("name");
            String email = req.getValues("email");
            if (name != null && email != null) {
                users.put(name, email);
                return "User added: " + name + " with email " + email;
            } else {
                return "Missing parameters: name and email are required";
            }
        });

        // Ruta para obtener todos los usuarios
        Router.get("/users", (req, res) -> {
            StringBuilder sb = new StringBuilder();
            users.forEach((key, value) -> sb.append(key).append(": ").append(value).append("\n"));
            return sb.toString();
        });

        // Iniciar el servidor
        HttpServer server = new HttpServer();
        server.start();
    }
}