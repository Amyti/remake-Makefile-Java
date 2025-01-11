import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Quel âge as-tu ? ");
        int age = scanner.nextInt();

        scanner.close();

         Utils.resultat(age);

    }
}
