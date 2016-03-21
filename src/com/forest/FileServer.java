package com.forest;

import java.io.*;
import java.util.Arrays;

public class FileServer {
    private File file;
    private PrintWriter writer;

    public void getFile(String fileAdress, OutputStream outputStream) {
        file = new File("content/"+fileAdress);
        writer = new PrintWriter(outputStream);
        if (file.isFile()) {
            try {
                byte[] buffer = new byte[(int) file.length()];
                FileInputStream fileReader = new FileInputStream(file);
                BufferedInputStream bufFileReader = new BufferedInputStream(fileReader);
                bufFileReader.read(buffer, 0, buffer.length);
                outputStream.write(buffer, 0, buffer.length);
                outputStream.flush();
                System.out.println("Sending file: " + file.getName());
            }
            catch (IOException ex) {
                System.out.println(ex);
            }
        }
        if (file.isDirectory()){
            ServerConnector.htmlResponse("<h1>" +
                    file.getName() +
                    " is Directory !</h1>" +
                    "<h2> File in directory:<h2>" +
                    Arrays.toString(file.list()), writer);
        }
        if (!file.canRead()) {
            ServerConnector.htmlResponse( "<h1>" +file.getName()+ " file not found !</h1>", writer);
        }
    }
}
