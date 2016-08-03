package org.mondojava.api.rest;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.mondojava.api.utilities.HttpResponseException;

/**
 *
 * @author dgraf JAX-RS client for the Mondo API
 */
public class RESTclient {

    private final static String BASE_URL = "https://api.getmondo.co.uk";
    private final String end_point;
    private final CloseableHttpClient httpclient;

    // Construct a client for a particular endpoint
    // e.g. "transactions" or "balance"
    public RESTclient(String endpoint) {
        end_point = endpoint;
        httpclient = HttpClients.createDefault();
    }

    // Execute a get method on this endpoint
    // Supply your access token 
    // and an optional array of parameters if the get method requires them
    // returns the JSON from the Mondo API
    public String get(String access_token, AbstractMap.SimpleEntry... parameters) throws Exception {
        HttpGet get = new HttpGet(BASE_URL + "/" + end_point);
        URIBuilder builder = new URIBuilder(get.getURI());

        for (AbstractMap.SimpleEntry parameter : parameters) {
            builder.setParameter((String) parameter.getKey(), (String) parameter.getValue());
        }
        get.setURI(builder.build());
        get.setHeader("Authorization", "Bearer " + access_token);
        CloseableHttpResponse resp = httpclient.execute(get);

        if (resp.getStatusLine().getStatusCode() != 200) {
            throw new HttpResponseException(resp);
        } else {
            String content = EntityUtils.toString(resp.getEntity(), "UTF-8");
            resp.close();
            return content;
        }

    }
    
    // Execute a get method on this endpoint on an entity
    // Supply your access token 
    // and an optional array of parameters if the get method requires them
    // returns the JSON from the Mondo API
    public String get(String access_token, String get_entity, AbstractMap.SimpleEntry... parameters) throws Exception {
        HttpGet get = new HttpGet(BASE_URL + "/" + end_point + "/" + get_entity);
        URIBuilder builder = new URIBuilder(get.getURI());

        for (AbstractMap.SimpleEntry parameter : parameters) {
            builder.setParameter((String) parameter.getKey(), (String) parameter.getValue());
        }
        get.setURI(builder.build());
        get.setHeader("Authorization", "Bearer " + access_token);
        CloseableHttpResponse resp = httpclient.execute(get);

        if (resp.getStatusLine().getStatusCode() != 200) {
            throw new HttpResponseException(resp);
        } else {
            String content = EntityUtils.toString(resp.getEntity(), "UTF-8");
            resp.close();
            return content;
        }

    }
    
    // Execute a delete method on this endpoint on an entity
    // Supply your access token 
    // and an optional array of parameters if the get method requires them
    // returns the JSON from the Mondo API
    public String delete(String access_token, String del_entity, AbstractMap.SimpleEntry... parameters) throws Exception {
        HttpDelete del = new HttpDelete(BASE_URL + "/" + end_point + "/" + del_entity);
        URIBuilder builder = new URIBuilder(del.getURI());

        for (AbstractMap.SimpleEntry parameter : parameters) {
            builder.setParameter((String) parameter.getKey(), (String) parameter.getValue());
        }
        del.setURI(builder.build());
        del.setHeader("Authorization", "Bearer " + access_token);
        CloseableHttpResponse resp = httpclient.execute(del);

        if (resp.getStatusLine().getStatusCode() != 200) {
            throw new HttpResponseException(resp);
        } else {
            String content = EntityUtils.toString(resp.getEntity(), "UTF-8");
            resp.close();
            return content;
        }

    }
    
    // Execute a patch method on this endpoint on an entity
    // This is used to annotate transactions
    // Supply your access token 
    // and the patch entity - e.g. the transaction id
    // and an array of tags
    // returns the JSON from the Mondo API
    public String patch(String access_token, String patch_entity, AbstractMap.SimpleEntry... tags) throws Exception {
        HttpPatch patch = new HttpPatch(BASE_URL + "/" + end_point + "/" + patch_entity);
        patch.setHeader("Authorization", "Bearer " + access_token);
        List<NameValuePair> formparams = new ArrayList<>();

        for (SimpleEntry tag : tags) {
            formparams.add(new BasicNameValuePair((String) tag.getKey(), (String) tag.getValue()));
        }

        UrlEncodedFormEntity form = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        patch.setEntity(form);

        CloseableHttpResponse resp = httpclient.execute(patch);

        if (resp.getStatusLine().getStatusCode() != 200) {
            throw new HttpResponseException(resp);
        }
        String content = EntityUtils.toString(resp.getEntity(), "UTF-8");
        resp.close();
        return content;

    }
    
    public String post(String access_token, AbstractMap.SimpleEntry... tags) throws Exception {
        HttpPost post = new HttpPost(BASE_URL + "/" + end_point);
        post.setHeader("Authorization", "Bearer " + access_token);
        List<NameValuePair> formparams = new ArrayList<>();

        for (SimpleEntry tag : tags) {
            formparams.add(new BasicNameValuePair((String) tag.getKey(), (String) tag.getValue()));
        }

        UrlEncodedFormEntity form = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

        post.setEntity(form);

        CloseableHttpResponse resp = httpclient.execute(post);

        if (resp.getStatusLine().getStatusCode() != 200) {
            throw new HttpResponseException(resp);
        }
        String content = EntityUtils.toString(resp.getEntity(), "UTF-8");
        resp.close();
        return content;

    }

}
