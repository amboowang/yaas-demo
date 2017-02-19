package com.sample.wishlistDemo;

import java.util.Map;
import java.util.Optional;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.sample.wishlistDemo.CallingYaaSServiceException;

@ManagedBean
@PropertySource("classpath:default.properties")
public class OAuthWrapper { 

	//@Value("${oauthURL}")
	private String URI = "https://api.beta.yaas.io/hybris/oauth2/v1/token";
	//@Value("${projectIDAkaTenant}")
    private String tenant = "caashiring"; 
	//@Value("${yaaSClientsIdentifier}")
    private String appId = "yb20161029proj1.clientforam177";
	//@Value("${docuRepoType}")
    private String type = "WishList";
	//@Value("${yaaSClientsClient_ID}")
	private String clientId = "R64UWNPnzFwkg74XAYiVc6mWpB0Qtu1R";
	//@Value("${yaaSClientsClient_Secret}")
	private String clientSecret = "EEmhYGZXGXPkjBpH";
	//@Value("${docuRepoScopes}")	
	//private String scopes = "hybris.tenant%3D"+tenant+"+hybris.document_manage+hybris.document_view+hybris.document_admin";
	//
	private String scope1 = "hybris.document_manage";
    private String grantType = "client_credentials";
   
    private static final Logger LOGGER = LoggerFactory.getLogger(OAuthWrapper.class);
    
    public OAuthWrapper( ){
    }
    
    public OAuthWrapper grantType( String grantType ){
    	this.grantType = grantType;
    	return this;
    }
    public OAuthWrapper clientId( String clientId ){
    	this.clientId = clientId;
    	return this;
    }
    public OAuthWrapper clientSecret( String clientSecret ){
    	this.clientSecret = clientSecret;
    	return this;
    }
    public OAuthWrapper tenant( String tenant ){
    	this.tenant = tenant;
    	return this;
    } 
    /*
    public OAuthWrapper scope( String scope ){
    	this.scopes = scope;
    	return this;
    }   
    public OAuthWrapper build(  ){
    	this.scopes = "hybris.tenant"+"="+tenant+" "+scopes; 	
    	return this;
    }
    */
    public Optional<Map<String,String>> getToken(){   	
    	try {
			HttpHeaders headers = new HttpHeaders();   	
			headers.add("content-type", "application/x-www-form-urlencoded" );  	
			RestTemplate restTemplate = new RestTemplate();
			String body = 
					"grant_type="+grantType+
					"&client_id="+clientId+
					"&client_secret="+clientSecret+
					"&scope="+scope1;
			String bodyTemp = "strangexxx";
			HttpEntity<Object> request = new HttpEntity<>( body, headers );
			LOGGER.info("URI:"+URI);
			LOGGER.info("getToken-request:" + new Gson().toJson(request));
			LOGGER.info("getToken-request:" + request.toString());
			Map<String,String> tokenMap = restTemplate.postForObject(URI, request, Map.class );  			
			return Optional.of(tokenMap);
		} catch (RestClientException e) {
			throw new CallingYaaSServiceException();
		}
    }

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	} 	
}
