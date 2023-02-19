public class Calc {

    public void expressions() {
        System.out.println(2+3/4);
        System.out.println(2.0+3/4);
        System.out.println(2+30E-1/4);
        System.out.println(11%4);
        System.out.println(Math.cos(Math.PI/4) == Math.sin(Math.PI/4));
    }

    public double racNeg(final double reel) {
        if(reel < 0)
            return -Math.sqrt(-reel);
        return Math.sqrt(reel);
    }

    public void afficheMoities(final int entier) {
        int dernier = entier;
        dernier/=2;
        while(true) {
            System.out.println(dernier);
            dernier/=2;
            if(dernier == 1) break;
        }
    }

    public boolean sontProches(final double reel1, final double reel2) {
        return Math.abs(reel1-reel2) <= 10e-9;
    }

}
