package com.wbl.rest.tests;

import static org.testng.Assert.*;
import java.io.IOException;
import java.util.HashMap;

//import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
//import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.wbl.rest.utils.ExcelUtils;
import com.wbl.rest.utils.JsonParser;
import com.wbl.restapi.main.BaseTest;
import com.wbl.restapi.main.RestApiExecutor;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;


public class UserApisTest extends BaseTest{
	@DataProvider(name="rest-Data")
	public Object [][] getData(){
		return ExcelUtils.getExcelData("sheet1", "restData.xlsx");
	}
	
	@Test(dataProvider="rest-Data")
	public void  testCandidatesGet(String resource,String requestData) throws ClientProtocolException, IOException
	{
		RestApiExecutor rest = new RestApiExecutor();
		HttpResponse  response = rest.httpget(resource,null,null,requestData);
		
		JsonParser parser = rest.getjson(response);
	   assertEquals(200,parser.getStatusCode());// assertion  for status code
	   assertEquals("ok",parser.getResponseMessage());
	    assertNotNull(parser.getjsonObject(0));
	}
	    
	    @Test(dataProvider="rest-Data")
		public void  testTwitterPost(String resource,String name,String desc) throws ClientProtocolException, IOException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException{
			RestApiExecutor rest = new RestApiExecutor();
			HashMap<String, String> requestData = new HashMap<String, String>();
			requestData.put("name", name);
			requestData.put("description", desc);
			HttpResponse  response = rest.post(resource,null,null,requestData);
			
			JsonParser parser = rest.getjson(response);
		   assertEquals(200,parser.getStatusCode());// assertion  for status code
		   assertEquals("ok",parser.getResponseMessage());
		   System.out.println("status code ::"+parser.getStatusCode());
		  // JSONObject json = new JSONObject(IOUtils.toString(response.getEntity().getContent()));
		 // System.out.println(json);
		  assertEquals( name,parser.getActualData("name"));
		   assertEquals(desc,parser.getActualData("description"));
		    assertNotNull(parser.getjsonObject(0));
		    
	    }  
	 
}
	    
	    
	


