import java.util.Map;

public class Volumen extends ConversionLogica implements Pantalla.Convertidor {
    private final Map<String, Double> conversionFactors = Map.of(
            "Mililitros->Litros", 1 / 1000.0,
            "Litros->Mililitros", 1000.0,
            "Litros->Galones", 0.264172,
            "Galones->Litros", 3.78541,
            "Mililitros->Mililitros", 1.0,
            "Litros->Litros", 1.0,
            "Galones->Galones", 1.0
    );

    @Override
    public String convertir(double valor, String unidadEntrada, String unidadSalida) {
        String conversionKey = unidadEntrada + "->" + unidadSalida;
        double factor = conversionFactors.getOrDefault(conversionKey, 1.0);
        return convertirUnidades(valor, factor);  // Usa el método de ConversionLogica
    }
    }