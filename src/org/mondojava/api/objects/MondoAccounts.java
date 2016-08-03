package org.mondojava.api.objects;

import java.util.ArrayList;

/**
 *
 * @author dgraf
 * Represents the list of Mondo accounts returned by the /accounts endpoint
 */
public class MondoAccounts {
    
    public MondoAccounts()
    {
        accounts = new ArrayList<>();
    }

    private ArrayList<MondoAccount> accounts;

    public ArrayList<MondoAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<MondoAccount> accounts) {
        this.accounts = accounts;
    }
    
}
