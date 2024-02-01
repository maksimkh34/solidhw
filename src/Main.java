public class Main {
    public static void main(String[] args) {
        Complex a1 = new Complex("5+4i");
        Complex a2 = new Complex("-5-6i");
        Complex a3 = new Complex("-4+5i");
        Complex a4 = new Complex("7-1i");
        System.out.printf("%s\n%s\n%s\n%s\n%n",
                a1.formatToStr(),
                a2.formatToStr(),
                a3.formatToStr(),
                a4.formatToStr());
    }
}