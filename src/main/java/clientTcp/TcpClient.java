package clientTcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TcpClient {

    public TcpClient() {
    }

    private void connect(String host, int port) throws InterruptedException {
        Socket socket = null;
      final  Writer out = null;
     final   BufferedReader in = null;
ScheduledExecutorService exec=Executors.newSingleThreadScheduledExecutor();
try {
	System.out.print("Connecting to " + host + " on port " + port + " ... ");
    socket = new Socket(host, port);
    System.out.println("connected.");
exec.scheduleAtFixedRate(new Runnable() {
	
	public void run() {
		Socket socket=null;
		   Writer out = null;
	        BufferedReader in = null;
		 try {
				 socket = new Socket("localhost", 5678);
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			 in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			 	
			 	int idSegment=13;
			 	int idChaine=6;
			 	int  etatArret = 0;
			 	int tArret=18;
			 	int type=0;
			 	int qte=(int) (Math.random()*200000);
			 	int bla =(int) (Math.random()*10);
			 	String post="45";
			 	/*if(bla%2==0) {
			 		post="PM";
			 	}else {
			 		post="PA";
			 	}*/
			 	
			     String msg =idSegment+","+post+","+idChaine+","+etatArret+","+tArret+","+qte+","+type+";";
System.out.println("envoie de trame: "+msg);
			     out.write(msg+"\r\n");
			     out.flush();
			    
			     Thread.sleep(1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
}, 0, 1, TimeUnit.MINUTES);
     
            
            


           
            


        } catch (IOException e) {

            System.err.println("Test ended with an exception: " + port + ", " + e.getMessage());

        } finally {
            try {
                socket.close();
                out.close();
                //in.close();

            } catch (Exception e) {
                // swallow exception
            }

        }       

    }

    public static void main(String[] args) throws InterruptedException {

        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new TcpClient().connect(host, port);
    }
}

