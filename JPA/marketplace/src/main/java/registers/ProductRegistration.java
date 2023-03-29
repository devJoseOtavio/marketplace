package registers;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import dao.CategoryDao;
import dao.ProductDao;
import marketplace.Product;
import model.Category;
import util.JPAUtil;

public class ProductRegistration {
	
	public static void main(String[] args) {
		registerProduct();
		EntityManager em = JPAUtil.getEntityManager();
		ProductDao productDao = new ProductDao(em);
		
		Product p = productDao.searchWithId(1l);
		System.out.println(p.getPrice());
		
		List<Product> all = productDao.searchAll();
		all.forEach(p2 -> System.out.println(p2.getName()));
	}
	
	public static void registerProduct() {
		Category fruit = new Category("fruits");
		Product procut = new Product("Banana", "Yellow", new BigDecimal("2"), fruit);

		EntityManager em = JPAUtil.getEntityManager();
		ProductDao productDao = new ProductDao(em);
		CategoryDao categoryDao = new CategoryDao(em);
		
		em.getTransaction().begin();
		
		productDao.register(fruit);
		categoryDao.register(fruit);
		
		em.flush();
		em.clear();
		
		fruit = em.merge(fruit);
		fruit.setName("mango");
		em.getTransaction().commit();
		em.close();
		
		em.flush();
		em.clear();
		em.remove(fruit);
		em.flush();
	}
}
