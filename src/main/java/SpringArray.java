import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class SpringArray {
    public static Spring equivalentSpring(String springExpr) {
        String out = springExpr.replaceAll("\\[\\]", "1").replaceAll("\\{\\}", "1");
        List<String> outStringList = Arrays.asList(out.split(""));

        while(outStringList.size() != 1) {
            int lastOpeningBrace = outStringList.lastIndexOf("{");
            int lastOpeningBracket = outStringList.lastIndexOf("[");

            if(lastOpeningBrace > lastOpeningBracket) {
                int firstClosingBrace = -1;
                int i = lastOpeningBrace + 1;
                while (true) {
                    if(outStringList.get(i).equals("}")) {
                        firstClosingBrace = i;
                        break;
                    }
                    i = i+1;
                }
                List<String> helper = new ArrayList<>();
                Spring spring1 = new Spring(Double.parseDouble(outStringList.get(lastOpeningBrace+1)));
                for(int j = 0; j < outStringList.size(); j++) {
                    if(j==lastOpeningBrace || j == firstClosingBrace || j==lastOpeningBrace+1) {

                    } else if (j >= lastOpeningBrace + 2 && j < firstClosingBrace) {
                        Spring spring2 = new Spring(Double.parseDouble(outStringList.get(j)));
                        spring1 = spring1.inSeries(spring2);
                    } else {
                        helper.add(outStringList.get(j));
                    }
                }
                helper.add(lastOpeningBrace, spring1.getStiffness()+"");
                outStringList = helper;

            } else if(lastOpeningBrace < lastOpeningBracket) {
                int firstClosingBracket = -1;
                int i = lastOpeningBracket + 1;
                while (true) {
                    if(outStringList.get(i).equals("]")) {
                        firstClosingBracket = i;
                        break;
                    }
                    i = i+1;
                }
                List<String> helper = new ArrayList<>();
                Spring spring1 = new Spring(Double.parseDouble(outStringList.get(lastOpeningBracket+1)));
                for(int j = 0; j < outStringList.size(); j++) {
                    if(j==lastOpeningBracket || j == firstClosingBracket || j==lastOpeningBracket+1) {

                    } else if (j >= lastOpeningBracket + 2 && j < firstClosingBracket) {
                        Spring spring2 = new Spring(Double.parseDouble(outStringList.get(j)));
                        spring1 = spring1.inParallel(spring2);
                    } else {
                        helper.add(outStringList.get(j));
                    }
                }
                helper.add(lastOpeningBracket, spring1.getStiffness()+"");
                outStringList = helper;
            }
        }

        return new Spring(Double.parseDouble(outStringList.get(0)));
    }
    public static Spring equivalentSpring(String springExpr, Spring[] springs) {

        List<String> outStringList = Arrays.asList(springExpr);
        int p = 0;
        int springPoint = 0;
        while(p < outStringList.size()-1) {
            if(outStringList.get(p) == "[" && outStringList.get(p+1) == "]") {
                outStringList.set(p, springs[springPoint].getStiffness()+"");
                springPoint++;
            }
            if(outStringList.get(p) == "{" && outStringList.get(p+1) == "}") {
                outStringList.set(p, springs[springPoint].getStiffness()+"");
                springPoint++;
            }
            p++;
        }

        while(outStringList.size() != 1) {
            int lastOpeningBrace = outStringList.lastIndexOf("{");
            int lastOpeningBracket = outStringList.lastIndexOf("[");

            if(lastOpeningBrace > lastOpeningBracket) {
                int firstClosingBrace = -1;
                int i = lastOpeningBrace + 1;
                while (true) {
                    if(outStringList.get(i).equals("}")) {
                        firstClosingBrace = i;
                        break;
                    }
                    i = i+1;
                }
                List<String> helper = new ArrayList<>();
                Spring spring1 = new Spring(Double.parseDouble(outStringList.get(lastOpeningBrace+1)));
                for(int j = 0; j < outStringList.size(); j++) {
                    if(j==lastOpeningBrace || j == firstClosingBrace || j==lastOpeningBrace+1) {

                    } else if (j >= lastOpeningBrace + 2 && j < firstClosingBrace) {
                        Spring spring2 = new Spring(Double.parseDouble(outStringList.get(j)));
                        spring1 = spring1.inSeries(spring2);
                    } else {
                        helper.add(outStringList.get(j));
                    }
                }
                helper.add(lastOpeningBrace, spring1.getStiffness()+"");
                outStringList = helper;

            } else if(lastOpeningBrace < lastOpeningBracket) {
                int firstClosingBracket = -1;
                int i = lastOpeningBracket + 1;
                while (true) {
                    if(outStringList.get(i).equals("]")) {
                        firstClosingBracket = i;
                        break;
                    }
                    i = i+1;
                }
                List<String> helper = new ArrayList<>();
                Spring spring1 = new Spring(Double.parseDouble(outStringList.get(lastOpeningBracket+1)));
                for(int j = 0; j < outStringList.size(); j++) {
                    if(j==lastOpeningBracket || j == firstClosingBracket || j==lastOpeningBracket+1) {

                    } else if (j >= lastOpeningBracket + 2 && j < firstClosingBracket) {
                        Spring spring2 = new Spring(Double.parseDouble(outStringList.get(j)));
                        spring1 = spring1.inParallel(spring2);
                    } else {
                        helper.add(outStringList.get(j));
                    }
                }
                helper.add(lastOpeningBracket, spring1.getStiffness()+"");
                outStringList = helper;
            }
        }

        return new Spring(Double.parseDouble(outStringList.get(0)));
    }


}