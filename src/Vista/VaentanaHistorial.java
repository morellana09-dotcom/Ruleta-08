package Vista;

import Controlador.SessionController;
import Modelo.Resultado;

import javax.swing.*;
import java.awt.*;

public class VaentanaHistorial {
    private final JFrame frameHistorial = new JFrame("Historial - Casino Black Cat");
    private final JList<String> listaHistorial = new JList<>();
    private final JButton btnVolver = new JButton("Volver al Menú");
    private final SessionController session;

    public VaentanaHistorial(SessionController session) {
        this.session = session;

        frameHistorial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHistorial.setSize(600, 400);
        frameHistorial.setLocationRelativeTo(null);
        frameHistorial.setLayout(new BorderLayout());
        frameHistorial.getContentPane().setBackground(new Color(50,50,50));
    }

    public void mostrar() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        for (Resultado r : session.getUsuarioActual().getHistorial()) {
            modelo.addElement(r.toString());
        }
        listaHistorial.setModel(modelo);

        JScrollPane scroll = new JScrollPane(listaHistorial);

        btnVolver.setBackground(new Color(200,50,50));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.addActionListener(e -> {
            frameHistorial.dispose();
            new VentanaMenu(session).mostrarVentanaMenu();
        });

        frameHistorial.add(scroll, BorderLayout.CENTER);
        frameHistorial.add(btnVolver, BorderLayout.SOUTH);

        frameHistorial.setVisible(true);
    }
}
