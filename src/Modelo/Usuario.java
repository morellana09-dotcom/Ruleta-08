package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Usuario {
    private final List<Resultado> historial = new ArrayList<>();
    private static int contador = 0;
    private int id;
    private String username;
    private String clave;
    private String nombre;
    private int saldo;

    //Constructores
    public Usuario(String username, String clave, String nombre, int saldo){
        this.id = ++contador;
        this.username = username;
        this.clave = clave;
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public Usuario() {
        this.username = "invitado";
        this.clave = "1234";
        this.nombre = "Invitado";
        this.saldo = 0;
    }

    //getters
    public String getUsername() {
        return username;
    }

    public String getClave() {
        return clave;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId(){
        return id;
    }

    public int getSaldo(){
        return saldo;
    }

    //seters
    public void setUsername(String username) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("El usuario no puede estar en blanco");
        }
        this.username = username;
    }

    public void setClave(String clave) {
        if (clave == null || clave.isBlank()) {
            throw new IllegalArgumentException("La clave no puede estar en blanco");
        }
        this.clave = clave;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
        }
        this.nombre = nombre;
    }

    public void setSaldo(int saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("El saldo no puede ser negativo");
        }
        this.saldo = saldo;
    }

    public void depositar(int monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("No puede haber un deposito negaivo");
        }
        this.saldo += monto;
    }

    public void apostar(int monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("No puede haber una apuesta negativa");
        } else if (saldo < monto) {
            throw new IllegalArgumentException("No puede apostar mas dinero del que tiene");
        } else {
            this.saldo -= monto;
        }
    }

    public String toString() {
        return  "( " + nombre + ", " + username + ", " + ", S" + saldo + ")";
    }

    public void agregarResultado(Resultado r) {
        historial.add(r);
    }

    public List<Resultado> getHistorial() {
        return Collections.unmodifiableList(historial);
    }


}
