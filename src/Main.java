import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        Logger logger = new Logger(Logger.LoggerType.Console);
        String help = """
                Ожидается выражение:\s
                (-)a(+\\-)bi (-\\+\\/\\*) (-)c(+\\-)di,\s
                где a, b, c, d - коэффициенты (могут быть нулевыми)
                """;

        System.out.print("Введите выражение (формат: a+bi - c-di) или ? для справки: ");
        String expression;
        try {
            expression = reader.readLine();
        } catch (IOException e) {
            logger.log("[main] Input error.", Logger.MsgType.Error);
            return;
        }

        if(Objects.equals(expression, "?")) {
            System.out.println(help);
            return;
        }

        Complex result;
        try {
            String[] expr_split = expression.split(" ");

            Complex a = new Complex(expr_split[0]);
            Complex b = new Complex(expr_split[2]);

            result = switch (expr_split[1]) {
                case "+" -> Complex.add(a, b);
                case "-" -> Complex.subtract(a, b);
                case "/" -> Complex.divide(a, b);
                case "*" -> Complex.multiply(a, b);
                default -> {
                    logger.log("Неверная операция. ", Logger.MsgType.Error);
                    throw new InvalidFormatException("Выбрана неверная операция. ");
                }
            };
        } catch (InvalidFormatException e) {
            logger.log("[main] Input error.", Logger.MsgType.Error);
            return;
        }

        System.out.println("Результат: " + result);
        logger.exitLogger();
    }
}