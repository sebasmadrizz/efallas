package proyectoclienteserver;

import java.sql.*;
import java.util.*;
import javax.swing.JOptionPane;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Cajas {

    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;
    private ServerSocket sc;
    private Socket cl;
    private DataOutputStream salida;
    private DataInputStream entrada;

    private Connection conexion = null;
    private String user = "root";
    private String password = "root";
    private PreparedStatement consulta = null;
    private ResultSet resultado = null;
    private Statement st = null;
    LinkedList<Fruta> fruta = Fruta.getFrutas();
    LinkedList<Double> ventas = new LinkedList<>();
    LinkedList<String> listaVentas = new LinkedList<>();

    public Cajas() {
    }

    public double ventasTotales() {
        double ventasTotales = 0;
        String msj="";
        try {
            conector();
            String SQL = "SELECT precio, nombreFruta FROM factura";

            PreparedStatement statement = conexion.prepareStatement(SQL);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                double precio = resultSet.getDouble("precio");
                String nombreF = resultSet.getString("nombreFruta");
                for (Fruta fruta : fruta) {
                    if (fruta.getNombre().equals(nombreF)) {
                        precio = precio * fruta.getCantidadExistente();

                    }

                }
                ventasTotales += precio;

            }
            desconectar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        ventas.add(ventasTotales);
        msj+="El total de ventas en el dia es de "+ventasTotales;
        listaVentas.add(InterfazFacturacion.listaVentas()+"\n"+msj);
        
        
        
        
        return ventasTotales;
    }

    public void conector() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Facturacion", "root", "123");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void desconectar() {
        try {
            if (!conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public void inicializarServidor() {
        JOptionPane.showMessageDialog(null, ventas);
        try {
            sc = new ServerSocket(PUERTO);
            System.out.println("¡Estoy esperando conexión!");
            cl = sc.accept();
            System.out.println("¡Se conectó un cliente!");
            entrada = new DataInputStream(cl.getInputStream());
            salida = new DataOutputStream(cl.getOutputStream());
            for (String mensaje : listaVentas) {
                salida.writeUTF(mensaje);
            }
            salida.writeUTF("SALIR"); // Envía mensaje de salida al cliente
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (sc != null) {
                    sc.close();
                }
                if (cl != null) {
                    cl.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
