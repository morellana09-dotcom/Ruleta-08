package Vista;

import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaRegistro {
    private final JFrame frameRegistro = new JFrame("Registro - Ruleta Black - Cat");
    private final JLabel lblUsuario = new JLabel("Nombre de Usuario");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave");
    private final JPasswordField txtClave = new JPasswordField();
    private final JLabel lblNombre = new JLabel("Nombre real");
    private final JTextField txtNombre = new JTextField();
    private final JButton btnRegistro = new JButton("Registrar");

    private final SessionController session;


    public VentanaRegistro(SessionController session) {
        this.session = session;

        frameRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameRegistro.setSize(700, 350);
        frameRegistro.setLocationRelativeTo(null);
        frameRegistro.setLayout(null);
        frameRegistro.getContentPane().setBackground(new Color(50,50,50));
        frameRegistro.getRootPane().setDefaultButton(btnRegistro);
    }

    public void labelsRegistro() {
        lblUsuario.setBounds(220, 20, 230, 25);
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 25));

        lblClave.setBounds(300,100,120,25);
        lblClave.setForeground(Color.WHITE);
        lblClave.setFont(new Font("Arial", Font.BOLD, 25));

        lblNombre.setBounds(263,170,170,25);
        lblNombre.setForeground(Color.WHITE);
        lblNombre.setFont(new Font("Arial", Font.BOLD, 25));
    }

    public void textosRegistro() {
        txtUsuario.setBounds(270,60,130,25);
        txtUsuario.setHorizontalAlignment(JTextField.CENTER);

        txtClave.setBounds(270,130,130,25);
        txtClave.setHorizontalAlignment(JTextField.CENTER);

        txtNombre.setBounds(270,200,130,25);
        txtNombre.setHorizontalAlignment(JTextField.CENTER);
    }

    public void botonesRegistro() {
        btnRegistro.setBounds(290,240,90,25);
        btnRegistro.setBackground(new Color(50, 103, 237));
        btnRegistro.setForeground(Color.WHITE);
        btnRegistro.addActionListener(e-> registrarUsuario());
    }

    public void agregarVentanaRegistro() {
        frameRegistro.add(lblUsuario);
        frameRegistro.add(lblClave);
        frameRegistro.add(lblNombre);
        frameRegistro.add(txtUsuario);
        frameRegistro.add(txtClave);
        frameRegistro.add(txtNombre);
        frameRegistro.add(btnRegistro);
    }

    public void mostrarVentanaRegistro() {
        labelsRegistro();
        textosRegistro();
        botonesRegistro();
        agregarVentanaRegistro();
        frameRegistro.setVisible(true);
    }


    public void registrarUsuario(){
        String u = txtUsuario.getText().trim();
        String c = new String(txtClave.getPassword()).trim();
        String n = txtNombre.getText().trim();

        try {
            if (session.registrar(u, c, n, 100)) {
                JOptionPane.showMessageDialog(frameRegistro,
                        "Registro exitoso del usuario: " + u,
                        "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                regresarLogin();
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frameRegistro,
                    ex.getMessage(),
                    "Error al registrar",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void regresarLogin(){
        frameRegistro.dispose();
        VentanaLogin login = new VentanaLogin(session);
        login.mostrarVentanaLogin();
    }
}
