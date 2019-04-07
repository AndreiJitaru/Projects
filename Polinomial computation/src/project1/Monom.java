package project1;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Monom {
	private double coeficient;
	private int exponent;

	public Monom(double coeficient, int exponent) {
		super();
		this.coeficient = coeficient;
		this.exponent = exponent;
	}

	public Monom() {}

	public double getCoeficient() {
		return coeficient;
	}

	public void setCoeficient(double Coeficient) {
		this.coeficient = Coeficient;
	}

	public int getExponent() {
		return exponent;
	}

	public void setExponent(int Exponent) {
		this.exponent = Exponent;
	}

	public String convertMonomialWithDoubleCoeficientToString() { //metoda parcurge toate posibilitatile de redare a unui monom cu Coeficienticienti reali sub forma de String
		DecimalFormat df = new DecimalFormat("#.#"); //(conform conventiei de redare a polinoamelor din documentatie) 
	    df.setRoundingMode(RoundingMode.FLOOR); //Coeficienticientii afisati cu o singura zecimala
		if(this.getCoeficient()>0 && this.getExponent()>1) {
			if(this.getCoeficient()==1) {
				return "+x^" + this.getExponent();
			}
			else {
				return "+" + df.format(this.getCoeficient()) + "x^" + this.getExponent();
			}
		} 
		else if(this.getCoeficient()>0 && this.getExponent()==1) {
			if(this.getCoeficient()==1) {
				return "+x";
			}
			else {
				return "+" + df.format(this.getCoeficient()) + "x";
			}
		}
		else if(this.getCoeficient()>0 && this.getExponent()==0) {
			if(this.getCoeficient()==1) {
				return "+1";
			}
			else {
				return "+" + df.format(this.getCoeficient());
			}
		} 
		else if(this.getCoeficient()<0 && this.getExponent()>1) {
			if(this.getCoeficient()==-1) {
				return "-x^" + this.getExponent();
			}
			else {
				return df.format(this.getCoeficient()) + "x^" + this.getExponent();
			}
		}
		else if(this.getCoeficient()<0 && this.getExponent()==1) {
			if(this.getCoeficient()==-1) {
				return "-x";
			} 
			else {
				return df.format(this.getCoeficient()) + "x";
			}
		}
		else if(this.getCoeficient()<0 && this.getExponent()==0) {
			return "" + df.format(this.getCoeficient());
		}
		return "0";
	}

	public String convertMonomialWithIntegerCoeficientToString() { ////metoda parcurge toate posibilitatile de redare a unui monom cu Coeficienticienti reali sub forma de String
		if(this.getCoeficient()>0 && this.getExponent()>1) { //(conform conventiei de redare a polinoamelor din documentatie)
			if(this.getCoeficient()==1) {
				return "+x^" + this.getExponent();
			}
			else {
				return "+" + (int)this.getCoeficient() + "x^" + this.getExponent();
			}
		} 
		else if(this.getCoeficient()>0 && this.getExponent()==1) {
			if(this.getCoeficient()==1) {
				return "+x";
			}
			else {
				return "+" + (int)this.getCoeficient() + "x";
			}
		}
		else if(this.getCoeficient()>0 && this.getExponent()==0) {
			if(this.getCoeficient()==1) {
				return "+1";
			}
			else {
				return "+" + (int)this.getCoeficient();
			}
		} 
		else if(this.getCoeficient()<0 && this.getExponent()>1) {
			if(this.getCoeficient()==-1) {
				return "-x^" + this.getExponent();
			}
			else {
				return (int)this.getCoeficient() + "x^" + this.getExponent();
			}
		}
		else if(this.getCoeficient()<0 && this.getExponent()==1) {
			if(this.getCoeficient()==-1) {
				return "-x";
			} 
			else {
				return (int)this.getCoeficient() + "x";
			}
		}
		else if(this.getCoeficient()<0 && this.getExponent()==0) {
			return "" + (int)this.getCoeficient();
		}
		return "0";
	}
}
