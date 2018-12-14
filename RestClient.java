package com.domain.Prac;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	public void get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url); // used for the URL upon which get Request will be called upon.

		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);
		/*
		 * <^used to send the GET request along with it's url. By creating the reference
		 * object we will receive the entire response.
		 */
		// (i.) Status Code
		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("<--------Status Code------> :" + statusCode);

		/* (ii.) JSON Response */

		String jSONresponseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject jsonObject = new JSONObject(jSONresponseString);
		System.out.println("<-----JSON Response from the API------>:" + jsonObject);

		/* (iii.) Response Headers */

		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
	System.out.println("<--------Response Headers------> :" +allHeaders); 

	}
}
