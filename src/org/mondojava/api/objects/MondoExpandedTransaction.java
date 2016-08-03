package org.mondojava.api.objects;

/**
 *
 * @author dgraf
 * Represents the transaction object with the merchant expanded
 * as returned in a list by the /transactions endpoint
 */
public class MondoExpandedTransaction extends MondoTransaction {

    public MondoExpandedTransaction() {

        merchant = new MondoMerchant();
        decline_reason = "";

    }
    
    private MondoMerchant merchant;
    private String decline_reason;

    public String getDecline_reason() {
        return decline_reason;
    }

    public void setDecline_reason(String decline_reason) {
        this.decline_reason = decline_reason;
    }

    
    @Override
    public MondoMerchant getMerchant() {
        return merchant;
    }

    public void setMerchant(MondoMerchant merchant) {
        this.merchant = merchant;
    }

}
