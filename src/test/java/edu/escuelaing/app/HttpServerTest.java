package edu.escuelaing.app;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

class HttpServerTest {

    @Test
    void testServerStarts() {
        try (ServerSocket serverSocket = new ServerSocket(35000)) {
            assertFalse(serverSocket.isClosed());
        } catch (IOException e) {
            fail("El servidor no pudo iniciar en el puerto 35000.");
        }
    }
}