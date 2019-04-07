package BusinessLogic;

import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.JTable;
import DataAccess.ProductsDAO;
import Model.Products;

public class ProductBLL {
	private ProductsDAO productDAO;
	
	public ProductBLL(){
		productDAO = new ProductsDAO();
	}
	
	public void addProduct(Products product) {
		try {
			productDAO.add(product);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void editProduct(int id, Products product) {
		try {
			productDAO.edit(id, product);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteProduct(int id) {
		try {
			productDAO.delete("id", id);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public List<Products> findProductById(int value) {
		List<Products> p = productDAO.findByIntegerFieldValue("id", value); 
		if(p == null) {
			throw new NoSuchElementException("The product with id =" + value + " was not found!");
		}
		return p;
	}

	public List<Products> viewProductsTable(){
		List<Products> p = productDAO.viewAll();
		if(p == null) {
			throw new NoSuchElementException("The Products table is empty!");
		}
		return p;
	}
	
	public JTable createProductsTable(List<Products> list) {
		try {
			return productDAO.createTable(list);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
