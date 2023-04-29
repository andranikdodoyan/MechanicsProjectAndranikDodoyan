public abstract class Converter {
    private static final double DT = 0.1;
    private static final double T = 10;
    private static final int N = 64;
    public abstract String toSpringExpr(String binarySequence);
    private static double[] computeOscillations(Spring systemOfSprings) {
        return systemOfSprings.move(T, DT, 3);
    }
    public static double[] computeAmplitudesOfeOscillations(Spring systemOfSprings) {
        double[] oscillations = computeOscillations(systemOfSprings);
        return FT.findAmplitudes(oscillations, N, DT);
    }

    public static double computeDecimalFromFT(Spring spring) {
        return Math.pow(FT.findMaxAmplitudeIndex(computeAmplitudesOfeOscillations(spring)), 2);
    }
}
