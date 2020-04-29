package echo;

import java.io.IOException;
import java.net.Socket;

public class RequestHandler extends Correspondent implements Runnable {

	public RequestHandler(Socket s) { super(s); }
	protected String welcomeMessage = "Welcome to Default Request Handler";
	public RequestHandler() { }
	// override in a subclass:
	protected String response(String msg) throws Exception {
		return "echo: " + msg;
	}
	public void run() {

		/*if(welcomeMessage!=null)
			send(welcomeMessage);*/

		while(true) {
			try {
				// receive request
				String request = receive();
				System.out.println("received: "+ request);

				if(request.equals("quit")) break;
			    // send response
				String response = response(request);

				send(response(request));
				System.out.println("sending: "+ response);
			    // sleep
				Thread.sleep(1);

			} catch(Exception e) {
				send(e.toString() + "... ending session");
				break;
			}

		}
		// close
		close();

	}
}

