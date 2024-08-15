package proyectoclienteserver;

import java.util.*;
import javax.swing.JOptionPane;

public class Provedor extends Catalogo {

    private String identificacion;
    private String nombre;
    private String apellido;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String correoE;
    private String estado;
    private static LinkedList<Provedor> provedores = new LinkedList<>();
    LinkedList<Provedor> provedoresUso = Fruta.getProvedoresEnUso();

    public Provedor(String identificacion, String nombre, String apellido, String ciudad, String direccion, String telefono, String correoE, String estado) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoE = correoE;
        this.estado = estado;

    }

    public Provedor() {
    }

    @Override
    public void agregar(Object... parametros) {
        String identificacion = (String) parametros[0];
        String nombre = (String) parametros[1];
        String apellido = (String) parametros[2];
        String ciudad = (String) parametros[3];
        String direccion = (String) parametros[4];
        String telefono = (String) parametros[5];
        String correoE = (String) parametros[6];
        String estado = (String) parametros[7];
        provedores.add(new Provedor(identificacion, nombre, apellido, ciudad, direccion,
                telefono, correoE, "Activo"));

    }

    @Override
    public void editar(Object... parametros) {
        boolean encontrado = true;
        String identificacionCambiar = (String) parametros[0];
        String nombre = (String) parametros[1];
        String apellido = (String) parametros[2];
        String ciudad = (String) parametros[3];
        String direccion = (String) parametros[4];
        String telefono = (String) parametros[5];
        String correoE = (String) parametros[6];

        for (Provedor provedor : provedores) {
            if (provedor.getIdentificacion().equals(identificacionCambiar)) {
                encontrado = false;

                if (!nombre.equals("")) {
                    provedor.setNombre(nombre);

                }
                if (!apellido.equals("")) {
                    provedor.setApellido(apellido);
                }
                if (!ciudad.equals("")) {
                    provedor.setCiudad(ciudad);
                }
                if (!direccion.equals("")) {
                    provedor.setDireccion(direccion);
                }
                if (!telefono.equals("")) {
                    provedor.setTelefono(telefono);
                }
                if (!correoE.equals("")) {
                    provedor.setCorreoE(correoE);
                }

            }

        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "La identificacion que ingreso no se encuentra registrada", "Identificacion no registrada", JOptionPane.ERROR_MESSAGE);
        }

    }

    public String mostrar() {
        String s = "";
        for (Provedor provedor : provedores) {
            s += provedor.getIdentificacion() + "\n" + provedor.getNombre() + "\n" + provedor.getApellido() + "\n" + provedor.getCiudad() + "\n" + provedor.getTelefono() + "\n" + provedor.getCorreoE() + "\n" + provedor.getDireccion() + "\n" + provedor.getEstado() + "\n" + "**********************************";

        }
        return s;
    }

    @Override
    public void inactivar(Object... parametros) {
        String nombreP = (String) parametros[0];
        boolean encontrado = true;
        boolean enUso=true;

        for (Provedor provedoor : provedoresUso) {
            if (!provedoor.getNombre().equals(nombreP)) {
                enUso=false;
                for (Provedor provedor : provedores) {
                    if (provedor.getNombre().equals(nombreP)) {
                        encontrado = false;

                        provedor.setEstado("Inactivo");
                        JOptionPane.showMessageDialog(null, "Provedor inactivado");

                    }
                }

            }

        }
        if (enUso) {
            JOptionPane.showMessageDialog(null, "Provedor en uso no se puede inactivar");
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "El nombre del provedor no se encuentra registrado");
        }

    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoE() {
        return correoE;
    }

    public void setCorreoE(String correoE) {
        this.correoE = correoE;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public static LinkedList<Provedor> getProvedores() {
        return provedores;
    }

}
