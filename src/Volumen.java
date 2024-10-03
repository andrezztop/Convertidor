import java.util.Map;

public class Volumen extends ConversionLogica {
    // Factores de conversión para unidades de volumen
    private final Map<String, Double> conversionFactors = Map.of(
            "Mililitros->Litros", 1 / 1000.0,
            "Litros->Mililitros", 1000.0,
            "Mililitros->Galones", 1 / 3785.41,
            "Galones->Mililitros", 3785.41,
            "Litros->Galones", 1 / 3.78541,
            "Galones->Litros", 3.78541,
            "Mililitros->Mililitros", 1.0,
            "Litros->Litros", 1.0,
            "Galones->Galones", 1.0
    );

    // Método para convertir unidades basado en las unidades de entrada y salida
    public String convertirUnidades(double valor, String unidadEntrada, String unidadSalida) {
        // Crear la clave para buscar el factor en el mapa
        String conversionKey = unidadEntrada + "->" + unidadSalida;
        double factor = conversionFactors.getOrDefault(conversionKey, 1.0);
        
        // Llamar al método de la clase padre con el valor y el factor de conversión
        return super.convertirUnidades(valor, factor);
    }
}