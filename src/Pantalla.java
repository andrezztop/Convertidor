import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class Pantalla extends JFrame {
    private final JTextField inputField, outputField;
    private final JComboBox<String> inputUnitBox, outputUnitBox, categoryBox;

    private final Map<String, String[]> unidades = Map.of(
            "Tiempo", new String[]{"Nanosegundo", "Microsegundo", "Milisegundo", "Segundos", "Minutos", "Horas", "Día", "Semana", "Mes", "Año", "Década", "Siglo"},
            "Volumen", new String[]{"Mililitros", "Litros", "Galones"}
    );

    private final Map<String, Convertidor> convertidores = Map.of(
            "Tiempo", new Tiempo(),
            "Volumen", new Volumen()
    );

    private boolean updatingFields = false;

    public Pantalla() {
        setTitle("Convertidor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 250);
        setLayout(new GridLayout(4, 1, 10, 10));

        JPanel categoryPanel = new JPanel(new FlowLayout());
        categoryBox = new JComboBox<>(convertidores.keySet().toArray(new String[0]));
        categoryPanel.add(new JLabel("Categoría:"));
        categoryPanel.add(categoryBox);
        categoryBox.setSelectedItem("Tiempo");

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(10);
        inputUnitBox = new JComboBox<>(unidades.get("Tiempo"));
        inputPanel.add(inputField);
        inputPanel.add(inputUnitBox);

        JPanel outputPanel = new JPanel(new FlowLayout());
        outputField = new JTextField(10);
        outputUnitBox = new JComboBox<>(unidades.get("Tiempo"));
        outputPanel.add(outputField);
        outputPanel.add(outputUnitBox);

        JLabel equalsLabel = new JLabel("=");
        equalsLabel.setHorizontalAlignment(JLabel.CENTER);

        add(categoryPanel);
        add(inputPanel);
        add(equalsLabel);
        add(outputPanel);
        
        inputField.setText("1");
        inputUnitBox.setSelectedItem("Minutos");
        outputUnitBox.setSelectedItem("Segundos");

        categoryBox.addActionListener(e -> actualizarUnidades());

        inputField.getDocument().addDocumentListener(new SimpleDocumentListener() {
            @Override
            public void update() {
                if (!updatingFields) {
                    convertir(inputField, inputUnitBox, outputField, outputUnitBox);
                }
            }
        });

        outputField.getDocument().addDocumentListener(new SimpleDocumentListener() {
            @Override
            public void update() {
                if (!updatingFields) {
                    convertir(outputField, outputUnitBox, inputField, inputUnitBox);
                }
            }
        });

        inputUnitBox.addActionListener(e -> {
            if (!updatingFields) {
                convertir(inputField, inputUnitBox, outputField, outputUnitBox);
            }
        });

        outputUnitBox.addActionListener(e -> {
            if (!updatingFields) {
                convertir(outputField, outputUnitBox, inputField, inputUnitBox);
            }
        });

        convertir(inputField, inputUnitBox, outputField, outputUnitBox);
        setLocationRelativeTo(null);
    }

    private void actualizarUnidades() {
        String categoriaSeleccionada = (String) categoryBox.getSelectedItem();

        Map<String, Runnable> configuradores = Map.of(
            "Tiempo", () -> {
                inputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get("Tiempo")));
                outputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get("Tiempo")));
                inputUnitBox.setSelectedItem("Minutos");
                outputUnitBox.setSelectedItem("Segundos");
            },
            "Volumen", () -> {
                inputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get("Volumen")));
                outputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get("Volumen")));
                inputUnitBox.setSelectedItem("Mililitros");
                outputUnitBox.setSelectedItem("Litros");
            }
        );

        configuradores.get(categoriaSeleccionada).run();
        convertir(inputField, inputUnitBox, outputField, outputUnitBox);
    }

private void convertir(JTextField sourceField, JComboBox<String> sourceUnitBox, 
                           JTextField targetField, JComboBox<String> targetUnitBox) {
        updatingFields = true;
        try {
            String inputText = sourceField.getText();
            double inputValue = parseDouble(inputText);
            String unidadEntrada = (String) sourceUnitBox.getSelectedItem();
            String unidadSalida = (String) targetUnitBox.getSelectedItem();

            Convertidor convertidor = convertidores.get(categoryBox.getSelectedItem());
            String resultado = convertidor.convertir(inputValue, unidadEntrada, unidadSalida);
            targetField.setText(resultado);
        } catch (NumberFormatException e) {
            // If parsing fails, don't update the target field
        } finally {
            updatingFields = false;
        }
    }

    private double parseDouble(String value) {
        return value == null || value.isEmpty() ? 0.0 :
               tryParse(value.toLowerCase().replaceAll("e$|e\\+$|e-$", "e0"));
    }

    private double tryParse(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
}
    class SimpleDocumentListener implements javax.swing.event.DocumentListener {
        @Override
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            update();
        }

        @Override
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            update();
        }

        @Override
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            update();
        }

        public void update() {}
    }

    interface Convertidor {
        String convertir(double valor, String unidadEntrada, String unidadSalida);
    }
}