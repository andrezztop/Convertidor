import java.util.function.BiFunction;

public class ConversionLogica {
    // BiFunction para realizar la conversión
    protected final BiFunction<Double, Double, Double> convertir = (valor, factor) -> valor * factor;

    // Método genérico para convertir, esperando un factor
    public String convertirUnidades(double valor, double factor) {
        double resultado = convertir.apply(valor, factor); // Aplica la función de conversión
        return String.format("%.3f", resultado); // Retorna el resultado formateado
    }
}