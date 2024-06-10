import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductoGrid {

    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Productos");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            String[] columnNames = {"Código", "Descripción", "Unidad de Medida", "Costo UM", "Precio UM", "Estatus"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            JTable table = new JTable(model);

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT PROCODIGO, PRODESCRIPCION, PROUNIDADMEDIDA, PROCOSTOUM, PROPRECIOUM, PROSTATUS FROM PRODUCTOS")) {

                while (rs.next()) {
                    String codigo = rs.getString("PROCODIGO");
                    String descripcion = rs.getString("PRODESCRIPCION");
                    String unidadMedida = rs.getString("PROUNIDADMEDIDA");
                    double costoUM = rs.getDouble("PROCOSTOUM");
                    double precioUM = rs.getDouble("PROPRECIOUM");
                    String estatus = rs.getString("PROSTATUS");

                    Object[] data = {codigo, descripcion, unidadMedida, costoUM, precioUM, estatus};
                    model.addRow(data);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error al conectar con la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane);
            frame.setVisible(true);
        });
    }
}
