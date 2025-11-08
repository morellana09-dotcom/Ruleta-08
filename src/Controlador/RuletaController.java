package Controlador;

import Modelo.ApuestaBase;
import Modelo.RepositorioResultados;
import Modelo.RepositorioArchivo;
import Vista.VentanaMenu;
import Vista.VentanaRuleta;

public class RuletaController {
    private final SessionController session;

    public RuletaController(SessionController session) {
        this.session = session;
    }

    public int getSaldoUsuario() {
        return session.getUsuarioActual().getSaldo();
    }

    public void jugar(int monto, String tipo, VentanaRuleta vista) {
        try {
            if (monto <= 0) {
                vista.mostrarResultado("El monto debe ser mayor a 0");
                return;
            }
            if (monto > getSaldoUsuario()) {
                vista.mostrarResultado("Saldo insuficiente");
                return;
            }

            ApuestaBase apuesta;
            switch (tipo.toUpperCase()) {
                case "ROJO" -> apuesta = new Modelo.ApuestaRojo(monto);
                case "NEGRO" -> apuesta = new Modelo.ApuestaNegro(monto);
                case "PAR" -> apuesta = new Modelo.ApuestaPar(monto);
                case "IMPAR" -> apuesta = new Modelo.ApuestaImpar(monto);
                default -> throw new IllegalArgumentException("Tipo de apuesta no válido");
            }


            RepositorioResultados repo = new RepositorioArchivo();
            Modelo.Ruleta ruleta = new Modelo.Ruleta(repo);

            Modelo.Resultado resultado = ruleta.jugar(apuesta);

            if (resultado.isGano()) {
                session.getUsuarioActual().depositar(monto);
                vista.mostrarResultado("Número: " + resultado.getNumero() + " → ¡Ganaste! +" + monto);
            } else {
                session.getUsuarioActual().apostar(monto);
                vista.mostrarResultado("Número: " + resultado.getNumero() + " → Perdiste -" + monto);
            }

            Controlador.ResultadoController resultadoCtrl = new Controlador.ResultadoController(session);
            resultadoCtrl.registrarResultado(
                    resultado.getNumero(),
                    resultado.getTipoApuesta(),
                    resultado.isGano(),
                    resultado.isGano() ? monto : -monto
            );

        } catch (IllegalArgumentException e) {
            vista.mostrarResultado("Error: " + e.getMessage());
        }
    }

    public void volverMenu() {
        VentanaMenu menu = new VentanaMenu(session);
        menu.mostrarVentanaMenu();
    }
}
