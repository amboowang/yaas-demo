'use strict';

angular.module('ds.wishlist')
    .factory('WishlistSvc', ['WishlistREST', '$q',
        function(WishlistREST, $q){

        var getItems = function (parms) {
            //wishlists,parms
            var wsPromise = WishlistREST.Wishlist.one('wishlists', parms).get();
            return wsPromise;
        };

            return {
                createWishlist: function (newWishlist) {
                    var createItemDef = $q.defer();
                    WishlistREST.Wishlist.all('wishlists').post(newWishlist).then(function() {
                        createItemDef.resolve();
                    }, function() {
                        createItemDef.reject();
                    });

                    return createItemDef.promise;
                },

                query: function (parms) {
                    return getItems(parms);
                },

            };
        }]);
