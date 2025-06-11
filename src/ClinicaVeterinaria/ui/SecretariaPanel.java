
package ClinicaVeterinaria.ui;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author LEGION
 */
public class SecretariaPanel extends JFrame{
     public SecretariaPanel () {
        setTitle("Gestión de Secretarias");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("INE:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Nombre Completo:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Teléfono Celular:"));
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
