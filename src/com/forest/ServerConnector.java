package com.forest;

import java.io.*;
import java.net.Socket;

public class ServerConnector extends Thread {

    private Socket client;
    private BufferedReader reader;
    private PrintWriter writer;
    private String httpRequest;
    private OutputStream outputStream;

    public ServerConnector(Socket client){
        this.client = client;
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outputStream = client.getOutputStream();
            writer = new PrintWriter(new OutputStreamWriter(outputStream));
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public void run(){
        try {
            httpRequest = reader.readLine(); //Get Http Request
            System.out.println(httpRequest);
            String uri = getUri(httpRequest);

            if(uri.equals("/") || uri.equals("index.html")){
                new FileServer().getFile("index.html", outputStream);
            }
            else {
                new FileServer().getFile(uri,outputStream);
            }
            writer.close();
            reader.close();
        }
        catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    //Get Uri from HTTP Request
    public String getUri(String uri) {
        return uri.split(" ", 3)[1];
    }
}
