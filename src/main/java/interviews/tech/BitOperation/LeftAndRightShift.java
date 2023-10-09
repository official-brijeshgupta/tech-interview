package interviews.tech.BitOperation;

public class LeftAndRightShift {

    public static void main(String[] args){
//        leftShift();
        rightShift();

    }

    /**
     * Left shift operator basically multiplies the first number (a) with Math.pow(2, b)
     * a << b == a * Math.pow(2, b)
     */
    private static void leftShift(){

        int a = 1;
        int b = 1;
        System.out.println("a << b " + (a << b));

        a = 1;
        b = 2;
        System.out.println("a << b " + (a << b));

        a = 2;
        b = 1;
        System.out.println("a << b " + (a << b));

        a = 3;
        b = 1;
        System.out.println("a << b " + (a << b));

        a = 3;
        b = 2;
        System.out.println("a << b " + (a << b));

    }

    /**
     * Right shift operator basically divides the first number (a) with Math.pow(2, b)
     * a << b == a / Math.pow(2, b)
     */
    private static void rightShift(){

        int a = 1;
        int b = 0;
        System.out.println("a >> b " + (a >> b)); // 1

        a = 2;
        b = 1;
        System.out.println("a >> b " + (a >> b)); // 1

        a = 2;
        b = 2;
        System.out.println("a >> b " + (a >> b)); // 0

        a = 1;
        b = 2;
        System.out.println("a >> b " + (a >> b)); // 0

        a = 3;
        b = 1;
        System.out.println("a >> b " + (a >> b)); // 1

        a = 12;
        b = 2;
        System.out.println("a >> b " + (a >> b)); // 3

    }
}
