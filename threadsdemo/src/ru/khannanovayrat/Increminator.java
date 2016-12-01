package ru.khannanovayrat;

/**
 * Created by Ayrat on 29.11.2016.
 */
public class Increminator extends Thread {

    private volatile boolean mIsIncrement = true;

    public void changeAction(){
        mIsIncrement = !mIsIncrement;
    }

    @Override
    public void run() {
        while (true){
            if(!Thread.interrupted()){
                if(mIsIncrement){
                    Main.mValue ++;
                }else{
                    Main.mValue --;
                }
                System.out.println("Current value " + Main.mValue);
            }else {
                return;
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
