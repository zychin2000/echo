package echo;

import java.net.Socket;

public class ProxyHandler extends RequestHandler{
    protected Correspondent peer;

    public ProxyHandler(Socket s) { super(s);    }
    public ProxyHandler() { super();}

    public void initPeer(String host, int port) {
        peer = new Correspondent();
        peer.requestConnection(host, port);
    }

    @Override
    protected String response(String msg) throws Exception {

        String preProcessedString =  preProcess(msg);

        //send via correspondent
        peer.send(preProcessedString);

        //receive via correspondent
        preProcessedString = peer.receive();

        return postProcess(preProcessedString);

    }

    //override this method
    String preProcess(String msg){
        return "add " + msg;
    }

    //override this method
    String postProcess(String msg){
        return msg;
    }
}
