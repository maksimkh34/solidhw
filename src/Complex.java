import java.util.Arrays;

public class Complex {
    public double a = 0, b = 1;

    Complex(double a_, double b_) {
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
            return format(a);
        }

        String result = "";
        if(a != 0){
            result = format(a);
        }

        if(b >= 0 && a != 0) result += "+";
        result += format(b);
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
            this.a = Double.parseDouble(str);
            this.b = 0;
            return;
        }

        if(str.matches("-?[0-9]+i")) {
            this.a = 0;
            this.b = Double.parseDouble(str.substring(0, str.length()-1));
            return;
        }

        str = str.substring(0, str.length()-1);
        double[] nums = new double[] {0f, 0f};
        for(int i = 2; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i)) && (str.charAt(i-1) == '-' || str.charAt(i-1) == '+')) {
                    nums[0] = Double.parseDouble(str.substring(0, i - 1));
                    nums[1] = Double.parseDouble(str.substring(i - 1));
                }

        }

        this.a = nums[0];
        this.b = nums[1];
    }

    public static Complex add(Complex a, Complex b) {
        return new Complex(a.a+b.a, a.b+b.b);
    }

    public static Complex subtract(Complex a, Complex b) {
        return new Complex(a.a-b.a, a.b-b.b);
    }

    public static Complex divide(Complex a, Complex b) {
        return new Complex((a.a * b.a + a.b *b.b) / (Math.pow(b.a, 2) + Math.pow(b.b, 2)),
                (b.a * a.b - a.a * b.b) / (Math.pow(b.a, 2) + Math.pow(b.b, 2)));
    }

    public static Complex multiply(Complex a, Complex b) {
        return new Complex(a.a * b.a - a.b * b.b , a.a * b.b + a.b * b.a);
    }

    public static String format(double x) {
        if (x%1 < 0.0000000001) {
            return String.valueOf((int)x);
        }
        String value = String.valueOf(x);

        for(int i = 2; i < value.length(); i++) {
            if(value.charAt(i) == value.charAt(i-1) &&
                    value.charAt(i-1) == value.charAt(i-2) &&
                    value.charAt(i) == 0) {
                return value.substring(0, i-2);
            }
        }

        return value;
    }
}
