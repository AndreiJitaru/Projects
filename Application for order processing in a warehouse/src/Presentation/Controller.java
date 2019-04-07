package Presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import BusinessLogic.*;
import Model.*;

public class Controller {

	private LogInWindow logInWindow;
	private MainView mainView;
	private MainViewCustomer mainViewCustomer;
	private AddCustomerWindow addCustomerWindow;
	private UpdateCustomerWindow updateCustomerWindow;
	private DeleteCustomerWindow deleteCustomerWindow;
	private FindCustomerWindow findCustomerWindow;
	private FindProductWindow findProductWindow;
	private UpdateProductWindow updateProductWindow;
	private AddProductWindow addProductWindow;
	private DeleteProductWindow deleteProductWindow;
	private CreateOrderWindow createOrderWindow;
	
	public Controller(LogInWindow logInWindow, MainView mainView, MainViewCustomer mainViewCustomer, AddCustomerWindow addCustomerWindow, UpdateCustomerWindow updateCustomerWindow,
			DeleteCustomerWindow deleteCustomerWindow, FindCustomerWindow findCustomerWindow, UpdateProductWindow updateProductWindow, AddProductWindow addProductWindow, 
			FindProductWindow findProductWindow, DeleteProductWindow deleteProductWindow, CreateOrderWindow createOrderWindow) {
		
		this.logInWindow = logInWindow;
		this.mainView = mainView;
		this.mainViewCustomer = mainViewCustomer;
		this.addCustomerWindow = addCustomerWindow;
		this.updateCustomerWindow = updateCustomerWindow;
		this.deleteCustomerWindow = deleteCustomerWindow;
		this.findCustomerWindow = findCustomerWindow;
		this.findProductWindow = findProductWindow;
		this.addProductWindow = addProductWindow;
		this.updateProductWindow = updateProductWindow;
		this.deleteProductWindow = deleteProductWindow;
		this.createOrderWindow = createOrderWindow;
		
		this.logInWindow.showPasswordWhenClicked(new showPasswordListener());
		this.logInWindow.connectWhenClicked(new loginListener());
		
		
		this.mainView.showCustomersMenu(new showCustomersMenuListener());
		this.mainView.backToMainMenuFromCustomers(new backToMainMenuFromCustomersListener());
		this.mainView.openAddCustomerWindow(new openAddCustomerWindowListener());
		this.mainView.openDeleteCustomerWindow(new openDeleteCustomerWindowListener());
		this.mainView.openFindCustomerWindow(new openFindCustomerWindowListener());
		this.mainView.openUpdateCustomerWindow(new openUpdateCustomerWindowListener());
		this.mainView.viewCustomerTable(new viewCustomerTableListener());
		this.mainView.openCreateOrderWindow(new openCreateOrderWindowListener());

		this.mainView.showProductsMenu(new showProductsMenuListener());
		this.mainView.backToMainMenuFromProducts(new backToMainMenuFromProductsListener());
		this.mainView.openAddProductWindow(new openAddProductWindowListener());
		this.mainView.openDeleteProductWindow(new openDeleteProductWindowListener());
		this.mainView.openFindProductWindow(new openFindProductWindowListener());
		this.mainView.openUpdateProductWindow(new openUpdateProductWindowListener());
		this.mainView.viewProductsTable(new viewProductTableListener());
		this.mainView.viewOrdersTable(new viewOrdersTableListener());
		
		this.addCustomerWindow.addCustomerFromAddWindow(new addCustomerListener());
		this.addProductWindow.addProductFromAddProductWindow(new addProductListener());
		
		this.updateCustomerWindow.updateCustomerFromUpdateWindow(new updateCustomerListener());
		this.updateProductWindow.updateProductFromUpdateWindow(new updateProductListener());
		
		this.deleteCustomerWindow.deleteCustomer(new deleteCustomerListener());
		this.deleteProductWindow.deleteProduct(new deleteProductListener());
		
		this.findCustomerWindow.findCustomer(new findCustomerListener());
		this.findProductWindow.findProduct(new findProductListener());
		
		this.createOrderWindow.createOrder(new createOrderListener());
		
		this.mainViewCustomer.openEditProfileWindow(new openEditProfileWindowFromMainViewCustomerListener());
		this.mainViewCustomer.openCreateOrderWindow(new openCreateOrderWindowFromMainViewCustomerListener());
		this.mainViewCustomer.openFindProductWindow(new openFindProductWindowFromMainViewCustomerListener());
		this.mainViewCustomer.viewOrdersTable(new viewOrdersTableFromMainViewCustomerListener());
		this.mainViewCustomer.viewProductsTable(new viewProductsTableFromMainViewCustomerListener());
		
		this.findProductWindow.findProduct(new findProductFromMainViewCustomerListener());
		
		this.mainViewCustomer.viewProfile(new viewProfilePanelListener());
	}

	public class loginListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			AdminBLL admin = new AdminBLL();
			CustomerBLL customer = new CustomerBLL(); 
			if(logInWindow.getJComboBoxStatus()==true) {
				JOptionPane.showMessageDialog(null, "Choose your logging method!");
			}
			else if(logInWindow.getLogInAsValue().equals("ADMIN") && admin.checkUsername(logInWindow.getUsername())==true && admin.checkPassword(logInWindow.getPassword())==true) {
				mainView.setVisible(true);
				logInWindow.setVisible(false);
			}
			else if(logInWindow.getLogInAsValue().equals("CLIENT") && customer.checkUsername(logInWindow.getUsername())==true && customer.checkPassword(logInWindow.getPassword())==true) {
				mainViewCustomer.setVisible(true);
				logInWindow.setVisible(false);
			}
			else {
				JOptionPane.showMessageDialog(null, "Wrong username or password!");
			}
			
		}
	}
	
	public class showPasswordListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if (logInWindow.getPasswordStatus()==true) {
                logInWindow.setPasswordVisible();
            } else {
                logInWindow.setPasswordInvisible();
            }
		}
	}
	
	
	public class backToMainMenuFromCustomersListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			mainView.setGeneralAccesMenuVisible();
			mainView.setEmptyPanelVisible();
		}
	}
	
	public class showCustomersMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			mainView.setCustomersMenuVisible();
		}
	}
	
	public class viewCustomerTableListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			mainView.setTablePanelVisible();
			CustomerBLL customers = new CustomerBLL(); 
			mainView.setTable(customers.createCustomersTable(customers.viewCustomersTable()));
		}
	}
	
	public class openAddCustomerWindowListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			addCustomerWindow.setVisible(true);
			addCustomerWindow.setValuesFromAddCustomerWindowToNull();
			mainView.setEmptyPanelVisible();
		}
	}
	
	public class openDeleteCustomerWindowListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			deleteCustomerWindow.setVisible(true);
			deleteCustomerWindow.setIdToNull();
			mainView.setEmptyPanelVisible();
		}
	}
	
	public class openFindCustomerWindowListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			findCustomerWindow.setVisible(true);
			findCustomerWindow.setIdTextFieldToNull();
			mainView.setEmptyPanelVisible();
		}
	}
	
	public class openUpdateCustomerWindowListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			updateCustomerWindow.setVisible(true);
			updateCustomerWindow.setValuesFromUpdateCustomerWindowToNull();
			mainView.setEmptyPanelVisible();
		}
	}
	
	
	public class backToMainMenuFromProductsListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			mainView.setGeneralAccesMenuVisible();
			mainView.setEmptyPanelVisible();
		}
	}
	
	public class showProductsMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			mainView.setProductsMenuVisible();
		}
	}
	
	public class viewProductTableListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			mainView.setTablePanelVisible();
			ProductBLL products = new ProductBLL(); 
			mainView.setTable(products.createProductsTable(products.viewProductsTable()));
		}
	}
	
	public class openAddProductWindowListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			addProductWindow.setVisible(true);
			addProductWindow.setValuesFromAddProductWindowToNull();
			mainView.setEmptyPanelVisible();
		}
	}

	public class openDeleteProductWindowListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			deleteProductWindow.setVisible(true);
			deleteProductWindow.setIdToNull();
			mainView.setEmptyPanelVisible();
		}
	}
	
	public class openFindProductWindowListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			findProductWindow.setVisible(true);
			findProductWindow.setIdTextFieldToNull();
			mainView.setEmptyPanelVisible();
		}
	}
	
	public class openUpdateProductWindowListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			updateProductWindow.setVisible(true);
			updateProductWindow.setValuesFromUpdateProductWindowToNull();
			mainView.setEmptyPanelVisible();
		}
	}
	

	public class openCreateOrderWindowListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			createOrderWindow.setVisible(true);
			createOrderWindow.setValuesFromCreateOrderWindowToNull();
			mainView.setEmptyPanelVisible();
		}
	}
	
	public class viewOrdersTableListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			mainView.setTablePanelVisible();
			OrderBLL orders = new OrderBLL();
			mainView.setTable(orders.createOrdersTable(orders.viewOrdersTable()));
		}
	}
	
	
	public class addCustomerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Customers customer = new Customers(addCustomerWindow.getIdTextField(), addCustomerWindow.getFirstNameTextField(), addCustomerWindow.getLastNameTextField(), addCustomerWindow.getPhoneNumberTextField(), addCustomerWindow.getEmailTextField(), addCustomerWindow.getUsernameTextField(), addCustomerWindow.getPasswordTextField());
			CustomerBLL c = new CustomerBLL();
			c.addCustomer(customer);
			addCustomerWindow.setVisible(false);
		}
	}
	
	public class addProductListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			Products product = new Products(addProductWindow.getIdTextField(), addProductWindow.getNameTextField(), addProductWindow.getPriceTextField(), addProductWindow.getDescriptionTextField(), addProductWindow.getStocTextField());
			ProductBLL p = new ProductBLL();
			p.addProduct(product);
			addProductWindow.setVisible(false);
		}
	}
	
	
	public class updateCustomerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			CustomerBLL c = new CustomerBLL();
			if(logInWindow.getLogInAsValue().equals("ADMIN")) {
				Customers customer = new Customers(updateCustomerWindow.getId(), updateCustomerWindow.getFirstName(), updateCustomerWindow.getLastName(),
						updateCustomerWindow.getPhoneNumber(), updateCustomerWindow.getEmail(), updateCustomerWindow.getUsername(), updateCustomerWindow.getPassword());
				c.editCustomer(updateCustomerWindow.getId(), customer);
				updateCustomerWindow.setVisible(false);
			} else {
				if(logInWindow.getLogInAsValue().equals("CLIENT") && updateCustomerWindow.getId() == c.findCustomerByUsername(logInWindow.getUsername()).get(0).getId() ) {
					if(updateCustomerWindow.getUsername().equals(c.findCustomerByUsername(logInWindow.getUsername()).get(0).getUsername()) || updateCustomerWindow.getUsername().equals("")) {
						Customers customer = new Customers(updateCustomerWindow.getId(), updateCustomerWindow.getFirstName(), updateCustomerWindow.getLastName(),
								updateCustomerWindow.getPhoneNumber(), updateCustomerWindow.getEmail(), updateCustomerWindow.getUsername(), updateCustomerWindow.getPassword());
						c.editCustomer(updateCustomerWindow.getId(), customer);
						updateCustomerWindow.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "You can't change username!");
					}
				} else {
					JOptionPane.showMessageDialog(null, "You can't update another customer's profile");
				}
			}
			updateCustomerWindow.setIdAndUsernameEditable();
		}
	}
	
	public class updateProductListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ProductBLL p = new ProductBLL();
			Products actualProduct = p.findProductById(updateProductWindow.getId()).get(0);
			int price, stoc;
			if(updateProductWindow.getPrice().equals("")) {
				price = actualProduct.getPrice();
			}
			else {
				price = Integer.parseInt(updateProductWindow.getPrice());
			}
			if(updateProductWindow.getStoc().equals("")) {
				stoc = actualProduct.getStoc();
			}
			else {
				stoc = Integer.parseInt(updateProductWindow.getStoc());
			}
			Products product = new Products(updateProductWindow.getId(), updateProductWindow.getProductName(), price, updateProductWindow.getDescription(), stoc);
			p.editProduct(updateProductWindow.getId(), product);
			updateProductWindow.setVisible(false);
		}
	}
	
	
	public class deleteCustomerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			CustomerBLL customer = new CustomerBLL();			
			customer.deleteCustomer(deleteCustomerWindow.getId());
			deleteCustomerWindow.setVisible(false);
		}
	}
	
	public class deleteProductListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ProductBLL product = new ProductBLL();			
			product.deleteProduct(deleteProductWindow.getId());
			deleteProductWindow.setVisible(false);
		}
	}
	
	
	public class findCustomerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			CustomerBLL customer = new CustomerBLL();			
			mainView.setTable(customer.createCustomersTable(customer.findCustomerById(findCustomerWindow.getId())));
			findCustomerWindow.setVisible(false);
		}
	}
	
	public class findProductListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ProductBLL product = new ProductBLL();			
			mainView.setTable(product.createProductsTable(product.findProductById(findProductWindow.getId())));			
			findProductWindow.setVisible(false);
		}
	}
	
	
	public class createOrderListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			CustomerBLL c = new CustomerBLL();
			OrderBLL o = new OrderBLL();
			if(logInWindow.getLogInAsValue().equals("ADMIN")) {
				Orders order = new Orders(createOrderWindow.getIdCustomer(), createOrderWindow.getIdProduct(), createOrderWindow.getQuantity());
				o.addOrder(order);
				createOrderWindow.setVisible(false);
			}
			else {
				if(logInWindow.getLogInAsValue().equals("CLIENT") && createOrderWindow.getIdCustomer() == c.findCustomerByUsername(logInWindow.getUsername()).get(0).getId() ) {
					Orders order = new Orders(createOrderWindow.getIdCustomer(), createOrderWindow.getIdProduct(), createOrderWindow.getQuantity());
					o.addOrder(order);
					createOrderWindow.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "You can't create an order for another customer!");
				}
			}
		}
	}
	
	
	public class viewProductsTableFromMainViewCustomerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			mainViewCustomer.setTablePanelVisible();
			ProductBLL products = new ProductBLL(); 
			mainViewCustomer.setTable(products.createProductsTable(products.viewProductsTable()));
		}
	}
	
	public class viewOrdersTableFromMainViewCustomerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			mainViewCustomer.setTablePanelVisible();
			OrderBLL orders = new OrderBLL();
			CustomerBLL customer = new CustomerBLL();
			mainViewCustomer.setTable(orders.createOrdersTable(orders.findOrderByCustomerId(customer.findCustomerByUsername(logInWindow.getUsername()).get(0).getId())));
		}
	}
	
	public class openFindProductWindowFromMainViewCustomerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			findProductWindow.setVisible(true);
			findProductWindow.setIdTextFieldToNull();
			mainViewCustomer.setEmptyPanelVisible();
		}
	}
	
	public class openCreateOrderWindowFromMainViewCustomerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			createOrderWindow.setVisible(true);
			createOrderWindow.setValuesFromCreateOrderWindowToNull();
			mainViewCustomer.setEmptyPanelVisible();
		}
	}
	
	public class openEditProfileWindowFromMainViewCustomerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			updateCustomerWindow.setVisible(true);
			updateCustomerWindow.setValuesFromUpdateCustomerWindowToNull();
			CustomerBLL customer = new CustomerBLL();
			updateCustomerWindow.setIdTextField(customer.findCustomerByUsername(logInWindow.getUsername()).get(0).getId());
			updateCustomerWindow.setIdAndUsernameUneditable();
			mainViewCustomer.setEmptyPanelVisible();
		}
	}
	
	
	public class findProductFromMainViewCustomerListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			ProductBLL product = new ProductBLL();			
			mainViewCustomer.setTable(product.createProductsTable(product.findProductById(findProductWindow.getId())));			
			findProductWindow.setVisible(false);
		}
	}
	
	public class viewProfilePanelListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			mainViewCustomer.setProfilePanelVisible();
			CustomerBLL customers = new CustomerBLL(); 
			Customers c = new Customers();
			c = customers.findCustomerByUsername(logInWindow.getUsername()).get(0);
			mainViewCustomer.setProfileValues(c.getId(), c.getFirstName() + c.getLastName(), c.getEmail(), c.getUsername(), c.getPassword());
		}
	}
	
	
	public static void main(String[] args) {
		new Controller(new LogInWindow(), new MainView(), new MainViewCustomer(), new AddCustomerWindow(), new UpdateCustomerWindow(), new DeleteCustomerWindow(), new FindCustomerWindow(), new UpdateProductWindow(), 
				new AddProductWindow(), new FindProductWindow(), new DeleteProductWindow(), new CreateOrderWindow());
	}
}

