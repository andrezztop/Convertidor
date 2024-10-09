import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Pantalla extends JFrame {
    private final JTextField inputField, outputField;
    private final JComboBox<String> inputUnitBox, outputUnitBox, categoryBox;

    // Mapa para las unidades de cada categoría
    private final Map<String, String[]> unidades = Map.of(
            "Tiempo", new String[]{"Nanosegundo","Microsegundo","Milisegundo","Segundos", "Minutos", "Horas","Dia","Semana","Mes","Año","Decada","Siglo"},
            "Volumen", new String[]{"Mililitros", "Litros", "Galones"}
    );

    // Mapa para las conversiones por categoría
    private final Map<String, Convertidor> convertidores = Map.of(
            "Tiempo", new Tiempo(),
            "Volumen", new Volumen()
    );

    public Pantalla() {
        setTitle("Convertidor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 250);
        setLayout(new GridLayout(4, 1, 10, 10));

        // Panel de categoría (Tiempo o Volumen)
        JPanel categoryPanel = new JPanel(new FlowLayout());
        categoryBox = new JComboBox<>(convertidores.keySet().toArray(new String[0]));  // Opciones de categoría
        categoryPanel.add(new JLabel("Categoría:"));
        categoryPanel.add(categoryBox);
        categoryBox.setSelectedItem("Tiempo"); 

        // Panel de entrada
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(10);
        inputUnitBox = new JComboBox<>(unidades.get("Tiempo"));  // Usa las unidades de tiempo inicialmente
        inputPanel.add(inputField);
        inputPanel.add(inputUnitBox);

        // Panel de salida
        JPanel outputPanel = new JPanel(new FlowLayout());
        outputField = new JTextField(10);
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

        inputField.setText("60");
        inputField.setText(inputField.getText().isEmpty() ? "0" : inputField.getText());// Valor predeterminado (60 segundos)
        
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

    // Método para actualizar las unidades dependiendo de la categoría seleccionada
private void actualizarUnidades() {
    String categoriaSeleccionada = (String) categoryBox.getSelectedItem();
    
    // Mapa que contiene los métodos de configuración para cada categoría
    Map<String, Runnable> configuradores = new HashMap<>();
    
    configuradores.put("Tiempo", () -> {
        inputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get("Tiempo")));
        outputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get("Tiempo")));
        // Establecer unidades predeterminadas: de segundos a minutos
        inputUnitBox.setSelectedItem("Segundos");
        outputUnitBox.setSelectedItem("Minutos");
    });

    configuradores.put("Volumen", () -> {
        inputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get("Volumen")));
        outputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get("Volumen")));
        // Establecer unidades predeterminadas: de mililitros a litros
        inputUnitBox.setSelectedItem("Mililitros");
        outputUnitBox.setSelectedItem("Litros");
    });

    // Ejecutar el configurador correspondiente
    configuradores.get(categoriaSeleccionada).run();

    // Realiza la conversión inicial después de actualizar las unidades
    convertir();
}

private void convertir() {
    try {
        // Establecer el valor de inputField a "0" si está vacío
        String valorTexto = inputField.getText().isEmpty() ? "0" : inputField.getText();
        double valor = Double.parseDouble(valorTexto);  // Obtén el valor a convertir
        String unidadEntrada = (String) inputUnitBox.getSelectedItem();  // Obtén la unidad de entrada
        String unidadSalida = (String) outputUnitBox.getSelectedItem();  // Obtén la unidad de salida
        String categoria = (String) categoryBox.getSelectedItem();  // Obtén la categoría seleccionada

        // Llama al convertidor correspondiente
        String resultado = convertidores.get(categoria).convertir(valor, unidadEntrada, unidadSalida);
        outputField.setText(resultado);  // Muestra el resultado en el campo de salida
    } catch (NumberFormatException e) {
        outputField.setText("0");  // Muestra "0" si el valor de entrada no es válido
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
}