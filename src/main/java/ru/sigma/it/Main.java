package ru.sigma.it;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket();
        socket.bind(null);
        socket.connect(new InetSocketAddress("www.silicon.co.uk", 80));
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        InputStream inputStream = socket.getInputStream();
        byte[] buffer = new byte[1024];
        int read;

        printWriter.print("GET / HTTP/1.1\r\n");
        printWriter.print("Host: www.silicon.co.uk\r\n");
        printWriter.print("Connection: keep-alive\r\n");
        printWriter.print("\r\n");
        printWriter.flush();


        printWriter.print("GET / HTTP/1.1\r\n");
        printWriter.print("Host: www.silicon.co.uk\r\n");
        printWriter.print("Connection: keep-alive\r\n");
        printWriter.print("\r\n");
        printWriter.flush();


        while ((read = inputStream.read(buffer)) != -1) {
            String output = new String(buffer, 0, read);
            System.out.print(output);
            System.out.flush();
        }

        int sendBufferSize = socket.getSendBufferSize();
        int receiveBufferSize = socket.getReceiveBufferSize();


        System.out.println(sendBufferSize);
        System.out.println(receiveBufferSize);

    }
}
