package Modelo;

import java.util.Random;

public class Ruleta {

    private final Random random = new Random();
    private final RepositorioResultados repositorio;

    public Ruleta(RepositorioResultados repositorio) {
        this.repositorio = repositorio;
    }

    public Resultado jugar(ApuestaBase apuesta) {
        int numero = girar();
        String color = colorDe(numero);

        boolean gano = apuesta.acierta(numero, color);
        double ganancia = gano ? apuesta.getMonto() * 2 : 0;

        Resultado resultado = new Resultado(numero, apuesta.getEtiqueta(), gano, (int) ganancia);
        repositorio.guardarResultado(resultado);
        return resultado;
    }

    private int girar() {
        return random.nextInt(37); // 0 al 36
    }

    private String colorDe(int numero) {
        if (numero == 0) return "Verde";
        return (numero % 2 == 0) ? "Negro" : "Rojo";
    }
}
