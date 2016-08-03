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
public class MondoExpandedTransactionContainer {
    
    public MondoExpandedTransactionContainer()
    {
        transaction = new MondoExpandedTransaction();
    }
    private MondoExpandedTransaction transaction;

    public MondoExpandedTransaction getTransaction() {
        return transaction;
    }

    public void setTransaction(MondoExpandedTransaction transaction) {
        this.transaction = transaction;
    }
    
}
