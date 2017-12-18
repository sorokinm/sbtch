package ru.mephi.sbertech.serverclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class SocketServer {

    public static void main(String[] args) throws Exception {
        System.out.println("The server is running.");
        int clientNumber = 0;
        ServerSocket listener = new ServerSocket(7866);
        try {
            while (true) {
                new DirChecker(listener.accept(), clientNumber++).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class DirChecker extends Thread {
        private Socket socket;
        private int clientNumber;
        // String currentDirStr = "/home/";
        BufferedReader in = null;
        PrintWriter out = null;


        public DirChecker(Socket socket, int clientNumber) {
            this.socket = socket;
            this.clientNumber = clientNumber;
            log("New connection with client# " + clientNumber + " at " + socket);
        }

        public void run() {
            try {

                this.in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));
                this.out = new PrintWriter(socket.getOutputStream(), true);

                // Send a welcome message to the client.
                out.println("Hello, you are client #" + clientNumber + ".");

                // Get messages from the client, line by line;
                while (true) {
                    String input = in.readLine();
                    if (input == null || input.equals(".")) {
                        break;
                    }
                    if (input.startsWith("pwd")){
                        pwd();
                    } else if (input.startsWith("ls")) {
                        ls();
                    } else if (input.startsWith("cd")) {
                        input = input.substring(2).trim();
                        this.out.println(1);
                        this.out.println(input);
                    }
                }
            } catch (IOException e) {
                log("Error handling client# " + clientNumber + ": " + e);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    log("Couldn't close a socket, what's going on?");
                }
                log("Connection with client# " + clientNumber + " closed");
            }
        }

        private void log(String message) {
            System.out.println(message);
        }

        private void ls() {
            File dir = new File(System.getProperty("user.dir"));
            String childs[] = dir.list();
            this.out.println(childs.length);
            this.out.println(String.join("\n", childs));
        }

        private void pwd() {
            String pwd = System.getProperty("user.dir");
            this.out.println(1);
            this.out.println(pwd);
        }

        private void cd(String path) {
            File dir = new File(path);
            if(dir.isDirectory()==true) {
                System.setProperty("user.dir", dir.getAbsolutePath());
            } else {
                System.out.println(path + " is not a directory.");
            }
        }
    }
}