package BusinessLogic;

import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.JTable;

import DataAccess.CustomerDAO;
import Model.Customers;

/**
 * Aceasta clasa prezinta operatiile care pot fi realizate la nivelul tabelei Customer
 * definind metode care sa permita interactiunea cu aceasta tabela
 * @author andre
 */
public class CustomerBLL {
	private CustomerDAO customerDAO;
	
	/**
	 * instantiaza atributulul customerDAO odata cu apelarea constructorului
	 */
	public CustomerBLL(){
		customerDAO = new CustomerDAO();
	}
	
	/**
	 * Aceasta metoda perminte adaugarea unui obiect de tip Customer in tabela
	 * @param Customer obiectul care va fi introdus in tabela
	 */
	public void addCustomer(Customers customer) {
		try {
			customerDAO.add(customer);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Aceasta metoda permite actualizarea unei linii din tabela Customers
	 * cu valorile @param customers, linia fiind definita de valoarea lui @param id
	 * @param id defineste linia care va fi actualizata
	 * @param Customer defineste noile valori ale liniei
	 */
	public void editCustomer(int id, Customers customer) {
		try {
			customerDAO.edit(id, customer);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Aceasta metoda permite stergerea unei linii din tabela Customersin functie
	 *  de valoarea @param id
	 * @param id determina linia care va fi stearasa
	 */
	public void deleteCustomer(int id) {
		try {
			customerDAO.delete("id", id);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Aceasta metoda cauta in tabela Customers linii in functie de coloana "id"
	 * a tabelei si de valoarea @param id returnand o lista de obiecte 
	 * @param value valoarea in functie de care se va realiza cautarea
	 * @return o lista de obiecte de tip Customers
	 */
	public List<Customers> findCustomerById(int value) {
		List<Customers> p = customerDAO.findByIntegerFieldValue("id", value); 
		if(p == null) {
			throw new NoSuchElementException("The Customer with id =" + value + " was not found!");
		}
		return p;
	}
	
	/**
	 * Aceasta metoda cauta in tabela Customers linii in functie de coloana "username"
	 * a tabelei si de valoarea @param value, returnand o lista de obiecte 
	 * @param value valoarea in functie de care se va realiza cautarea
	 * @return o lista de obiecte de tip Customers
	 */
	public List<Customers> findCustomerByUsername(String value) {
		List<Customers> p = customerDAO.findByStringFieldValue("username", value); 
		if(p == null) {
			throw new NoSuchElementException("The Customer with username =" + value + " was not found!");
		}
		return p;
	}

	/**
	 * Aceasta metoda toate elementele din tabela Customers si le returneaza
	 * pe aceste sub forma de lista 
	 * @return o lista de obiecte de tip Customers
	 */
	public List<Customers> viewCustomersTable(){
		List<Customers> p = customerDAO.viewAll();
		if(p == null) {
			throw new NoSuchElementException("The Customers table is empty!");
		}
		return p;
	}
	
	/**
	 * Creaza tabela Customers in functie de valorile din lista deobiecte
	 * primita ca si parametru
	 * @param list lista de obiecte care va defini tabela
	 * @return tabela Customers
	 */
	public JTable createCustomersTable(List<Customers> list) {
		try {
			return customerDAO.createTable(list);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Aceasta metoda verifica daca un username exista sa nu in tabela Customers
	 * @param value valoarea coloanei "username" care va fi verificata in tabela
	 * @return true daca exista un username cu valoarea @param value, false in caz contrat
	 */
	public boolean checkUsername(String value) {
		if(customerDAO.findByStringFieldValue("username", value).isEmpty()==false)
			return true;
		return false;
	}
	
	/**
	 * Aceasta metoda verifica daca o parola exista sa nu in tabela Customers
	 * @param value valoarea coloanei "password" care va fi verificata in tabela
	 * @return true daca exista o parola cu valoarea @param value, false in caz contrat
	 */
	public boolean checkPassword(String value) {
		if(customerDAO.findByStringFieldValue("password", value).isEmpty()==false)
			return true;
		return false;
	}
	
}
