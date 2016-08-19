/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
/**
 *
 * @author psych0
 */
public class APIConnection {
    public static String getHTML(String urlToRead) {
     
      String json = "";
      
      
      try {
          
         

        try {
            HttpGet request = new HttpGet(urlToRead);
            HttpClient client = new DefaultHttpClient();
            org.apache.http.HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                json = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            // Oh well... no realms for now
        }

        return json;
    }
         
       catch (Exception esx) {
         esx.printStackTrace();
         }
    
   
      return json;
   }
}

