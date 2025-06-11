
package ClinicaVeterinaria.ui;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author LEGION
 */
public class VeterinarioPanel extends JFrame {
    public VeterinarioPanel() {
        setTitle("Gestión de Veterinarios");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Cédula Profesional:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Nombre Completo:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Tel. Celular:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Tel. Emergencia:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Dirección:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Usuario:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Contraseña:"));
        panel.add(new JPasswordField(20));

        add(panel);
    }
}
