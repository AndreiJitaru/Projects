package BusinessLogic;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import DataAccess.OrdersDAO;
import Model.Customers;
import Model.Orders;
import Model.Products;

public class OrderBLL {
	private OrdersDAO orderDAO;
	
	public OrderBLL(){
		orderDAO = new OrdersDAO();
	}
	
	/**
	 * Aceasta metoda adauga o comanda in tabela Orders, actualizeaza stocul produsului si
	 * returneaza in acelasi timp o chitanta cu datele produsului cumparat dar si ale cumparatorului 
	 * @param order comanda care va fi introdusa in tabela
	 */
	public void addOrder(Orders order) {
		try {
			orderDAO.add(order);
			ProductBLL p = new ProductBLL();
			CustomerBLL c = new CustomerBLL();
			Products product = p.findProductById(order.getIdProduct()).get(0);
			Customers customer = c.findCustomerById(order.getIdClient()).get(0);
			Products productAfterUpdate = new Products(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getStoc() - order.getQuantity());
			if(product.getStoc() - order.getQuantity() >= 0) {
				p.editProduct(product.getId(), productAfterUpdate);
				PrintWriter writer;
				try {
					writer = new PrintWriter("Bill_for_Customer_" + order.getIdClient() +  ".txt", "UTF-8");
					writer.println("Customer: " + customer.getFirstName() + " " + customer.getLastName());
					writer.println("Product: " + productAfterUpdate.getName());
					writer.println("Quantity: " + order.getQuantity());
					writer.println("Price: " + product.getPrice());
					writer.close();
				} catch (FileNotFoundException | UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Under stock for product " + order.getIdProduct() + "!");
			}
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void editOrder(int id, Orders order) {
		try {
			orderDAO.edit(id, order);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteOrder(int id) {
		try {
			orderDAO.delete("id", id);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public List<Orders> findOrderByOrderId(int value) {
		List<Orders> p = orderDAO.findByIntegerFieldValue("id", value); 
		if(p == null) {
			throw new NoSuchElementException("The Order with id =" + value + " was not found!");
		}
		return p;
	}
	
	public List<Orders> findOrderByCustomerId(int value) {
		List<Orders> p = orderDAO.findByIntegerFieldValue("idClient", value); 
		if(p == null) {
			throw new NoSuchElementException("The Order with idClient =" + value + " was not found!");
		}
		return p;
	}
	
	public List<Orders> findOrderByProductId(int value) {
		List<Orders> p = orderDAO.findByIntegerFieldValue("idProduct", value); 
		if(p == null) {
			throw new NoSuchElementException("The Order with idProduct =" + value + " was not found!");
		}
		return p;
	}
	
	public List<Orders> viewOrdersTable(){
		List<Orders> p = orderDAO.viewAll();

		if(p == null) {
			throw new NoSuchElementException("The Orders table is empty!");
		}
		return p;
	}
	
	public JTable createOrdersTable(List<Orders> list) {
		try {
			return orderDAO.createTable(list);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
