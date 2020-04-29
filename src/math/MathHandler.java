package math;

import echo.RequestHandler;

import java.io.StringReader;
import java.util.*;

public class MathHandler extends RequestHandler {
    public MathHandler() {
        super();
        init();
    }



    private void init(){
        super.welcomeMessage = "Welcome to Math Handler! \n Enter commmands by operator num1 num2";
        Operation add = (double a, double b) -> a+b;
        operationsMap.put("add",add);
        Operation sub = (double a, double b) -> a-b;
        operationsMap.put("sub",sub);
        Operation mul = (double a, double b) -> a*b;
        operationsMap.put("mul",mul);
        Operation div = (double a, double b) -> a/b;
        operationsMap.put("div",div);
    }

    interface Operation{
        double operate(double a, double b);
    }

    HashMap<String,Operation> operationsMap = new HashMap<>();

    @Override
    protected String response(String msg) throws Exception {
        Scanner sc = new Scanner(msg);
        String operation;
        double arg1,arg2;

        if(sc.hasNext() && operationsMap.containsKey(operation=sc.next())) ;
        else return "error: Invalid operator";

        if(sc.hasNextDouble())  arg1 = sc.nextDouble();
        else return "error: Invalid number = ";
        if(sc.hasNextDouble()) arg2 = sc.nextDouble();
        else return "error: Invalid number = ";

        return Double.toString(operationsMap.get(operation).operate(arg1,arg2));

    }

}
