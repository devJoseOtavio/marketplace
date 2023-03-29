package dao;

import java.util.List;

import javax.persistence.EntityManager;

import marketplace.Product;
import model.Category;

public class CategoryDao {

	private EntityManager em;
	
	public CategoryDao(EntityManager em) {
		this.em = em;
	}
	
	public void register(Category category) {
		this.em.persist(category);
	}
	
	public void update(Category category) {
		this.em.merge(category);
	}
	
	public void remove(Category category) {
		category = em.merge(category);
		this.em.remove(category);
	}
	
	public Product searchWithId(Long id) {
		return em.find(Product.class, id);
	}
	
	public List<Product> searchAll() {
		String jpql = "SELECT p FROM PRODUCT p";
		return em.createQuery(jpql, Product.class).getResultList();
	}
}
