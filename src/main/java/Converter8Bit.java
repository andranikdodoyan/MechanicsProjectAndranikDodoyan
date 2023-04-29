public class Converter8Bit extends Converter{
    @Override
    public String toSpringExpr(String binarySequence) {
        String springExpr = "";
        if(binarySequence.equals("00000000"))
            return "";
        char[] binaryCharArray = binarySequence.toCharArray();
        for(int i = 7; i >= 0; i--) {
            if(binaryCharArray[i] == '1') {
                String add = "";
                for(int k = 0; k < Math.pow(2, 7-i); k++){
                    add += "[]";
                }
                if(add.length() > 2) {
                    add += "]";
                    add = "["+add;
                }
                springExpr += add;
            }
        }
        if(springExpr.length() > 2) {
            springExpr += "]";
            springExpr = "["+springExpr;
        }
        return springExpr;
    }
    @Override
    public double computeDecimalFromFT(Spring spring, int N) {
        return Math.pow(FT.findMaxAmplitudeIndex(computeAmplitudesOfeOscillations(spring, N)), 2);

    }

    public static void main(String[] args) {
        Converter8Bit thisConverter8Bit = new Converter8Bit();
        String binaryExample = "00000000"; //0
        String binarySpring = thisConverter8Bit.toSpringExpr(binaryExample);
        Spring spring = SpringArray.equivalentSpring(binarySpring);
        double decimal = spring.getStiffness();
        System.out.println(thisConverter8Bit.computeDecimalFromFT(spring, 30));
        System.out.println(decimal);

        binaryExample = "00001001"; //9
        binarySpring = thisConverter8Bit.toSpringExpr(binaryExample);
        spring = SpringArray.equivalentSpring(binarySpring);
        decimal = spring.getStiffness();
        System.out.println(thisConverter8Bit.computeDecimalFromFT(spring, 30));
        System.out.println(decimal);

        binaryExample = "00011000"; //24
        binarySpring = thisConverter8Bit.toSpringExpr(binaryExample);
        spring = SpringArray.equivalentSpring(binarySpring);
        decimal = spring.getStiffness();
        System.out.println(thisConverter8Bit.computeDecimalFromFT(spring, 255/8));
        System.out.println(decimal);

        binaryExample = "00001000"; //24
        binarySpring = thisConverter8Bit.toSpringExpr(binaryExample);
        spring = SpringArray.equivalentSpring(binarySpring);
        decimal = spring.getStiffness();
        System.out.println(thisConverter8Bit.computeDecimalFromFT(spring, 255/8));
        System.out.println(decimal);

        binaryExample = "11011000";//216
        binarySpring = thisConverter8Bit.toSpringExpr(binaryExample);
        spring = SpringArray.equivalentSpring(binarySpring);
        decimal = spring.getStiffness();
        System.out.println(thisConverter8Bit.computeDecimalFromFT(spring, 255/8));
        System.out.println(decimal);

    }

}
