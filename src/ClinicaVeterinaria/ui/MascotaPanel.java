
package ClinicaVeterinaria.ui;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author LEGION
 */
public class MascotaPanel extends JFrame {
     public MascotaPanel() {
        setTitle("Gestión de Mascotas");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(9, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Especie:"));
        panel.add(new JComboBox<>(new String[]{"Perro", "Gato"}));
        panel.add(new JLabel("Raza:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Color:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Peso (kg):"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Tamaño (cm):"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Fecha de nacimiento (YYYY-MM-DD):"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Dueño:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel());
        panel.add(new JButton("Guardar"));

        add(panel);
    }
}
