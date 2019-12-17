package clientTcp;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client2 {

	static Socket socket;
    static List<Socket> sl = new ArrayList<Socket>();
    static DataOutputStream out;

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 2; i++) {
            socket = new Socket("localhost", 5678);
            sl.add(socket);
            out = new DataOutputStream(socket.getOutputStream());
            		String smg="1,2,pm,7,17,1220;";
            out.writeBytes(smg);
        }
        System.in.read();
    }

}
