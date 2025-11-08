package Vista;

import Controlador.EstadisticasController;

import javax.swing.*;
import java.awt.*;

public class VentanaEstadistica {

    private final JFrame frameEstadisticas = new JFrame("Estadísticas - Ruleta Black Cat");
    private final EstadisticasController estadCtrl;

    private JTextArea areaTexto;
    private JButton btnReiniciar;
    private JButton btnVolver;

    public VentanaEstadistica(EstadisticasController estadCtrl) {
        this.estadCtrl = estadCtrl;
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        frameEstadisticas.setSize(500, 400);
        frameEstadisticas.setLayout(new BorderLayout());
        frameEstadisticas.setLocationRelativeTo(null);
        frameEstadisticas.getContentPane().setBackground(new Color(40, 40, 40));
        frameEstadisticas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titulo = new JLabel("📊 Estadísticas de Juego", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Consolas", Font.PLAIN, 15));
        areaTexto.setBackground(new Color(50, 50, 50));
        areaTexto.setForeground(Color.WHITE);
        areaTexto.setMargin(new Insets(10, 20, 10, 20));

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.setBackground(new Color(40, 40, 40));


        btnReiniciar = new JButton("Reiniciar estadísticas");
        btnReiniciar.setBackground(new Color(90, 90, 90));
        btnReiniciar.setForeground(Color.WHITE);
        btnReiniciar.addActionListener(e -> {
            estadCtrl.getEstadisticas().getRepositorio().limpiarHistorial();
            actualizarTexto();
        });

        btnVolver = new JButton("Volver al menú");
        btnVolver.setBackground(new Color(90, 90, 90));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.addActionListener(e -> frameEstadisticas.dispose());

        panelBotones.add(btnReiniciar);
        panelBotones.add(btnVolver);

        frameEstadisticas.add(titulo, BorderLayout.NORTH);
        frameEstadisticas.add(scroll, BorderLayout.CENTER);
        frameEstadisticas.add(panelBotones, BorderLayout.SOUTH);
    }

    public void mostrar() {
        actualizarTexto();
        frameEstadisticas.setVisible(true);
    }

    private void actualizarTexto() {
        String resumen = estadCtrl.getEstadisticas().generarResumen();
        areaTexto.setText(resumen);
    }

    public void mostrarResumen(String resumen) {
        areaTexto.setText(resumen);
    }
}
