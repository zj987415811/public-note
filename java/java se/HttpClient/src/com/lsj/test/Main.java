package com.lsj.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.*;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

public class Main {
	
	public static void Test1(){
		try {
        	CloseableHttpClient httpclient = HttpClients.createDefault();
        	
        	HttpGet httpGet = new HttpGet("http://localhost/HttpTest/hello");
        	HttpContext context = HttpClientContext.create();
        	context.setAttribute("Cookie", "user=lsj");
        	httpGet.setHeader("Cookie", "special_cookie=value");
        	
			CloseableHttpResponse response = httpclient.execute(httpGet,
					new ResponseHandler<CloseableHttpResponse>() {  
						@Override
						public CloseableHttpResponse handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
							System.out.println("=================================Entry=================================");
							HttpEntity entity = response.getEntity();
							BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
							String buf = null;
							while((buf = in.readLine())!=null){
								System.out.println(buf);
							}
							
							System.out.println("=================================Headers=================================");
							Header[] headers = response.getAllHeaders();
							
							for(Header header : headers){
								System.out.println(header.getName() + " : " + header.getValue());
							}
							
							System.out.println("=================================SetCookie=================================");
							HeaderIterator it = response.headerIterator("Set-Cookie");  
							while (it.hasNext()) {  
							    System.out.println(it.next());  
							}  
							
							return (CloseableHttpResponse) response;
						}  
					}, context);
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void Test2(){
		CloseableHttpClient httpclient = HttpClients.createDefault();
    	HttpGet httpGet = new HttpGet("http://localhost/HttpTest/hello");
    	Thread thread = new GetThread(httpclient, httpGet, new ResponseHandler(){
			@Override
			public Object handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
				System.out.println("=================================Entry=================================");
				HttpEntity entity = response.getEntity();
				BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
				String buf = null;
				while((buf = in.readLine())!=null){
					System.out.println(buf);
				}
				
				System.out.println("=================================Headers=================================");
				Header[] headers = response.getAllHeaders();
				
				for(Header header : headers){
					System.out.println(header.getName() + " : " + header.getValue());
				}
				
				System.out.println("=================================SetCookie=================================");
				HeaderIterator it = response.headerIterator("Set-Cookie");  
				while (it.hasNext()) {  
				    System.out.println(it.next());  
				}  
				return response;
			}
    	});
    	
    	thread.start();
	}
	
	public static void main(String[] args){
		Test2();
		
		System.out.println("Main Done!");
	}
}


class GetThread extends Thread{
	
	private final CloseableHttpClient httpClient;
	private final HttpGet httpGet; 
	private final ResponseHandler hr;
	
	public GetThread(CloseableHttpClient httpClient, HttpGet httpGet, ResponseHandler hr){
		this.httpClient = httpClient;
		this.httpGet = httpGet;
		this.hr = hr;
	}
	
	@Override
	public void run(){
		try {
			httpClient.execute(httpGet, hr);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}