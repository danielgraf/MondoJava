# MondoJava
A Java library for Mondo Bank

This library enables Java applications to use the Mondo Bank API, including helper methods for OAuth2 authentication, and almost all API features.

Still to do : Transaction Attachments.

# How ?

The library is a Netbeans / Ant project. Compile it from the command line with ant, or load it as a Netbeans project. The result is a JAR file you can incorporate into your own application. 

This library does not store or manage client_id or client_secret - that's up to you.

# Examples - Authorisation

Because of the OAuth2 flow with the email magic button, it is only possible to perform authorisation with this library using a web app.

To use this library without performing the authorisation process is possible, but you need to have obtained your access token outside of the library first - then you can set it like this :

```java
   // Initialise a MondoClient with an existing access token
   MondoClient mc = new MondoClient("<your client ID>", "<your client secret>");
   mc.setAccess_token(access_token); // if you already have one
```
Within a Java web app you can use servlets to provide the steps of the authorisation flow. One servlet should be used for stage 1, in which we redirect the user to the Mondo authorisation page where they can enter their email address and receive the email containing the redirect link which should be pointed at the second servlet. The first servlet looks like this :

```java
    // A Servlet which performs the first step in authentication
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        PrintWriter out = response.getWriter();
        
        // Initialise a new MondoClient - here I keep the client ID and client secret in environment vars
        MondoClient mc = new MondoClient(System.getenv("mondo_client_id"), System.getenv("mondo_client_secret"));
        
        try {
            // Build a Mondo authentication URL using the MondoClient
            String URL = mc.getAuthenticationURL(request.getRequestURL().toString());
            
            // The URL is based on that of this servlet - point the redirect at the callback servlet
            // which will be called after the magic email button is pressed
            URL = URL.replace("/AuthServlet", "/OauthCallbackServlet");

            // Store the MondoClient in the user's session 
            // so it's available after the redirect - the state token is in here now
            HttpSession session = request.getSession();
            session.setAttribute("client", mc);
            
            // Redirect to Mondo
            response.sendRedirect(URL);
            
        } catch (Exception ex) 
        {
            // OH NO!
            response.setStatus(500);
            out.write(ex.getMessage());
            out.flush();
            Logger.getLogger(AuthServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
```
The second servlet which is called by the magic email button looks like this, performs the token exchange using MondoJava library and then redirects the user to the launch page of the web application itself.

```java
 // A Servlet which is called by the magic email button to exchange the code for the token
 @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter out = response.getWriter();
        
        // The callback will have the code and state tokens as parameters
        String code = request.getParameter("code") != null ? request.getParameter("code") : "";
        String state = request.getParameter("state") != null ? request.getParameter("state") : "";
        
        // Fetch the MondoClient from the user's session
        // This was embedded at stage 1 (documented above)
        HttpSession session = request.getSession();
        MondoClient mc = (MondoClient) session.getAttribute("client");
         
        // Verify the state matches the one sent in this call
         if (mc.getState() != null)
         {
             // If it does, then continue
             if (mc.getState().equals(state))
             {
                 // Get the URL called here
                 String URL = request.getRequestURL().toString();
                 
                 try 
                 {
                     // Use MondoClient to perform Authorization Token Exchange
                     // This performs the API call to Mondo, and on success it will
                     // Put the authorization_token into the MondoClient for use
                     // in this application
                     mc.requestAccessToken(code, URL);
                     
                     // Then redirect to our application landing page
                     response.sendRedirect("/MondoWebTestApp/content/home.jsp");
                     
                 } catch (Exception ex) {
                     // If token exchange fails we land here
                     response.setStatus(500);
                     out.write(ex.getMessage());
                     out.flush();
                     Logger.getLogger(OauthCallbackServlet.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 
             }
             else
             {
                 // If the state token doesn't match we end it here
                 response.setStatus(500);
                 out.write("State does not match");
                 out.write("Sent " + mc.getState() + " - received " + state);
                 out.flush();
             }
         }
        
    }
```

# API use in MondoJava

To use the API, a MondoClient object must be created, and furnished with the relevant credentials :

```java
   // Either initialise in a web app using the oauth2 flow described above or 
   // if you obtained your token in another way do it like this :
   
   MondoClient mc = new MondoClient("<your_client_id>", "<your_client_secret>");
   mc.setAccess_token("<your_access_token>");
```
Some examples here of how to use the MondoClient methods. The calls return Java objects by serialising the JSON to POJOs :

```java
   // Get Accounts
   MondoAccounts accounts = mc.getAccounts();
            
   // Get Balances
   for (MondoAccount account : accounts.getAccounts())
   {
      MondoBalance bal = mc.getBalance(account.getId());
      System.out.println(bal.getCurrency() + " " + bal.getBalance());
   }

   // Get Transactions
   MondoExpandedTransactions txns = mc.getTransactions(account_id);
           
   // Single the most recent one out
   MondoExpandedTransaction mostrecent = txns.getTransactions().get(txns.getTransactions().size()-1);
   System.out.println("mostrecent : " + mostrecent.getId() + " " + mostrecent.getAmount() + " at " 
                                      + mostrecent.getMerchant().getName());
            
   // Get Transaction by ID
   MondoExpandedTransaction mrtxn = mc.getTransaction(account_id, mostrecent.getId());
   System.out.println(mrtxn.getId() + "-" + mrtxn.getDescription());

   // Annotate transaction
   SimpleEntry[] tags = {new SimpleEntry("metadata[rating]", "100"), 
                         new SimpleEntry("metadata[stars]", "5")};
                         
   MondoTransaction tagged = mc.annotateTransaction(mostrecent.getId(), tags);
   System.out.println(tagged.getMetadata());
            
   // Post Feed Item - try this one, it won't annoy you AT ALL
   MondoFeedItem item = new MondoFeedItem();
   item.setTitle("Hello I'm Zippy");
   item.setImage_url("http://i711.photobucket.com/albums/ww112/kinimaru/zippy.png");
   mc.postFeedItem(item, account_id);
            
   // Register a Webhook
   mc.registerWebhook(account_id, "http://www.grafsnowboards.com");
            
   // List webhooks
   MondoWebhooks webhooks = mc.getWebhooks(account_id);
   for (MondoWebhook webhook : webhooks.getWebhooks())
   {
      System.out.println(webhook.getId());
      System.out.println(webhook.getUrl());
                
      // Delete webhook
      mc.deleteWebhook(webhook.getId());
   }
            
   webhooks = mc.getWebhooks(account_id);
   System.out.println("There are " + webhooks.getWebhooks().size() + " webhooks registered");
```

# Utilities

MondoJava provides a utility to convert a JSON string to a MondoJava object :

```java
    // Convert some JSON containing a transaction list to a MondoExpandedTransactions object
    MondoExpandedTransactions transactions = (MondoExpandedTransactions) 
                                             ToJava.convert(json, MondoExpandedTransactions.class);
```

...and to convert from a MondoJava object back to a JSON string :

```java
   // Get transactions as Java objects and convert them back to JSON - yeah I know...!
   MondoExpandedTransactions txns = mc.getTransactions(account_id);
   out.write(ToJSON.convert(txns));
```
