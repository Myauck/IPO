public class Utilisation {

    public static void essai() {
        Cercle cercle = new Cercle(10, 5, 5);

        System.out.println(cercle.perimetre());
        System.out.println(cercle.surface());
        System.out.println(cercle.calcRatio());
    }

    public static void main(String[] args) throws Exception {
        essai();
    }

}