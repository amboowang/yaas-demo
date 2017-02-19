package datapersist;

import java.util.List;

import javax.annotation.ManagedBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.sample.wishlistDemo.api.generated.Wishlist;

@Component
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
		mongoOperation.save(wishlist);
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
}