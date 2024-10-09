public class ConversionLogica {
    protected String convertirUnidades(double valor, double factor) {
        double resultado = valor * factor;
        return String.valueOf(resultado);
    }
}