package Model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;
import Model.Account;

public class Person implements Serializable, Observer {
	
	private static final long serialVersionUID = 1L;
	private int cnp;
	private String firstName;
	private String lastName;
	private String email;
	
	public Person(int cnp, String firstName, String lastName, String email) {
		this.cnp = cnp;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	public Person(int cnp) {
		this.cnp = cnp;
	}
	
	public Person() { }
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cnp;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (cnp != other.cnp)
			return false;
		return true;
	}
	
	public void update(Observable o, Object arg){
		if(this.getCnp()==((Account)o).getOwner()) 
			System.out.println("Something has changed in Account " + ((Account)o).getIdAccount() + " which belongs to Client " + ((Account)o).getOwner());
	}
	
	public int getCnp() {
		return cnp;
	}
	
	public void setCnp(int cnp) {
		this.cnp = cnp;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
}
