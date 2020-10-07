package ru.job4j.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            boolean serverOperation = true;
            while (serverOperation) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String str = in.readLine();
                    String str1 = "";
                    while (!(str).isEmpty()) {
                        System.out.println(str);
                        if (str.contains("GET")) {
                            str1 = str;
                        }
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    if (str1.contains("/?msg=Exit")) {
                        serverOperation = false;
                    } else if (str1.contains("/?msg=Hello")) {
                        out.write("Hello\r\n\\".getBytes());
                    } else {
                        out.write(str1.substring(10, str1.lastIndexOf("HTTP/1.1")).getBytes());
                    }
                }
            }
        }
    }
}