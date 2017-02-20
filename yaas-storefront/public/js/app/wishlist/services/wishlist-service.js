'use strict';

angular.module('ds.wishlist')
    .factory('WishlistSvc', ['WishlistREST',
        function(WishlistREST){

        var getItems = function (parms) {
        	//wishlists/parms
            var wsPromise = WishlistREST.Wishlist.one('wishlists', parms).getList();
            //ordersPromise.then(function(response) {
            //    if (response.headers) {
            //        GlobalData.orders.meta.total = parseInt(response.headers[settings.headers.paging.total], 10) || 0;
            //    }
            //});
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




        ///
    //function abc(data){
    //    return ()=>{return data + 5};
    //}

    //console.log(abc(10)());
