package com.domain.CodesToMemorize;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.HttpClient;

public class postRestApiCode {

	String URL;

	String resultFile = "This is the filepath to which the values of "
			+ "the users object will write onto as in the json format";

	String value = "this is the name of the reference object of that class holding the getters and setters";

	@Test
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException {
		HashMap<String, String> headersMap = new HashMap<String, String>();
		headersMap.put("Content-type", "application/json");

		// create instance of Object Mapper via the Jackson API
		ObjectMapper objectMapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader");
		// marshal the reference object from above into a JSON file
		objectMapper.writeValue(new File(resultFile), value);

		// create reference object to write the values of the marshalled json file into
		// the entity payload.
		String usersJSONString = objectMapper.writeValueAsString(users);

		/*
		 * now invoke the api by creating object of the closeableHttpResponse, initiate
		 * it by invoking the post method which you had created.
		 */

		CloseableHttpResponse closeableHttpResponse = postRestApiCode.post(URL, usersJSONString, headersMap);

		// now validate all the responses.

		int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		String jsonPayLoadResults = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		System.out.println(jsonPayLoadResults);

		Header[] headersArray = closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeadersMap = new HashMap<String, String>();
		for (Header header : headersArray) {
			allHeadersMap.put(header.getName(), header.getValue());

		}
		System.out.println(allHeadersMap);
		
		// read the json values as java objects by readValue method
		
		Users usersResponseObject = objectMapper.readValue(jsonPayLoadResults, Users.class);
		
		System.out.println(usersResponseObject);
		
		//must assert True that the value which has been set has actually been updated after it is being read.
		
		Assert.assertTrue(users.getUserName().equals(usersResponseObject.getUserName()));
		
		Assert.assertTrue(users.getRole().equals(usersResponseObject.getRole()));
		
		
	
	}
	

	public CloseableHttpResponse postMethod(String url, String payloadEntityString, HashMap<String, String> headersMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		// setting the payload entity
		httpPost.setEntity(new StringEntity(payloadEntityString));
		// setting the headers
		for (Map.Entry<String, String> entry : headersMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}

		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
		return closeableHttpResponse;
	}



	// POST METHOD

	public static CloseableHttpResponse post(String url, String payloadEntityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);

		// to set the payload
		httpPost.setEntity(new StringEntity(payloadEntityString));
		// to set the header
		for (Map.Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpPost);
		return closeableHttpResponse;
	}
}
