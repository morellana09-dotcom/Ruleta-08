package Modelo;

import java.util.List;

public interface RepositorioResultados {

    void guardarResultado(Resultado resultado);

    List<Resultado> obtenerHistorial();

    void limpiarHistorial();
}
