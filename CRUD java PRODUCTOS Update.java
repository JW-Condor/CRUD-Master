import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductoManager {

    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_de_datos";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el código del producto a modificar:");
        String codigo = scanner.nextLine();

        mostrarProducto(codigo);

        System.out.println("Ingrese la nueva descripción del producto:");
        String descripcion = scanner.nextLine();

        System.out.println("Ingrese la nueva unidad de medida del producto:");
        String unidadMedida = scanner.nextLine();

        System.out.println("Ingrese el nuevo saldo inicial del producto:");
        double saldoInicial = scanner.nextDouble();

        System.out.println("Ingrese los nuevos ingresos del producto:");
        double ingresos = scanner.nextDouble();

        System.out.println("Ingrese los nuevos egresos del producto:");
        double egresos = scanner.nextDouble();

        System.out.println("Ingrese los nuevos ajustes del producto:");
        double ajustes = scanner.nextDouble();

        System.out.println("Ingrese el nuevo saldo final del producto:");
        double saldoFinal = scanner.nextDouble();

        System.out.println("Ingrese el nuevo costo unitario del producto:");
        double costoUM = scanner.nextDouble();

        System.out.println("Ingrese el nuevo precio unitario del producto:");
        double precioUM = scanner.nextDouble();

        scanner.nextLine();  // Consumir la nueva línea

        System.out.println("Ingrese el nuevo status del producto:");
        String status = scanner.nextLine();

        modificarProducto(codigo, descripcion, unidadMedida, saldoInicial, ingresos, egresos, ajustes, saldoFinal, costoUM, precioUM, status);

        scanner.close();
    }

    public static void mostrarProducto(String codigo) {
        String sql = "SELECT * FROM PRODUCTOS WHERE PROCODIGO = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Código: " + rs.getString("PROCODIGO"));
                System.out.println("Descripción: " + rs.getString("PRODESCRIPCION"));
                System.out.println("Unidad de Medida: " + rs.getString("PROUNIDADMEDIDA"));
                System.out.println("Saldo Inicial: " + rs.getDouble("PROSALDOINICIAL"));
                System.out.println("Ingresos: " + rs.getDouble("PROINGRESOS"));
                System.out.println("Egresos: " + rs.getDouble("PROEGRESOS"));
                System.out.println("Ajustes: " + rs.getDouble("PROAJUSTES"));
                System.out.println("Saldo Final: " + rs.getDouble("PROSALDOFINAL"));
                System.out.println("Costo UM: " + rs.getDouble("PROCOSTOUM"));
                System.out.println("Precio UM: " + rs.getDouble("PROPRECIOUM"));
                System.out.println("Status: " + rs.getString("PROSTATUS"));
            } else {
                System.out.println("Producto no encontrado.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void modificarProducto(String codigo, String descripcion, String unidadMedida, double saldoInicial, double ingresos, double egresos, double ajustes, double saldoFinal, double costoUM, double precioUM, String status) {
        String sql = "UPDATE PRODUCTOS SET PRODESCRIPCION = ?, PROUNIDADMEDIDA = ?, PROSALDOINICIAL = ?, PROINGRESOS = ?, PROEGRESOS = ?, PROAJUSTES = ?, PROSALDOFINAL = ?, PROCOSTOUM = ?, PROPRECIOUM = ?, PROSTATUS = ? WHERE PROCODIGO = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, descripcion);
            pstmt.setString(2, unidadMedida);
            pstmt.setDouble(3, saldoInicial);
            pstmt.setDouble(4, ingresos);
            pstmt.setDouble(5, egresos);
            pstmt.setDouble(6, ajustes);
            pstmt.setDouble(7, saldoFinal);
            pstmt.setDouble(8, costoUM);
            pstmt.setDouble(9, precioUM);
            pstmt.setString(10, status);
            pstmt.setString(11, codigo);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Producto modificado exitosamente.");
            } else {
                System.out.println("Error al modificar el producto.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
