package Vista;

import Controlador.SessionController;

import javax.swing.*;
import java.awt.*;

public class VentanaLogin {
    private final JFrame frameLogin = new JFrame("Login - Casino Black Cat");
    private final JLabel lblUsuario = new JLabel("Usuario");
    private final JTextField txtUsuario = new JTextField();
    private final JLabel lblClave = new JLabel("Clave");
    private final JPasswordField txtClave = new JPasswordField("");
    private final JButton btnIngresar = new JButton("Ingresar");
    private final JButton btnRegistro = new JButton("Registrar");
    private final SessionController session;

    public VentanaLogin(SessionController session) {
        this.session = session;
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setSize(700, 300);
        frameLogin.setLocationRelativeTo(null);
        frameLogin.setLayout(null);
        frameLogin.getContentPane().setBackground(new Color(50,50,50));
        frameLogin.getRootPane().setDefaultButton(btnIngresar);
    }

    public void labelsLogin() {
        lblUsuario.setBounds(290, 30, 120, 25);
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 25));
        lblClave.setBounds(300,100,80,25);
        lblClave.setForeground(Color.WHITE);
        lblClave.setFont(new Font("Arial", Font.BOLD, 25));
    }

    public void textosLogin() {
        txtUsuario.setBounds(270,60,130,25);
        txtUsuario.setHorizontalAlignment(JTextField.CENTER);
        txtClave.setBounds(270,130,130,25);
        txtClave.setHorizontalAlignment(JTextField.CENTER);
    }

    public void botonesLogin() {
        btnIngresar.setBounds(230,175,90,25);
        btnIngresar.setBackground(new Color(39, 207, 96));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.addActionListener(e -> verificacionLogin());

        btnRegistro.setBounds(350,175,90,25);
        btnRegistro.setBackground(new Color(50, 103, 237));
        btnRegistro.setForeground(Color.WHITE);
        btnRegistro.addActionListener(e -> abrirRegistro());
    }

    public void agregarVentanaLogin() {
        frameLogin.add(lblUsuario);
        frameLogin.add(txtUsuario);
        frameLogin.add(lblClave);
        frameLogin.add(txtClave);
        frameLogin.add(btnIngresar);
        frameLogin.add(btnRegistro);
    }

    public void mostrarVentanaLogin() {
        labelsLogin();
        textosLogin();
        botonesLogin();
        agregarVentanaLogin();

        frameLogin.setVisible(true);
    }

    public void verificacionLogin() {
        String u = txtUsuario.getText().trim();
        String c = new String(txtClave.getPassword()).trim();

        try {
            if (session.iniciarSesion(u, c)) {
                JOptionPane.showMessageDialog(frameLogin,
                        "Bienvenido " + session.getUsuarioActual().getNombre(),
                        "Login correcto",
                        JOptionPane.INFORMATION_MESSAGE);

                frameLogin.dispose();
                VentanaMenu menu = new VentanaMenu(session);
                menu.mostrarVentanaMenu();
            } else {
                JOptionPane.showMessageDialog(frameLogin,
                        "Usuario y/o clave incorrectos",
                        "Intente nuevamente",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(frameLogin,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirRegistro() {
        frameLogin.dispose();
        VentanaRegistro registro = new VentanaRegistro(session);
        registro.mostrarVentanaRegistro();
    }
}
