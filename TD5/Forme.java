public class Forme implements Comparable<Forme> {

    protected final int aX, aY;

    public Forme(int aX, int aY) {
        this.aX = aX;
        this.aY = aY;
    }

    public double perimetre() {
        return 2 * (aX + aY);
    }

    public double surface() {
        return aX * aY;
    }

    public double calcRatio() {
        return perimetre() / surface();
    }

    public int compareTo(final Forme forme) {
        if (forme == this)
            return 0;
        else
            return 99;
    }

}