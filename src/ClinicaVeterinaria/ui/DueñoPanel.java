
package ClinicaVeterinaria.ui;
import ClinicaVeterinaria.controller.DueñoController;
import ClinicaVeterinaria.dto.DueñoDTO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author LEGION
 */
public class DueñoPanel extends JFrame{
     public DueñoPanel() {
        setTitle("Gestión de Dueños");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(20);
        JLabel lblApellidoP = new JLabel("Apellido Paterno:");
        JTextField txtApellidoP = new JTextField(20);
        JLabel lblApellidoM = new JLabel("Apellido Materno:");
        JTextField txtApellidoM = new JTextField(20);
        JLabel lblDireccion = new JLabel("Dirección:");
        JTextField txtDireccion = new JTextField(20);
        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField(20);

        JButton btnGuardar = new JButton("Guardar");

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(lblNombre); panel.add(txtNombre);
        panel.add(lblApellidoP); panel.add(txtApellidoP);
        panel.add(lblApellidoM); panel.add(txtApellidoM);
        panel.add(lblDireccion); panel.add(txtDireccion);
        panel.add(lblTelefono); panel.add(txtTelefono);
        panel.add(new JLabel()); panel.add(btnGuardar);

        add(panel);
        
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DueñoDTO dueño = new DueñoDTO();
                dueño.setNombre(txtNombre.getText());
                dueño.setApellido_P(txtApellidoP.getText());
                dueño.setApellido_M(txtApellidoM.getText());
                dueño.setNumDirCliente(txtDireccion.getText());
                dueño.setTelefono(txtTelefono.getText());

                try {
                    DueñoController controller = new DueñoController();
                    controller.agregarDueno(dueño); // método void
                    JOptionPane.showMessageDialog(null, "Dueño guardado correctamente");

                    // Limpiar campos
                    txtNombre.setText(""); 
                    txtApellidoP.setText(""); 
                    txtApellidoM.setText("");
                    txtDireccion.setText(""); 
                    txtTelefono.setText("");

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al guardar dueño: " + ex.getMessage());
                }
            }
    });
    }
}

