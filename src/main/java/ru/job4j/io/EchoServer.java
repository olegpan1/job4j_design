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
            while (!server.isClosed()) {
                System.out.println("Server open");
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String str = in.readLine(); str != null && !str.isEmpty(); str = in.readLine()) {
                        System.out.println(str);
                        if (str.contains("msg=Exit")) {
                            out.flush();
                            System.out.println("Server close");
                            server.close();
                            break;
                        }
                        if (str.contains("msg=Hello")) {
                            out.write("Hello, dear friend!".getBytes());
                        } else {
                            if (str.contains("msg=")) {
                                out.write("What".getBytes());
                            }
                        }

                    }
                    out.flush();
                }
            }
        }
    }
}