package Modelo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivo implements RepositorioResultados {

    private final File archivo = new File("historial_ruleta.csv");

    @Override
    public void guardarResultado(Resultado resultado) {
        try (FileWriter fw = new FileWriter(archivo, true)) {
            fw.write(resultado.getNumero() + "," +
                    resultado.getTipoApuesta() + "," +
                    resultado.isGano() + "," +
                    resultado.getMonto() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Resultado> obtenerHistorial() {
        List<Resultado> historial = new ArrayList<>();
        if (!archivo.exists()) return historial;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    int numero = Integer.parseInt(datos[0]);
                    String tipo = datos[1];
                    boolean gano = Boolean.parseBoolean(datos[2]);
                    int ganancia = Integer.parseInt(datos[3]);
                    historial.add(new Resultado(numero, tipo, gano, ganancia));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return historial;
    }

    @Override
    public void limpiarHistorial() {
        if (archivo.exists()) archivo.delete();
    }
}
