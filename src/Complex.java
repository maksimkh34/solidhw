public class Complex {
    public int a = 0, b = 1;

    Complex(int a_, int b_) {
        a = a_;
        b = b_;
    }

    Complex() {
        this(0, 1);
    }

    Complex(String str) {
        getFromString(str);
    }

    public String formatToStr() {
        String result = "";
        if(a != 0){
            result = String.valueOf(a);
        }

        if(b >= 0 && a != 0) result += "+";
        result += String.valueOf(b);
        result += "i";

        return result;
    }

    public void getFromString(String str) {
        String[] nums = new String[] {"", ""};
        if(str.contains("+")) {
            nums = str.split("\\+");
        } else if (str.contains("-")) {
            nums = str.split("-");
        } else {
            a = 0;
            b = Integer.parseInt(str.substring(str.length() - 1));
            return;
        }

        nums[1] = nums[1].substring(nums[1].length() - 1);
        a = Integer.parseInt(nums[0]);   // Не работают отриц числа
        b = Integer.parseInt(nums[1]);
    }
}
