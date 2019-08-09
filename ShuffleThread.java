class ShuffleThread extends HelpingMethods implements Runnable{
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
        String guessedPass = ""; int itr = 0;
        do {
            if (!Bruteforce.passwordFound) {
                itr++;
                guessedPass = shuffle(combination);
            } else  System.exit(0);
        } while (!encrypt(guessedPass).equals(encryptedPass));
        System.out.println("\n" + thread.getName() + " found the password. Say thanks");
        System.out.println("\nPassword: " + guessedPass);
        System.out.println("Iterations: " + itr);
        System.out.println("Time elapsed: " + Bruteforce.seconds + " seconds");
        Bruteforce.passwordFound = true;
    }
    static String shuffle(String str ) {
        char[] chars = str.toCharArray();

        for (int i = 0; i < chars.length;i++) {
            int j = (int) (Math.random()*chars.length);
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
        }

        return new String(chars);
    }
}
