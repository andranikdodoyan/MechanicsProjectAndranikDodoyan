import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task2 {

    private static Random random = new Random();



    public static Pair2D generateInitialPoint() {
        return Task1.generateInitialPoint();
    }

    public static Pair2D generateInitialMomentum() {
        double px, py;
        double first = Math.pow(-1, random.nextInt(2)) * (5 + 5 * random.nextDouble());
        double upperForPy = Math.sqrt(100 - Math.pow(first, 2));
        double lowerForPy = 25 - Math.pow(first, 2) < 0 ? 0 : Math.sqrt(25 - Math.pow(first, 2));
        double second = Math.pow(-1, random.nextInt(2)) * (lowerForPy + (upperForPy - lowerForPy) * random.nextDouble());
        if(random.nextInt(2) == 1) {
            px = first;
            py = second;
        } else {
            px = second;
            py = first;
        }
        return new Pair2D(px, py);
    }

    public static Pair2D generateNextPosition(Pair2D initialPosition, Pair2D initialMotion) {
        double x1 = initialPosition.getX();
        double y1 = initialPosition.getY();

        //calculate coordinates of vector taking into consideration that the initial points should be (x1,y1)
        //and y should be reduced by 10 because of gravitation
        double x2 = initialMotion.getX();
        double y2 = initialMotion.getY() - 10;

        //need to solve ax^2+bx+c = y and x^2+y^2 = 1. Intersection points are approximated
        double a = (y2 - y1)/(2*(x2-x1));
        double b = y1 - (2*a*x1);
        double c = y1 - (a*Math.pow(x1,2)) - (b*x1);

        double x=-1;
        double y=-1;
        if(x2 > x1) {
            for(x = x1; x <= 1; x+=0.01) {
                y = a*Math.pow(x,2)+(b*x)+c;
                if(x*x+y*y > 1){
                    x -= 0.01;
                    y = a*Math.pow(x,2)+(b*x)+c;
                    break;
                }
            }
        } else {
            for(x = x1; x>=-1; x-=0.01) {
                y = a*Math.pow(x,2)+(b*x)+c;
                if(x*x+y*y > 1){
                    x += 0.01;
                    y = a*Math.pow(x,2)+(b*x)+c;
                    break;
                }
            }
        }
        return new Pair2D(x,y);

    }

    public static Pair2D calculateNextMomentum(Pair2D position, Pair2D momentum) {
        return Task1.calculateNextMomentum(position, momentum);
    }

    public static List<Pair2D> testBilliardParabola(int n, Pair2D initPosition, Pair2D initMomentum) {
        List<Pair2D> positions = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            Pair2D nextPosition = generateNextPosition(initPosition, initMomentum);
            Pair2D nextMomentum = calculateNextMomentum(nextPosition, initMomentum);
            positions.add(nextPosition);
            initPosition = nextPosition;
            initMomentum = nextMomentum;
        }
        return positions;
    }

    public static List<List<Pair2D>> testReversed(int n) {
        Pair2D initPosition = generateInitialPoint();
        Pair2D initMomentum = generateInitialMomentum();
        List<Pair2D> positions = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            Pair2D nextPosition = generateNextPosition(initPosition, initMomentum);
            Pair2D nextMomentum = calculateNextMomentum(nextPosition, initMomentum);
            positions.add(nextPosition);
            initPosition = nextPosition;
            initMomentum = nextMomentum;
        }
        initMomentum = new Pair2D(-initMomentum.getX(), -initMomentum.getY());
        List<Pair2D> positionsReversed = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            Pair2D nextPosition = generateNextPosition(initPosition, initMomentum);
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

    public static String findMinNumberOfIterationsReversed(double delta) {
        int n = -1;
        List<List<Pair2D>> result = testReversed(100);
        double[] differences = new double[result.get(0).size()];
        for(int i=0; i < result.get(0).size(); i++) {
            differences[i] = Math.sqrt(Math.pow(result.get(1).get(i).getX() - result.get(0).get(i).getX(), 2) +
                    Math.pow(result.get(1).get(i).getY() - result.get(0).get(i).getY(), 2));
            System.out.println(differences[i]);
            if(differences[i] > delta) {
                n = i;
                break;
            }
        }
        return "After n numbers of refelections, n="+(n+1);
    }

    public static void runNtimes() {
        Pair2D initPosition = generateInitialPoint();
        Pair2D initMomentum = generateInitialMomentum();
        int[] iterations = new int[]{10, 100, 1000};
        for (int i = 0; i < iterations.length; i++) {
            System.out.println("Number of n = " + iterations[i]);
            List<Pair2D> pos = testBilliardParabola(iterations[i], initPosition, initMomentum);
            Task1.printPositions(pos);
        }
    }



    public static void main(String[] args) {
        runNtimes();
        System.out.println(findMinNumberOfIterationsReversed(0.000001));

    }


}
