package org.mondojava.api.objects;

/**
 *
 * @author dgraf
 * Contains the payload returned when token exchange is successful
 */
public class MondoAuthResponse {
    
    public MondoAuthResponse()
    {
        access_token = "";
        client_id = "";
        expires_in = 0;
        refresh_token = "";
        token_type = "";
        user_id = "";

    }
    
    private String access_token;
    private String client_id;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    private int expires_in;
    private String refresh_token;
    private String token_type;
    private String user_id;
  
    
}
