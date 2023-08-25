import java.util.Scanner;

public class calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat datang di Kalkulator Sederhana!");
        System.out.println("Operasi yang dapat dilakukan:");
        System.out.println("1. Penjumlahan");
        System.out.println("2. Pengurangan");
        System.out.println("3. Perkalian");
        System.out.println("4. Pembagian");

        while (true) {
            System.out.print("Masukkan pilihan operasi (1/2/3/4): ");
            int choice = scanner.nextInt();

            if (choice < 1 || choice > 4) {
                System.out.println("Pilihan tidak valid. Mohon masukkan angka antara 1 hingga 4.");
                continue;
            }

            System.out.print("Masukkan angka pertama: ");
            double num1 = scanner.nextDouble();

            System.out.print("Masukkan angka kedua: ");
            double num2 = scanner.nextDouble();

            double result = 0;
            switch (choice) {
                case 1:
                    result = num1 + num2;
                    break;
                case 2:
                    result = num1 - num2;
                    break;
                case 3:
                    result = num1 * num2;
                    break;
                case 4:
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        System.out.println("Error: Pembagian oleh nol.");
                        continue;
                    }
                    break;
            }

            System.out.println("Hasil: " + result);

            System.out.print("Apakah Anda ingin melakukan operasi lain? (y/n): ");
            String another = scanner.next();
            if (!another.equalsIgnoreCase("y")) {
                System.out.println("Terima kasih telah menggunakan kalkulator ini. Sampai jumpa!");
                break;
            }
        }

        scanner.close();
    }
}
