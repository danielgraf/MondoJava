/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mondojava.api.objects;

/**
 *
 * @author dgraf
 */
public class MondoTransactionContainer {
    
    public MondoTransactionContainer()
    {
        transaction = new MondoTransaction();
    }
    private MondoTransaction transaction;

    public MondoTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(MondoTransaction transaction) {
        this.transaction = transaction;
    }
    
}
