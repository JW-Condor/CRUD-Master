import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductoManager {

    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el código del producto a eliminar:");
        String codigo = scanner.nextLine();

        System.out.println("¿Está seguro de que desea eliminar el producto con código " + codigo + "? (S/N)");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {
            eliminarProducto(codigo);
        } else {
            System.out.println("Operación cancelada.");
        }

        scanner.close();
    }

    public static void eliminarProducto(String codigo) {
        String sql = "DELETE FROM PRODUCTOS WHERE PROCODIGO = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("Error al eliminar el producto o producto no encontrado.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
