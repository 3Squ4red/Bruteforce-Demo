package abhi.bruteforce;

import java.util.Scanner;

public class Login extends SignUp{
    public static void main(String... args) {
        // Input stuff as usual
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String name, pass;
        name = input.next();
        do {
            if (nameNotFound(name)) {
                System.out.print("Name not found. Enter a new name or consider signing up first: ");
                name = input.next();
            }
        } while (nameNotFound(name));
        System.out.print("Enter your password: ");
        pass = input.next();
        do {
            if (!validPassword(pass)) {
                System.out.print("Enter a valid password: ");
                pass = input.next();
            }
        } while (!validPassword(pass));
        input.close();
        // Done inputting

        if (encrypt(pass).equals(getEncryptedPass(name))) System.out.println("LOGIN SUCCESSFUL!");
        else System.out.println("WRONG PASSWORD");
    }
}
