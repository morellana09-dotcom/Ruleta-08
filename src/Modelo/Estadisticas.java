package Modelo;

import java.util.*;
import java.util.stream.Collectors;

public class Estadisticas {

    private final RepositorioResultados repositorio;

    public Estadisticas(RepositorioResultados repositorio) {
        this.repositorio = repositorio;
    }

    public String generarResumen() {
        List<Resultado> historial = repositorio.obtenerHistorial();

        if (historial.isEmpty()) {
            return "⚠ No hay resultados registrados todavía.";
        }

        int totalJugadas = historial.size();
        long victorias = historial.stream().filter(Resultado::isGano).count();
        double porcentajeVictorias = (victorias * 100.0) / totalJugadas;

        String tipoMasJugado = historial.stream()
                .collect(Collectors.groupingBy(Resultado::getTipoApuesta, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("N/A");

        return String.format(
                "Total de jugadas: %d%n" +
                        "Victorias: %d%n" +
                        "Porcentaje de victorias: %.2f%%%n" +
                        "Tipo más jugado: %s",
                totalJugadas, victorias, porcentajeVictorias, tipoMasJugado
        );
    }
    public RepositorioResultados getRepositorio() {
        return repositorio;
    }
}
