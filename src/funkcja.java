import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import java.util.Scanner;

public class funkcja {
    //tu całość obsługi funkcji
    Expression f;
    Argument[] x;
    funkcja(int ile){
        Scanner in=new Scanner(System.in);
        x=new Argument[ile];
        for(int i=0;i<ile;i++){
            x[i]=new Argument("x"+(i+1));
            System.out.println(x[i].getArgumentName());
        }
        System.out.println("podaj funkcję zmienne zaczynają się od x i mają numery od 1");
        String cialo=in.next();
        f=new Expression(cialo,x);
    }
    double evalute(double[] x0){
        for (int i=0;i<x.length;i++) {
            x[i].setArgumentValue(x0[i]);
        }
        return f.calculate();
    }

}
