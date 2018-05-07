public class funkcja_ograniczenia extends funkcja{
    double wartosc_warunku;
    int warunek;
    funkcja_ograniczenia(double wartosc,int typ,int ile){
        super(ile);
        wartosc_warunku=wartosc;
        warunek=typ;
    };
    boolean sprawdz(double[] x){
        switch (warunek) {
            case 0: return evalute(x)<wartosc_warunku;
            case 1: return evalute(x)<=wartosc_warunku;
            case 2: return evalute(x)==wartosc_warunku;
            case 3: return evalute(x)>wartosc_warunku;
            case 4: return evalute(x)>=wartosc_warunku;
            default:{
                System.err.println("nieistniejacy typ warunku, istnieja 0-'<' 1-'<=' 2-'==' 3-'>' 4-'>='");
                return false;
            }
        }
    }
}
