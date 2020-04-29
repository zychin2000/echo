package echo;

import java.io.*;
import java.net.*;

public class Correspondent {
	protected Socket sock;
	protected BufferedReader sockIn;
	protected PrintWriter sockOut;

	public Correspondent() { } // init fields later
	public Correspondent(Socket s) {
		sock = s;
		initStreams();
	}
	
	public void setSocket(Socket socket) {
		this.sock = socket;
		initStreams();
	}

	protected void initStreams() {
		try {
			sockIn = 
					new BufferedReader(
					new InputStreamReader(
							sock.getInputStream()));
			sockOut = new PrintWriter(
					sock.getOutputStream(), true);
		} catch(IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	protected void close() {
		try {
			sock.close();
		} catch(IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void requestConnection(String host, int port) {
		try {
			sock = new Socket(host, port);
			initStreams();
		} catch(UnknownHostException uhe) {
			System.err.println("unknown host " + uhe);
			System.exit(1);
		} catch(IOException ioe) {
			System.err.println("failed to create streams " + ioe);
			System.exit(1);
		}
	}

	public void send(String request) {
		sockOut.println(request);
	}

	public String receive() {
		String msg = null;
		try {
			msg = sockIn.readLine();
		} catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();

			//commented the code as to prevent the whole server from crashing when client disconnects
			//System.exit(1);
		}
		return msg;
	}
} // Correspondent

