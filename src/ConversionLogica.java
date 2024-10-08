import java.util.function.BiFunction;

public class ConversionLogica {
    protected String convertirUnidades(double valor, double factor) {
        double resultado = valor * factor;
        return String.format("%.2e", resultado);
    }
}