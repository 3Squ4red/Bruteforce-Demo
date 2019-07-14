package abhi.bruteforce;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SignUp {
    public static void main(String[] args) {
        // Input stuff as usual
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String name, pass;
        name = input.next();
        do {
            if (!nameNotFound(name)) {
                System.out.print("Name already entered. Enter a new name or try to login: ");
                name = input.next();
            }
        } while (!nameNotFound(name));
        System.out.println("Your password should:\n *CONTAIN ANYTHING BUT NOT SPACE\n *BE 5 TO 15 LETTERS LONG");
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

        // Writing information to the file
        try(FileWriter fw = new FileWriter("Passwords.txt", true)) {
            fw.write(name + "-" + encrypt(pass) + "\n");
        } catch (IOException e ) {
            System.out.println("Error creating file");
            System.exit(0);
        }
        System.out.println("Information successfully written to Passwords.txt");
    }

    static String encrypt(String password) {
        String encryptPass = "";
        char[] characters = password.toCharArray();
        int[] encryptChars = new int[characters.length];
        int garbage = 0;
        for (int i = 0; i < 15; i++) {
            if (i < characters.length) {
                encryptChars[i] = characters[i] - 1;
                encryptPass += encryptChars[i];
            } else {
                encryptPass += garbage++;
            }
        }
        return encryptPass;
    }

    static boolean validPassword(String password) {
        return password.length() >= 5 && password.length() <= 15;
    }

    static boolean nameNotFound(String name) {
        String[] names = getAllNames();
        for (String n : names)
            if (name.equals(n)) return false;
        return true;
    }
    private static String[] getAllNames() {
        String text;
        ArrayList<String> names = new ArrayList<>();
        String[] info;
        try (BufferedReader bf = new BufferedReader(new FileReader("Passwords.txt"))) {
            while ((text = bf.readLine()) != null) {
                info = text.split("-");
                names.add(info[0]);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        String[] namesArr = new String[names.size()];
        for (int i = 0; i < names.size(); i++) namesArr[i] = names.get(i);
        return namesArr;
    }

    // Use this if you want. It will return an String array containing all the Passwords in 'Password.txt' file.
    static String[] getAllEncryptedPass() {
        String text;
        ArrayList<String> pass = new ArrayList<>();
        String[] info;
        try (BufferedReader bf = new BufferedReader(new FileReader("Passwords.txt"))) {
            while ((text = bf.readLine()) != null) {
                info = text.split("-");
                pass.add(info[1]);
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        String[] passArr = new String[pass.size()];
        for (int i = 0; i < pass.size(); i++) passArr[i] = pass.get(i);
        return passArr;
    }
    static String getEncryptedPass(String name) {
        String text;
        String[] info;
        try (BufferedReader bf = new BufferedReader(new FileReader("Passwords.txt"))) {
            while ((text = bf.readLine()) != null) {
                info = text.split("-");
                if (info[0].equals(name)) return info[1];
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        return "-1";
    }
}
