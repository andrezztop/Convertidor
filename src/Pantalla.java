import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Pantalla extends JFrame {
    private JTextField inputField, outputField;
    private JComboBox<String> inputUnitBox, outputUnitBox, categoryBox;

    // Mapa para las unidades de cada categoría
    private final Map<String, String[]> unidades = Map.of(
            "Tiempo", new String[]{"Segundos", "Minutos", "Horas"},
            "Volumen", new String[]{"Mililitros", "Litros", "Galones"}
    );

    // Mapa para las conversiones por categoría
    private final Map<String, Convertidor> convertidores = Map.of(
            "Tiempo", new ConvertidorTiempo(),
            "Volumen", new ConvertidorVolumen()
    );

    public Pantalla() {
        setTitle("Convertidor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Panel de categoría (Tiempo o Volumen)
        JPanel categoryPanel = new JPanel(new FlowLayout());
        categoryBox = new JComboBox<>(convertidores.keySet().toArray(new String[0]));  // Opciones de categoría
        categoryPanel.add(new JLabel("Categoría:"));
        categoryPanel.add(categoryBox);

        // Panel de entrada
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(10);
        inputUnitBox = new JComboBox<>(unidades.get("Tiempo"));  // Usa las unidades de tiempo inicialmente
        inputPanel.add(inputField);
        inputPanel.add(inputUnitBox);

        // Panel de salida
        JPanel outputPanel = new JPanel(new FlowLayout());
        outputField = new JTextField(10);
        outputField.setEditable(false);
        outputUnitBox = new JComboBox<>(unidades.get("Tiempo"));  // Usa las unidades de tiempo inicialmente
        outputPanel.add(outputField);
        outputPanel.add(outputUnitBox);

        // Etiqueta de igualdad
        JLabel equalsLabel = new JLabel("=");
        equalsLabel.setHorizontalAlignment(JLabel.CENTER);

        // Añadir componentes a la ventana
        add(categoryPanel);
        add(inputPanel);
        add(equalsLabel);
        add(outputPanel);

        inputField.setText("60");  // Valor predeterminado (60 segundos)
        inputUnitBox.setSelectedItem("Segundos");  // Unidad de entrada predeterminada
        outputUnitBox.setSelectedItem("Minutos");  // Unidad de salida predeterminada

        // Actualiza las unidades cuando se selecciona una categoría
        categoryBox.addActionListener(e -> actualizarUnidades());

        // Actualiza la conversión al cambiar el texto o las unidades
        inputField.getDocument().addDocumentListener(new SimpleDocumentListener() {
            @Override
            public void update() {
                convertir();  // Llama a la conversión cuando cambia el texto
            }
        });

        inputUnitBox.addActionListener(e -> convertir());  // Llama a la conversión cuando cambia la unidad de entrada
        outputUnitBox.addActionListener(e -> convertir()); // Llama a la conversión cuando cambia la unidad de salida

        convertir();  // Realiza una conversión inicial
        setLocationRelativeTo(null);  // Centra la ventana
    }

    // Método para actualizar las unidades dependiendo de la categoría seleccionada sin usar if
    private void actualizarUnidades() {
        String categoriaSeleccionada = (String) categoryBox.getSelectedItem();
        inputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get(categoriaSeleccionada)));
        outputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get(categoriaSeleccionada)));
        convertir();  // Realiza la conversión tras actualizar las unidades
    }

    private void convertir() {
        try {
            double valor = Double.parseDouble(inputField.getText());  // Obtén el valor a convertir
            String unidadEntrada = (String) inputUnitBox.getSelectedItem();  // Obtén la unidad de entrada
            String unidadSalida = (String) outputUnitBox.getSelectedItem();  // Obtén la unidad de salida
            String categoria = (String) categoryBox.getSelectedItem();  // Obtén la categoría seleccionada

            // Llama al convertidor correspondiente sin usar if
            String resultado = convertidores.get(categoria).convertir(valor, unidadEntrada, unidadSalida);
            outputField.setText(resultado);  // Muestra el resultado en el campo de salida
        } catch (NumberFormatException e) {
            outputField.setText("Error");  // Muestra "Error" si el valor de entrada no es válido
        }
    }

    // DocumentListener simplificado para detectar cambios en el JTextField
    class SimpleDocumentListener implements javax.swing.event.DocumentListener {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
        public void update() {}
    }

    // Interfaz común para los convertidores
    interface Convertidor {
        String convertir(double valor, String unidadEntrada, String unidadSalida);
    }

    // Convertidor para Tiempo
public class ConvertidorTiempo extends ConversionLogica implements Pantalla.Convertidor {
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

    @Override
    public String convertir(double valor, String unidadEntrada, String unidadSalida) {
        String conversionKey = unidadEntrada + "->" + unidadSalida;
        double factor = conversionFactors.getOrDefault(conversionKey, 1.0);
        return convertirUnidades(valor, factor);  // Usa el método de ConversionLogica
    }
}

    // Convertidor para Volumen
public class ConvertidorVolumen extends ConversionLogica implements Pantalla.Convertidor {
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
}