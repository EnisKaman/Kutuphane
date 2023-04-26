package kutuphane;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Baglanti {

    static String dosyayolu = "C:/Users/ekmn2/OneDrive/Belgeler/New Folder/Kutuphane/src/kutuphane/Veritabani.txt";
    static File dosya ;
    
   
    private static String DB_URL ;
    private static String USER ;
    private static String PASSWORD ;

    // Singleton deseni kullanarak tek bir bağlantı nesnesi oluşturun
    private static Connection connection;
    
   

    public static Connection getConnection() {
        try {
            File dosya = new File(dosyayolu);
            if (dosya.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(dosya));
                DB_URL =reader.readLine();
                USER = reader.readLine();
                PASSWORD = reader.readLine();
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Dosya okuma hatası.");
        }
        
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
