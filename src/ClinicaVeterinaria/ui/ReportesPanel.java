
package ClinicaVeterinaria.ui;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author LEGION
 */
public class ReportesPanel extends JFrame{
    public ReportesPanel() {
        setTitle("Reportes y Vistas");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 1));
        panel.add(new JButton("Citas por veterinario"));
        panel.add(new JButton("Historial de consultas"));
        panel.add(new JButton("Productos sin vender a cliente"));
        panel.add(new JButton("Veterinario del mes"));

        add(panel);
    }
}
