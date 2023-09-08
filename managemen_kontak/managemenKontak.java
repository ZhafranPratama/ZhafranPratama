import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class managemenKontak {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Kontak> daftarKontak = new ArrayList<>();

        // Memuat daftar kontak dari file jika file sudah ada
        try {
            FileInputStream fileInput = new FileInputStream("kontak.ser");
            ObjectInputStream objectInput = new ObjectInputStream(fileInput);
            daftarKontak = (ArrayList<Kontak>) objectInput.readObject();
            objectInput.close();
            fileInput.close();
        } catch (IOException | ClassNotFoundException e) {
            // File belum ada atau ada masalah saat membaca, abaikan
        }

        int pilihan;
        do {
            System.out.println("Menu Aplikasi Manajemen Kontak:");
            System.out.println("1. Tambah Kontak");
            System.out.println("2. Hapus Kontak berdasarkan Nama");
            System.out.println("3. Hapus Kontak berdasarkan Nomor Telepon");
            System.out.println("4. Tampilkan Daftar Kontak");
            System.out.println("5. Simpan dan Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Membuang newline

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Nomor Telepon: ");
                    String nomorTelepon = scanner.nextLine();
                    System.out.print("Masukkan Email: ");
                    String email = scanner.nextLine();
                    Kontak kontakBaru = new Kontak(nama, nomorTelepon, email);
                    daftarKontak.add(kontakBaru);
                    System.out.println("Kontak berhasil ditambahkan!");
                    break;
                case 2:
                    System.out.print("Masukkan Nama Kontak yang akan dihapus: ");
                    String namaHapus = scanner.nextLine();
                    daftarKontak.removeIf(kontak -> kontak.getNama().equalsIgnoreCase(namaHapus));
                    System.out.println("Kontak berhasil dihapus!");
                    break;
                case 3:
                    System.out.print("Masukkan Nomor Telepon Kontak yang akan dihapus: ");
                    String nomorTeleponHapus = scanner.nextLine();
                    daftarKontak.removeIf(kontak -> kontak.getNomorTelepon().equals(nomorTeleponHapus));
                    System.out.println("Kontak berhasil dihapus!");
                    break;
                case 4:
                    System.out.println("Daftar Kontak:");
                    for (Kontak kontak : daftarKontak) {
                        System.out.println(kontak);
                        System.out.println("-----------");
                    }
                    break;
                case 5:
                    // Simpan daftar kontak ke dalam file sebelum keluar
                    try {
                        FileOutputStream fileOutput = new FileOutputStream("kontak.ser");
                        ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
                        objectOutput.writeObject(daftarKontak);
                        objectOutput.close();
                        fileOutput.close();
                        System.out.println("Daftar kontak berhasil disimpan!");
                    } catch (IOException e) {
                        System.out.println("Terjadi kesalahan saat menyimpan kontak.");
                    }
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 5);

        scanner.close();
    }
}

class Kontak implements Serializable {
    private String nama;
    private String nomorTelepon;
    private String email;

    // Konstruktor
    public Kontak(String nama, String nomorTelepon, String email) {
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.email = email;
    }

    // Getter dan setter untuk atribut-atribut
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + "\nNomor Telepon: " + nomorTelepon + "\nEmail: " + email;
    }
}
