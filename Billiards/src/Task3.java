import java.util.Random;

public class Task3 {

    private static Random random = new Random();

    //In my solution the center of the billiard is supposed to be the (0,0) point
    public static Pair2D generateInitialPoint(double L) {
        double x = (-1-(L/2))  + (random.nextDouble() * (2+L));
        double y;
        if(x < -(L/2) || x > (L/2)) {
            double upperBound = Math.sqrt(1 - Math.pow(x, 2));
            double lowerBound = -upperBound;
            y = lowerBound + (upperBound-lowerBound)* random.nextDouble();
        }else {
            y = -1 + random.nextDouble();

        }
        return new Pair2D(x,y);
    }

    public static Pair2D generateInitialMomentum() {
        return Task1.generateInitialMomentum();
    }

    public static Pair2D calculateNextPosition(Pair2D initialPosition, Pair2D momentum, double L) {
        double x = initialPosition.getX();
        double y = initialPosition.getY();
        double px = momentum.getX();
        double py = momentum.getY();
        double k = py/px;

        // y = kx + b
        double b = y - (k * x);

        //intersection with upper or lower bound
        double next_x;
        double next_y;
        if (py > 0) {
            next_x = (1 - b) / k;
        } else {
            next_x = (-1-b) / k;
        }

        if(next_x > L/2 || next_x < -L/2) {
            double discriminant = 4 * Math.pow(k*b, 2) - 4 * ((1+Math.pow(k, 2)) * (Math.pow(b, 2) - 1));

            double x_1 = (-2 * k * b - Math.sqrt(discriminant)) / (2 * (1 + Math.pow(k,2)));
            double x_2 = (-2 * k * b + Math.sqrt(discriminant)) / (2 * (1 + Math.pow(k,2)));

            if(px < 0) {
                next_x = Math.min(x_1, x_2);
            } else {
                next_x = Math.max(x_1, x_2);
            }

            if(next_x < -L/2) {
                next_x = next_x - (L/2);
            } else {
                next_x = next_x + (L/2);
            }
        }
        next_y = k * next_x + b;


        return new Pair2D(next_x, next_y);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++) {
            System.out.println(calculateNextPosition(generateInitialPoint(2), generateInitialMomentum(), 2));
        }
    }
}
