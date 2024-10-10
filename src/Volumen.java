import java.util.Map;

public class Volumen extends ConversionLogica implements Pantalla.Convertidor {
    private static final Map<String, Double> conversionFactors = Map.ofEntries(
        // Conversión de Mililitros
        Map.entry("Mililitros->Mililitros", 1.0),
        Map.entry("Mililitros->Litros", 0.001),
        Map.entry("Mililitros->Galones", 0.000264172),
        Map.entry("Mililitros->Galón estadounidense", 0.000264172),
        Map.entry("Mililitros->Cuarto estadounidense", 0.00211338),
        Map.entry("Mililitros->Pinta estadounidense", 0.00211338),
        Map.entry("Mililitros->Taza americana oficial", 0.00422675),
        Map.entry("Mililitros->Onza líquida estadounidense", 0.033814),
        Map.entry("Mililitros->Cucharada estadounidense", 0.067628),
        Map.entry("Mililitros->Cucharadita estadounidense", 0.202884),
        Map.entry("Mililitros->Metro cúbico", 0.000001),
        Map.entry("Mililitros->Galón imperial", 0.000219969),
        Map.entry("Mililitros->Cuarto imperial", 0.00175975),
        Map.entry("Mililitros->Pinta imperial", 0.00351951),
        Map.entry("Mililitros->Taza imperial", 0.00351951),
        Map.entry("Mililitros->Onza líquida imperial", 0.0351951),
        Map.entry("Mililitros->Cucharada imperial", 0.067628),
        Map.entry("Mililitros->Cucharadita imperial", 0.202884),
        Map.entry("Mililitros->Pie cúbico", 0.000035315),
        Map.entry("Mililitros->Pulgada cúbica", 0.0610237),

        // Conversión de Litros
        Map.entry("Litros->Mililitros", 1000.0),
        Map.entry("Litros->Litros", 1.0),
        Map.entry("Litros->Galones", 0.264172),
        Map.entry("Litros->Galón estadounidense", 0.264172),
        Map.entry("Litros->Cuarto estadounidense", 4.22675),
        Map.entry("Litros->Pinta estadounidense", 2.11338),
        Map.entry("Litros->Taza americana oficial", 4.22675),
        Map.entry("Litros->Onza líquida estadounidense", 33.814),
        Map.entry("Litros->Cucharada estadounidense", 67.628),
        Map.entry("Litros->Cucharadita estadounidense", 202.884),
        Map.entry("Litros->Metro cúbico", 0.001),
        Map.entry("Litros->Galón imperial", 0.219969),
        Map.entry("Litros->Cuarto imperial", 2.11338),
        Map.entry("Litros->Pinta imperial", 4.22675),
        Map.entry("Litros->Taza imperial", 4.22675),
        Map.entry("Litros->Onza líquida imperial", 35.1951),
        Map.entry("Litros->Cucharada imperial", 67.628),
        Map.entry("Litros->Cucharadita imperial", 202.884),
        Map.entry("Litros->Pie cúbico", 0.0353147),
        Map.entry("Litros->Pulgada cúbica", 61.0237),

        // Conversión de Galones
        Map.entry("Galones->Mililitros", 3785.41),
        Map.entry("Galones->Litros", 3.78541),
        Map.entry("Galones->Galones", 1.0),
        Map.entry("Galones->Galón estadounidense", 1.0),
        Map.entry("Galones->Cuarto estadounidense", 4.0),
        Map.entry("Galones->Pinta estadounidense", 8.0),
        Map.entry("Galones->Taza americana oficial", 15.772),
        Map.entry("Galones->Onza líquida estadounidense", 128.0),
        Map.entry("Galones->Cucharada estadounidense", 256.0),
        Map.entry("Galones->Cucharadita estadounidense", 768.0),
        Map.entry("Galones->Metro cúbico", 0.00378541),
        Map.entry("Galones->Galón imperial", 0.832674),
        Map.entry("Galones->Cuarto imperial", 3.32869),
        Map.entry("Galones->Pinta imperial", 6.61734),
        Map.entry("Galones->Taza imperial", 7.99363),
        Map.entry("Galones->Onza líquida imperial", 105.669),
        Map.entry("Galones->Cucharada imperial", 224.0),
        Map.entry("Galones->Cucharadita imperial", 672.0),
        Map.entry("Galones->Pie cúbico", 0.133681),
        Map.entry("Galones->Pulgada cúbica", 231.0),

        // Conversión de Galón estadounidense
        Map.entry("Galón estadounidense->Mililitros", 3785.41),
        Map.entry("Galón estadounidense->Litros", 3.78541),
        Map.entry("Galón estadounidense->Galones", 1.0),
        Map.entry("Galón estadounidense->Galón estadounidense", 1.0),
        Map.entry("Galón estadounidense->Cuarto estadounidense", 4.0),
        Map.entry("Galón estadounidense->Pinta estadounidense", 8.0),
        Map.entry("Galón estadounidense->Taza americana oficial", 15.772),
        Map.entry("Galón estadounidense->Onza líquida estadounidense", 128.0),
        Map.entry("Galón estadounidense->Cucharada estadounidense", 256.0),
        Map.entry("Galón estadounidense->Cucharadita estadounidense", 768.0),
        Map.entry("Galón estadounidense->Metro cúbico", 0.00378541),
        Map.entry("Galón estadounidense->Galón imperial", 0.832674),
        Map.entry("Galón estadounidense->Cuarto imperial", 3.32869),
        Map.entry("Galón estadounidense->Pinta imperial", 6.61734),
        Map.entry("Galón estadounidense->Taza imperial", 7.99363),
        Map.entry("Galón estadounidense->Onza líquida imperial", 105.669),
        Map.entry("Galón estadounidense->Cucharada imperial", 224.0),
        Map.entry("Galón estadounidense->Cucharadita imperial", 672.0),
        Map.entry("Galón estadounidense->Pie cúbico", 0.133681),
        Map.entry("Galón estadounidense->Pulgada cúbica", 231.0),

        // Conversión de Cuarto estadounidense
        Map.entry("Cuarto estadounidense->Mililitros", 946.353),
        Map.entry("Cuarto estadounidense->Litros", 0.946353),
        Map.entry("Cuarto estadounidense->Galones", 0.25),
        Map.entry("Cuarto estadounidense->Galón estadounidense", 0.25),
        Map.entry("Cuarto estadounidense->Cuarto estadounidense", 1.0),
        Map.entry("Cuarto estadounidense->Pinta estadounidense", 2.0),
        Map.entry("Cuarto estadounidense->Taza americana oficial", 4.0),
        Map.entry("Cuarto estadounidense->Onza líquida estadounidense", 32.0),
        Map.entry("Cuarto estadounidense->Cucharada estadounidense", 64.0),
        Map.entry("Cuarto estadounidense->Cucharadita estadounidense", 192.0),
        Map.entry("Cuarto estadounidense->Metro cúbico", 0.000946353),
        Map.entry("Cuarto estadounidense->Galón imperial", 0.219969),
        Map.entry("Cuarto estadounidense->Cuarto imperial", 1.0),
        Map.entry("Cuarto estadounidense->Pinta imperial", 2.0),
        Map.entry("Cuarto estadounidense->Taza imperial", 4.0),
        Map.entry("Cuarto estadounidense->Onza líquida imperial", 32.0),
        Map.entry("Cuarto estadounidense->Cucharada imperial", 64.0),
        Map.entry("Cuarto estadounidense->Cucharadita imperial", 192.0),
        Map.entry("Cuarto estadounidense->Pie cúbico", 0.0003285),
        Map.entry("Cuarto estadounidense->Pulgada cúbica", 57.75),

        // Conversión de Pinta estadounidense
        Map.entry("Pinta estadounidense->Mililitros", 473.176),
        Map.entry("Pinta estadounidense->Litros", 0.473176),
        Map.entry("Pinta estadounidense->Galones", 0.125),
        Map.entry("Pinta estadounidense->Galón estadounidense", 0.125),
        Map.entry("Pinta estadounidense->Cuarto estadounidense", 0.5),
        Map.entry("Pinta estadounidense->Pinta estadounidense", 1.0),
        Map.entry("Pinta estadounidense->Taza americana oficial", 2.0),
        Map.entry("Pinta estadounidense->Onza líquida estadounidense", 16.0),
        Map.entry("Pinta estadounidense->Cucharada estadounidense", 32.0),
        Map.entry("Pinta estadounidense->Cucharadita estadounidense", 96.0),
        Map.entry("Pinta estadounidense->Metro cúbico", 0.000473176),
        Map.entry("Pinta estadounidense->Galón imperial", 0.10984),
        Map.entry("Pinta estadounidense->Cuarto imperial", 0.5),
        Map.entry("Pinta estadounidense->Pinta imperial", 1.0),
        Map.entry("Pinta estadounidense->Taza imperial", 1.0),
        Map.entry("Pinta estadounidense->Onza líquida imperial", 16.0),
        Map.entry("Pinta estadounidense->Cucharada imperial", 32.0),
        Map.entry("Pinta estadounidense->Cucharadita imperial", 96.0),
        Map.entry("Pinta estadounidense->Pie cúbico", 0.0001654),
        Map.entry("Pinta estadounidense->Pulgada cúbica", 28.875),

        // Conversión de Taza americana oficial
        Map.entry("Taza americana oficial->Mililitros", 236.588),
        Map.entry("Taza americana oficial->Litros", 0.236588),
        Map.entry("Taza americana oficial->Galones", 0.0625),
        Map.entry("Taza americana oficial->Galón estadounidense", 0.0625),
        Map.entry("Taza americana oficial->Cuarto estadounidense", 0.25),
        Map.entry("Taza americana oficial->Pinta estadounidense", 0.5),
        Map.entry("Taza americana oficial->Taza americana oficial", 1.0),
        Map.entry("Taza americana oficial->Onza líquida estadounidense", 8.0),
        Map.entry("Taza americana oficial->Cucharada estadounidense", 16.0),
        Map.entry("Taza americana oficial->Cucharadita estadounidense", 48.0),
        Map.entry("Taza americana oficial->Metro cúbico", 0.000236588),
        Map.entry("Taza americana oficial->Galón imperial", 0.052042),
        Map.entry("Taza americana oficial->Cuarto imperial", 0.25),
        Map.entry("Taza americana oficial->Pinta imperial", 0.5),
        Map.entry("Taza americana oficial->Taza imperial", 0.5),
        Map.entry("Taza americana oficial->Onza líquida imperial", 8.0),
        Map.entry("Taza americana oficial->Cucharada imperial", 16.0),
        Map.entry("Taza americana oficial->Cucharadita imperial", 48.0),
        Map.entry("Taza americana oficial->Pie cúbico", 0.00008612),
        Map.entry("Taza americana oficial->Pulgada cúbica", 14.4375),

        // Conversión de Onza líquida estadounidense
        Map.entry("Onza líquida estadounidense->Mililitros", 29.5735),
        Map.entry("Onza líquida estadounidense->Litros", 0.0295735),
        Map.entry("Onza líquida estadounidense->Galones", 0.0078125),
        Map.entry("Onza líquida estadounidense->Galón estadounidense", 0.0078125),
        Map.entry("Onza líquida estadounidense->Cuarto estadounidense", 0.03125),
        Map.entry("Onza líquida estadounidense->Pinta estadounidense", 0.0625),
        Map.entry("Onza líquida estadounidense->Taza americana oficial", 0.125),
        Map.entry("Onza líquida estadounidense->Onza líquida estadounidense", 1.0),
        Map.entry("Onza líquida estadounidense->Cucharada estadounidense", 2.0),
        Map.entry("Onza líquida estadounidense->Cucharadita estadounidense", 6.0),
        Map.entry("Onza líquida estadounidense->Metro cúbico", 0.0000295735),
        Map.entry("Onza líquida estadounidense->Galón imperial", 0.006196),
        Map.entry("Onza líquida estadounidense->Cuarto imperial", 0.03125),
        Map.entry("Onza líquida estadounidense->Pinta imperial", 0.0625),
        Map.entry("Onza líquida estadounidense->Taza imperial", 0.125),
        Map.entry("Onza líquida estadounidense->Onza líquida imperial", 0.0351951),
        Map.entry("Onza líquida estadounidense->Cucharada imperial", 0.0625),
        Map.entry("Onza líquida estadounidense->Cucharadita imperial", 0.1875),
        Map.entry("Onza líquida estadounidense->Pie cúbico", 0.00001731),
        Map.entry("Onza líquida estadounidense->Pulgada cúbica", 1.80469),

        // Conversión de Cucharada estadounidense
        Map.entry("Cucharada estadounidense->Mililitros", 14.7868),
        Map.entry("Cucharada estadounidense->Litros", 0.0147868),
        Map.entry("Cucharada estadounidense->Galones", 0.00390625),
        Map.entry("Cucharada estadounidense->Galón estadounidense", 0.00390625),
        Map.entry("Cucharada estadounidense->Cuarto estadounidense", 0.015625),
        Map.entry("Cucharada estadounidense->Pinta estadounidense", 0.03125),
        Map.entry("Cucharada estadounidense->Taza americana oficial", 0.0625),
        Map.entry("Cucharada estadounidense->Onza líquida estadounidense", 0.676280),
        Map.entry("Cucharada estadounidense->Cucharada estadounidense", 1.0),
        Map.entry("Cucharada estadounidense->Cucharadita estadounidense", 3.0),
        Map.entry("Cucharada estadounidense->Metro cúbico", 0.0000147868),
        Map.entry("Cucharada estadounidense->Galón imperial", 0.00087648),
        Map.entry("Cucharada estadounidense->Cuarto imperial", 0.015625),
        Map.entry("Cucharada estadounidense->Pinta imperial", 0.03125),
        Map.entry("Cucharada estadounidense->Taza imperial", 0.0625),
        Map.entry("Cucharada estadounidense->Onza líquida imperial", 0.0208033),
        Map.entry("Cucharada estadounidense->Cucharada imperial", 0.0625),
        Map.entry("Cucharada estadounidense->Cucharadita imperial", 0.1875),
        Map.entry("Cucharada estadounidense->Pie cúbico", 0.00000855),
        Map.entry("Cucharada estadounidense->Pulgada cúbica", 0.87492),

        // Conversión de Cucharadita estadounidense
        Map.entry("Cucharadita estadounidense->Mililitros", 4.92892),
        Map.entry("Cucharadita estadounidense->Litros", 0.00492892),
        Map.entry("Cucharadita estadounidense->Galones", 0.00130208),
        Map.entry("Cucharadita estadounidense->Galón estadounidense", 0.00130208),
        Map.entry("Cucharadita estadounidense->Cuarto estadounidense", 0.00520833),
        Map.entry("Cucharadita estadounidense->Pinta estadounidense", 0.0104167),
        Map.entry("Cucharadita estadounidense->Taza americana oficial", 0.0208333),
        Map.entry("Cucharadita estadounidense->Onza líquida estadounidense", 0.202884),
        Map.entry("Cucharadita estadounidense->Cucharada estadounidense", 0.333333),
        Map.entry("Cucharadita estadounidense->Cucharadita estadounidense", 1.0),
        Map.entry("Cucharadita estadounidense->Metro cúbico", 0.00000492892),
        Map.entry("Cucharadita estadounidense->Galón imperial", 0.0002629),
        Map.entry("Cucharadita estadounidense->Cuarto imperial", 0.00520833),
        Map.entry("Cucharadita estadounidense->Pinta imperial", 0.0104167),
        Map.entry("Cucharadita estadounidense->Taza imperial", 0.0208333),
        Map.entry("Cucharadita estadounidense->Onza líquida imperial", 0.00681249),
        Map.entry("Cucharadita estadounidense->Cucharada imperial", 0.0208333),
        Map.entry("Cucharadita estadounidense->Cucharadita imperial", 0.0625),
        Map.entry("Cucharadita estadounidense->Pie cúbico", 0.00000283),
        Map.entry("Cucharadita estadounidense->Pulgada cúbica", 0.29363),

        // Conversión de Metro cúbico
        Map.entry("Metro cúbico->Mililitros", 1000000.0),
        Map.entry("Metro cúbico->Litros", 1000.0),
        Map.entry("Metro cúbico->Galones", 264.172),
        Map.entry("Metro cúbico->Galón estadounidense", 264.172),
        Map.entry("Metro cúbico->Cuarto estadounidense", 4226.75),
        Map.entry("Metro cúbico->Pinta estadounidense", 2113.38),
        Map.entry("Metro cúbico->Taza americana oficial", 422.675),
        Map.entry("Metro cúbico->Onza líquida estadounidense", 33814.0),
        Map.entry("Metro cúbico->Cucharada estadounidense", 67628.0),
        Map.entry("Metro cúbico->Cucharadita estadounidense", 202884.0),
        Map.entry("Metro cúbico->Metro cúbico", 1.0),
        Map.entry("Metro cúbico->Galón imperial", 219.969),
        Map.entry("Metro cúbico->Cuarto imperial", 422.675),
        Map.entry("Metro cúbico->Pinta imperial", 844.25),
        Map.entry("Metro cúbico->Taza imperial", 1000.0),
        Map.entry("Metro cúbico->Onza líquida imperial", 33814.0),
        Map.entry("Metro cúbico->Cucharada imperial", 67628.0),
        Map.entry("Metro cúbico->Cucharadita imperial", 202884.0),
        Map.entry("Metro cúbico->Pie cúbico", 35.3147),
        Map.entry("Metro cúbico->Pulgada cúbica", 61023.7),

        // Conversión de Galón imperial
        Map.entry("Galón imperial->Mililitros", 4546.09),
        Map.entry("Galón imperial->Litros", 4.54609),
        Map.entry("Galón imperial->Galones", 1.0),
        Map.entry("Galón imperial->Galón estadounidense", 1.20095),
        Map.entry("Galón imperial->Cuarto estadounidense", 4.0),
        Map.entry("Galón imperial->Pinta estadounidense", 8.0),
        Map.entry("Galón imperial->Taza americana oficial", 16.0),
        Map.entry("Galón imperial->Onza líquida estadounidense", 128.0),
        Map.entry("Galón imperial->Cucharada estadounidense", 256.0),
        Map.entry("Galón imperial->Cucharadita estadounidense", 768.0),
        Map.entry("Galón imperial->Metro cúbico", 0.000454609),
        Map.entry("Galón imperial->Galón imperial", 1.0),
        Map.entry("Galón imperial->Cuarto imperial", 4.0),
        Map.entry("Galón imperial->Pinta imperial", 8.0),
        Map.entry("Galón imperial->Taza imperial", 16.0),
        Map.entry("Galón imperial->Onza líquida imperial", 128.0),
        Map.entry("Galón imperial->Cucharada imperial", 256.0),
        Map.entry("Galón imperial->Cucharadita imperial", 768.0),
        Map.entry("Galón imperial->Pie cúbico", 0.00011983),
        Map.entry("Galón imperial->Pulgada cúbica", 277.419),

        // Conversión de Cuarto imperial
        Map.entry("Cuarto imperial->Mililitros", 1136.52),
        Map.entry("Cuarto imperial->Litros", 1.13652),
        Map.entry("Cuarto imperial->Galones", 0.25),
        Map.entry("Cuarto imperial->Galón estadounidense", 0.300781),
        Map.entry("Cuarto imperial->Cuarto estadounidense", 1.0),
        Map.entry("Cuarto imperial->Pinta estadounidense", 2.0),
        Map.entry("Cuarto imperial->Taza americana oficial", 4.0),
        Map.entry("Cuarto imperial->Onza líquida estadounidense", 32.0),
        Map.entry("Cuarto imperial->Cucharada estadounidense", 64.0),
        Map.entry("Cuarto imperial->Cucharadita estadounidense", 192.0),
        Map.entry("Cuarto imperial->Metro cúbico", 0.00113652),
        Map.entry("Cuarto imperial->Galón imperial", 0.25),
        Map.entry("Cuarto imperial->Cuarto imperial", 1.0),
        Map.entry("Cuarto imperial->Pinta imperial", 2.0),
        Map.entry("Cuarto imperial->Taza imperial", 4.0),
        Map.entry("Cuarto imperial->Onza líquida imperial", 32.0),
        Map.entry("Cuarto imperial->Cucharada imperial", 64.0),
        Map.entry("Cuarto imperial->Cucharadita imperial", 192.0),
        Map.entry("Cuarto imperial->Pie cúbico", 0.00005961),
        Map.entry("Cuarto imperial->Pulgada cúbica", 68.5793)
);
    @Override
    public String convertir(double valor, String unidadEntrada, String unidadSalida) {
        String conversionKey = unidadEntrada + "->" + unidadSalida;
        double factor = conversionFactors.getOrDefault(conversionKey, 1.0);
        return convertirUnidades(valor, factor);  // Usa el método de ConversionLogica
    }
}