package cache;

import echo.ProxyHandler;

import java.util.HashMap;
import java.util.HashSet;

public class CacheHandler extends ProxyHandler {
    @Override
    protected String response(String msg) throws Exception {

        Cache cache = Cache.getCache();

        if(cache.containsKey(msg)){
            return cache.search(msg);
        }
        else{
            peer.send(msg);
            String output = peer.receive();

            cache.update(msg,output);

            return output;
        }

    }


}
