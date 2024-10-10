import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

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

    private final Map<String, Map<String, String>> formulas = Map.of(
    "Tiempo", Map.ofEntries(
        //Nanosegundo
        Map.entry("Nanosegundo->Microsegundo", "dividir entre 1,000"),
        Map.entry("Nanosegundo->Milisegundos", "dividir entre 1,000,000"),
        Map.entry("Nanosegundo->Segundos", "dividir entre 1,000,000,000"),
        Map.entry("Nanosegundo->Minutos", "dividir entre 60,000,000,000"),
        Map.entry("Nanosegundo->Horas", "dividir entre 3,600,000,000,000"),
        Map.entry("Nanosegundo->Día", "dividir entre 86,400,000,000,000"),
        Map.entry("Nanosegundo->Semana", "dividir entre 604,800,000,000,000"),
        Map.entry("Nanosegundo->Mes", "dividir entre 2,592,000,000,000,000"),
        Map.entry("Nanosegundo->Año", "dividir entre 31,536,000,000,000,000"),
        Map.entry("Nanosegundo->Década", "dividir entre 315,360,000,000,000,000"),
        Map.entry("Nanosegundo->Siglo", "dividir entre 3,153,600,000,000,000,000"),

        //Microsegundo
        Map.entry("Microsegundos->Nanosegundo", "multiplicar por 1,000"),
        Map.entry("Microsegundos->Milisegundo", "dividir entre 1,000"),
        Map.entry("Microsegundos->Segundos", "dividir entre 1,000,000"),
        Map.entry("Microsegundos->Minutos", "dividir entre 60,000,000"),
        Map.entry("Microsegundos->Horas", "dividir entre 3,600,000,000"),
        Map.entry("Microsegundos->Días", "dividir entre 86,400,000,000"),
        Map.entry("Microsegundos->Semana", "dividir entre 604,800,000,000"),
        Map.entry("Microsegundos->Mes", "dividir entre 2,592,000,000,000"),
        Map.entry("Microsegundos->Año", "dividir entre 31,536,000,000,000"),
        Map.entry("Microsegundos->Década", "dividir entre 315,360,000,000,000"),
        Map.entry("Microsegundos->Siglo", "dividir entre 3,153,600,000,000,000"),
        
        //Milisegundo
        Map.entry("Milisegundos->Nanosegundo", "multiplicar por 1,000,000"),
        Map.entry("Milisegundos->Microsegundo", "multiplicar por 1,000"),
        Map.entry("Milisegundos->Segundos", "dividir entre 1,000"),
        Map.entry("Milisegundos->Minutos", "dividir entre 60,000"),
        Map.entry("Milisegundos->Horas", "dividir entre 3,600,000"),
        Map.entry("Milisegundos->Día", "dividir entre 86,400,000"),
        Map.entry("Milisegundos->Semana", "dividir entre 604,800,000"),
        Map.entry("Milisegundos->Mes", "dividir entre 2,592,000,000"),
        Map.entry("Milisegundos->Año", "dividir entre 31,536,000,000"),
        Map.entry("Milisegundos->Década", "dividir entre 315,360,000,000"),
        Map.entry("Milisegundos->Siglo", "dividir entre 3,153,600,000,000"),

        //Segundos
        Map.entry("Segundos->Nanosegundo", "multiplicar por 1,000,000,000"),
        Map.entry("Segundos->Microsegundo", "multiplicar por 1,000,000"),
        Map.entry("Segundos->Milisegundo", "multiplicar por 1,000"),
        Map.entry("Segundos->Minutos", "dividir entre 60"),
        Map.entry("Segundos->Horas", "dividir entre 3,600"),
        Map.entry("Segundos->Día", "dividir entre 86,400"),
        Map.entry("Segundos->Semana", "dividir entre 604,800"),
        Map.entry("Segundos->Mes", "dividir entre 2,592,000"),
        Map.entry("Segundos->Año", "dividir entre 31,536,000"),
        Map.entry("Segundos->Década", "dividir entre 315,360,000"),
        Map.entry("Segundos->Siglo", "dividir entre 3,153,600,000"),

        //Minutos
        Map.entry("Minutos->Nanosegundo", "multiplicar por 60,000,000,000"),
        Map.entry("Minutos->Microsegundo", "multiplicar por 60,000,000"),
        Map.entry("Minutos->Milisegundo", "multiplicar por 60,000"),
        Map.entry("Minutos->Segundos", "multiplicar por 60"),
        Map.entry("Minutos->Horas", "dividir entre 60"),
        Map.entry("Minutos->Día", "dividir entre 1,440"),
        Map.entry("Minutos->Semana", "dividir entre 10,080"),
        Map.entry("Minutos->Mes", "dividir entre 43,200"),
        Map.entry("Minutos->Año", "dividir entre 525,600"),
        Map.entry("Minutos->Década", "dividir entre 5,256,000"),
        Map.entry("Minutos->Siglo", "dividir entre 52,560,000"),

        //Horas
        Map.entry("Horas->Nanosegundo", "multiplicar por 3,600,000,000,000"),
        Map.entry("Horas->Microsegundo", "multiplicar por 3,600,000,000"),
        Map.entry("Horas->Milisegundo", "multiplicar por 3,600,000"),
        Map.entry("Horas->Segundos", "multiplicar por 3,600"),
        Map.entry("Horas->Minutos", "multiplicar por 60"),
        Map.entry("Horas->Día", "dividir entre 24"),
        Map.entry("Horas->Semana", "dividir entre 168"),
        Map.entry("Horas->Mes", "dividir entre 720"),
        Map.entry("Horas->Año", "dividir entre 8,760"),
        Map.entry("Horas->Década", "dividir entre 87,600"),
        Map.entry("Horas->Siglo", "dividir entre 876,000"),

        //Dias
        Map.entry("Día->Nanosegundo", "multiplicar por 86,400,000,000,000"),
        Map.entry("Día->Microsegundo", "multiplicar por 86,400,000,000"),
        Map.entry("Día->Milisegundo", "multiplicar por 86,400,000"),
        Map.entry("Día->Segundos", "multiplicar por 86,400"),
        Map.entry("Día->Minutos", "multiplicar por 1,440"),
        Map.entry("Día->Horas", "multiplicar por 24"),
        Map.entry("Día->Semana", "dividir entre 7"),
        Map.entry("Día->Mes", "dividir entre 30"),
        Map.entry("Día->Año", "dividir entre 365"),
        Map.entry("Día->Década", "dividir entre 3,650"),
        Map.entry("Día->Siglo", "dividir entre 36,500"),

        //Semanas
        Map.entry("Semana->Nanosegundo", "multiplicar por 604,800,000,000,000"),
        Map.entry("Semana->Microsegundo", "multiplicar por 604,800,000,000"),
        Map.entry("Semana->Milisegundo", "multiplicar por 604,800,000"),
        Map.entry("Semana->Segundos", "multiplicar por 604,800"),
        Map.entry("Semana->Minutos", "multiplicar por 10,080"),
        Map.entry("Semana->Horas", "multiplicar por 168"),
        Map.entry("Semana->Día", "multiplicar por 7"),
        Map.entry("Semana->Mes", "dividir entre 4.345"),
        Map.entry("Semana->Año", "dividir entre 52.142"),
        Map.entry("Semana->Década", "dividir entre 521.42"),
        Map.entry("Semana->Siglo", "dividir entre 5,214.2"),

        //Mes
        Map.entry("Mes->Nanosegundo", "multiplicar por 2,592,000,000,000,000"),
        Map.entry("Mes->Microsegundo", "multiplicar por 2,592,000,000,000"),
        Map.entry("Mes->Milisegundo", "multiplicar por 2,592,000,000"),
        Map.entry("Mes->Segundos", "multiplicar por 2,592,000"),
        Map.entry("Mes->Minutos", "multiplicar por 43,200"),
        Map.entry("Mes->Horas", "multiplicar por 720"),
        Map.entry("Mes->Día", "multiplicar por 30"),
        Map.entry("Mes->Semana", "multiplicar por 4.345"),
        Map.entry("Mes->Año", "dividir entre 12"),
        Map.entry("Mes->Década", "dividir entre 120"),
        Map.entry("Mes->Siglo", "dividir entre 1,200"),

        //Años
        Map.entry("Año->Nanosegundo", "multiplicar por 31,536,000,000,000,000"),
        Map.entry("Año->Microsegundo", "multiplicar por 31,536,000,000,000"),
        Map.entry("Año->Milisegundo", "multiplicar por 31,536,000,000"),
        Map.entry("Año->Segundos", "multiplicar por 31,536,000"),
        Map.entry("Año->Minutos", "multiplicar por 525,600"),
        Map.entry("Año->Horas", "multiplicar por 8,760"),
        Map.entry("Año->Día", "multiplicar por 365"),
        Map.entry("Año->Semana", "multiplicar por 52.142"),
        Map.entry("Año->Mes", "multiplicar por 12"),
        Map.entry("Año->Década", "dividir entre 10"),
        Map.entry("Año->Siglo", "dividir entre 100"),
            
        //Decada
        Map.entry("Década->Nanosegundo", "multiplicar por 315,360,000,000,000,000"),
        Map.entry("Década->Microsegundo", "multiplicar por 315,360,000,000,000"),
        Map.entry("Década->Milisegundo", "multiplicar por 315,360,000,000"),
        Map.entry("Década->Segundos", "multiplicar por 315,360,000"),
        Map.entry("Década->Minutos", "multiplicar por 5,256,000"),
        Map.entry("Década->Horas", "multiplicar por 87,600"),
        Map.entry("Década->Día", "multiplicar por 3,650"),
        Map.entry("Década->Semana", "multiplicar por 521.42"),
        Map.entry("Década->Mes", "multiplicar por 120"),
        Map.entry("Década->Año", "multiplicar por 10"),
        Map.entry("Década->Siglo", "dividir entre 10"),

        //Siglo
        Map.entry("Siglo->Nanosegundo", "multiplicar por 3,153,600,000,000,000,000"),
        Map.entry("Siglo->Microsegundo", "multiplicar por 3,153,600,000,000"),
        Map.entry("Siglo->Milisegundo", "multiplicar por 3,153,600,000"),
        Map.entry("Siglo->Segundos", "multiplicar por 31,536,000,000"),
        Map.entry("Siglo->Minutos", "multiplicar por 525,600,000"),
        Map.entry("Siglo->Horas", "multiplicar por 8,760,000"),
        Map.entry("Siglo->Día", "multiplicar por 365,000"),
        Map.entry("Siglo->Semana", "multiplicar por 52,560,000"),
        Map.entry("Siglo->Mes", "multiplicar por 1,200"),
        Map.entry("Siglo->Año", "multiplicar por 100"),
        Map.entry("Siglo->Década", "multiplicar por 10")
        
    ),

    
    "Volumen", Map.ofEntries(
        Map.entry("Mililitros -> Litros", "dividir entre 1,000"),
        Map.entry("Mililitros -> Galones", "dividir entre 3,785,411.784"),
        Map.entry("Mililitros -> Galón estadounidense", "dividir entre 3,785.411784"),
        Map.entry("Mililitros -> Cuarto estadounidense", "dividir entre 946.353"),
        Map.entry("Mililitros -> Pinta estadounidense", "dividir entre 473.176"),
        Map.entry("Mililitros -> Taza americana oficial", "dividir entre 236.588"),
        Map.entry("Mililitros -> Onza líquida estadounidense", "dividir entre 29.5735"),
        Map.entry("Mililitros -> Cucharada estadounidense", "dividir entre 14.7868"),
        Map.entry("Mililitros -> Cucharadita estadounidense", "dividir entre 4.92892"),
        Map.entry("Mililitros -> Metro cúbico", "dividir entre 1,000,000"),
        Map.entry("Mililitros -> Galón imperial", "dividir entre 4,546.09"),
        Map.entry("Mililitros -> Cuarto imperial", "dividir entre 1,136.52"),
        Map.entry("Mililitros -> Pinta imperial", "dividir entre 568.261"),
        Map.entry("Mililitros -> Taza imperial", "dividir entre 284.131"),
        Map.entry("Mililitros -> Onza líquida imperial", "dividir entre 28.4131"),
        Map.entry("Mililitros -> Cucharada imperial", "dividir entre 17.7582"),
        Map.entry("Mililitros -> Cucharadita imperial", "dividir entre 5.91939"),
        Map.entry("Mililitros -> Pie cúbico", "dividir entre 28,316.8466"),
        Map.entry("Mililitros -> Pulgada cúbica", "dividir entre 16.3871"),
        
        Map.entry("Litros -> Mililitros", "multiplicar por 1,000"),
        Map.entry("Litros -> Galones", "dividir entre 3.78541"),
        Map.entry("Litros -> Galón estadounidense", "dividir entre 3.78541"),
        Map.entry("Litros -> Cuarto estadounidense", "multiplicar por 4.22675"),
        Map.entry("Litros -> Pinta estadounidense", "multiplicar por 2.11398"),
        Map.entry("Litros -> Taza americana oficial", "multiplicar por 4.22675"),
        Map.entry("Litros -> Onza líquida estadounidense", "multiplicar por 33.814"),
        Map.entry("Litros -> Cucharada estadounidense", "multiplicar por 67.628"),
        Map.entry("Litros -> Cucharadita estadounidense", "multiplicar por 202.884"),
        Map.entry("Litros -> Metro cúbico", "dividir entre 1,000"),
        Map.entry("Litros -> Galón imperial", "dividir entre 4.54609"),
        Map.entry("Litros -> Cuarto imperial", "multiplicar por 2.11398"),
        Map.entry("Litros -> Pinta imperial", "multiplicar por 4.22675"),
        Map.entry("Litros -> Taza imperial", "multiplicar por 3.51951"),
        Map.entry("Litros -> Onza líquida imperial", "multiplicar por 35.1951"),
        Map.entry("Litros -> Cucharada imperial", "multiplicar por 63.3962"),
        Map.entry("Litros -> Cucharadita imperial", "multiplicar por 190.592"),
        Map.entry("Litros -> Pie cúbico", "dividir entre 28.3168466"),
        Map.entry("Litros -> Pulgada cúbica", "dividir entre 0.0163871"),
        
        Map.entry("Galones -> Mililitros", "multiplicar por 3,785.411784"),
        Map.entry("Galones -> Litros", "multiplicar por 3.78541"),
        Map.entry("Galones -> Galón estadounidense", "multiplicar por 1"),
        Map.entry("Galones -> Cuarto estadounidense", "multiplicar por 4"),
        Map.entry("Galones -> Pinta estadounidense", "multiplicar por 8"),
        Map.entry("Galones -> Taza americana oficial", "multiplicar por 16"),
        Map.entry("Galones -> Onza líquida estadounidense", "multiplicar por 128"),
        Map.entry("Galones -> Cucharada estadounidense", "multiplicar por 256"),
        Map.entry("Galones -> Cucharadita estadounidense", "multiplicar por 768"),
        Map.entry("Galones -> Metro cúbico", "dividir entre 264.172"),
        Map.entry("Galones -> Galón imperial", "multiplicar por 0.832674"),
        Map.entry("Galones -> Cuarto imperial", "multiplicar por 0.832674"),
        Map.entry("Galones -> Pinta imperial", "multiplicar por 1.66534"),
        Map.entry("Galones -> Taza imperial", "multiplicar por 7.040"),
        Map.entry("Galones -> Onza líquida imperial", "multiplicar por 128.000"),
        Map.entry("Galones -> Cucharada imperial", "multiplicar por 219.984"),
        Map.entry("Galones -> Cucharadita imperial", "multiplicar por 659.952"),
        Map.entry("Galones -> Pie cúbico", "multiplicar por 0.133681"),
        Map.entry("Galones -> Pulgada cúbica", "multiplicar por 231"),
        
        Map.entry("Galón estadounidense->Mililitros", "multiplicar por 3,785.411784"),
        Map.entry("Galón estadounidense->Litros", "multiplicar por 3.78541"),
        Map.entry("Galón estadounidense->Galones", "multiplicar por 1"),
        Map.entry("Galón estadounidense->Cuarto estadounidense", "multiplicar por 4"),
        Map.entry("Galón estadounidense->Pinta estadounidense", "multiplicar por 8"),
        Map.entry("Galón estadounidense->Taza americana oficial", "multiplicar por 16"),
        Map.entry("Galón estadounidense->Onza líquida estadounidense", "multiplicar por 128"),
        Map.entry("Galón estadounidense->Cucharada estadounidense", "multiplicar por 256"),
        Map.entry("Galón estadounidense->Cucharadita estadounidense", "multiplicar por 768"),
        Map.entry("Galón estadounidense->Metro cúbico", "dividir entre 264.172"),
        Map.entry("Galón estadounidense->Galón imperial", "multiplicar por 0.832674"),
        Map.entry("Galón estadounidense->Cuarto imperial", "multiplicar por 0.832674"),
        Map.entry("Galón estadounidense->Pinta imperial", "multiplicar por 1.66534"),
        Map.entry("Galón estadounidense->Taza imperial", "multiplicar por 7.040"),
        Map.entry("Galón estadounidense->Onza líquida imperial", "multiplicar por 128.000"),
        Map.entry("Galón estadounidense->Cucharada imperial", "multiplicar por 219.984"),
        Map.entry("Galón estadounidense->Cucharadita imperial", "multiplicar por 659.952"),
        Map.entry("Galón estadounidense->Pie cúbico", "multiplicar por 0.133681"),
        Map.entry("Galón estadounidense->Pulgada cúbica", "multiplicar por 231"),
        
        Map.entry("Cuarto estadounidense->Mililitros", "multiplicar por 946.353"),
        Map.entry("Cuarto estadounidense->Litros", "dividir entre 4.22675"),
        Map.entry("Cuarto estadounidense->Galones", "dividir entre 4"),
        Map.entry("Cuarto estadounidense->Galón estadounidense", "dividir entre 1"),
        Map.entry("Cuarto estadounidense->Pinta estadounidense", "multiplicar por 2"),
        Map.entry("Cuarto estadounidense->Taza americana oficial", "multiplicar por 4"),
        Map.entry("Cuarto estadounidense->Onza líquida estadounidense", "multiplicar por 32"),
        Map.entry("Cuarto estadounidense->Cucharada estadounidense", "multiplicar por 64"),
        Map.entry("Cuarto estadounidense->Cucharadita estadounidense", "multiplicar por 192"),
        Map.entry("Cuarto estadounidense->Metro cúbico", "dividir entre 1,056.688"),
        Map.entry("Cuarto estadounidense->Galón imperial", "dividir entre 2.04198"),
        Map.entry("Cuarto estadounidense->Cuarto imperial", "dividir entre 1.20198"),
        Map.entry("Cuarto estadounidense->Pinta imperial", "multiplicar por 1.20198"),
        Map.entry("Cuarto estadounidense->Taza imperial", "multiplicar por 2.08333"),
        Map.entry("Cuarto estadounidense->Onza líquida imperial", "multiplicar por 32.000"),
        Map.entry("Cuarto estadounidense->Cucharada imperial", "multiplicar por 56.688"),
        Map.entry("Cuarto estadounidense->Cucharadita imperial", "multiplicar por 170.064"),
        Map.entry("Cuarto estadounidense->Pie cúbico", "dividir entre 28.3168"),
        Map.entry("Cuarto estadounidense->Pulgada cúbica", "dividir entre 16.3871")


    )
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