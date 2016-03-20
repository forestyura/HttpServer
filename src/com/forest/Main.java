package com.forest;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String args[]) {
        try {
            ServerSocket server = new ServerSocket(8080);
            while (true) {
                new ServerConnector(server.accept()).start();
            }
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}
