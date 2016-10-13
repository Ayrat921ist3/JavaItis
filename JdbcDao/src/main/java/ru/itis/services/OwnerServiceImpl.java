package ru.itis.services;

import ru.itis.dao.Dao;
import ru.itis.dao.OwnersDaoJdbcImpl;
import ru.itis.models.Owner;
import ru.itis.utils.Verifier;

import java.util.List;

/**
 * Created by KFU-user on 13.10.2016.
 */
public class OwnerServiceImpl implements OwnerService{

    private Dao ownersDao;

    public OwnerServiceImpl(Dao ownersDao) {
        this.ownersDao = ownersDao;
    }

    public Owner findOwner(int id){
        return (Owner)ownersDao.find(id);
    }

    public List<Owner> getOwners(){
        return ownersDao.getAll();
    }

    public void deleteOwner(int id){
        ownersDao.delete(id);
    }

    public void updateOwner(Owner owner){
        Verifier.verifyUserExist(owner.getId());
        ownersDao.update(owner);
    }

    public void addOwner(Owner owner){
        ownersDao.add(owner);
    }
}

