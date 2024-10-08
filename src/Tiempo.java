import java.util.Map;

public class Tiempo extends ConversionLogica {
    // Factores de conversión para unidades de tiempo
    private final Map<String, Double> conversionFactors = Map.of(
            "Segundos->Minutos", 1 / 60.0,
            "Minutos->Segundos", 60.0,
            "Segundos->Horas", 1 / 3600.0,
            "Horas->Segundos", 3600.0,
            "Minutos->Horas", 1 / 60.0,
            "Horas->Minutos", 60.0,
            "Segundos->Segundos", 1.0,
            "Minutos->Minutos", 1.0,
            "Horas->Horas", 1.0
    );

    public String convertirUnidades(double valor, String unidadEntrada, String unidadSalida) {
        //  Crear la clave para buscar el factor en el mapa
        String conversionKey = unidadEntrada + "->" + unidadSalida;
        double factor = conversionFactors.getOrDefault(conversionKey, 1.0);
        
        // Llamar al método de la clase padre con el valor y el factor de conversión
        return super.convertirUnidades(valor, factor);
    }
}