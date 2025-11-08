package Controlador;

import Modelo.Estadisticas;
import Modelo.RepositorioResultados;
import Modelo.RepositorioArchivo;
import Vista.VentanaEstadistica;

public class EstadisticasController {

    private final Estadisticas estadisticas;

    // Constructor: crea el modelo con el repositorio actual (archivo CSV)
    public EstadisticasController() {
        RepositorioResultados repo = new RepositorioArchivo();
        this.estadisticas = new Estadisticas(repo);
    }


    public void mostrarEnVentana(VentanaEstadistica vista) {
        String resumen = estadisticas.generarResumen();
        vista.mostrarResumen(resumen);
    }

    // Si necesitas acceder directamente al modelo
    public Estadisticas getEstadisticas() {
        return estadisticas;
    }
}
