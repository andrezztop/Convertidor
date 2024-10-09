import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Map;

public class Pantalla extends JFrame {
    private final JTextField inputField, outputField;
    private final JComboBox<String> inputUnitBox, outputUnitBox, categoryBox;
    private final JLabel formulaLabel;

    private final Map<String, String[]> unidades = Map.of(
            "Tiempo", new String[]{
                "Nanosegundo", 
                "Microsegundo", 
                "Milisegundo", 
                "Segundos", 
                "Minutos", 
                "Horas", 
                "Día", 
                "Semana", 
                "Mes", 
                "Año", 
                "Década", 
                "Siglo"
            },
            "Volumen", new String[]{
                "Mililitros", 
                "Litros", 
                "Galones",
                "Galón estadounidense",
                "Cuarto estadounidense",
                "Pinta estadounidense",
                "Taza americana oficial",
                "Onza líquida estadounidense",
                "Cucharada estadounidense",
                "Cucharadita estadounidense",
                "Metro cúbico",
                "Galón imperial",
                "Cuarto imperial",
                "Pinta imperial",
                "Taza imperial",
                "Onza líquida imperial",
                "Cucharada imperial",
                "Cucharadita imperial",
                "Pie cúbico",
                "Pulgada cúbica"
                }
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
            setLayout(new BorderLayout(10, 10));
            getContentPane().setBackground(new Color(32, 33, 36)); // Fondo oscuro

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBackground(new Color(32, 33, 36));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Selector de categoría
            categoryBox = new JComboBox<>(convertidores.keySet().toArray(new String[0]));
            styleComboBox(categoryBox);

            // Panel de conversión
            JPanel conversionPanel = new JPanel(new GridBagLayout());
            conversionPanel.setBackground(new Color(41, 42, 45));
            conversionPanel.setBorder(BorderFactory.createLineBorder(new Color(95, 99, 104), 1));

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);

            // Campo de entrada
            inputField = createStyledTextField();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            conversionPanel.add(inputField, gbc);

            // Selector de unidad de entrada
            inputUnitBox = new JComboBox<>(unidades.get("Tiempo"));
            styleComboBox(inputUnitBox);
            gbc.gridx = 0;
            gbc.gridy = 1;
            conversionPanel.add(inputUnitBox, gbc);

            // Etiqueta de igualdad
            JLabel equalsLabel = new JLabel("=");
            equalsLabel.setForeground(Color.WHITE);
            equalsLabel.setHorizontalAlignment(JLabel.CENTER);
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.gridheight = 2;
            conversionPanel.add(equalsLabel, gbc);

            // Campo de salida
            outputField = createStyledTextField();
            outputField.setEditable(false);
            gbc.gridx = 2;
            gbc.gridy = 0;
            gbc.gridheight = 1;
            conversionPanel.add(outputField, gbc);

            // Selector de unidad de salida
            outputUnitBox = new JComboBox<>(unidades.get("Tiempo"));
            styleComboBox(outputUnitBox);
            gbc.gridx = 2;
            gbc.gridy = 1;
            conversionPanel.add(outputUnitBox, gbc);

            // Etiqueta de fórmula
            formulaLabel = new JLabel("Fórmula: divide el valor de tiempo entre 60");
            formulaLabel.setForeground(new Color(255, 204, 0)); // Color amarillo para la fórmula
            formulaLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

            // Agregar componentes al panel principal
            mainPanel.add(categoryBox);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            mainPanel.add(conversionPanel);
            mainPanel.add(formulaLabel);

            // Agregar panel principal al frame
            add(mainPanel, BorderLayout.CENTER);

            // Mantén los listeners existentes sin cambios

            setLocationRelativeTo(null);
            
            categoryBox.setSelectedItem("Tiempo");
            inputField.setText("1");
            inputUnitBox.setSelectedItem("Minutos");
            outputUnitBox.setSelectedItem("Segundos");

        //Logica
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
    
    private JTextField createStyledTextField() {
            JTextField field = new JTextField(10);
            field.setBackground(new Color(41, 42, 45));
            field.setForeground(Color.WHITE);
            field.setCaretColor(Color.WHITE);
            field.setFont(new Font("Arial", Font.BOLD, 16));
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(95, 99, 104), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
            ));
            return field;
        }

    private void styleComboBox(JComboBox<String> box) {
            box.setBackground(new Color(41, 42, 45));
            box.setForeground(Color.WHITE);
            box.setBorder(BorderFactory.createLineBorder(new Color(95, 99, 104), 1));
            box.setRenderer(new DefaultListCellRenderer() {
                @Override
                public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    setBackground(isSelected ? new Color(55, 56, 59) : new Color(41, 42, 45));
                    setForeground(Color.WHITE);
                    return this;
                }
            });
        }
        
    private void actualizarUnidades() {
        String categoriaSeleccionada = (String) categoryBox.getSelectedItem();

        Map<String, Runnable> configuradores = Map.of(
            "Tiempo", () -> {
                inputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get("Tiempo")));
                outputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get("Tiempo")));
                inputUnitBox.setSelectedItem("Minutos");
                inputField.setText("1");
                outputUnitBox.setSelectedItem("Segundos");
            },
            "Volumen", () -> {
                inputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get("Volumen")));
                outputUnitBox.setModel(new DefaultComboBoxModel<>(unidades.get("Volumen")));
                inputUnitBox.setSelectedItem("Mililitros");
                inputField.setText("1");
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