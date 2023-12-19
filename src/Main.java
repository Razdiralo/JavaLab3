import javax.swing.*;

public class Main extends JFrame {
    public static void main(String[] args) {

        GUI frame = new GUI();
        if (args.length == 0) {
            System.out.println("Невозможно табулировать многочлен, для которого не задано ни одного коэффициента!");
            System.exit(-1);
        }
        Double[] coefficients = new Double[args.length];
        int i = 0;
        try {
            for (String arg : args)
                coefficients[i++] = Double.parseDouble(arg);

            frame.setCoefficients(coefficients);
        } catch (NumberFormatException ex) {
            System.out.println("Ошибка преобразования строки '" + args[i] + "' в число типа Double");
            System.exit(-2);
        }
    }
}