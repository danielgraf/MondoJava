package org.mondojava.api.rest;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.mondojava.api.objects.MondoAuthResponse;
import org.mondojava.api.utilities.GenerateState;
import org.mondojava.api.utilities.HttpResponseException;
import org.mondojava.api.utilities.ToJava;

/**
 *
 * @author dgraf Authentication api consumer Uses JAX-RS to perform OAuth2 steps
 */
public class AUTHclient {

    private final CloseableHttpClient httpclient;
    private final static String BASE_URL = "https://auth.getmondo.co.uk";
    private final static String EXCH_URL = "https://api.getmondo.co.uk/oauth2/token";
    private String state; // The state token used in the first authorisation step

    // Return the state token (for comparison)
    public String getState() {
        return state;
    }

    public AUTHclient() {
        httpclient = HttpClients.createDefault();
        state = "";
    }

    // Generates the URL required to get an authorisation code for this client_id
    public String getAuthenticationURL(String cl_id, String redirect_uri) throws Exception {

        StringBuilder url = new StringBuilder();
        url.append(BASE_URL).append("?");
        url.append("client_id=").append(cl_id).append("&");
        url.append("redirect_uri=").append(redirect_uri).append("&");
        url.append("response_type=").append("code").append("&");
        state = GenerateState.generate();
        url.append("state=").append(state);
        return url.toString();
    }

    // Performs the API call to exchange an access code for the access token
    public MondoAuthResponse getAccessToken(String code, String client_id, String client_secret, String redirect_uri) throws Exception {

        HttpPost post = new HttpPost(EXCH_URL);

        List<NameValuePair> formparams = new ArrayList<>();

        formparams.add(new BasicNameValuePair("grant_type", "authorization_code"));
        formparams.add(new BasicNameValuePair("client_id", client_id));
        formparams.add(new BasicNameValuePair("client_secret", client_secret));
        formparams.add(new BasicNameValuePair("redirect_uri", redirect_uri));
        formparams.add(new BasicNameValuePair("code", code));

        UrlEncodedFormEntity form = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        post.setEntity(form);

        CloseableHttpResponse resp = httpclient.execute(post);

        if (resp.getStatusLine().getStatusCode() != 200) {
            throw new HttpResponseException(resp);
        }

        MondoAuthResponse authresponse = (MondoAuthResponse) ToJava.convert(EntityUtils.toString(resp.getEntity(), "UTF-8"), MondoAuthResponse.class);
        //TODO - remove this
        System.out.println("Access Token : " + authresponse.getAccess_token());
        resp.close();
        return authresponse;

    }

}
