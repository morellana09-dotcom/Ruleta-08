package Vista;

import Controlador.SessionController;
import Controlador.EstadisticasController;

import javax.swing.*;
import java.awt.*;

public class VentanaMenu {
    private final JFrame frameMenu = new JFrame("Menú Principal - Casino Black Cat");
    private final JLabel lblBienvenida = new JLabel("Bienvenido");
    private final JLabel lblSaldo = new JLabel("Saldo: ");
    private final JButton btnCerrarSesion = new JButton("Cerrar Sesión");
    private final JButton btnRuleta = new JButton("Jugar Ruleta");
    private final JButton btnHistorial = new JButton("Historial");

    private final SessionController session;
    private final EstadisticasController estadCtrl = new EstadisticasController();
    private JButton btnEstadisticas;

    public VentanaMenu(SessionController session) {
        this.session = session;

        frameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMenu.setSize(700, 500);
        frameMenu.setLocationRelativeTo(null);
        frameMenu.setLayout(null);
        frameMenu.getContentPane().setBackground(new Color(50,50,50));
        frameMenu.getRootPane().setDefaultButton(btnCerrarSesion);
    }

    public void labelsMenu() {
        lblBienvenida.setBounds(250, 50, 300, 30);
        lblBienvenida.setForeground(Color.WHITE);
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 25));
        lblBienvenida.setText("Bienvenido " + session.getUsuarioActual().getNombre());

        lblSaldo.setBounds(250, 100, 300, 30);
        lblSaldo.setForeground(Color.WHITE);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 20));
        lblSaldo.setText("Saldo actual: $" + session.getUsuarioActual().getSaldo());
    }

    public void botonesMenu() {
        btnCerrarSesion.setBounds(280, 200, 150, 30);
        btnCerrarSesion.setBackground(new Color(200, 50, 50));
        btnCerrarSesion.setForeground(Color.WHITE);

        btnRuleta.setBounds(280, 150, 150, 30);
        btnRuleta.setBackground(new Color(39,207,96));
        btnRuleta.setForeground(Color.WHITE);

        btnHistorial.setBounds(280, 250, 150, 30);
        btnHistorial.setBackground(new Color(100, 100, 200));
        btnHistorial.setForeground(Color.WHITE);


        btnEstadisticas = new JButton("Estadísticas");
        btnEstadisticas.setBounds(280, 300, 150, 30);
        btnEstadisticas.setBackground(new Color(80, 80, 80));
        btnEstadisticas.setForeground(Color.WHITE);
        btnEstadisticas.addActionListener(e -> {
            VentanaEstadistica ventana = new VentanaEstadistica(estadCtrl);
            ventana.mostrar();
        });
        frameMenu.add(btnEstadisticas);



        btnCerrarSesion.addActionListener(e -> {
            session.cerrarSesion();
            frameMenu.dispose();
            VentanaLogin login = new VentanaLogin(session);
            login.mostrarVentanaLogin();
        });

        btnRuleta.addActionListener(e -> {
            frameMenu.dispose();
            new Vista.VentanaRuleta(new Controlador.RuletaController(session)).mostrar();
        });


        btnHistorial.addActionListener(e -> {
            frameMenu.dispose();
            new VaentanaHistorial(session).mostrar();
        });
    }

    public void agregarVentanaMenu() {
        frameMenu.add(lblBienvenida);
        frameMenu.add(lblSaldo);
        frameMenu.add(btnCerrarSesion);
        frameMenu.add(btnRuleta);
        frameMenu.add(btnHistorial);

    }

    public void mostrarVentanaMenu() {
        labelsMenu();
        botonesMenu();
        agregarVentanaMenu();
        frameMenu.setVisible(true);
    }
}
