package proyectoclienteserver;

import java.time.LocalDateTime;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.JOptionPane;

public class Facturacion implements Interface {

    private LocalDateTime fechaHora;
    private String descripcionFruta;
    private String nombreCliente;
    private double precio;
    private Connection conexion = null;
    private String user = "root";
    private String password = "root";
    private PreparedStatement consulta = null;
    private ResultSet resultado = null;
    private Statement st = null;
    LinkedList<Fruta> fruta = Fruta.getFrutas();

    public Facturacion() {
        this.fechaHora = fechaHora;
        this.descripcionFruta = "";
        this.nombreCliente = "";
        this.precio = 0.00;
    }

    @Override
    public void reservar(Object... parametros) {
        String nickname = (String) parametros[0];
        String contra = (String) parametros[1];
        String nombreFruta = (String) parametros[2];
        double precio;
        String descripcionFruta;
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fecha = dateTime.format(formatter);
        if (UsuarioInterfaz.validarU(nickname, contra)) {
            boolean encontradaFruta = true;
            for (Fruta fruta : fruta) {
                if (fruta.getNombre().equals(nombreFruta)) {
                    descripcionFruta = fruta.getDescripcion();
                    precio = fruta.getPrecio();
                    encontradaFruta = false;
                    try {
                        conector();
                        String SQL = "INSERT INTO factura(nombreCliente, descripcionFruta, precio, fechaHora, nombreFruta) VALUES (?, ?, ?, ?, ?)";
                        PreparedStatement statement = conexion.prepareStatement(SQL);
                        statement.setString(1, nickname);
                        statement.setString(2, descripcionFruta);
                        statement.setDouble(3, precio);
                        statement.setString(4, fecha);
                        statement.setString(5, nombreFruta);
                        statement.executeUpdate();

                        JOptionPane.showMessageDialog(null, "dato agregado");
                        desconectar();

                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }

            }
            if (encontradaFruta) {
                JOptionPane.showMessageDialog(null, "Fruta no encontrada");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Nombre de usuario o contraseña incorrecta");
        }

    }

    @Override
    public void modificar(Object... parametros) {
        boolean encontradaFruta = true;
        String nickname = (String) parametros[0];
        String nombreF = (String) parametros[1];
        String descripcion;
        double precio;
        try {
            conector();

            for (Fruta fruta : fruta) {
                if (fruta.getNombre().equals(nombreF)) {
                    descripcion = fruta.getDescripcion();
                    precio = fruta.getPrecio();
                    encontradaFruta = false;
                    String SQL = "UPDATE factura SET descripcionFruta = ? , precio = ? , nombreFruta = ?   WHERE nombreCliente = ? ";
                    PreparedStatement statement = conexion.prepareStatement(SQL);
                    statement.setString(1, descripcion);
                    statement.setDouble(2, precio);
                    statement.setString(3, nombreF);
                    statement.setString(4, nickname);

                    int filasActualizadas = statement.executeUpdate();

                    if (filasActualizadas > 0) {
                        JOptionPane.showMessageDialog(null, "Datos actualizados correctamente.");

                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ningún registro para actualizar.");

                    }
                }
            }

            if (encontradaFruta) {
                JOptionPane.showMessageDialog(null, "Fruta no encontrada");
            }

            desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar los datos: " + e.getMessage());
        }

    }

    @Override
    public void cancelar(Object... parametros) {
        String nombreCliente = (String) parametros[0];
        try {
            conector();

            String SQL = "DELETE FROM factura WHERE nombreCliente = ?";

            PreparedStatement statement = conexion.prepareStatement(SQL);
            statement.setString(1, nombreCliente);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {
                JOptionPane.showMessageDialog(null, "Datos eliminados correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún registro para eliminar.");
            }

            desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar los datos: " + e.getMessage());
        }

    }

    public String mostrar() {
        String s = "";
        try {
            conector();
            String SQL = "SELECT nombreCliente, descripcionFruta, precio, fechaHora, nombreFruta FROM factura";

            PreparedStatement statement = conexion.prepareStatement(SQL);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String nombreC = resultSet.getString("nombreCliente");
                String descripcion = resultSet.getString("descripcionFruta");
                double precio = resultSet.getDouble("precio");
                String fecha = resultSet.getString("fechaHora");
                String nombreF = resultSet.getString("nombreFruta");
                s += "El nombre del cliente es: " + nombreC + "\n"
                        + "La fruta que compro es: " + nombreF + "\n"
                        + "La fecha de la compra es: " + fecha + "\n"
                        + "El precio de la fruta por unidad es: " + precio + "\n"
                        + "La descripcion de la fruta es: " + descripcion + "\n";

            }
            desconectar();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return s;
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

}
