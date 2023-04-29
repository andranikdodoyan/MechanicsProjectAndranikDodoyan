public class ConverterInt extends Converter{

    private String springExpr = "";
    private Spring[] springs;
    @Override
    public String toSpringExpr(String binarySequence) {
        int length = binarySequence.length();
        char[] sequence = binarySequence.toCharArray();
        springs = new Spring[length-1];
        for(int i = 0; i < springs.length; i++) {
            if(sequence[i] == '1') {
                springs[i] = new Spring(Math.pow(2, i));
                springExpr += "[]";
            }
        }
        if(springExpr.length() > 2) {
            springExpr += "]";
            springExpr = "["+springExpr;
        }
        return springExpr;
    }

    public static void main(String[] args) {
        ConverterInt converterInt = new ConverterInt();
        converterInt.toSpringExpr("00001000");
        System.out.println(SpringArray.equivalentSpring(converterInt.springExpr, converterInt.springs).getStiffness());
    }

    @Override
    public double computeDecimalFromFT(Spring spring, int N) {
        return 0;
    }


}
