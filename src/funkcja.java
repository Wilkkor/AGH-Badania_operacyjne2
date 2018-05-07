import java.util.Scanner;

public class funkcja {
    //tu całość obsługi funkcji
    double wspolczynniki[][];
    funkcja(int ile){
        Scanner in=new Scanner(System.in);
        System.out.println("jezeli wielomian to podaj ile  wspolczynnikow i nastepnie wspolczynniki poszczególnych zmiennych");
        int ile2=in.nextInt()+1;
        wspolczynniki=new double[ile][ile2];
        for(int i=0;i<ile;i++){
            wspolczynniki[i]=new double[ile2];
            for(int j=0;j<ile2-1;j++){
                System.out.printf("podaj %d. wsp. %d. zmiennej",j+1,i+1);
                wspolczynniki[i][j]=in.nextDouble();
            }
            wspolczynniki[i][wspolczynniki[i].length-1]=0;
        }
    }
    double evalute(double[] x){
        double[] sum=new double[x.length];
        double suum=0;
        if(wspolczynniki.length==x.length){
            for(int i=0;i<wspolczynniki.length;i++){
                sum[i]=0;
                for(int j=0;j<wspolczynniki[i].length;j++) {
                    sum[i] = sum[i] * x[i] + wspolczynniki[i][j];
                }
            }
            for (double s:sum) {
                suum+=s;
            }
            return suum;
        }else{
            System.err.println("f. niezainicjalizowana lub niezgodne wielkości wektorów wsp. i x");
            return 0;
        }
    }

}
