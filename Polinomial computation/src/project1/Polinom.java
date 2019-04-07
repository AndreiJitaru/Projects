package project1;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polinom {
	protected ArrayList<Monom> polinom = new ArrayList<Monom>();

	public Polinom(ArrayList<Monom> polinom) {
		super();
		this.polinom = polinom;
	}

	public Polinom() {}

	public Polinom allignPolynomial() { //metoda produce ordonarea crescatoare a termenilor polinomului si insumarea termenilor cu coeficient egal
		Polinom result = new Polinom();
		while(this.polinom.isEmpty()==false) { //cat timp lista de monoame mai are termeni
			int maxPow = 0;
			for(Iterator<Monom> j = this.polinom.iterator();j.hasNext();) {
				Monom m = j.next();
				if(m.getExponent()>maxPow) { //cautam gradul maxim al polinomului
					maxPow = m.getExponent();
				}
			}
			double newCoeficient = 0;
			for(Iterator<Monom> k = this.polinom.iterator();k.hasNext();) {//cautam toate monoamele cu grad maxim
				Monom m = k.next();
				if(m.getExponent()==maxPow) { //daca gasim mai multe monoame cu grad maxim adunam coeficientii 
					newCoeficient += m.getCoeficient();
					k.remove(); //scoatem monomul din polinom
				}
			}
			if(newCoeficient!=0) {
				result.polinom.add(new Monom(newCoeficient, maxPow)); //adaugam monomul obtinut din insumarea coeficientilor 
			}// monoamelor cu grad = gradMaxim in polinomul rezultat
		}
		return result;
	}

	public Polinom addPolynomial(Polinom a) { //metoda de adunare a doua polinoame
		Polinom result = new Polinom();
		for(Iterator<Monom> i = a.polinom.iterator();i.hasNext();) {
			Monom ma = i.next();
			this.polinom.add(ma); // punem in lista de monoame a celui de-al doilea polinom lista de moanoame a primului
		}
		result = this.allignPolynomial(); //aliniem polinomul rezultat
		return result;
	}

	public Polinom subPolynomial(Polinom a) { //metoda de scadere a doua polinoame
		Polinom result = new Polinom();
		for(Iterator<Monom> i = a.polinom.iterator();i.hasNext();) {
			Monom ma = i.next();
			ma.setCoeficient(-ma.getCoeficient()); //inversam semnul coeficientilor monoamelor din polinomul a
		}
		result = this.addPolynomial(a); //adunam cele doua polinoame
		return result;
	}

	public Polinom derivatePolynomial() { //metoda de derivare a unui polinom
		for(Iterator<Monom> i = this.polinom.iterator();i.hasNext();) {
			Monom m = i.next();
			if(m.getExponent()!=0) { //daca exponentul monomului e diferit de 0
				m.setCoeficient(m.getCoeficient()*(m.getExponent())); //setam noul coeficient
				m.setExponent(m.getExponent()-1); //setam noul exponent
			} 
			else {
				m.setCoeficient(0); //in caz contrat punem coeficientul pe 0
			}
		}
		return this.allignPolynomial(); //aliniem polinomul in caz ca nu a fost aliniat initial
	}

	public Polinom integratePolynomial() { //metoda de integrare a unui polinom
		for(Iterator<Monom> i = this.polinom.iterator();i.hasNext();) {
			Monom m = i.next();
			m.setCoeficient(m.getCoeficient()/(m.getExponent()+1)); //setam noul coeficient
			m.setExponent(m.getExponent()+1); //setam noul exponent
		}
		return this.allignPolynomial(); //aliniem polinomul in caz ca nu a fost aliniat initial
	} 

	public Polinom multiplyPolynomial(Polinom a) { //metoda de inmultire a doua polinoame
		Polinom result = new Polinom();
		for(Iterator<Monom> i = a.polinom.iterator();i.hasNext();) { //inmultim termenii celor doua liste de monoame termen cu termen(monom cu monom)
			Monom ma = i.next();
			for(Iterator<Monom> j = this.polinom.iterator();j.hasNext();) {
				Monom mb = j.next(); 
				result.polinom.add(new Monom(ma.getCoeficient()*mb.getCoeficient(), ma.getExponent()+mb.getExponent())); //introducem in polinomul rezultat noile valori obtinute 
			}
		}
		return result.allignPolynomial(); //aliniem polinomul deoarece exista posibilitatea ca doua monoame sa aiba exponent egal
	}

	public ArrayList<Polinom> dividePolynomial(Polinom a) { //metode de impartire a doua polinoame
		Polinom firstPolynomial = this.allignPolynomial(), secondPolynomial = a.allignPolynomial();
		Polinom remainder = firstPolynomial, quotient = new Polinom(); //dam lui remainder valoarea deimpartitului si lui quotient valoarea nula
		ArrayList<Polinom> result = new ArrayList<Polinom>();
		if(secondPolynomial.polinom.size()>0 && secondPolynomial.polinom.get(0).getCoeficient()!=0 && firstPolynomial.polinom.size()>0 && firstPolynomial.polinom.get(0).getCoeficient()!=0) { // daca cele doua polinoame sunt diferite de polinomul null
			if(firstPolynomial.polinom.get(0).getExponent()>=secondPolynomial.polinom.get(0).getExponent()) {//daca gradul deimpartitului este mai mare decat al impartitorului			
				while(remainder.polinom.size()>0 && remainder.polinom.get(0).getExponent()>=secondPolynomial.polinom.get(0).getExponent()) { // cat timp remainder-ul este diferit de nul si are gradul maxim mai mare decat al impartitorului 
					Monom m = new Monom(remainder.polinom.get(0).getCoeficient()/secondPolynomial.polinom.get(0).getCoeficient(), remainder.polinom.get(0).getExponent()-secondPolynomial.polinom.get(0).getExponent()); // cream monomul care va fi adaugat quotient-ului
					Polinom t = new Polinom();
					t.polinom.add(m);
					quotient = quotient.addPolynomial(t);
					remainder = remainder.subPolynomial(t.multiplyPolynomial(secondPolynomial)); //scadem din remainder valoarea quotient-ul temporar inmultit cu impartitorului 
				}
				result.add(quotient); //punem pe prima pozitie din result quotient
				result.add(remainder); //punem pe a dpua poztie din result reumainder
			}
			else {// daca gradul impartitorului e mai mare decat al deimpartitului
				result.add(new Polinom());
				result.add(firstPolynomial);
			}
		}
		else {
			result.add(new Polinom()); // daca polinoamele sunt nule
			result.add(firstPolynomial);//o sa fie 0 tot timpul
		}
		return result;
	}

	public String convertIntegerPolynomialBackToString() { //metoda de conversia a unui polinom cu coeficienti intregi in String
		if(this.polinom.isEmpty()==true) { //daca polinomul e null
			return "0";
		}
		String polynomialAsString = new String();
		for(Iterator<Monom> i = this.polinom.iterator();i.hasNext();) {
			Monom m = i.next();
			String monomialAsString = m.convertMonomialWithIntegerCoeficientToString(); // convertim polinomul monom cu monom
			polynomialAsString = polynomialAsString + monomialAsString;
		}
		if(polynomialAsString.length()>1) { 
			if(polynomialAsString.substring(0, 1).equals("+")) { //daca cumva coeficientul termenului cu grad maxim este pozitiv
				polynomialAsString = polynomialAsString.substring(1);// se realizeaza inlaturarea semnului "+" de la inceputul polinomului
			}
		}
		return polynomialAsString;
	}

	public String convertDoublePolynomialBackToString() { //idem ca la metoda anterioare doar ca coeficientii sunt reali
		if(this.polinom.isEmpty()==true) {
			return "0";
		}
		String polynomialAsString = new String();
		for(Iterator<Monom> i = this.polinom.iterator();i.hasNext();) {
			Monom m = i.next();
			String monomialAsString = m.convertMonomialWithDoubleCoeficientToString();
			polynomialAsString = polynomialAsString + monomialAsString;
		}
		if(polynomialAsString.length()>1) {
			if(polynomialAsString.substring(0, 1).equals("+")) {
				polynomialAsString = polynomialAsString.substring(1);
			}
		}
		return polynomialAsString;
	}

	public static Polinom convertStringToPolynomial(String polynomialAsString) {
		String theRegex = "([+-]?\\d*)x(\\^(\\d+))?|([+-]?\\d+)";//expresia regex pentru identificarea coeficientilor si a exponentilor
		Pattern checkRegex = Pattern.compile(theRegex);
		Matcher regexMatcher = checkRegex.matcher(polynomialAsString);
		Polinom result = new Polinom();
		while(regexMatcher.find()){
			double newCoeficient;
			int newExponent;
			if(regexMatcher.group(4)==null) { //daca nu se gaseste ceva de genul +-nr
				if(regexMatcher.group(3)==null) { //daca monomul nu are exponent 
					newExponent = 1; //atunci acesta trebuie sa fie 1 (cazul x la nicio putere)
				}
				else {
					newExponent = Integer.valueOf(regexMatcher.group(3)); //altfel setam exponentul
				}
				if(regexMatcher.group(1).equals("")) { //daca monomul nu are coeficient si semn 
					newCoeficient = 1; //atunci acesta trebuie sa fie 1 (cazul x^n)
				}

				else {
					if(regexMatcher.group(1).equals("-")) //daca monomul are doar semnul minus pe pozitia coeficientului
						newCoeficient = -1; //acesta este cu siguranta -1
					else if(regexMatcher.group(1).equals("+")) //daca monomul are doar semnul minus pe pozitia coeficientului
						newCoeficient = 1; //acesta este cu siguranta 1
					else
						newCoeficient = Integer.valueOf(regexMatcher.group(1)); //altfel setam coeficientul
				}
			}
			else { //daca se gaseste ceva de genul +-nr
				newCoeficient = Integer.valueOf(regexMatcher.group(4)); //setam coeficientul 
				newExponent = 0; //exponentul devinde 0
			}
			result.polinom.add(new Monom(newCoeficient, newExponent)); //cream polinomul
		}
		return result;
	}

}
