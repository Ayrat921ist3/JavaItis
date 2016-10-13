package ru.itis.services;

import ru.itis.models.Owner;

import java.util.List;

/**
 * Created by KFU-user on 13.10.2016.
 */
public interface OwnerService {
    Owner findOwner(int id);
    List<Owner> getOwners();
    void deleteOwner(int id);
    void addOwner(Owner owner);
    void updateOwner(Owner owner);
}
