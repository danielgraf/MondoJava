#MondoJava
A Java library for Mondo Bank

This library enables Java applications to use the Mondo Bank API, including helper methods for OAuth2 authentication, and almost all API features.

Still to do : Transaction Attachments.

#How ?

The library is a Netbeans / Ant project. Compile it from the command line with ant, or load it as a Netbeans project. The result is a JAR file you can incorporate into your own application. 

This library does not store or manage client_id or client_secret - that's up to you.

#Examples - Authorisation

```java
   // Initialise a MondoClient with an existing access token
   MondoClient mc = new MondoClient("<your client ID>", "<your client secret>");
   mc.setAccess_token(access_token); // if you already have one
```

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
 
 
