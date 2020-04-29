package cache;

import java.util.HashMap;

public class Cache extends HashMap<String,String> {

    private final static Cache singleInstance = new Cache();

    static Cache getCache(){
        return singleInstance;
    }

    synchronized String search(String request){
        return super.get(request);
    }

    synchronized String update(String request, String response){
        return super.put(request,response);
    }

    @Override
    public synchronized boolean containsKey(Object key) {
        return super.containsKey(key);
    }
}
