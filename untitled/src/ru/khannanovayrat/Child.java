package ru.khannanovayrat;

import java.util.List;

/**
 * Created by Ayrat on 18.11.2016.
 */
public class Child {
    public void m(final List arg){
        arg.add("a");
        arg.add("b");
        for (Object s : arg){
            System.out.println(s.toString());
        }
    }
}
