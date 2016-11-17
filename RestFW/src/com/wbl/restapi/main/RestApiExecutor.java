package com.wbl.restapi.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.wbl.rest.utils.JsonParser;
import com.wbl.rest.utils.configuration;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;

public class RestApiExecutor {
	
	public HttpResponse httpget(String resource,HashMap<String,String> headers,String authorisation,String requestData) throws ClientProtocolException, IOException

	{
	 HttpClient client = HttpClientBuilder.create().build();
	  HttpGet get = new HttpGet(configuration.URI+"?"+resource+requestData);
	  if(null!=headers){
		  for(Map.Entry<String, String> entry : headers.entrySet()){
		  get.setHeader(entry.getKey(),entry.getValue());//for setting the user defined  headers
	  }
	  
	  }
	  if ( null!=authorisation){
		  get.addHeader(HttpHeaders.AUTHORIZATION,authorisation);
		   }
        HttpResponse response = client.execute(get);
	   return response;
	}
	
	
	public HttpResponse post(String resource,HashMap<String,String> headers,String authorisation,HashMap<String,String> request) throws ClientProtocolException, IOException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException
	{
	 HttpClient client = HttpClientBuilder.create().build();
	 HttpPost post = new HttpPost(configuration.URI+resource);
	   post.addHeader("Accept","application/json");
	   post.addHeader("Content-Type","application/json");
	   
	  if(null!=headers){
		  for(Map.Entry<String, String> entry : headers.entrySet()){
			  post.setHeader(entry.getKey(),entry.getValue());//for setting the user defined  headers
	  }
	  }
	   if ( null!=authorisation){
		  post.addHeader(HttpHeaders.AUTHORIZATION,authorisation);
		   }
	  ArrayList<NameValuePair> requestdata = new ArrayList<NameValuePair>();
	  for(Map.Entry<String, String> entry : request.entrySet()){
		  requestdata.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		  OAuthConsumer consumer = new CommonsHttpOAuthConsumer("DtIMCQIXSMPmEmY4OCZ6k7bNQ","RVJNNzzpvUuVqPpTMZQEFON8l7gkuW76XEDkBrtISBnvEEAWHq");
			consumer.setTokenWithSecret("252165696-8IgrfJ6FtHBQAv252dALGQysjiIDriQdKVRqzQOr", "eLBsVKfibTMUz0jUgA02oOKkNHoEfcnZcavgwYG99yNlx");
			consumer.sign(post);
		  }//get the request form request data map
	  HttpEntity entity = new UrlEncodedFormEntity(requestdata);
	      
            post.setEntity(entity);
	   HttpResponse response = client.execute(post);
	   return response;
	}
	
	public  JsonParser getjson(HttpResponse response)//getting the json parser object
	{
		JsonParser jParser = new JsonParser();
		jParser.setResponse(response);
		return jParser;
	}
	public void setAuthentication()
	{
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer("DtIMCQIXSMPmEmY4OCZ6k7bNQ","RVJNNzzpvUuVqPpTMZQEFON8l7gkuW76XEDkBrtISBnvEEAWHq");
	consumer.setTokenWithSecret("252165696-8IgrfJ6FtHBQAv252dALGQysjiIDriQdKVRqzQOr","eLBsVKfibTMUz0jUgA02oOKkNHoEfcnZcavgwYG99yNlx");
	
	}

}
