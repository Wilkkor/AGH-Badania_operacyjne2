import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;
import org.mariuszgromada.math.mxparser.mXparser;

public class test {
    Argument[] x = {new Argument("x1",1),new Argument("x2",1),new Argument("x3",1),new Argument("x4",1)};
    Expression a=new Expression("x4*( sin(x1+x2)-cos(x2/x3) )", x[0], x[1],x[2],x[3]);

    public static void main(String[] args) {
        test b=new test();


        mXparser.consolePrintln("Res 1: " + b.a.getExpressionString() + " = " + b.a.calculate());
        System.out.println();
        System.out.println(b.a.calculate());
    }
}
