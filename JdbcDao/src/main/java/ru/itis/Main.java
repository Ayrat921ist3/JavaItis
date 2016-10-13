package ru.itis;

import ru.itis.factory.ServiceFactory;
import ru.itis.models.Owner;
import ru.itis.services.OwnerService;
import ru.itis.services.OwnerServiceImpl;

/**
 * Created by KFU-user on 12.10.2016.
 */
public class Main {

    public static void main(String[] args) {
        OwnerService ownerService = ServiceFactory.getInstance().getOwnerService();

        System.out.println(ownerService.findOwner(1));
        System.out.println();

        for (Owner owner : ownerService.getOwners()){
            System.out.println(owner);
        }

        ownerService.deleteOwner(2);

        ownerService.updateOwner(new Owner("Vasiliy", 1, "Moscow", 25));

        ownerService.addOwner(new Owner("John", "New York", 43));

        System.out.println();

        for (Owner owner : ownerService.getOwners()){
            System.out.println(owner);
        }
    }
}
