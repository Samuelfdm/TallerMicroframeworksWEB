package edu.escuelaing.app;

import java.io.*;
import java.net.Socket;

public class RequestHandler {
    private final Socket clientSocket;

    public RequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void handle() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             OutputStream out = clientSocket.getOutputStream();
             PrintWriter writer = new PrintWriter(out, true)) {

            String requestLine = in.readLine();
            if (requestLine == null) return;

            System.out.println("Received: " + requestLine);
            String[] requestParts = requestLine.split(" ");
            String method = requestParts[0];
            String path = requestParts[1];

            Request req = new Request();
            Response res = new Response();

            if (path.contains("?")) {
                String[] pathAndQuery = path.split("\\?");
                path = pathAndQuery[0];
                req.setQueryParams(pathAndQuery[1]);
            }

            String response = Router.handleRequest(path, req, res);
            if (response != null) {
                ResponseHelper.sendJsonResponse(writer, response);
            } else {
                StaticFileHandler.serve(path, out, writer);
            }
        } catch (IOException e) {
            System.err.println("Error procesando la solicitud: " + e.getMessage());
        }
    }
}