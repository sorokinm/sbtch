package ru.mephi.sbertech.serverclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    static String host = "127.0.0.1";
    static int port = 7866;
    static Socket echoSocket = null;
    static PrintWriter out = null;
    static BufferedReader in = null;

    public static void main(String args[]) {
        try {

            System.out.println("Connecting to host " + host + " on port " + port + ".");

            try {
                echoSocket = new Socket(host, port);
                out = new PrintWriter(echoSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            } catch (UnknownHostException e) {
                System.err.println("Unknown host: " + host);
                System.exit(1);
            } catch (IOException e) {
                System.err.println("Unable to get streams from server");
                System.exit(1);
            }

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println(in.readLine());

            while (true) {
                System.out.print("client: ");
                String userInput = stdIn.readLine();
                /** Exit on 'q' char sent */
                if ("q".equals(userInput)) {
                    break;
                }
                out.println(userInput);
                try {
                    int lineNumber = Integer.parseInt(in.readLine());
                    System.out.println("server: ");
                    if (lineNumber == 0) {
                        System.out.println("Done");
                    }
                    for (int i = 0; i < lineNumber; ++i) {
                        System.out.println(in.readLine());
                    }
                } catch (Exception e) {
                    System.out.println("error: WRONG FORMAT!!!");
                }
            }

            /** Closing all the resources */
            out.close();
            in.close();
            stdIn.close();
            echoSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}