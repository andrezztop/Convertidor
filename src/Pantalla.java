import javax.swing.*;
import java.awt.*;

public class Pantalla extends JFrame {
    private JTextField inputField, outputField;
    private JComboBox<String> inputUnitBox, outputUnitBox;
    // Actualiza las unidades a convertir entre tiempo
    private final String[] units = {"Segundos", "Minutos", "Horas"};
    private final Tiempo convertidorDeTiempo = new Tiempo();  // Instancia de la clase hija Tiempo

    public Pantalla() {
        setTitle("Convertidor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLayout(new GridLayout(3, 1, 10, 10));

        // Panel de entrada
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(10);
        inputUnitBox = new JComboBox<>(units);  // Usa las unidades de tiempo
        inputPanel.add(inputField);
        inputPanel.add(inputUnitBox);

        // Panel de salida
        JPanel outputPanel = new JPanel(new FlowLayout());
        outputField = new JTextField(10);
        outputField.setEditable(false);
        outputUnitBox = new JComboBox<>(units);  // Usa las unidades de tiempo
        outputPanel.add(outputField);
        outputPanel.add(outputUnitBox);

        // Etiqueta de igualdad
        JLabel equalsLabel = new JLabel("=");
        equalsLabel.setHorizontalAlignment(JLabel.CENTER);

        // Añadir componentes a la ventana
        add(inputPanel);
        add(equalsLabel);
        add(outputPanel);

        inputField.setText("60");  // Valor predeterminado (60 segundos)
        inputUnitBox.setSelectedItem("Segundos");  // Unidad de entrada predeterminada
        outputUnitBox.setSelectedItem("Minutos");  // Unidad de salida predeterminada

        // Actualiza la conversión al cambiar el texto o las unidades
        inputField.getDocument().addDocumentListener(new SimpleDocumentListener() {
            @Override
            public void update() {
                convertirTiempo();  // Llama a la conversión cuando cambia el texto
            }
        });

        inputUnitBox.addActionListener(e -> convertirTiempo());  // Llama a la conversión cuando cambia la unidad de entrada
        outputUnitBox.addActionListener(e -> convertirTiempo()); // Llama a la conversión cuando cambia la unidad de salida

        convertirTiempo();  // Realiza una conversión inicial
        setLocationRelativeTo(null);  // Centra la ventana
    }

    private void convertirTiempo() {
        try {
            double valor = Double.parseDouble(inputField.getText());  // Obtén el valor a convertir
            String unidadEntrada = (String) inputUnitBox.getSelectedItem();  // Obtén la unidad de entrada
            String unidadSalida = (String) outputUnitBox.getSelectedItem();  // Obtén la unidad de salida
            
            // Llama al método de la clase Tiempo para convertir
            String resultado = convertidorDeTiempo.convertirUnidades(valor, unidadEntrada, unidadSalida);
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
}

