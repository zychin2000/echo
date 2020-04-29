package echo;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.io.*;
import java.net.*;

public class Server {

	protected ServerSocket mySocket;
	protected int myPort;
	public static boolean DEBUG = true;
	protected Class<?> handlerType;

	public Server(int port, String handlerType) {
		try {
			myPort = port;
			mySocket = new ServerSocket(myPort);
			this.handlerType = (Class.forName(handlerType));
		} catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		} // catch
	}


	public void listen() {
		while(true) {
			try {
				System.out.println("Currently listening on " + myPort);
				// accept a connection
				Socket socket = mySocket.accept();
				System.out.println("Accepted connection with "+ socket);
				// make handler
				RequestHandler handler = makeHandler(socket);
				// start handler in its own thread
				Thread newThread = new Thread(handler);

				newThread.start();

			} catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}


		} // while
	}

	public RequestHandler makeHandler(Socket s) {
		// handler = a new instance of handlerType
		//    use: try { handlerType.getDeclaredConstructor().newInstance() } catch ...
		// set handler's socket to s
		// return handler
		try {
			RequestHandler handler = (RequestHandler) handlerType.getDeclaredConstructor().newInstance();
			handler.setSocket(s);
			return handler;
		} catch(Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			System.exit(1);
		} // catch

		return null;
	}



	public static void main(String[] args) {
		int port = 5555;
		String service = "echo.RequestHandler";
		if (1 <= args.length) {
			service = args[0];
		}
		if (2 <= args.length) {
			port = Integer.parseInt(args[1]);
		}
		Server server = new Server(port, service);
		server.listen();
	}
}
