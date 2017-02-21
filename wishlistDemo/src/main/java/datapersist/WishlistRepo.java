package datapersist;

import java.util.List;

import javax.annotation.ManagedBean;

import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import com.sample.wishlistDemo.api.generated.Amount;
import com.sample.wishlistDemo.api.generated.Wishlist;
import com.sample.wishlistDemo.api.generated.WishlistItem;

public class WishlistRepo implements DataPersist {
	
	public WishlistRepo() {
		
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(WishlistRepo.class);
	
	// For Annotation
	ApplicationContext ctx =
             new AnnotationConfigApplicationContext(SpringMongoConfig.class);
	MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
	
	@Override
	public void create(Wishlist wishlist) {
		//if the list already existed, how to do?
		String owner = wishlist.getOwner();
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_owner").is(owner));
		
		Wishlist p = mongoOperation.findOne(query, Wishlist.class);
		
		if (p == null) {
			//this is first one, so just save it
			mongoOperation.save(wishlist);
		}
		else
		{
			//append the new to the previous list
			p.getItems().addAll(wishlist.getItems());
			mongoOperation.remove(query, Wishlist.class);
			mongoOperation.save(p);
		}		
		
	}
	
	@Override
	public Wishlist getByOwner(String owner) {
		Wishlist r = null;
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_owner").is(owner));
		
		//if (mongoOperation.exists(query, Wishlist.class)) {
			// list belong to the owner exist one at least
		r = mongoOperation.findOne(query, Wishlist.class);
		//}
		
		return r;
	}
	
	@Override
	public Amount getAmounts(String owner) {
		Wishlist r = null;
		Amount amount = new Amount();
		
		Query query = new Query();
		query.addCriteria(Criteria.where("_owner").is(owner));
		
		//if (mongoOperation.exists(query, Wishlist.class)) {
			// list belong to the owner exist one at least
		r = mongoOperation.findOne(query, Wishlist.class);
		//}
		double t = 0;
		if (r != null) {
			for (WishlistItem a : r.getItems()) {
				t = t + a.getAmount();
			}
		}
		
		amount.setAmounts(t);
		
		return amount;	
	}
}