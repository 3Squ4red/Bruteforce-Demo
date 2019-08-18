import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Bruteforce extends HelpingMethods {
    static boolean passwordFound = false;
    static long seconds, possiblePasswords;
    static String lastPassword;
    private static ShuffleThread[] shuffleThreads;
    private static String passwordHash;
    private static int len;
    private static String guessedPass = "";

    public static void main(String[] args) {
        String combination;
        // Gathering information
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String name;
        name = input.next();
        do {
            if (nameNotFound(name)) {
                System.out.print("Name not found. Check it: ");
                name = input.next();
            }
        } while (nameNotFound(name));
        System.out.println("NAME SUCCESSFULLY MATCHED!");

        System.out.println("There are two Attack Modes based on information you have." +
                "\n   *Jumbled word decryptor: Choose this if you have a jumbled form of the complete password." +
                "\n                            DESCRIPTION* Choose this only if you have a word of EXACT SAME length as that of the original password\n" +
                "                            BUT IN JUMBLED FORM." +
                "\n                            FOR EXAMPLE: The password is: cricketlover." +
                "\n                            And you have a jumbled form of it. Something like: irlcceoktrve. Then you must go for" +
                "\n                            Jumbled word decryptor by entering 1." +
                "\n\n *Password guessing.:     Choose this if you think you can guess the original password by providing few random words." +
                "\n                            DESCRIPTION* Use this mode only if you can provide a set of random words which you think might" +
                "\n                            consist the words of the original password" +
                "\n                            YOU MUST ALSO PROVIDE THE LENGTH OF THE ACTUAL PASSWORD" +
                "\n                            FOR EXAMPLE: The password is: cricketlover." +
                "\n                            And you are able to provide the EXACT length of it(13, in this case) and also few random words like:" +
                "\n                           \"home, laundry, cricket, love, boyfriend, laptop, lover\"" +
                "\n                            Then you must go for Password guessing by entering 2.\n");
        System.out.println("Choose your attack type:");
        System.out.println("    1. Jumbled word decryptor." +
                "               2. Password guessing");

        int mode = input.nextInt();
        if (mode == 1) {
            System.out.println("Now enter the jumbled word and I'll try to crack your password ;)");
            combination = input.next();
            // Displaying known information
            System.out.print("\n(NAME: " + name + ")\t(PROVIDED COMBINATION: " + combination + ")");

            // Cracking begins
            shuffleThreads = new ShuffleThread[120];
            for (int i = 0; i < 120; i++)
                shuffleThreads[i] = ShuffleThread.createAndStart(i + "#Thread", combination, name);
            TimerThread timerThread = TimerThread.createAndStart();
            timerThread.thread.setPriority(Thread.MAX_PRIORITY);
            try {
                for (ShuffleThread st : shuffleThreads) st.thread.join();
                timerThread.thread.join();
            } catch (InterruptedException e) {
                System.out.println("Thread Interrupted");
            }
        } else if (mode == 2) {
            passwordHash = getEncryptedPass(name);
            System.out.print("Enter the length of the password: ");
            len = input.nextInt();
            System.out.println("Now enter few words separated by semicolon (NO SPACE!), I'll try to crack your password ;)");
            String word = input.next();
            String[] enteredWords = word.split(";");
            ArrayList<String> words = new ArrayList<>();
            Collections.addAll(words, enteredWords);

            // Main Stuff
            System.out.println("Words entered: " + words);
            String[] elements = new String[words.size()];
            for (int i = 0; i < elements.length; i++) elements[i] = words.get(i);
            Permutation.addAllRecursive(elements, ';');
            //System.out.println("\nPermutated result: " + abhi.bruteforce.Permutation.permutedResult);
            for (int i = 0; i < Permutation.permutedResult.size(); i++) {
                String[] set = Permutation.permutedResult.get(i).split(";");
                // UNCOMMENT THIS TO SEE ALL THE GENERATED COMBINATION OF PASSWORDS
                // System.out.println(Arrays.toString(set));
                loop(set, 0);
                guessedPass = "";
            }
            System.out.println("\nSORRY BRO CAN'T FIND YOUR PASSWORD.\nTRY WITH SOME OTHER LENGTH");
        }
        input.close();
    }

    private static void loop(String[] words, int index) {
        if (index < words.length) {
            guessedPass += words[index];
            if (encrypt(guessedPass).equals(passwordHash)) {
                System.out.println("\nPassword: " + guessedPass);
                System.out.println("Possible passwords found so far: " + possiblePasswords);
                System.out.println("Last tried password: " + lastPassword);
                System.exit(0);
            } else if (guessedPass.length() < len) {
                index++;
                loop(words, index);
            }
            if (guessedPass.length() == len) {
                lastPassword = guessedPass;
                possiblePasswords++;
            }
        }
    }
}
