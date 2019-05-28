import api.PeselApi;
import models.Person;

import java.util.Scanner;

/**
 * Created by Jakub Filipiak on 28.05.2019.
 */
public class PeselDecoderApp {

    public static void main(String[] args) {

        PeselApi peselApi = new PeselApi();

        Scanner scanner = new Scanner(System.in);
        String pesel;

        System.out.println("------ PESEL Decoder ------");
        System.out.println("---------------------------");

        do {
            System.out.println("PESEL number:");
            pesel = scanner.nextLine().trim();
            System.out.println(pesel);
        } while (!peselApi.isPeselCorrect(pesel));

        Person person = peselApi.createPersonFromPesel(pesel);
        System.out.println(person);
    }
}
