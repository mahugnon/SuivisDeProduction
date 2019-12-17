package clientTcp;

import java.io.*;
import java.net.*;

class Client {
	public static void main(String args[]) {
		Writer out = null;
		Socket skt = null;
		int port = 5678;
		try {
			skt = new Socket("127.0.0.1", port);
			BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
			OutputStream os = skt.getOutputStream();
			out = new BufferedWriter(new OutputStreamWriter(skt.getOutputStream()));

			out.write("Bonjour Server");
			out.flush();

			while (!in.ready()) {
			}
			System.out.println(in.readLine()); // Read one line and output it

			System.out.print("'\n");
			in.close();
		} catch (IOException e) {

			System.err.println("Test ended with an exception: " + port + ", " + e.getMessage());

		} finally {
			try {
				skt.close();
				out.close();
				// in.close();

			} catch (Exception e) {
				// swallow exception
			}
		}
	}
}