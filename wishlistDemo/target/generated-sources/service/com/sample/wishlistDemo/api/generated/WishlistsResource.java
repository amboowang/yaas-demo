package com.sample.wishlistDemo.api.generated;

import javax.ws.rs.core.Response;

@javax.ws.rs.Path("/wishlists")
public interface WishlistsResource
{
	@javax.ws.rs.GET
	@javax.ws.rs.Produces({"application/json"})
	Response get(@javax.ws.rs.BeanParam @javax.validation.Valid final YaasAwareParameters yaasAware);

	@javax.ws.rs.POST
	@javax.ws.rs.Consumes({"application/json"})
	Response post(@javax.ws.rs.BeanParam @javax.validation.Valid final YaasAwareParameters yaasAware, @javax.validation.Valid final Wishlist wishlist);

	@javax.ws.rs.Path("/{wishlistId}")
	@javax.ws.rs.GET
	@javax.ws.rs.Produces({"application/json"})
	Response getByWishlistId(@javax.ws.rs.BeanParam @javax.validation.Valid final YaasAwareParameters yaasAware, @javax.ws.rs.PathParam("wishlistId") final java.lang.String wishlistId);

	@javax.ws.rs.Path("/{wishlistId}")
	@javax.ws.rs.PUT
	@javax.ws.rs.Consumes({"application/json"})
	Response putByWishlistId(@javax.ws.rs.BeanParam @javax.validation.Valid final YaasAwareParameters yaasAware, @javax.ws.rs.PathParam("wishlistId") final java.lang.String wishlistId, @javax.validation.Valid final Wishlist wishlist);

	@javax.ws.rs.Path("/{wishlistId}")
	@javax.ws.rs.DELETE
	Response deleteByWishlistId(@javax.ws.rs.BeanParam @javax.validation.Valid final YaasAwareParameters yaasAware, @javax.ws.rs.PathParam("wishlistId") final java.lang.String wishlistId);

	@javax.ws.rs.Path("/{wishlistId}/amounts")
	@javax.ws.rs.GET
	@javax.ws.rs.Produces({"application/json"})
	Response getByWishlistIdAmounts(@javax.ws.rs.BeanParam @javax.validation.Valid final YaasAwareParameters yaasAware, @javax.ws.rs.PathParam("wishlistId") final java.lang.String wishlistId);

	@javax.ws.rs.Path("/{wishlistId}/amounts")
	@javax.ws.rs.PUT
	@javax.ws.rs.Consumes({"application/json"})
	Response putByWishlistIdAmounts(@javax.ws.rs.PathParam("wishlistId") final java.lang.String wishlistId, @javax.validation.Valid final Amount amount);

	@javax.ws.rs.Path("/{wishlistId}/amounts")
	@javax.ws.rs.DELETE
	Response deleteByWishlistIdAmounts(@javax.ws.rs.PathParam("wishlistId") final java.lang.String wishlistId);

	@javax.ws.rs.Path("/{wishlistId}/wishlistItems")
	@javax.ws.rs.GET
	@javax.ws.rs.Produces({"application/json"})
	Response getByWishlistIdWishlistItems(@javax.ws.rs.BeanParam @javax.validation.Valid final YaasAwareParameters yaasAware, @javax.ws.rs.PathParam("wishlistId") final java.lang.String wishlistId);

	@javax.ws.rs.Path("/{wishlistId}/wishlistItems")
	@javax.ws.rs.POST
	@javax.ws.rs.Consumes({"application/json"})
	@javax.ws.rs.Produces({"application/json"})
	Response postByWishlistIdWishlistItems(@javax.ws.rs.BeanParam @javax.validation.Valid final YaasAwareParameters yaasAware, @javax.ws.rs.PathParam("wishlistId") final java.lang.String wishlistId, @javax.validation.Valid final WishlistItem wishlistItem);

}
