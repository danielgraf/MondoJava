package org.mondojava.api.objects;

/**
 *
 * @author dgraf
 * Contains the error message if authorisation fails
 */
public class MondoAuthError {
    
    private String error;
    
    public MondoAuthError()
    {
        error = "";
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
