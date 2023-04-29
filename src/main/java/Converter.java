public abstract class Converter {
    public static final double DT = 0.1;
    public static final double T = 10;
    public static final int N = 100;
    public abstract String toSpringExpr(String binarySequence);
    public static double[] computeOscillations(Spring systemOfSprings) {
        return systemOfSprings.move(T, DT, 0);
    }
    public static double[] computeAmplitudesOfeOscillations(Spring systemOfSprings) {
        double[] oscillations = computeOscillations(systemOfSprings);
        return FT.findAmplitudes(oscillations, N, DT);
    }
}
