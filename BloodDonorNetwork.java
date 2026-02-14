import java.io.*;
import java.net.*;

public class BloodBankServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(5000);
        System.out.println("Server Started...");

        Socket s = ss.accept();
        System.out.println("Client Connected");

        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        String request = dis.readUTF();
        System.out.println("Client Request: " + request);

        dos.writeUTF("Blood Group A+ Available: 10 Units");

        dis.close();
        dos.close();
        s.close();
        ss.close();
    }
}
import java.io.*;
import java.net.*;

public class BloodBankClient {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 5000);

        DataOutputStream dos = new DataOutputStream(s.getOutputStream());
        DataInputStream dis = new DataInputStream(s.getInputStream());

        dos.writeUTF("Request for A+ Blood");

        String response = dis.readUTF();
        System.out.println("Server Response: " + response);

        dis.close();
        dos.close();
        s.close();
    }
}