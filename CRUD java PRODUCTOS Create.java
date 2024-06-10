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

        System.out.println("Ingrese el código del producto:");
        String codigo = scanner.nextLine();

        System.out.println("Ingrese la descripción del producto:");
        String descripcion = scanner.nextLine();

        System.out.println("Ingrese la unidad de medida del producto:");
        String unidadMedida = scanner.nextLine();

        System.out.println("Ingrese el saldo inicial del producto:");
        double saldoInicial = scanner.nextDouble();

        System.out.println("Ingrese los ingresos del producto:");
        double ingresos = scanner.nextDouble();

        System.out.println("Ingrese los egresos del producto:");
        double egresos = scanner.nextDouble();

        System.out.println("Ingrese los ajustes del producto:");
        double ajustes = scanner.nextDouble();

        System.out.println("Ingrese el saldo final del producto:");
        double saldoFinal = scanner.nextDouble();

        System.out.println("Ingrese el costo unitario del producto:");
        double costoUM = scanner.nextDouble();

        System.out.println("Ingrese el precio unitario del producto:");
        double precioUM = scanner.nextDouble();

        scanner.nextLine();  // Consumir la nueva línea

        System.out.println("Ingrese el status del producto:");
        String status = scanner.nextLine();

        insertarProducto(codigo, descripcion, unidadMedida, saldoInicial, ingresos, egresos, ajustes, saldoFinal, costoUM, precioUM, status);

        scanner.close();
    }

    public static void insertarProducto(String codigo, String descripcion, String unidadMedida, double saldoInicial, double ingresos, double egresos, double ajustes, double saldoFinal, double costoUM, double precioUM, String status) {
        String sql = "INSERT INTO PRODUCTOS (PROCODIGO, PRODESCRIPCION, PROUNIDADMEDIDA, PROSALDOINICIAL, PROINGRESOS, PROEGRESOS, PROAJUSTES, PROSALDOFINAL, PROCOSTOUM, PROPRECIOUM, PROSTATUS) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, codigo);
            pstmt.setString(2, descripcion);
            pstmt.setString(3, unidadMedida);
            pstmt.setDouble(4, saldoInicial);
            pstmt.setDouble(5, ingresos);
            pstmt.setDouble(6, egresos);
            pstmt.setDouble(7, ajustes);
            pstmt.setDouble(8, saldoFinal);
            pstmt.setDouble(9, costoUM);
            pstmt.setDouble(10, precioUM);
            pstmt.setString(11, status);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Producto insertado exitosamente.");
            } else {
                System.out.println("Error al insertar el producto.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
