package src.Lesson06_SOLID.Lab.solid.p04_InterfaceSegregation.p02_identity;

import src.Lesson06_SOLID.Lab.solid.p04_InterfaceSegregation.p02_identity.interfaces.Account;

public class AccountController {
    private final Account manager;

    public AccountController(Account manager) {
        this.manager = manager;
    }
    public void changePassword(String oldPass,String newPass){
        this.manager.changePassword(oldPass,newPass);
    }
}
