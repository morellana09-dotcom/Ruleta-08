package Modelo;

import java.util.ArrayList;
import java.util.List;

public class RepositorioEnMemoria implements RepositorioResultados {

    private final List<Resultado> historial = new ArrayList<>();

    @Override
    public void guardarResultado(Resultado resultado) {
        historial.add(resultado);
    }

    @Override
    public List<Resultado> obtenerHistorial() {
        return new ArrayList<>(historial);
    }

    @Override
    public void limpiarHistorial() {
        historial.clear();
    }
}
