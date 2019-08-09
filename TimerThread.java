package abhi.bruteforce;

class TimerThread implements Runnable {
    Thread thread;
    private TimerThread() {
        thread = new Thread(this);
    }

    static TimerThread createAndStart() {
        TimerThread timerThread = new TimerThread();
        timerThread.thread.start();
        return timerThread;
    }

    @Override
    public void run() {
        do {
            try {
                Thread.sleep(1000);
                Bruteforce.seconds++;
                if (Bruteforce.seconds == 60)
                    System.out.println("\nIt's taking longer than usual");
                else if (Bruteforce.seconds == 120)
                    System.out.println("\nPassword still not found. Terminating in 30 seconds");
                else if (Bruteforce.seconds == 150) {
                    System.out.println("Terminating...\tTime elapsed: " + Bruteforce.seconds + " seconds");
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (true);
    }
}
