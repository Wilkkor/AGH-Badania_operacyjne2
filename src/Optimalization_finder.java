import java.util.Random;
import java.util.Scanner;

public class Optimalization_finder {
    funkcja_ograniczenia[] warunki;
    int typ_celu;
    funkcja cel;
    int zmienne;

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        double[] x0;
        double[] prev_max;
        double [][]punkty;
        double[] odl;
        Optimalization_finder problem=new Optimalization_finder();
        problem.create_problem();
        x0=new double[problem.zmienne];
        for (double x:x0) {
            x=0;
        }
        punkty=new double[(int)Math.pow(100,problem.zmienne)][];
        System.out.println("podaj zakres odległości od punktu zerowego");
        double zakres=in.nextDouble();
        int przeszlo_poza_x0=0;
        do {
            for (int i = 0; i < (int) Math.pow(100, problem.zmienne); i++) {
                punkty[i] = single_shot(problem, zakres, x0);
            }
            if(problem.typ_celu==0) {
                prev_max = x0;
                x0 = find_max(punkty, problem, x0);
                odl = find_20_max(punkty, problem, x0);
            }
            else {
                prev_max = x0;
                x0 = find_min(punkty, problem, x0);
                odl = find_20_min(punkty, problem, x0);
            }
            zakres=0;
            for(int i=0;i<x0.length;i++){
                zakres+=Math.pow(x0[i]-odl[i],2);
            }
            zakres=Math.pow(zakres,1/x0.length);
            przeszlo_poza_x0++;
            System.out.println(przeszlo_poza_x0);
            for (double x:x0) {
                System.out.printf("%f ; ",x);
            }
            System.out.println();
        }while ((Math.abs(problem.cel.evalute(prev_max)-problem.cel.evalute(x0))>0.0001||przeszlo_poza_x0<2)&&przeszlo_poza_x0<100);
        System.out.println();
        for (double x:x0) {
            System.out.printf("%f ; ",x);
        }
        System.out.println();

    }

    void create_problem(){
        Scanner in=new Scanner(System.in);
        System.out.println("podaj ile warunków i ile wymiarów (zmiennych)");
        int ile_warunkow=in.nextInt();
        zmienne=in.nextInt();
        int war;
        double wartwar;
        warunki=new funkcja_ograniczenia[ile_warunkow];
        for(int i=0;i<ile_warunkow;i++){
            System.out.println("warunek:");
            System.out.println("podaj warunek : 0-'<' 1-'<=' 2-'==' 3-'>' 4-'>='");
            war=in.nextInt();
            System.out.println("podaj wartość do porównania");
            wartwar=in.nextDouble();
            warunki[i]=new funkcja_ograniczenia(wartwar,war,zmienne);
        }
        System.out.println("funkcja celu :");
        System.out.println("podaj typ f. celu 0 max, inne min");
        typ_celu=in.nextInt();
        cel=new funkcja(zmienne);
    }

    private static double[] find_max(double[][] punkty, Optimalization_finder problem, double[] x0) {
        boolean r;
        double[] max=null;
        r=true;
        for(funkcja_ograniczenia fun:problem.warunki){
            if(!fun.sprawdz(x0)){
                r=false;
                break;
            }
        }
        if(r){
            max=x0;
        }
        double max_value;
        for(double[] x:punkty){
            r=true;
            for(funkcja_ograniczenia fun:problem.warunki){
                if(!fun.sprawdz(x)){
                    r=false;
                    break;
                }
            }
            if(r&&max==null){
                max=x;
            }else if(r&&problem.cel.evalute(x)>problem.cel.evalute(max)){
                max=x;
            }
        }
        return max;
    }

    private static double[] find_min(double[][] punkty, Optimalization_finder problem, double[] x0) {
        boolean r;
        double[] min=null;
        r=true;
        for(funkcja_ograniczenia fun:problem.warunki){
            if(!fun.sprawdz(x0)){
                r=false;
                break;
            }
        }
        if(r){
            min=x0;
        }
        double max_value;
        for(double[] x:punkty){
            r=true;
            for(funkcja_ograniczenia fun:problem.warunki){
                if(!fun.sprawdz(x)){
                    r=false;
                    break;
                }
            }
            if(r&&min==null){
                min=x;
            }else if(r&&problem.cel.evalute(x)<problem.cel.evalute(min)){
                min=x;
            }
        }
        return min;
    }

    private static double[] find_20_max(double[][] punkty, Optimalization_finder problem, double[] x0) {
        boolean r;
        double [][] maxy=new double[(int)Math.pow(3,problem.zmienne)][];
        for (int i=0;i<maxy.length;i++) {
            maxy[i]=new double[0];
        }
        double max_value;
        for(double[] x:punkty){
            r=true;
            for(funkcja_ograniczenia fun:problem.warunki){
                if(!fun.sprawdz(x)){
                    r=false;
                    break;
                }
            }
            if(r){
                int i=0;
                while(i<maxy.length&&maxy[i].length>0&&problem.cel.evalute(maxy[i])>problem.cel.evalute(x)){
                    i++;
                }
                for(int j=maxy.length-1;j>i;j--){
                    maxy[j]=maxy[j-1];
                }
                if(i<maxy.length){
                    maxy[i]=x;
                }
            }
        }
        return maxy[maxy.length-1];
    }

    private static double[] find_20_min(double[][] punkty, Optimalization_finder problem, double[] x0) {
        boolean r;
        double [][] maxy=new double[(int)Math.pow(3,problem.zmienne)][];
        for (int i=0;i<maxy.length;i++) {
            maxy[i]=new double[0];
        }
        double max_value;
        for(double[] x:punkty){
            r=true;
            for(funkcja_ograniczenia fun:problem.warunki){
                if(!fun.sprawdz(x)){
                    r=false;
                    break;
                }
            }
            if(r){
                int i=0;
                while(i<maxy.length&&maxy[i].length>0&&problem.cel.evalute(maxy[i])<problem.cel.evalute(x)){
                    i++;
                }
                for(int j=maxy.length-1;j>i;j--){
                    maxy[j]=maxy[j-1];
                }
                if(i<maxy.length){
                    maxy[i]=x;
                }
            }
        }
        return maxy[maxy.length-1];
    }

    private static double[] single_shot(Optimalization_finder problem, double zakres, double[] x0) {
        Scanner in=new Scanner(System.in);
        double[] wynik=new double[problem.zmienne];
        Random random=new Random();
        //random.nextDouble()*zakres;
        for(int i=0;i<problem.zmienne;i++){
            wynik[i]=random.nextDouble()*2*zakres+x0[i]-zakres;
        }
        return wynik;
    }
}
