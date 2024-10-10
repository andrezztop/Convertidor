import java.util.Map;

    public class Tiempo extends ConversionLogica implements Pantalla.Convertidor {
                private final Map<String, Double> conversionFactors = Map.ofEntries(
                    // Nanosegundo a otras unidades
                    Map.entry("Nanosegundo->Microsegundo", 1.0e-3),
                    Map.entry("Nanosegundo->Milisegundo", 1.0e-6),
                    Map.entry("Nanosegundo->Segundos", 1.0e-9),
                    Map.entry("Nanosegundo->Minutos", 1.6667e-11),
                    Map.entry("Nanosegundo->Horas", 2.7778e-13),
                    Map.entry("Nanosegundo->Dia", 1.1574e-14),
                    Map.entry("Nanosegundo->Semana", 1.6534e-15),
                    Map.entry("Nanosegundo->Mes", 3.8580e-16),
                    Map.entry("Nanosegundo->Año", 3.1709e-17),
                    Map.entry("Nanosegundo->Decada", 3.1709e-18),
                    Map.entry("Nanosegundo->Siglo", 3.1709e-19),
            
                    // Microsegundo a otras unidades
                    Map.entry("Microsegundo->Nanosegundo", 1.0e3),
                    Map.entry("Microsegundo->Milisegundo", 1.0e-3),
                    Map.entry("Microsegundo->Segundos", 1.0e-6),
                    Map.entry("Microsegundo->Minutos", 1.6667e-8),
                    Map.entry("Microsegundo->Horas", 2.7778e-10),
                    Map.entry("Microsegundo->Dia", 1.1574e-11),
                    Map.entry("Microsegundo->Semana", 1.6534e-12),
                    Map.entry("Microsegundo->Mes", 3.8580e-13),
                    Map.entry("Microsegundo->Año", 3.1709e-14),
                    Map.entry("Microsegundo->Decada", 3.1709e-15),
                    Map.entry("Microsegundo->Siglo", 3.1709e-16),
            
                    // Milisegundo a otras unidades
                    Map.entry("Milisegundo->Nanosegundo", 1.0e6),
                    Map.entry("Milisegundo->Microsegundo", 1.0e3),
                    Map.entry("Milisegundo->Segundos", 1.0e-3),
                    Map.entry("Milisegundo->Minutos", 1.6667e-5),
                    Map.entry("Milisegundo->Horas", 2.7778e-7),
                    Map.entry("Milisegundo->Dia", 1.1574e-8),
                    Map.entry("Milisegundo->Semana", 1.6534e-9),
                    Map.entry("Milisegundo->Mes", 3.8580e-10),
                    Map.entry("Milisegundo->Año", 3.1709e-11),
                    Map.entry("Milisegundo->Decada", 3.1709e-12),
                    Map.entry("Milisegundo->Siglo", 3.1709e-13),
            
                    // Segundos a otras unidades
                    Map.entry("Segundos->Nanosegundo", 1.0e9),
                    Map.entry("Segundos->Microsegundo", 1.0e6),
                    Map.entry("Segundos->Milisegundo", 1.0e3),
                    Map.entry("Segundos->Minutos", 1 / 60.0),
                    Map.entry("Segundos->Horas", 1 / 3600.0),
                    Map.entry("Segundos->Dia", 1 / 86400.0),
                    Map.entry("Segundos->Semana", 1 / 604800.0),
                    Map.entry("Segundos->Mes", 1 / 2592000.0),
                    Map.entry("Segundos->Año", 1 / 31536000.0),
                    Map.entry("Segundos->Decada", 1 / 315360000.0),
                    Map.entry("Segundos->Siglo", 1 / 3153600000.0),
            
                    // Minutos a otras unidades
                    Map.entry("Minutos->Nanosegundo", 6.0e10),
                    Map.entry("Minutos->Microsegundo", 6.0e7),
                    Map.entry("Minutos->Milisegundo", 6.0e4),
                    Map.entry("Minutos->Segundos", 60.0),
                    Map.entry("Minutos->Horas", 1 / 60.0),
                    Map.entry("Minutos->Dia", 1 / 1440.0),
                    Map.entry("Minutos->Semana", 1 / 10080.0),
                    Map.entry("Minutos->Mes", 1 / 43200.0),
                    Map.entry("Minutos->Año", 1 / 525600.0),
                    Map.entry("Minutos->Decada", 1 / 5256000.0),
                    Map.entry("Minutos->Siglo", 1 / 52560000.0),
            
                    // Horas a otras unidades
                    Map.entry("Horas->Nanosegundo", 3.6e12),
                    Map.entry("Horas->Microsegundo", 3.6e9),
                    Map.entry("Horas->Milisegundo", 3.6e6),
                    Map.entry("Horas->Segundos", 3600.0),
                    Map.entry("Horas->Minutos", 60.0),
                    Map.entry("Horas->Dia", 1 / 24.0),
                    Map.entry("Horas->Semana", 1 / 168.0),
                    Map.entry("Horas->Mes", 1 / 720.0),
                    Map.entry("Horas->Año", 1 / 8760.0),
                    Map.entry("Horas->Decada", 1 / 87600.0),
                    Map.entry("Horas->Siglo", 1 / 876000.0),
        
        // Día a otras unidades
                    Map.entry("Dia->Nanosegundo", 8.64e13),
                    Map.entry("Dia->Microsegundo", 8.64e10),
                    Map.entry("Dia->Milisegundo", 8.64e7),
                    Map.entry("Dia->Segundos", 86400.0),
                    Map.entry("Dia->Minutos", 1440.0),
                    Map.entry("Dia->Horas", 24.0),
                    Map.entry("Dia->Semana", 1 / 7.0),
                    Map.entry("Dia->Mes", 1 / 30.0),
                    Map.entry("Dia->Año", 1 / 365.0),
                    Map.entry("Dia->Decada", 1 / 3650.0),
                    Map.entry("Dia->Siglo", 1 / 36500.0),
            
                    // Semana a otras unidades
                    Map.entry("Semana->Nanosegundo", 6.048e14),
                    Map.entry("Semana->Microsegundo", 6.048e11),
                    Map.entry("Semana->Milisegundo", 6.048e8),
                    Map.entry("Semana->Segundos", 604800.0),
                    Map.entry("Semana->Minutos", 10080.0),
                    Map.entry("Semana->Horas", 168.0),
                    Map.entry("Semana->Dia", 7.0),
                    Map.entry("Semana->Mes", 1 / 4.345),
                    Map.entry("Semana->Año", 1 / 52.143),
                    Map.entry("Semana->Decada", 1 / 521.43),
                    Map.entry("Semana->Siglo", 1 / 5214.3),
            
                    // Mes a otras unidades
                    Map.entry("Mes->Nanosegundo", 2.628e15),
                    Map.entry("Mes->Microsegundo", 2.628e12),
                    Map.entry("Mes->Milisegundo", 2.628e9),
                    Map.entry("Mes->Segundos", 2.628e6),
                    Map.entry("Mes->Minutos", 43800.0),
                    Map.entry("Mes->Horas", 730.0),
                    Map.entry("Mes->Dia", 30.44),
                    Map.entry("Mes->Semana", 4.345),
                    Map.entry("Mes->Año", 1 / 12.0),
                    Map.entry("Mes->Decada", 1 / 120.0),
                    Map.entry("Mes->Siglo", 1 / 1200.0),
            
                    // Año a otras unidades
                    Map.entry("Año->Nanosegundo", 3.1536e16),
                    Map.entry("Año->Microsegundo", 3.1536e13),
                    Map.entry("Año->Milisegundo", 3.1536e10),
                    Map.entry("Año->Segundos", 31536000.0),
                    Map.entry("Año->Minutos", 525600.0),
                    Map.entry("Año->Horas", 8760.0),
                    Map.entry("Año->Dia", 365.0),
                    Map.entry("Año->Semana", 52.143),
                    Map.entry("Año->Mes", 12.0),
                    Map.entry("Año->Decada", 1 / 10.0),
                    Map.entry("Año->Siglo", 1 / 100.0),
            
                    // Década a otras unidades
                    Map.entry("Decada->Nanosegundo", 3.1536e17),
                    Map.entry("Decada->Microsegundo", 3.1536e14),
                    Map.entry("Decada->Milisegundo", 3.1536e11),
                    Map.entry("Decada->Segundos", 315360000.0),
                    Map.entry("Decada->Minutos", 5256000.0),
                    Map.entry("Decada->Horas", 87600.0),
                    Map.entry("Decada->Dia", 3650.0),
                    Map.entry("Decada->Semana", 521.43),
                    Map.entry("Decada->Mes", 120.0),
                    Map.entry("Decada->Año", 10.0),
                    Map.entry("Decada->Siglo", 1 / 10.0),
            
                    // Siglo a otras unidades
                    Map.entry("Siglo->Nanosegundo", 3.1536e18),
                    Map.entry("Siglo->Microsegundo", 3.1536e15),
                    Map.entry("Siglo->Milisegundo", 3.1536e12),
                    Map.entry("Siglo->Segundos", 3.1536e9),
                    Map.entry("Siglo->Minutos", 52560000.0),
                    Map.entry("Siglo->Horas", 876000.0),
                    Map.entry("Siglo->Dia", 36500.0),
                    Map.entry("Siglo->Semana", 5214.3),
                    Map.entry("Siglo->Mes", 1200.0),
                    Map.entry("Siglo->Año", 100.0),
                    Map.entry("Siglo->Decada", 10.0),
            
                    // Unidades idénticas
                    Map.entry("Nanosegundo->Nanosegundo", 1.0),
                    Map.entry("Microsegundo->Microsegundo", 1.0),
                    Map.entry("Milisegundo->Milisegundo", 1.0),
                    Map.entry("Segundos->Segundos", 1.0),
                    Map.entry("Minutos->Minutos", 1.0),
                    Map.entry("Horas->Horas", 1.0),
                    Map.entry("Dia->Dia", 1.0),
                    Map.entry("Semana->Semana", 1.0),
                    Map.entry("Mes->Mes", 1.0),
                    Map.entry("Año->Año", 1.0),
                    Map.entry("Decada->Decada", 1.0)
    );
private final Map<String, String> formulas = Map.ofEntries(
    // Nanosegundo a otras unidades
    Map.entry("Nanosegundo->Microsegundo", "Fórmula: Nanosegundo * 1.0e-3"),
    Map.entry("Nanosegundo->Milisegundo", "Fórmula: Nanosegundo * 1.0e-6"),
    Map.entry("Nanosegundo->Segundos", "Fórmula: Nanosegundo * 1.0e-9"),
    Map.entry("Nanosegundo->Minutos", "Fórmula: Nanosegundo * 1.6667e-11"),
    Map.entry("Nanosegundo->Horas", "Fórmula: Nanosegundo * 2.7778e-13"),
    Map.entry("Nanosegundo->Dia", "Fórmula: Nanosegundo * 1.1574e-14"),
    Map.entry("Nanosegundo->Semana", "Fórmula: Nanosegundo * 1.6534e-15"),
    Map.entry("Nanosegundo->Mes", "Fórmula: Nanosegundo * 3.8580e-16"),
    Map.entry("Nanosegundo->Año", "Fórmula: Nanosegundo * 3.1709e-17"),
    Map.entry("Nanosegundo->Decada", "Fórmula: Nanosegundo * 3.1709e-18"),
    Map.entry("Nanosegundo->Siglo", "Fórmula: Nanosegundo * 3.1709e-19"),
    
    // Microsegundo a otras unidades
    Map.entry("Microsegundo->Nanosegundo", "Fórmula: Microsegundo * 1.0e3"),
    Map.entry("Microsegundo->Milisegundo", "Fórmula: Microsegundo * 1.0e-3"),
    Map.entry("Microsegundo->Segundos", "Fórmula: Microsegundo * 1.0e-6"),
    Map.entry("Microsegundo->Minutos", "Fórmula: Microsegundo * 1.6667e-8"),
    Map.entry("Microsegundo->Horas", "Fórmula: Microsegundo * 2.7778e-10"),
    Map.entry("Microsegundo->Dia", "Fórmula: Microsegundo * 1.1574e-11"),
    Map.entry("Microsegundo->Semana", "Fórmula: Microsegundo * 1.6534e-12"),
    Map.entry("Microsegundo->Mes", "Fórmula: Microsegundo * 3.8580e-13"),
    Map.entry("Microsegundo->Año", "Fórmula: Microsegundo * 3.1709e-14"),
    Map.entry("Microsegundo->Decada", "Fórmula: Microsegundo * 3.1709e-15"),
    Map.entry("Microsegundo->Siglo", "Fórmula: Microsegundo * 3.1709e-16"),
    
    // Milisegundo a otras unidades
    Map.entry("Milisegundo->Nanosegundo", "Fórmula: Milisegundo * 1.0e6"),
    Map.entry("Milisegundo->Microsegundo", "Fórmula: Milisegundo * 1.0e3"),
    Map.entry("Milisegundo->Segundos", "Fórmula: Milisegundo * 1.0e-3"),
    Map.entry("Milisegundo->Minutos", "Fórmula: Milisegundo * 1.6667e-5"),
    Map.entry("Milisegundo->Horas", "Fórmula: Milisegundo * 2.7778e-7"),
    Map.entry("Milisegundo->Dia", "Fórmula: Milisegundo * 1.1574e-8"),
    Map.entry("Milisegundo->Semana", "Fórmula: Milisegundo * 1.6534e-9"),
    Map.entry("Milisegundo->Mes", "Fórmula: Milisegundo * 3.8580e-10"),
    Map.entry("Milisegundo->Año", "Fórmula: Milisegundo * 3.1709e-11"),
    Map.entry("Milisegundo->Decada", "Fórmula: Milisegundo * 3.1709e-12"),
    Map.entry("Milisegundo->Siglo", "Fórmula: Milisegundo * 3.1709e-13"),
    
    // Segundos a otras unidades
    Map.entry("Segundos->Nanosegundo", "Fórmula: Segundos * 1.0e9"),
    Map.entry("Segundos->Microsegundo", "Fórmula: Segundos * 1.0e6"),
    Map.entry("Segundos->Milisegundo", "Fórmula: Segundos * 1.0e3"),
    Map.entry("Segundos->Minutos", "Fórmula: Segundos * (1 / 60.0)"),
    Map.entry("Segundos->Horas", "Fórmula: Segundos * (1 / 3600.0)"),
    Map.entry("Segundos->Dia", "Fórmula: Segundos * (1 / 86400.0)"),
    Map.entry("Segundos->Semana", "Fórmula: Segundos * (1 / 604800.0)"),
    Map.entry("Segundos->Mes", "Fórmula: Segundos * (1 / 2592000.0)"),
    Map.entry("Segundos->Año", "Fórmula: Segundos * (1 / 31536000.0)"),
    Map.entry("Segundos->Decada", "Fórmula: Segundos * (1 / 315360000.0)"),
    Map.entry("Segundos->Siglo", "Fórmula: Segundos * (1 / 3153600000.0)"),
    
    // Minutos a otras unidades
    Map.entry("Minutos->Nanosegundo", "Fórmula: Minutos * 6.0e10"),
    Map.entry("Minutos->Microsegundo", "Fórmula: Minutos * 6.0e7"),
    Map.entry("Minutos->Milisegundo", "Fórmula: Minutos * 6.0e4"),
    Map.entry("Minutos->Segundos", "Fórmula: Minutos * 60.0"),
    Map.entry("Minutos->Horas", "Fórmula: Minutos * (1 / 60.0)"),
    Map.entry("Minutos->Dia", "Fórmula: Minutos * (1 / 1440.0)"),
    Map.entry("Minutos->Semana", "Fórmula: Minutos * (1 / 10080.0)"),
    Map.entry("Minutos->Mes", "Fórmula: Minutos * (1 / 43200.0)"),
    Map.entry("Minutos->Año", "Fórmula: Minutos * (1 / 525600.0)"),
    Map.entry("Minutos->Decada", "Fórmula: Minutos * (1 / 5256000.0)"),
    Map.entry("Minutos->Siglo", "Fórmula: Minutos * (1 / 52560000.0)"),
    
    // Horas a otras unidades
    Map.entry("Horas->Nanosegundo", "Fórmula: Horas * 3.6e12"),
    Map.entry("Horas->Microsegundo", "Fórmula: Horas * 3.6e9"),
    Map.entry("Horas->Milisegundo", "Fórmula: Horas * 3.6e6"),
    Map.entry("Horas->Segundos", "Fórmula: Horas * 3600.0"),
    Map.entry("Horas->Minutos", "Fórmula: Horas * 60.0"),
    Map.entry("Horas->Dia", "Fórmula: Horas * (1 / 24.0)"),
    Map.entry("Horas->Semana", "Fórmula: Horas * (1 / 168.0)"),
    Map.entry("Horas->Mes", "Fórmula: Horas * (1 / 720.0)"),
    Map.entry("Horas->Año", "Fórmula: Horas * (1 / 8760.0)"),
    Map.entry("Horas->Decada", "Fórmula: Horas * (1 / 87600.0)"),
    Map.entry("Horas->Siglo", "Fórmula: Horas * (1 / 876000.0)"),
    
    // Día a otras unidades
    Map.entry("Dia->Nanosegundo", "Fórmula: Dia * 8.64e13"),
    Map.entry("Dia->Microsegundo", "Fórmula: Dia * 8.64e10"),
     Map.entry("Dia->Milisegundo", "Fórmula: Dia * 8.64e7"),
    Map.entry("Dia->Segundos", "Fórmula: Dia * 86400.0"),
    Map.entry("Dia->Minutos", "Fórmula: Dia * 1440.0"),
    Map.entry("Dia->Horas", "Fórmula: Dia * 24.0"),
    Map.entry("Dia->Semana", "Fórmula: Dia * (1 / 7.0)"),
    Map.entry("Dia->Mes", "Fórmula: Dia * (1 / 30.44)"),
    Map.entry("Dia->Año", "Fórmula: Dia * (1 / 365.25)"),
    Map.entry("Dia->Decada", "Fórmula: Dia * (1 / 3652.5)"),
    Map.entry("Dia->Siglo", "Fórmula: Dia * (1 / 36525.0)"),
    
    // Semana a otras unidades
    Map.entry("Semana->Nanosegundo", "Fórmula: Semana * 6.048e14"),
    Map.entry("Semana->Microsegundo", "Fórmula: Semana * 6.048e11"),
    Map.entry("Semana->Milisegundo", "Fórmula: Semana * 6.048e8"),
    Map.entry("Semana->Segundos", "Fórmula: Semana * 604800.0"),
    Map.entry("Semana->Minutos", "Fórmula: Semana * 10080.0"),
    Map.entry("Semana->Horas", "Fórmula: Semana * 168.0"),
    Map.entry("Semana->Dia", "Fórmula: Semana * 7.0"),
    Map.entry("Semana->Mes", "Fórmula: Semana * (1 / 4.345)"),
    Map.entry("Semana->Año", "Fórmula: Semana * (1 / 52.143)"),
    Map.entry("Semana->Decada", "Fórmula: Semana * (1 / 521.43)"),
    Map.entry("Semana->Siglo", "Fórmula: Semana * (1 / 5214.3)"),

    // Mes a otras unidades
    Map.entry("Mes->Nanosegundo", "Fórmula: Mes * 2.628e15"),
    Map.entry("Mes->Microsegundo", "Fórmula: Mes * 2.628e12"),
    Map.entry("Mes->Milisegundo", "Fórmula: Mes * 2.628e9"),
    Map.entry("Mes->Segundos", "Fórmula: Mes * 2592000.0"),
    Map.entry("Mes->Minutos", "Fórmula: Mes * 43200.0"),
    Map.entry("Mes->Horas", "Fórmula: Mes * 720.0"),
    Map.entry("Mes->Dia", "Fórmula: Mes * 30.44"),
    Map.entry("Mes->Semana", "Fórmula: Mes * 4.345"),
    Map.entry("Mes->Año", "Fórmula: Mes * (1 / 12.0)"),
    Map.entry("Mes->Decada", "Fórmula: Mes * (1 / 120.0)"),
    Map.entry("Mes->Siglo", "Fórmula: Mes * (1 / 1200.0)"),

    // Año a otras unidades
    Map.entry("Año->Nanosegundo", "Fórmula: Año * 3.15576e16"),
    Map.entry("Año->Microsegundo", "Fórmula: Año * 3.15576e13"),
    Map.entry("Año->Milisegundo", "Fórmula: Año * 3.15576e10"),
    Map.entry("Año->Segundos", "Fórmula: Año * 31536000.0"),
    Map.entry("Año->Minutos", "Fórmula: Año * 525600.0"),
    Map.entry("Año->Horas", "Fórmula: Año * 8760.0"),
    Map.entry("Año->Dia", "Fórmula: Año * 365.25"),
    Map.entry("Año->Semana", "Fórmula: Año * 52.143"),
    Map.entry("Año->Mes", "Fórmula: Año * 12.0"),
    Map.entry("Año->Decada", "Fórmula: Año * 0.1"),
    Map.entry("Año->Siglo", "Fórmula: Año * 0.01"),

    // Decada a otras unidades
    Map.entry("Decada->Nanosegundo", "Fórmula: Decada * 3.15576e17"),
    Map.entry("Decada->Microsegundo", "Fórmula: Decada * 3.15576e14"),
    Map.entry("Decada->Milisegundo", "Fórmula: Decada * 3.15576e11"),
    Map.entry("Decada->Segundos", "Fórmula: Decada * 315360000.0"),
    Map.entry("Decada->Minutos", "Fórmula: Decada * 5256000.0"),
    Map.entry("Decada->Horas", "Fórmula: Decada * 87600.0"),
    Map.entry("Decada->Dia", "Fórmula: Decada * 3652.5"),
    Map.entry("Decada->Semana", "Fórmula: Decada * 521.43"),
    Map.entry("Decada->Mes", "Fórmula: Decada * 120.0"),
    Map.entry("Decada->Año", "Fórmula: Decada * 10.0"),
    Map.entry("Decada->Siglo", "Fórmula: Decada * 0.1"),

    // Siglo a otras unidades
    Map.entry("Siglo->Nanosegundo", "Fórmula: Siglo * 3.15576e19"),
    Map.entry("Siglo->Microsegundo", "Fórmula: Siglo * 3.15576e16"),
    Map.entry("Siglo->Milisegundo", "Fórmula: Siglo * 3.15576e13"),
    Map.entry("Siglo->Segundos", "Fórmula: Siglo * 3155760000.0"),
    Map.entry("Siglo->Minutos", "Fórmula: Siglo * 52560000.0"),
    Map.entry("Siglo->Horas", "Fórmula: Siglo * 876000.0"),
    Map.entry("Siglo->Dia", "Fórmula: Siglo * 36525.0"),
    Map.entry("Siglo->Semana", "Fórmula: Siglo * 5214.3"),
    Map.entry("Siglo->Mes", "Fórmula: Siglo * 1200.0"),
    Map.entry("Siglo->Año", "Fórmula: Siglo * 100.0"),
    Map.entry("Siglo->Decada", "Fórmula: Siglo * 10.0")
);
    @Override
    public String convertir(double valor, String unidadEntrada, String unidadSalida) {
        String conversionKey = unidadEntrada + "->" + unidadSalida;
        double factor = conversionFactors.getOrDefault(conversionKey, 1.0);
        return convertirUnidades(valor, factor);  // Usa el método de ConversionLogica
    }
    
    public String obtenerFormula(String unidadEntrada, String unidadSalida) {
        return formulas.getOrDefault(unidadEntrada + "->" + unidadSalida, "Fórmula no disponible");
    }
}