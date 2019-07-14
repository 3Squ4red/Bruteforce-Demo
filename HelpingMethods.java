import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


class HelpingMethods {
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
}
