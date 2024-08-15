package proyectoclienteserver;

import java.util.*;
import javax.swing.JOptionPane;

public class Cliente extends Catalogo {

    private String identificacion;
    private String nombre;
    private String apellido;
    private String ciudad;
    private String direccion;
    private String telefono;
    private String correoE;
    private String estado;
    List<Cliente> clientes = new LinkedList<>();

    public Cliente(String identificacion, String nombre, String apellido, String ciudad, String direccion, String telefono, String correoE, String estado) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoE = correoE;
        this.estado = estado;
    }

    public Cliente() {
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
        clientes.add(new Cliente(identificacion, nombre, apellido, ciudad, direccion,
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

        for (Cliente cliente : clientes) {
            if (cliente.getIdentificacion().equals(identificacionCambiar)) {
                encontrado = false;

                if (!nombre.equals("")) {
                    cliente.setNombre(nombre);

                }
                if (!apellido.equals("")) {
                    cliente.setApellido(apellido);
                }
                if (!ciudad.equals("")) {
                    cliente.setCiudad(ciudad);
                }
                if (!direccion.equals("")) {
                    cliente.setDireccion(direccion);
                }
                if (!telefono.equals("")) {
                    cliente.setTelefono(telefono);
                }
                if (!correoE.equals("")) {
                    cliente.setCorreoE(correoE);
                }

            }

        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "La identificacion que ingreso no se encuentra registrada", "Identificacion no registrada", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void inactivar(Object... parametros) {
        String nombreC=(String)parametros[0];
        

        boolean encontrado = true;
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombreC)) {
                encontrado = false;
                cliente.setEstado("Inactivo");
            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "El nombre del cliente no se encuentra registrado");
        }

    }

    public String mostrar() {
        String s = "";
        for (Cliente cliente : clientes) {
            s += cliente.getIdentificacion() + "\n" + cliente.getNombre() + "\n" + cliente.getApellido() + "\n" + cliente.getCiudad() + "\n" + cliente.getTelefono() + "\n" + cliente.getCorreoE() + "\n" + cliente.getDireccion() + "\n" + cliente.getEstado() + "\n" + "**********************************" + "\n";

        }
        return s;
    }

}
