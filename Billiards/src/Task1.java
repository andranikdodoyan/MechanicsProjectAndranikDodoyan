import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task1 {
    private static Random random = new Random();

    public static Pair2D generateInitialPoint() {
        double x = -1 + (random.nextDouble() * 2);
        double y = -1 + (random.nextDouble() * 2);
        if(x*x+y*y > 1) {
            return generateInitialPoint();
        } else {
            return new Pair2D(x,y);
        }
    }

    public static Pair2D generateInitialMomentum() {
        double px = -1 + (random.nextDouble() * 2);
        double py = Math.sqrt(1 - px*px);
        return new Pair2D(px, py);
    }

    public static Pair2D calculateNextPosition(Pair2D initialPosition, Pair2D momentum) {
        double x = initialPosition.getX();
        double y = initialPosition.getY();
        double px = momentum.getX();
        double py = momentum.getY();
        double k = py/px;

        // y = kx + b
        double b = y - (k * x);

        // solve y^2 + x^2 = 1 and y = kx+b to find intersections
        double discriminant = 4 * Math.pow(k*b, 2) - 4 * ((1+Math.pow(k, 2)) * (Math.pow(b, 2) - 1));
        double x_1 = (-2 * k * b - Math.sqrt(discriminant)) / (2 * (1 + Math.pow(k,2)));
        double x_2 = (-2 * k * b + Math.sqrt(discriminant)) / (2 * (1 + Math.pow(k,2)));
        double next_x, next_y;
        if(px < 0) {
            next_x = Math.min(x_1, x_2);
        } else {
            next_x = Math.max(x_1, x_2);
        }
        next_y = k * next_x + b;


        return new Pair2D(next_x, next_y);
    }

    public static Pair2D calculateNextMomentum(Pair2D position, Pair2D momentum) {
        double x = position.getX();
        double y = position.getY();
        double px = momentum.getX();
        double py = momentum.getY();

        double px_prime = (y*y-x*x)*px - 2 * x * y * py;
        double py_prime = - 2 * x * y * px + (x * x - y * y) * py;

        return new Pair2D(px_prime, py_prime);
    }

    public static List<Pair2D> testBilliard(int n, Pair2D initPosition, Pair2D initMomentum) {
        List<Pair2D> positions = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            Pair2D nextPosition = calculateNextPosition(initPosition, initMomentum);
            Pair2D nextMomentum = calculateNextMomentum(nextPosition, initMomentum);
            positions.add(nextPosition);
            initPosition = nextPosition;
            initMomentum = nextMomentum;
        }
        return positions;
    }
    public static List<List<Pair2D>> testBilliardAlsoReversed(int n, Pair2D initPosition, Pair2D initMomentum) {
        List<Pair2D> positions = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            Pair2D nextPosition = calculateNextPosition(initPosition, initMomentum);
            Pair2D nextMomentum = calculateNextMomentum(nextPosition, initMomentum);
            positions.add(nextPosition);
            initPosition = nextPosition;
            initMomentum = nextMomentum;
        }
        initMomentum = new Pair2D(-initMomentum.getX(), -initMomentum.getY());
        List<Pair2D> positionsReversed = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            Pair2D nextPosition = calculateNextPosition(initPosition, initMomentum);
            Pair2D nextMomentum = calculateNextMomentum(nextPosition, initMomentum);
            positionsReversed.add(nextPosition);
            initPosition = nextPosition;
            initMomentum = nextMomentum;
        }
        List<List<Pair2D>> arrayOfLists = new ArrayList<List<Pair2D>>(2);
        arrayOfLists.add(positions);
        arrayOfLists.add(positionsReversed);
        return arrayOfLists;
    }

    public static void printPositions(List<Pair2D> positions) {
        for(int i = 0; i < positions.size(); i++) {
            System.out.println(positions.get(i));
        }
    }

    public static void runNtimes() {
        Pair2D initPosition = generateInitialPoint();
        Pair2D initMomentum = generateInitialMomentum();
        int[] iterations = new int[]{10, 100, 1000};
        for (int i = 0; i < iterations.length; i++) {
            System.out.println("Number of n = " + iterations[i]);
            List<Pair2D> pos = testBilliard(iterations[i], initPosition, initMomentum);
            printPositions(pos);
        }
    }


    public static void main(String[] args) {
        runNtimes();
        //Paths almost coincided, the difference very small
        Pair2D initPosition = generateInitialPoint();
        Pair2D initMomentum = generateInitialMomentum();
        List<List<Pair2D>> result = testBilliardAlsoReversed(10, initPosition, initMomentum);
        for (int i = 0; i < result.get(0).size(); i++) {
            System.out.println(result.get(0).get(i) + " vs " + result.get(1).get(result.get(0).size() - 1 - i));
        }

    }
}



