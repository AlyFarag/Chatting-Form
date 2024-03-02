package TCP_server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TCP_Server {

    public static void main(String[] args) {

        try (
                //The will server listen on port 3000 for incoming data
                ServerSocket serverSocket = new ServerSocket(3000)) {
                System.out.println("Server listening on port 3000...");

                while (true) {
                
                try (
                       //Accept the connection from the client and get the sent data  
                        Socket socket = serverSocket.accept(); 
                        Scanner fromClient = new Scanner(socket.getInputStream()); 
                        PrintWriter fromServer = new PrintWriter(socket.getOutputStream())) {

                    // Read data from the client and print it line by line 
                    String inputMsg;
                    while (fromClient.hasNextLine()) {
                        inputMsg = fromClient.nextLine();
                        System.out.println("Client : " + inputMsg.toUpperCase());
                    }

                    fromServer.println("Server received your message.");
                    fromServer.flush();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

