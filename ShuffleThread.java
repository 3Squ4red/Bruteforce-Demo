import java.io.FileWriter;
import java.io.IOException;

class ShuffleThread extends HelpingMethods implements Runnable {
    Thread thread;
    private String combination, name;

    private ShuffleThread(String threadName, String combination, String name) {
        thread = new Thread(this, threadName);
        this.combination = combination;
        this.name = name;
    }

    static ShuffleThread createAndStart(String threadName, String combination, String name) {
        ShuffleThread shuffleThread = new ShuffleThread(threadName, combination, name);
        shuffleThread.thread.start();
        return shuffleThread;
    }

    @Override
    public void run() {
        String encryptedPass = getEncryptedPass(name);
        String guessedPass;
        do {
            if (!Bruteforce.passwordFound) {
                Bruteforce.possiblePasswords++;
                guessedPass = shuffle(combination);
                if (!encrypt(guessedPass).equals(encryptedPass))
                    Bruteforce.lastPassword = guessedPass;
            } else return;
        } while (!encrypt(guessedPass).equals(encryptedPass));
        Bruteforce.passwordFound = true;
        System.out.println("\n" + thread.getName() + " found the password. Say thanks");
        System.out.println("\nPassword: " + guessedPass);
        System.out.println("Time elapsed: " + Bruteforce.seconds + " second(s)");
        System.out.println("Tried passwords: " + Bruteforce.possiblePasswords);
        System.out.println("Last tried password: " + Bruteforce.lastPassword);
        System.exit(0);
    }

    static String shuffle(String str) {
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            int j = (int) (Math.random() * chars.length);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        return new String(chars);
    }
}
