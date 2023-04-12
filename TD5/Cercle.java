public class Cercle extends Forme {

    protected final int rayon;

    public Cercle(final int aX, final int aY, final int rayon) {
        super(aX, aY);
        this.rayon = rayon;
    }

    @Override
    public double perimetre() {
        return 2 * Math.PI * rayon;
    }

    @Override
    public double surface() {
        return Math.PI * rayon * rayon;
    }

}
