package edu.escuelaing.app;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
    private static final int PORT = 35000;

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado en el puerto " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new RequestHandler(clientSocket).handle();
            }
        } catch (IOException e) {
            System.err.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}