
package ClinicaVeterinaria.ui;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author LEGION
 */
public class VentaPanel extends JFrame{
     public VentaPanel() {
        setTitle("Gestión de Ventas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Producto:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Cantidad:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Fecha:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel("Cliente:"));
        panel.add(new JTextField(20));
        panel.add(new JLabel());
        panel.add(new JButton("Registrar Venta"));

        add(panel);
    }
}
