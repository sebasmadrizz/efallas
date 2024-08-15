package proyectoclienteserver;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

public class Usuario extends JFrame {

    Usuario[] usuarios = new Usuario[10];
    private String nombre;
    private String apellidos;
    private String nickname;
    private String contraseña;
    private String estado;

    public Usuario() {
        this.nombre = "";
        this.apellidos = "";
        this.nickname = "";
        this.contraseña = "";

    }

    public Usuario(String nombre, String apellidos, String nickname, String contraseña) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nickname = nickname;
        this.contraseña = contraseña;
        this.estado = "activo";

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void agregarUsuario(String nombre, String apellido, String nickname, String contraseña) {
        try {
            for (int i = 0; i < usuarios.length; i++) {
                if (usuarios[i] == null) {
                    usuarios[i] = new Usuario(nombre, apellido,
                            nickname, contraseña);
                    break;

                }

            }
        } catch (Exception e) {
            System.out.println("Ocurrio un error");

        }

    }

    public String consultarUsuario(String nombre, String contraseña) {
        try {
            boolean encontrado = true;
            for (int i = 0; i < usuarios.length; i++) {
                if (usuarios[i] != null) {

                    if (usuarios[i].getNickname().equals(nombre)) {

                        encontrado = false;
                        if (usuarios[i].getContraseña().equals(contraseña)) {
                            return "***DATOS DE USUARIO***" + "\n"
                                    + "Su nombre es: " + usuarios[i].getNombre() + "\n"
                                    + "Sus apellidos son: " + usuarios[i].getApellidos() + "\n"
                                    + "Su nickname es: " + usuarios[i].getNickname() + "\n"
                                    + "Su contraseña es: " + usuarios[i].getContraseña() + "\n"
                                    + "Su estado es: " + usuarios[i].getEstado();

                        } else {
                            return "Su contraseña es incorrecta";
                        }

                    }

                }

            }
            if (encontrado) {
                return "Lo sentimos el nickname que intriduciste no se encuentra registrado en nuestra base de datos";

            }
        } catch (Exception e) {
            return "Ocurrio un error";

        }
        return "";

    }

    public boolean encontrar(String nombre, String contra) {
        boolean encontrado = false;
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] != null) {
                if ((usuarios[i].getNickname().equals(nombre)) && usuarios[i].getContraseña().equals(contra)) {
                    encontrado = true;
                }
            }
        }

        return encontrado;
    }

}
