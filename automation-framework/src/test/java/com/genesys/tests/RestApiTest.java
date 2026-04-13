package com.genesys.tests;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.genesys.utils.Config;
import com.genesys.utils.LoggerUtil;

public class RestApiTest {
	private static final Logger log = LoggerUtil.getLogger(RestApiTest.class);

    @Test
    public void testGetUsers() throws Exception {
    	log.info("===== Starting API Test =====");

        CloseableHttpClient client = HttpClients.createDefault();
        log.info("Sending GET request to /users");
        HttpGet request = new HttpGet(Config.API_URL);
        
        CloseableHttpResponse response = client.execute(request);
        log.info("Response received successfully");

        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);

        JSONArray users = new JSONArray(EntityUtils.toString(response.getEntity()));

        String firstEmail = null;

        for (int i = 0; i < users.length(); i++) {

            JSONObject user = users.getJSONObject(i);

            String name = user.getString("name");
            String email = user.getString("email");

            log.info(name + " | " + email);

            if (i == 0) firstEmail = email;
        }

        Assert.assertTrue(firstEmail.contains("@"));
        log.info("Email validation successful");

        response.close();
        client.close();
        log.info("===== Test Completed Successfully =====");
    }
}