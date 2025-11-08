package Modelo;

public class Resultado {
    private final int numero;
    private final String tipoApuesta;
    private final boolean gano;
    private final int monto;

    public Resultado(int numero, String tipoApuesta, boolean gano, int monto) {
        this.numero = numero;
        this.tipoApuesta = tipoApuesta;
        this.gano = gano;
        this.monto = monto;
    }

    public int getNumero() { return numero; }
    public String getTipoApuesta() { return tipoApuesta; }
    public boolean isGano() { return gano; }
    public int getMonto() { return monto; }

    @Override
    public String toString() {
        return String.format("Número: %d | Tipo: %s | %s | Monto: %d",
                numero, tipoApuesta, (gano ? "Ganó" : "Perdió"), monto);
    }
}
