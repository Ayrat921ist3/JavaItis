package ru.khannanovayrat;

public class Main {

    public static int mValue = 0;

    public static void main(String[] args) {
        daemonExample();
    }

    private static void daemonExample(){
        ExampleThread thread = new ExampleThread("Daemon thread");
        thread.setDaemon(true);
        thread.start();

        for (int i = 0; i < 50; i++){

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}

            System.out.println("Main thread running .. " + i);
        }
    }

    private static void interruptDemo(){
        Increminator inc = new Increminator();
        inc.start();

        for (int i = 0; i < 3; i++){
            try {
                Thread.sleep(i * 2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inc.changeAction();
        }

        inc.interrupt();
    }
}
