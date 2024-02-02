public class Main {
    public static void main(String[] args) {
        Complex a1, a2, a3, a4;
        try {
            a1 = new Complex("4i");
            a2 = new Complex("-5");
            a3 = new Complex("-4+5i");
            a4 = new Complex("-1i");
        } catch (Exception e) {
            System.out.println("There is not complex number! ");
            return;
        }
        System.out.printf("%s\n%s\n%s\n%s\n%n",
                a1.formatToStr(),
                a2.formatToStr(),
                a3.formatToStr(),
                a4.formatToStr());
    }
}