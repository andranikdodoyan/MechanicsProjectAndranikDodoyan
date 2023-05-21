import java.util.Random;

public class Task2 {

    private static Random random = new Random();


    public static Pair2D generateInitialPoint() {
        return Task1.generateInitialPoint();
    }

    public static Pair2D generateInitialMomentum() {
        double px, py;
        double[] twoRandom
        px = Math.pow(-1, random.nextInt(2)) * (7.5 + 2.5 * random.nextDouble());
        double upperBound = 100 - Math.pow(px, 2);
        double lowerBound = 25 - Math.pow(px, 2);
        double mid = ((upperBound + lowerBound) / 2);
        py = Math.pow(-1, random.nextInt(2)) * (mid + (upperBound - mid) * random.nextDouble());
        return new Pair2D(px, py);
    }

    public static void main(String[] args) {
        for(int i = 1; i < 5; i++) {
            Pair2D a= generateInitialMomentum();
            double x = a.getX();
            double y = a.getY();
            System.out.println(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
        }
    }


}
