package com.sample.wishlistDemo;

import java.util.Map;
import java.util.Optional;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
//import com.sample.WishlistDemo.Tip;
import com.sample.wishlistDemo.api.generated.Wishlist;
import com.sample.wishlistDemo.CallingYaaSServiceException;

@ManagedBean
@PropertySource("classpath:default.properties")
public class DocuServiceWrapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocuServiceWrapper.class);

	//@Value("${oauthURL}")
	private String URI = "https://api.beta.yaas.io/hybris/document/v1";
	//@Value("${projectIDAkaTenant}")
    private String tenant = "caashiring"; 
	//@Value("${yaaSClientsIdentifier}")
    //private String appId = "yb20161029proj1.clientforam177";
	//@Value("${docuRepoType}")
    private String type = "WishList";
	//@Value("${yaaSClientsClient_ID}")
	private String clientId = "R64UWNPnzFwkg74XAYiVc6mWpB0Qtu1R";
	//@Value("${yaaSClientsClient_Secret}")
	//private String clientSecret = "EEmhYGZXGXPkjBpH";
	//@Value("${docuRepoScopes}")	
	//private String scopes = "hybris.document_manage";


    //@Autowired
    private OAuthWrapper oaw = new OAuthWrapper();


    public DocuServiceWrapper() {
    }

    public DocuServiceWrapper tenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public DocuServiceWrapper appId(String clientId) {
        this.clientId = clientId;
        return this;
    }

    public String post(Wishlist t) {
        try {
            String token = getTokenForServiceToCallDocuRepoOnBehalfOfProject();
            LOGGER.info("token:"+ token);
            LOGGER.info("input:"+ new Gson().toJson(t));
            String url = URI + "/" + tenant + "/" + clientId + "/data/" + type;
            HttpEntity<String> request = new HttpEntity<>(toJson(t), headerWithToken(token));
            LOGGER.info("post: url:"+url+ " request:" + request.toString());
            LOGGER.info("post: url:"+url+ " request body:" + request.getBody());
            Map<String, String> map = new RestTemplate().postForObject(url, request, Map.class);
            if (map != null && map.containsKey("link"))
                return map.get("link");
        } catch (RestClientException e) {
            e.printStackTrace();
            if (e.getMessage().contains("Conflict"))
                return "Conflict";
        }
        throw new CallingYaaSServiceException();
    }

    public Optional<Wishlist> get(String id) {
        try {
            String token = getTokenForServiceToCallDocuRepoOnBehalfOfProject();
            HttpEntity<String> request = new HttpEntity<>(headerWithToken(token));
            String url = geturl4TipId(id);
            Wishlist tip = new RestTemplate().exchange(url, HttpMethod.GET, request, Wishlist.class).getBody();
            return Optional.of(tip);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        throw new CallingYaaSServiceException();
    }

    public Wishlist[] get() {
        try {
            String token = getTokenForServiceToCallDocuRepoOnBehalfOfProject();
            HttpEntity<String> request = new HttpEntity<>(headerWithToken(token));
            String url = URI + "/" + tenant + "/" + clientId + "/data/" + type;
            Wishlist[] tips = new RestTemplate().exchange(url, HttpMethod.GET, request, Wishlist[].class).getBody();
            return tips;
        } catch (RestClientException e) {
            e.printStackTrace();
            throw new CallingYaaSServiceException();
        }
    }

    public String put(Wishlist t) {
        try {
            String token = getTokenForServiceToCallDocuRepoOnBehalfOfProject();
            String id = t.getId();
            String url = geturl4TipId(id);
            LOGGER.debug("put url is [{}]", url);
            HttpEntity<String> request = new HttpEntity<>(toJson(t), headerWithToken(token));
            new RestTemplate().put(url, request);
            return url;
        } catch (RestClientException e) {
            e.printStackTrace();
            if (e.getMessage().contains("Conflict"))
                return "Conflict";
        }
        throw new CallingYaaSServiceException();
    }

    private String geturl4TipId(String id) {
        return URI + "/" + tenant + "/" + clientId + "/data/" + type + "/" + id;
    }

    public HttpStatus delete(String id) {
        try {
            String token = getTokenForServiceToCallDocuRepoOnBehalfOfProject();
            String url = geturl4TipId(id);
            HttpEntity<String> request = new HttpEntity<>(headerWithToken(token));
            Object o = new RestTemplate().exchange(url, HttpMethod.DELETE, request, Object.class).getBody();
            return HttpStatus.OK;
        } catch (RestClientException e) {
            e.printStackTrace();
            return HttpStatus.NOT_MODIFIED;
        }
    }

    public HttpStatus deleteAll() {
        try {
            String token = getTokenForServiceToCallDocuRepoOnBehalfOfProject();
            String url = URI + "/" + tenant + "/" + clientId;
            HttpEntity<Object> request = new HttpEntity<>(headerWithToken(token));
            new RestTemplate().exchange(url, HttpMethod.DELETE, request, Object.class).getBody();
            return HttpStatus.OK;
        } catch (RestClientException e) {
            e.printStackTrace();
            return HttpStatus.NOT_MODIFIED;
        }
    }

    private HttpHeaders headerWithToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", "application/json");
        headers.add("Authorization", "Bearer " + token);
        return headers;
    }

    private String toJson(Object o) {
        return new Gson().toJson(o);
    	//return null;
    }

    private String getTokenForServiceToCallDocuRepoOnBehalfOfProject() {
        Map<String, String> tokenMap = oaw.getToken().orElse(null);
        if (tokenMap == null)
            throw new CallingYaaSServiceException();
        LOGGER.info("token scope:"+tokenMap.get("scope"));
        return tokenMap.get("access_token");
    }

	public String getYaaSClientsClient_ID() {
		return clientId;
	}

	public void setYaaSClientsClient_ID(String yaaSClientsClient_ID) {
		this.clientId = yaaSClientsClient_ID;
	}
/*
	public String getYaaSClientsClient_Secret() {
		return yaaSClientsClient_Secret;
	}

	public void setYaaSClientsClient_Secret(String yaaSClientsClient_Secret) {
		this.yaaSClientsClient_Secret = yaaSClientsClient_Secret;
	}
	*/
}

