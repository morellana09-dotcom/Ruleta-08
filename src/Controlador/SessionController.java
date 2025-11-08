package Controlador;

import Modelo.Usuario;
import java.util.ArrayList;

public class SessionController {
    private ArrayList<Usuario> USUARIOS;
    private Usuario usuarioActual;

    //Constructor
    public SessionController(){
        USUARIOS = new ArrayList<>();
        usuarioActual = null;
        Usuario admin = new Usuario("admin", "1234", "Administrador", 5000);
        USUARIOS.add(admin);
    }

    //metodos
    public boolean iniciarSesion(String u, String c) {
        if (u == null || u.isBlank() || c == null || c.isBlank()){
            throw new IllegalArgumentException("Rellene todas las casilla");
        }

        for (Usuario usuario : USUARIOS) {
            if (usuario.getUsername().equals(u) && usuario.getClave().equals(c)){
                usuarioActual = usuario;
                return true;
            }
        } return false;
    }

    public boolean registrar(String u, String c, String n, int s) {
        if (u.isBlank() || u == null || c == null || c.isBlank() || n == null || n.isBlank()) {
            throw new IllegalArgumentException("Rellene todos los campos");
        } else if (evitarDuplicado(u)){
            throw new IllegalArgumentException("El usuario ya existe");
        } else {
            Usuario nuevoUsuario = new Usuario(u,c,n,s);
            USUARIOS.add(nuevoUsuario);
            return true;
        }
    }

    public boolean evitarDuplicado(String u){
        for (Usuario usuario : USUARIOS){
            if (usuario.getUsername().equals(u)){
                return true;
            }
        } return false;
    }
    public void cerrarSesion(){
        usuarioActual = null;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}
