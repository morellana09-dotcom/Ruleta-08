package Vista;

import Controlador.RuletaController;

import javax.swing.*;
import java.awt.*;

public class VentanaRuleta {
    private final JFrame frameRuleta = new JFrame("Ruleta - Casino Black Cat");
    private final JLabel lblSaldo = new JLabel();
    private final JComboBox<String> cboApuesta = new JComboBox<>(new String[]{"ROJO","NEGRO","PAR","IMPAR"});
    private final JTextField txtMonto = new JTextField();
    private final JButton btnJugar = new JButton("Jugar");
    private final JButton btnSalir = new JButton("Volver al Menú");

    private final RuletaController controller;

    public VentanaRuleta(RuletaController controller) {
        this.controller = controller;

        frameRuleta.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRuleta.setSize(500, 300);
        frameRuleta.setLocationRelativeTo(null);
        frameRuleta.setLayout(null);
        frameRuleta.getContentPane().setBackground(new Color(50,50,50));
    }

    private void componentes() {
        lblSaldo.setBounds(30,20,300,25);
        lblSaldo.setForeground(Color.WHITE);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel lblTipo = new JLabel("Tipo de apuesta:");
        lblTipo.setBounds(30,70,150,25);
        lblTipo.setForeground(Color.WHITE);
        frameRuleta.add(lblTipo);

        cboApuesta.setBounds(200,70,120,25);

        JLabel lblMonto = new JLabel("Monto:");
        lblMonto.setBounds(30,110,150,25);
        lblMonto.setForeground(Color.WHITE);
        frameRuleta.add(lblMonto);

        txtMonto.setBounds(200,110,120,25);
        txtMonto.setHorizontalAlignment(JTextField.CENTER);

        btnJugar.setBounds(150,160,90,25);
        btnJugar.setBackground(new Color(39,207,96));
        btnJugar.setForeground(Color.WHITE);
        btnJugar.addActionListener(e -> jugar());

        btnSalir.setBounds(260,160,120,25);
        btnSalir.setBackground(new Color(200,50,50));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.addActionListener(e -> salir());
    }

    private void agregar() {
        frameRuleta.add(lblSaldo);
        frameRuleta.add(cboApuesta);
        frameRuleta.add(txtMonto);
        frameRuleta.add(btnJugar);
        frameRuleta.add(btnSalir);
    }

    public void mostrar() {
        componentes();
        agregar();
        refrescarSaldo();
        frameRuleta.setVisible(true);
    }

    public void refrescarSaldo() {
        int saldo = controller.getSaldoUsuario();
        lblSaldo.setText("Saldo actual: $" + saldo);
    }

    private void jugar() {
        try {
            int monto = Integer.parseInt(txtMonto.getText().trim());
            String tipoSeleccionado = cboApuesta.getSelectedItem().toString().toUpperCase();
            controller.jugar(monto, tipoSeleccionado, this);
        } catch (NumberFormatException e) {
            mostrarResultado("Ingrese un monto válido");
        }
    }

    private void salir() {
        frameRuleta.dispose();
        controller.volverMenu();
    }

    public void mostrarResultado(String mensaje) {
        JOptionPane.showMessageDialog(frameRuleta, mensaje, "Resultado", JOptionPane.INFORMATION_MESSAGE);
        refrescarSaldo();
    }
}
