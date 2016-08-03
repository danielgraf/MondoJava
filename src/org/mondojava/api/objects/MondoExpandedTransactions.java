package org.mondojava.api.objects;

import java.util.ArrayList;

/**
 *
 * @author dgraf
 * Represents the list of transactions returned by the /transactions endpoint
 */
public class MondoExpandedTransactions {
    
    public MondoExpandedTransactions()
    {
        transactions = new ArrayList<>();
    }
    
    private ArrayList<MondoExpandedTransaction> transactions;

    public ArrayList<MondoExpandedTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<MondoExpandedTransaction> transactions) {
        this.transactions = transactions;
    }
    
}
