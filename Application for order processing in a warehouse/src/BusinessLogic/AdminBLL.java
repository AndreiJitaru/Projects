package BusinessLogic;

import DataAccess.AdminDAO;

/**
 * Aceasta clasa prezinta operatiile care pot fi realizate la nivelul tabelei Admin
 * definind metode care sa permita interactiunea cu aceasta tabela
 * @author andre
 */
public class AdminBLL {
	private AdminDAO adminDAO;
	
	/**
	 * instantiaza atributulul adminDAO odata cu apelarea constructorului
	 */
	public AdminBLL() {
		adminDAO = new AdminDAO();
	}
	
	/**
	 * Aceasta metoda verifica daca un username exista sa nu in tabela Admin
	 * @param value valoarea coloanei "username" care va fi verificata in tabela
	 * @return true daca exista un username cu valoarea @param value, false in caz contrat
	 */
	public boolean checkUsername(String value) {
		if(adminDAO.findByStringFieldValue("username", value).size()>0)
			return true;
		return false;
	}
	
	
	/**
	 * Aceasta metoda verifica daca o parola exista sa nu in tabela Admin
	 * @param value valoarea coloanei "password" care va fi verificata in tabela
	 * @return true daca exista o parola cu valoarea @param value, false in caz contrat
	 */
	public boolean checkPassword(String value) {
		if(adminDAO.findByStringFieldValue("password", value).size()>0)
			return true;
		return false;
	}
}
