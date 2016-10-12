package ru.itis.dao;

import java.util.List;

/**
 * Created by KFU-user on 12.10.2016.
 */
public interface Dao<T> {

    T find(int id);
    List<T> getAll();
    void delete(int id);
    void update(T obj);
    void add(T obj);
}
