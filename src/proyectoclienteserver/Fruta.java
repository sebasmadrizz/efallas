package proyectoclienteserver;

import java.util.*;
import javax.swing.JOptionPane;

public class Fruta extends Catalogo {

    private String descripcion;
    private String nombre;
    private int cantidadExistente;
    private String temporada;
    private double precio;
    private String estado;
    private String nombreProvedor;
    LinkedList<Provedor> nombresP = Provedor.getProvedores();
    LinkedList<Temporada> temporadas = Temporada.getTemporadas();
    private static LinkedList<Provedor> provedoresEnUso = new LinkedList<>();
    private static LinkedList<Temporada> temporadaEnUso = new LinkedList<>();

    private static LinkedList<Fruta> frutas = new LinkedList<>();

    public Fruta(String nombre, String descripcion, int cantidadExistente, double precio, String estado, String nomP, String tem) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadExistente = cantidadExistente;
        this.precio = precio;
        this.nombreProvedor = nomP;
        this.temporada = tem;
        this.estado = estado;

    }

    public static LinkedList<Fruta> getFrutas() {
        return frutas;
    }

    public static void setFrutas(LinkedList<Fruta> frutas) {
        Fruta.frutas = frutas;
    }
    
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadExistente() {
        return cantidadExistente;
    }

    public void setCantidadExistente(int cantidadExistente) {
        this.cantidadExistente = cantidadExistente;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreProvedor() {
        return nombreProvedor;
    }

    public void setNombreProvedor(String nombreProvedor) {
        this.nombreProvedor = nombreProvedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public static LinkedList<Provedor> getProvedoresEnUso() {
        return provedoresEnUso;
    }

    public static LinkedList<Temporada> getTemporadaEnUso() {
        return temporadaEnUso;
    }

    public static void setTemporadaEnUso(LinkedList<Temporada> temporadaEnUso) {
        Fruta.temporadaEnUso = temporadaEnUso;
    }
    

   
    

    public Fruta() {
    }

    @Override
    public void agregar(Object... parametros) {
        try{
        String nombreP = (String) parametros[0];
        String codigoT = (String) parametros[1];
        String nombre = (String) parametros[2];
        String descripcion = (String) parametros[3];
        int cantidad = (int) parametros[4];
        double precio = (double) parametros[5];
        boolean encontradoP = true;
        boolean encontradoT = true;
        for (Provedor provedor : nombresP) {
            if (provedor.getNombre().equals(nombreP) && (provedor.getEstado().equals("Activo"))) {
                encontradoP = false;

                for (Temporada tem : temporadas) {
                    if (tem.getCodigo().equals(codigoT)&&(tem.getEstado().equals("Activo"))) {
                        encontradoT = false;
                        provedoresEnUso.add(provedor);
                        temporadaEnUso.add(tem);
                        frutas.add(new Fruta(nombre, descripcion, cantidad,
                                precio, " Activo", nombreP, codigoT));
                    }

                }

            }
        }
        for (Temporada tem : temporadas) {
            if (tem.getCodigo().equals(codigoT)) {
                encontradoT = false;

            }

        }

        if (encontradoP) {
            JOptionPane.showMessageDialog(null, "El provedor no ha sido encontrada en el sistema",
                    "Provedor no registrada", JOptionPane.ERROR_MESSAGE);

        }
        if (encontradoT) {
            JOptionPane.showMessageDialog(null, "La temporada no ha sido encontrada en el sistema",
                    "Temporada no registrada", JOptionPane.ERROR_MESSAGE);

        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            
        }

    }

    @Override

    public void editar(Object... parametros) {
        boolean encontrado = true;
        String nombreVerificar = (String) parametros[0];
        String Descripcion = (String) parametros[1];
        int CantidadE = (int) parametros[2];
        String tem = (String) parametros[3];
        double precio = (double) parametros[4];
        String nombreP = (String) parametros[5];
        for (Fruta fruta : frutas) {
            if (fruta.getNombre().equals(nombreVerificar)) {
                encontrado = false;
                String[] opciones = {"Descripcion", "Cantidad Existente", "Temporada", "Precio", "Nombre Provedor"};
                if (!Descripcion.equals("")) {
                    fruta.setDescripcion(Descripcion);

                }
                if (CantidadE != 0) {
                    fruta.setCantidadExistente(CantidadE);

                }
                if (!tem.equals("")) {
                    boolean encontrada = true;

                    for (Temporada temporada : temporadas) {
                        if (temporada.getCodigo().equals(tem)) {
                            encontrada = false;
                            fruta.setTemporada(tem);
                        }

                    }
                    if (encontrada) {
                        JOptionPane.showMessageDialog(null, "La temporada no ha sido encontrada en el sistema",
                                "Temporada no registrada", JOptionPane.ERROR_MESSAGE);

                    }

                }
                if (precio != 0) {

                    fruta.setPrecio(precio);

                }
                if (!nombreP.equals("")) {
                    boolean encontradoo = false;

                    for (Provedor provedor : nombresP) {
                        if (provedor.equals(nombreP)) {
                            encontradoo = false;
                            fruta.setNombreProvedor(nombreP);
                        }

                    }
                    if (encontradoo) {
                        JOptionPane.showMessageDialog(null, "El provedor no ha sido encontrada en el sistema",
                                "Provedor no registrada", JOptionPane.ERROR_MESSAGE);

                    }

                }

            }

        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "El nombre de la fruta  que ingreso no se encuentra registrada", "Fruta no encontrada", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void inactivar(Object... parametros) {
        String nombre = (String) parametros[0];
        boolean encontrado = true;
        boolean usoP = true;
        boolean usoT = true;
        for (Fruta fruta : frutas) {
            if (fruta.getNombre().equals(nombre)) {
                encontrado = false;
                for (Provedor provedor : provedoresEnUso) {
                    if (provedor.getEstado().equals("Inactivo")) {
                        usoP = false;
                        for (Temporada temporada : temporadaEnUso) {
                            if (temporada.getEstado().equals("Inactivo")) {
                                usoT=false;
                                fruta.setEstado("Inactivo");

                            }
                        }

                    }
                }

            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "fruta no encontrada");
        }
        if (usoP) {
            JOptionPane.showMessageDialog(null, "Provedor activo, no se puede inactivar la fruta");

        }
        if (usoT) {
            JOptionPane.showMessageDialog(null, "Temporada activa, no se puede inactivar la fruta");
            
        }

    }

    public String mostrar() {
        String s = "";
        for (Fruta fruta : frutas) {
            s += fruta.getNombre() + "\n"
                    + fruta.getNombreProvedor() + "\n"
                    + fruta.getPrecio() + "\n"
                    + fruta.getEstado() + "\n"
                    + fruta.getTemporada();

        }
        return s;
    }
    public int cantidadF(String nombreF){
        int cantidad=0;
        for (Fruta fruta:frutas) {
            if (fruta.equals(nombreF)) {
                cantidad=fruta.getCantidadExistente();
            }
            
        }
        return cantidad;
    }

}
