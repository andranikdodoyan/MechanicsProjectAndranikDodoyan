public abstract class Converter {
    public final double DT = 0.1;
    public final double T = 10;
    public final int N = 100;
    public abstract int toDecimalUsingSprings(String binarySequence);
    public double[] computeOscillations(Spring systemOfSprings) {
        return systemOfSprings.move(T, DT, 0);
    }
    public double[] computeAmplitudesOfeOscillations(Spring systemOfSprings) {
        double[] oscillations = systemOfSprings.move(T, DT, 0);
        return FT.findAmplitudes(oscillations, N, DT);
    }
    public abstract int toDecimalUsingFT(String binarySequence);
}
