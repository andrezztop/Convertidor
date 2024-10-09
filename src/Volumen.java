import java.util.Map;

public class Volumen extends ConversionLogica implements Pantalla.Convertidor {
    private final Map<String, Double> conversionFactors = Map.ofEntries(
        // Existing conversions
        Map.entry("Mililitros->Litros", 1 / 1000.0),
        Map.entry("Litros->Mililitros", 1000.0),
        Map.entry("Litros->Galones", 0.264172),
        Map.entry("Galones->Litros", 3.78541),
        Map.entry("Mililitros->Mililitros", 1.0),
        Map.entry("Litros->Litros", 1.0),
        Map.entry("Galones->Galones", 1.0),
        
        // New conversions
        Map.entry("Galón estadounidense->Litros", 3.78541),
        Map.entry("Litros->Galón estadounidense", 1 / 3.78541),
        Map.entry("Cuarto estadounidense->Litros", 0.946353),
        Map.entry("Litros->Cuarto estadounidense", 1 / 0.946353),
        Map.entry("Pinta estadounidense->Litros", 0.473176),
        Map.entry("Litros->Pinta estadounidense", 1 / 0.473176),
        Map.entry("Taza americana oficial->Litros", 0.236588),
        Map.entry("Litros->Taza americana oficial", 1 / 0.236588),
        Map.entry("Onza líquida estadounidense->Mililitros", 29.5735),
        Map.entry("Mililitros->Onza líquida estadounidense", 1 / 29.5735),
        Map.entry("Cucharada estadounidense->Mililitros", 14.7868),
        Map.entry("Mililitros->Cucharada estadounidense", 1 / 14.7868),
        Map.entry("Cucharadita estadounidense->Mililitros", 4.92892),
        Map.entry("Mililitros->Cucharadita estadounidense", 1 / 4.92892),
        Map.entry("Metro cúbico->Litros", 1000.0),
        Map.entry("Litros->Metro cúbico", 1 / 1000.0),
        Map.entry("Galón imperial->Litros", 4.54609),
        Map.entry("Litros->Galón imperial", 1 / 4.54609),
        Map.entry("Cuarto imperial->Litros", 1.13652),
        Map.entry("Litros->Cuarto imperial", 1 / 1.13652),
        Map.entry("Pinta imperial->Litros", 0.568261),
        Map.entry("Litros->Pinta imperial", 1 / 0.568261),
        Map.entry("Taza imperial->Litros", 0.284131),
        Map.entry("Litros->Taza imperial", 1 / 0.284131),
        Map.entry("Onza líquida imperial->Mililitros", 28.4131),
        Map.entry("Mililitros->Onza líquida imperial", 1 / 28.4131),
        Map.entry("Cucharada imperial->Mililitros", 17.7582),
        Map.entry("Mililitros->Cucharada imperial", 1 / 17.7582),
        Map.entry("Cucharadita imperial->Mililitros", 5.91939),
        Map.entry("Mililitros->Cucharadita imperial", 1 / 5.91939),
        Map.entry("Pie cúbico->Litros", 28.3168),
        Map.entry("Litros->Pie cúbico", 1 / 28.3168),
        Map.entry("Pulgada cúbica->Mililitros", 16.3871),
        Map.entry("Mililitros->Pulgada cúbica", 1 / 16.3871)
    );

    @Override
    public String convertir(double valor, String unidadEntrada, String unidadSalida) {
        String conversionKey = unidadEntrada + "->" + unidadSalida;
        double factor = conversionFactors.getOrDefault(conversionKey, 1.0);
        return convertirUnidades(valor, factor);  // Usa el método de ConversionLogica
    }
}