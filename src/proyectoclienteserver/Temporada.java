package proyectoclienteserver;

import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Temporada extends Catalogo {

    private String mesCosecha;
    private String descripcion;
    private String codigo;
    private String estado;
    private static LinkedList<Temporada> temporadas = new LinkedList<>();
    private static LinkedList<Temporada> temporadasEnUso = Fruta.getTemporadaEnUso();

    public static LinkedList<Temporada> getTemporadas() {
        return temporadas;
    }

    public Temporada(String mesCosecha, String descripcion, String codigo, String estado) {
        this.mesCosecha = mesCosecha;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.estado = estado;
    }

    public Temporada() {
    }

    @Override
    public void agregar(Object... parametros) {
        String mesCosecha = (String) parametros[0];
        String descripcion = (String) parametros[1];
        String codigo = (String) parametros[2];
        temporadas.add(new Temporada(mesCosecha, descripcion, codigo, "Activo"));
    }

    @Override
    public void editar(Object... parametros) {
        boolean encontrado = true;
        String codigoVerificar = (String) parametros[0];
        String descripcion = (String) parametros[1];
        String mesCosecha = (String) parametros[2];
        for (Temporada temporada : temporadas) {
            if (temporada.getCodigo().equals(codigoVerificar)) {
                encontrado = false;
                if (!descripcion.equals("")) {
                    temporada.setDescripcion(descripcion);
                }
                if (!mesCosecha.equals("")) {
                    temporada.setMesCosecha(mesCosecha);

                }

            }

        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "El codigo de temporada que ingreso no se encuentra registrada", "Codigo no registrada", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void inactivar(Object... parametros) {
        String codigoT = (String) parametros[0];
        boolean encontrado = true;
        boolean enUso=true;
        for (Temporada tem : temporadasEnUso) {
            if (!tem.getCodigo().equals(codigoT)) {
                enUso=false;
                for (Temporada temporada : temporadas) {
                    if (temporada.getCodigo().equals(codigoT)) {
                        encontrado = false;
                        temporada.setEstado("Inactivo");
                        JOptionPane.showMessageDialog(null, "Temporada inactivada");
                    }

                }
            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Codigo de temporada no encontrado");
        }
        if (enUso) {
            JOptionPane.showMessageDialog(null, "Temporada en uso no se puede inactivar");
        }

    }

    public String getMesCosecha() {
        return mesCosecha;
    }

    public void setMesCosecha(String mesCosecha) {
        this.mesCosecha = mesCosecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String mostrar() {
        String s = "";
        for (Temporada temporada : temporadas) {

            s += temporada.getCodigo() + "\n" + temporada.getDescripcion() + "\n" + temporada.getMesCosecha() + "\n" + temporada.getEstado() + "\n";
        }
        return s;
    }

}
