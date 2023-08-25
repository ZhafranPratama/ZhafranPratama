import java.util.Scanner;

public class suhu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Masukkan suhu dalam celcius:");
        int celcius = scanner.nextInt();

        System.out.print("Suhu dalam Fahrenheit: ");
        System.out.println(celsiusToFahrenheit(celcius));
        System.out.print("Suhu dalam Kelvin: ");
        System.out.println(celsiusToKelvin(celcius));

        scanner.close();
    }

    public static double celsiusToFahrenheit(double celsius) {
        return celsius * 9/5 + 32;
    }

    public static double celsiusToKelvin(double celsius) {
        return celsius + 273.15;
    }

}