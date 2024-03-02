package client;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

public class ChattingClientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter printWriter = response.getWriter();

        //printWriter.println("Hello from servlet Page");

        // Retrieve message and protocol from the form
        String message = request.getParameter("message");
        String protocol = request.getParameter("protocol");

        if (protocol != null && message != null) {

            if (protocol.equalsIgnoreCase("tcp")) {
                //Apply the TCP chatting protocol

                try {
                    Socket socket = new Socket("localhost", 3000);
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(message);
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                //Apply the UDP chatting Prorocol

                try {
                    DatagramSocket socket = new DatagramSocket();
                    byte[] sendData = message.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), 9876);
                    socket.send(sendPacket);
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            //response.getWriter().println("Hello from the serverlet ");
            
            //redirect the servlet to the form
            response.sendRedirect("index.html");
        }
    }

}
