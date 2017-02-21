'use strict';

angular.module('ds.wishlist')
    .factory('WishlistSvc', ['WishlistREST',
        function(WishlistREST){

        var getItems = function (parms) {
            //wishlists,parms
            var wsPromise = WishlistREST.Wishlist.one('wishlists', parms).get();
            return wsPromise;
        };

            return {
                createWishlist: function (newWishlist) {
                    WishlistREST.Wishlist.all('wishlists').post(newWishlist);
                },

                query: function (parms) {
                    return getItems(parms);
                },

            };
        }]);
