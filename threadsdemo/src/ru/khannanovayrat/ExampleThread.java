package ru.khannanovayrat;

/**
 * Created by Ayrat on 29.11.2016.
 */
public class ExampleThread extends Thread{

    public ExampleThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            try {
                sleep(100);
            } catch (InterruptedException e) {}
            System.out.println(getName() + " running .. " + i);
        }
    }
}
