package org.mondojava.api.objects;

/**
 *
 * @author dgraf
 * Represents the response from the /ping endpoint
 */
public class MondoPing {
    
    public MondoPing()
    {
        ping = "";
    }
    
    private String ping;

    public String getPing() {
        return ping;
    }

    public void setPing(String ping) {
        this.ping = ping;
    }
    
    
}
