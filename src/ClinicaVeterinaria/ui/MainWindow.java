package ClinicaVeterinaria.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends JFrame {

    public MainWindow() {
        setTitle("Clínica Veterinaria");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(0, 1, 10, 10));

        JButton btnDueños = new JButton("Gestión de Dueños");
        JButton btnMascotas = new JButton("Gestión de Mascotas");
        JButton btnVeterinarios = new JButton("Gestión de Veterinarios");
        JButton btnSecretarias = new JButton("Gestión de Secretarias");
        JButton btnCitas = new JButton("Gestión de Citas");
        JButton btnProductos = new JButton("Gestión de Productos");
        JButton btnVentas = new JButton("Gestión de Ventas");
        JButton btnReportes = new JButton("Reportes y Vistas");

        add(btnDueños);
        add(btnMascotas);
        add(btnVeterinarios);
        add(btnSecretarias);
        add(btnCitas);
        add(btnProductos);
        add(btnVentas);
        add(btnReportes);

        // Acciones
        btnDueños.addActionListener(e -> new DueñoPanel().setVisible(true));
        btnMascotas.addActionListener(e -> new MascotaPanel().setVisible(true));
        btnVeterinarios.addActionListener(e -> new VeterinarioPanel().setVisible(true));
        btnSecretarias.addActionListener(e -> new SecretariaPanel().setVisible(true));
        btnCitas.addActionListener(e -> new CitaPanel().setVisible(true));
        btnProductos.addActionListener(e -> new ProductoPanel().setVisible(true));
        btnVentas.addActionListener(e -> new VentaPanel().setVisible(true));
        btnReportes.addActionListener(e -> new ReportesPanel().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow().setVisible(true));
    }
}

