
package ClinicaVeterinaria.ui;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author LEGION
 */
public class ProductoPanel extends JFrame {
    public ProductoPanel() {
        setTitle("Gestión de Productos");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Tipo:"));
        panel.add(new JComboBox<>(new String[]{"Medicamento", "Alimento", "Juguete", "Accesorio"}));
        panel.add(new JLabel("Especie:"));
        panel.add(new JComboBox<>(new String[]{"Perro", "Gato"}));
        panel.add(new JLabel("Marca:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Precio:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Existencia:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel());
        panel.add(new JButton("Guardar"));

        add(panel);
    }
}
