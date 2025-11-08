package Controlador;

import Modelo.Resultado;

public class ResultadoController {
    private final SessionController session;

    public ResultadoController(SessionController session) {
        this.session = session;
    }

    public void registrarResultado(int numero, String tipo, boolean gano, int monto) {
        Resultado r = new Resultado(numero, tipo, gano, monto);
        session.getUsuarioActual().agregarResultado(r);
    }


    private boolean esRojo(int n) {
        int[] rojos = {1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36};
        for (int r : rojos) if (r == n) return true;
        return false;
    }
}

