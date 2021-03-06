
package com.sample.wishlistDemo.api.generated;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import datapersist.DocuServiceWrapper;
import datapersist.OAuthWrapper;
import com.sample.wishlistDemo.api.generated.WishlistItem;
import com.sample.wishlistDemo.api.generated.YaasAwareParameters;
import com.sample.wishlistDemo.api.generated.Amount;

import datapersist.DataPersist;
import datapersist.WishlistRepo;

import com.google.gson.Gson;
/**
* Resource class containing the custom logic. Please put your logic here!
*/
@Component("apiWishlistsResource")
@Singleton
public class DefaultWishlistsResource implements com.sample.wishlistDemo.api.generated.WishlistsResource
{
	@javax.ws.rs.core.Context
	private javax.ws.rs.core.UriInfo uriInfo;
	private static final Logger LOGGER = LoggerFactory.getLogger(DocuServiceWrapper.class);
	
    //@Autowired
    //private DocuServiceWrapper dsw = new DocuServiceWrapper();
    
    //@Autowired
    private DataPersist dbRepo = new WishlistRepo();
	
	/* GET / */
	@Override
	public Response get(final YaasAwareParameters yaasAware)
	{
		// place some logic here
		LOGGER.info("GET /wishlist");
		
		return Response.ok()
			.entity(new java.util.ArrayList<Wishlist>()).build();	
	}

	/* POST / */
	@Override
	public Response post(final YaasAwareParameters yaasAware, final Wishlist wishlist)
	{
		// place some logic here
		LOGGER.info("POST /wishlist");
		// call the document service to save the data
		
		//dsw.post(wishlist);
		
		//save to db have to as the auth scope problem(403 error)
		dbRepo.create(wishlist);
		
		return Response.created(uriInfo.getAbsolutePath())
			.build();
	}

	/* GET /{wishlistId} */
	@Override
	public Response getByWishlistId(final YaasAwareParameters yaasAware, final java.lang.String wishlistId)
	{
		// place some logic here
		Wishlist r = dbRepo.getByOwner(wishlistId); 
		
		if (r != null){
			LOGGER.info("GET /wishlist/"+wishlistId+"found");	
			return Response.ok()
				.entity(new Gson().toJson(r)).build();
		}
		else {
			LOGGER.info("GET /wishlist/"+wishlistId+" not found");	
			return Response.ok()
					.entity("").build();
		}
	}

	/* PUT /{wishlistId} */
	@Override
	public Response putByWishlistId(final YaasAwareParameters yaasAware, final java.lang.String wishlistId, final Wishlist wishlist)
	{
		// place some logic here
		return Response.ok()
			.build();
	}

	/* DELETE /{wishlistId} */
	@Override
	public Response deleteByWishlistId(final YaasAwareParameters yaasAware, final java.lang.String wishlistId)
	{
		// place some logic here
		return Response.noContent()
			.build();
	}

	@Override
	public
	Response getByWishlistIdWishlistItems(
			final YaasAwareParameters yaasAware,  final java.lang.String wishlistId)
	{
		// place some logic here
		return Response.ok()
				.entity(new java.util.ArrayList<WishlistItem>()).build();
	}

	@Override
	public
	Response postByWishlistIdWishlistItems(final YaasAwareParameters yaasAware,
			final java.lang.String wishlistId, final WishlistItem wishlistItem){
		// place some logic here
		return Response.noContent()
					.build();
	}

	/* GET / /{wishlistId}/amounts */
	@Override
	public Response getByWishlistIdAmounts(final YaasAwareParameters yaasAware, final java.lang.String wishlistId)
	{
		// place some logic here
		Amount r = dbRepo.getAmounts(wishlistId);
		
		return Response.ok()
			.entity(r).build();	
	}

	/* DELETE / /{wishlistId}/amounts */
	@Override
	public Response deleteByWishlistIdAmounts(final java.lang.String wishlistId)
	{
		// place some logic here
		
		return Response.noContent()
				.build();
	}	
	
	/* GET / /{wishlistId}/amounts */
	@Override
	public Response putByWishlistIdAmounts(final java.lang.String wishlistId, final Amount amount)
	{
		// place some logic here
		
		return Response.ok()
			.entity("").build();	
	}	
}
