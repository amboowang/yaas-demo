package datapersist;

import com.sample.wishlistDemo.api.generated.Amount;
import com.sample.wishlistDemo.api.generated.Wishlist;

public interface DataPersist {
	public void create(Wishlist wishlist);
	public Wishlist getByOwner(String owner);
	public Amount getAmounts(String owner);
}
