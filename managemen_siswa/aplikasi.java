import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class aplikasi {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/managemen_siswa";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";


    public static void main(String[] args) throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Scanner input = new Scanner(System.in);

            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Tambah Siswa");
                System.out.println("2. Perbarui Data Siswa");
                System.out.println("3. Hapus Siswa");
                System.out.println("4. Tampilkan Daftar Siswa");
                System.out.println("5. Keluar");
                System.out.print("Pilih operasi (1/2/3/4/5): ");
                int choice = input.nextInt();
                input.nextLine();  // Membuang karakter baru

                switch (choice) {
                    case 1:
                        tambahSiswa(conn, input);
                        break;
                    case 2:
                        perbaruiSiswa(conn, input);
                        break;
                    case 3:
                        hapusSiswa(conn, input);
                        break;
                    case 4:
                        tampilkanDaftarSiswa(conn);
                        break;
                    case 5:
                        conn.close();
                        System.out.println("Terima kasih, program selesai.");
                        System.exit(0);
                    default:
                        System.out.println("Pilihan tidak valid, silakan coba lagi.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Koneksi database gagal. Pastikan database sudah diatur.");
        }
    }

    private static void tambahSiswa(Connection conn, Scanner input) throws SQLException {
        System.out.println("Masukkan data siswa baru:");
        System.out.print("NIS: ");
        String nis = input.nextLine();
        System.out.print("Nama: ");
        String nama = input.nextLine();
        System.out.print("Jurusan: ");
        String jurusan = input.nextLine();

        String sql = "INSERT INTO siswa (nis, nama, jurusan) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, nis);
        stmt.setString(2, nama);
        stmt.setString(3, jurusan);

        int affectedRows = stmt.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Siswa baru telah ditambahkan.");
        } else {
            System.out.println("Gagal menambahkan siswa.");
        }
    }

    private static void perbaruiSiswa(Connection conn, Scanner input) throws SQLException {
        System.out.print("Masukkan NIS siswa yang akan diperbarui: ");
        String nis = input.nextLine();

        // Periksa apakah siswa dengan NIS yang diberikan ada
        String checkSql = "SELECT * FROM siswa WHERE nis = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        checkStmt.setString(1, nis);
        ResultSet resultSet = checkStmt.executeQuery();

        if (resultSet.next()) {
            System.out.println("Data siswa saat ini:");
            System.out.println("NIS: " + resultSet.getString("nis"));
            System.out.println("Nama: " + resultSet.getString("nama"));
            System.out.println("Jurusan: " + resultSet.getString("jurusan"));

            System.out.println("Masukkan data perbaruan:");
            System.out.print("Nama: ");
            String nama = input.nextLine();
            System.out.print("Jurusan: ");
            String jurusan = input.nextLine();

            String updateSql = "UPDATE siswa SET nama = ?, jurusan = ? WHERE nis = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setString(1, nama);
            updateStmt.setString(2, jurusan);
            updateStmt.setString(3, nis);

            int affectedRows = updateStmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Data siswa telah diperbarui.");
            } else {
                System.out.println("Gagal memperbarui data siswa.");
            }
        } else {
            System.out.println("Siswa dengan NIS " + nis + " tidak ditemukan.");
        }
    }

    private static void hapusSiswa(Connection conn, Scanner input) throws SQLException {
        System.out.print("Masukkan NIS siswa yang akan dihapus: ");
        String nis = input.nextLine();

        String deleteSql = "DELETE FROM siswa WHERE nis = ?";
        PreparedStatement deleteStmt = conn.prepareStatement(deleteSql);
        deleteStmt.setString(1, nis);

        int affectedRows = deleteStmt.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Data siswa telah dihapus.");
        } else {
            System.out.println("Gagal menghapus data siswa.");
        }
    }

    private static void tampilkanDaftarSiswa(Connection conn) throws SQLException {
        String sql = "SELECT * FROM siswa";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet resultSet = stmt.executeQuery();

        System.out.println("Daftar Siswa:");
        while (resultSet.next()) {
            System.out.println("NIS: " + resultSet.getString("nis"));
            System.out.println("Nama: " + resultSet.getString("nama"));
            System.out.println("Jurusan: " + resultSet.getString("jurusan"));
            System.out.println("-----------------------");
        }
    }
}
