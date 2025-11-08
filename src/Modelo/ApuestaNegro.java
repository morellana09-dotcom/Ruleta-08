package Modelo;

public class ApuestaNegro extends ApuestaBase {
    public ApuestaNegro(double monto) {
        super(monto, "Negro");
    }

    @Override
    public boolean acierta(int numero, String color) {
        return numero != 0 && color.equalsIgnoreCase("Negro");
    }
}