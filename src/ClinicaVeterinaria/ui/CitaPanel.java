
package ClinicaVeterinaria.ui;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author LEGION
 */
public class CitaPanel extends JFrame{
     public CitaPanel() {
        setTitle("Gestión de Citas");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 2));
        panel.add(new JLabel("Veterinario:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Mascota:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Motivo:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Fecha (YYYY-MM-DD):"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Hora (HH:MM):"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Tipo (Emergencia / Rutina):"));
        panel.add(new JComboBox<>(new String[]{"Emergencia", "Revisión"}));
        panel.add(new JLabel("Estatus:"));
        panel.add(new JComboBox<>(new String[]{"Pendiente", "Realizada", "Cancelada"}));
        panel.add(new JLabel());
        panel.add(new JButton("Guardar"));

        add(panel);
    }
}
