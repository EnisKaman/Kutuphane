package kutuphane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Baglanti {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/Kutuphane";
    private static final String USER = "postgres";
    private static final String PASSWORD = "enes3349";

    // Singleton deseni kullanarak tek bir bağlantı nesnesi oluşturun
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // PostgreSQL JDBC sürücüsünü yükleyin
                Class.forName("org.postgresql.Driver");
                // Veritabanına bağlantıyı kurun
                connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                System.out.println("Veritabanına başarıyla bağlandı!");
            } catch (ClassNotFoundException e) {
                System.out.println("JDBC sürücüsü bulunamadı!");
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Veritabanına bağlanırken bir hata oluştu!");
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                // Bağlantıyı kapat
                connection.close();
                System.out.println("Veritabanı bağlantısı kapatıldı.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        getConnection();
    }
}