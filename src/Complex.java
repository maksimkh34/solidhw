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

    Complex(String str) throws Exception {
        getFromString(str);
    }

    public String formatToStr() {
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

    public void getFromString(String str) throws Exception {
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
                };

        }

        if(Arrays.equals(nums, new Integer[]{0, 0})) {
            throw new Exception("This is not complex number. ");
        }

        this.a = nums[0];
        this.b = nums[1];
    }
}
