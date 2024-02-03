import java.util.Arrays;

public class Complex {
    public int a = 0, b = 1;

    Complex(int a_, int b_) {
        a = a_;
        b = b_;
    }

    Complex() {
        this(0, 1);
    }

    Complex(String str) throws InvalidFormatException {
        getFromString(str);
    }

    @Override
    public String toString() {
        if(b==0) {
            return String.valueOf(a);
        }

        String result = "";
        if(a != 0){
            result = String.valueOf(a);
        }

        if(b >= 0 && a != 0) result += "+";
        result += String.valueOf(b);
        result += "i";

        return result;
    }

    public void getFromString(String str) throws InvalidFormatException {
        if(!str.matches(
                "^(?=[iI.\\d+-])([+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?(?![iI.\\d]))" +
                        "?([+-]?(?:(?:\\d+(?:\\.\\d*)?|\\.\\d+)(?:[eE][+-]?\\d+)?)?[iI])?$")) {
            throw new InvalidFormatException("This is not complex number! ");
        }

        if(!str.contains("i")) {
            this.a = Integer.parseInt(str);
            this.b = 0;
            return;
        }

        if(str.matches("-?[0-9]+i")) {
            this.a = 0;
            this.b = Integer.parseInt(str.substring(0, str.length()-1));
            return;
        }

        str = str.substring(0, str.length()-1);
        Integer[] nums = new Integer[] {0, 0};
        for(int i = 2; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) && (str.charAt(i-1) == '-' || str.charAt(i-1) == '+')) {
                    nums[0] = Integer.parseInt(str.substring(0, i - 1));
                    nums[1] = Integer.parseInt(str.substring(i - 1));
                }

        }

        this.a = nums[0];
        this.b = nums[1];
    }

    public static Complex add(Complex a, Complex b) {
        return new Complex();
    }

    public static Complex subtract(Complex a, Complex b) {
        return new Complex();
    }

    public static Complex divide(Complex a, Complex b) {
        return new Complex();
    }

    public static Complex multiply(Complex a, Complex b) {
        return new Complex();
    }


}
