package org.mondojava.api.objects;

/**
 *
 * @author dgraf
 * Represents the Counterparty in the transaction object
 * in a funds transfer
 */
public class MondoCounterParty {
    
    public MondoCounterParty()
    {
        name = "";
        number = "";
        prefered_name = "";
        user_id = "";
    }
    private String name;
    private String number;
    private String prefered_name;
    private String user_id;

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setPrefered_name(String prefered_name) {
        this.prefered_name = prefered_name;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    
    
    
}
