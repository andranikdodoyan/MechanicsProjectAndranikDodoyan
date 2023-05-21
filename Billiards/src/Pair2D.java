public class Pair2D {
    private double x;
    private double y;

    public Pair2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return " (" + x +
                "," + y + ")";
    }
}