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
            if(springExpr.length() > 2) {
                springExpr += "]";
                springExpr = "["+springExpr;
            }
        }
        return springExpr;
    }

    public static void main(String[] args) {
        String binaryExample = "00000000"; //0
        String binarySpring = new Converter8Bit().toSpringExpr(binaryExample);
        Spring spring = SpringArray.equivalentSpring(binarySpring);
        double decimal = spring.getStiffness();
        System.out.println(decimal);

        binaryExample = "00001001"; //9
        binarySpring = new Converter8Bit().toSpringExpr(binaryExample);
        spring = SpringArray.equivalentSpring(binarySpring);
        decimal = spring.getStiffness();
        System.out.println(computeDecimalFromFT(spring));
        System.out.println(decimal);

        binaryExample = "00011000"; //24
        binarySpring = new Converter8Bit().toSpringExpr(binaryExample);
        spring = SpringArray.equivalentSpring(binarySpring);
        decimal = spring.getStiffness();
        System.out.println(computeDecimalFromFT(spring));
        System.out.println(decimal);

        binaryExample = "11011000";//216
        binarySpring = new Converter8Bit().toSpringExpr(binaryExample);
        spring = SpringArray.equivalentSpring(binarySpring);
        decimal = spring.getStiffness();
        System.out.println(computeDecimalFromFT(spring));
        System.out.println(decimal);

    }

}
