package ClinicaVeterinaria.ui;

import ClinicaVeterinaria.DAO.VeterinarioDAO;
import ClinicaVeterinaria.DAO.SecretariaDAO;
import ClinicaVeterinaria.dto.VeterinarioDTO;
import ClinicaVeterinaria.dto.SecretariaDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginWindow extends JFrame {
    public LoginWindow() {
        setTitle("Inicio de Sesión");
        setSize(300, 180);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblUsuario = new JLabel("Usuario:");
        JTextField txtUsuario = new JTextField(15);
        JLabel lblPassword = new JLabel("Contraseña:");
        JPasswordField txtPassword = new JPasswordField(15);
        JButton btnLogin = new JButton("Iniciar sesión");

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));
        panel.add(lblUsuario); panel.add(txtUsuario);
        panel.add(lblPassword); panel.add(txtPassword);
        panel.add(new JLabel()); panel.add(btnLogin);

        add(panel);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String password = new String(txtPassword.getPassword());
                if(autenticarUsuario(usuario, password)) {
                    JOptionPane.showMessageDialog(null, "¡Bienvenido!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
                }
            }
        });
    }

    private boolean autenticarUsuario(String usuario, String password) {
        try {
            VeterinarioDAO vetDao = new VeterinarioDAO();
            VeterinarioDTO vet = vetDao.autenticar(usuario, password);
            if (vet != null) return true;

            SecretariaDAO secDao = new SecretariaDAO();
            SecretariaDTO sec = secDao.autenticar(usuario, password);
            return sec != null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error de conexión: " + ex.getMessage());
            return false;
        }
    }
}