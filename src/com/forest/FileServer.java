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
            writer.write("HTTP/1.1 200 OK\r\n" +
                    "Server: JavaServer\r\n" +
                    "Content-Type: text \r\n" +
                    "Connection: close\r\n\r\n" +
                    "<html>" +
                    "<body>" +
                    "<h1>" +file.getName()+ " is Directory !</h1>" +
                    "<h2> File in directory:<h2>" +
                    Arrays.toString(file.list())+
                    "</body>" +
                    "</html>");
            writer.flush();
        }
    }
}
