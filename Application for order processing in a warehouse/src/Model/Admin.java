package Model;

/*
 * Aceasta clasa reprezinta scheletul tabelei Admin, definind prin atributele
 * sale coloanele acestei tabele 
 */
public class Admin {
	private int id;
	private String username;
	private String password;
	
	/**
	 * instatiaza campurile unui obiect de tip Admin cu valorile parametrilor
	 * oferiti in antetul metoei
	 * @param id reprezinta id-ul unui admin
	 * @param username reprezinta username-ul unui admin
	 * @param password reprezinta parola carespunzatoare username-ului unui admin
	 */
	public Admin(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	
	/**
	 * nu se initializeaza campurile obiectului de tip Admin create
	 */
	public Admin() {}
	
	/**
	 * Metoda care returneaza id-ul unui obiect de tip Admin
	 * @return id-ul unui Admin
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Metodaca care asociaza atributului id al unui obiect de tip Admin
	 * valoarea @param id
	 * @param id valoarea noului id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Metoda care returneaza username-ul unui obiect de tip Admin
	 * @return id-ul unui Admin
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Metodaca care asociaza atributului username al unui obiect de tip Admin
	 * valoarea @param username
	 * @param username valoarea noului username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Metoda care returneaza password-ul unui obiect de tip Admin
	 * @return parola unui Admin
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * Metodaca care asociaza atributului password al unui obiect de tip Admin
	 * valoarea @param password
	 * @param password valoarea noului password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
