import javax.swing.table.AbstractTableModel;

public class GornerTableModel extends AbstractTableModel {
    private final Double[] coefficients;
    private final Double from;
    private final Double to;
    private final Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return (int) Math.ceil((to - from) / step) + 1;
    }


    private boolean isIncreasingSequence(double value) {
        String numStr = String.format("%.3f", value);
        //numStr = numStr.replaceAll("0*$", "");
        //System.out.println(numStr);
        int dotIndex = numStr.indexOf(',');
        //System.out.println("dot index=" + dotIndex);
        if (dotIndex != -1) {
            numStr = numStr.substring(dotIndex + 1);
            int numAfterDot = Integer.parseInt(numStr);
            //System.out.println("numafter dot:"+numAfterDot);
            return isSquare(numAfterDot);
        }
        return true;
    }
    private boolean isSquare(double value) {
        double squareRoot = Math.sqrt(value);
        return squareRoot % 1 == 0 && squareRoot > 0;
    }

    public Object getValueAt(int row, int col){
        double x = from + step * row;
        return switch (col) {
            case 0 -> x;
            case 1 -> funcGoner(x);
            case 2 -> isIncreasingSequence(funcGoner(x));
            default -> null;
        };
    }

    private double funcGoner(double x) {
        int n = coefficients.length - 1;
        double result = coefficients[n];

        for (int i = n - 1; i >= 0; i--)
            result = result * x + coefficients[i];

        return result;
    }

    public String getColumnName(int col) {
        return switch (col) {
            case 0 -> "Значение X";
            case 1 -> "Значение многочлена";
            case 2 -> "Дробная часть ялвяется квадратом";
            default -> null;
        };
    }

    public Class<?> getColumnClass(int col) {
        return switch (col) {
            case 0, 1 -> Double.class;
            case 2 -> Boolean.class;
            default -> Object.class;
        };
    }
}