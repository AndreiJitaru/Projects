package testare;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import project1.Polinom;

public class JUnitTest {
	public JUnitTest() {
		
	}
	
	@Test
	public void testAdd() {
		Polinom p1 = Polinom.convertStringToPolynomial("3x^2+2x+1");
		Polinom p2 = Polinom.convertStringToPolynomial("-2x^4+x^2-3");
		assertEquals("-2x^4+4x^2+2x-2", p1.addPolynomial(p2).convertIntegerPolynomialBackToString());
	}
	
	@Test
	public void testSub() {
		Polinom p1 = Polinom.convertStringToPolynomial("-3x^3+x^2+5");
		Polinom p2 = Polinom.convertStringToPolynomial("-x^2-3x-1");
		assertEquals("-3x^3+2x^2+3x+6", p1.subPolynomial(p2).convertIntegerPolynomialBackToString());
	}
	
	@Test
	public void testDerivation() {
		Polinom p = Polinom.convertStringToPolynomial("-6x^5+x^3+3x^2");
		assertEquals("-30x^4+3x^2+6x", p.derivatePolynomial().convertIntegerPolynomialBackToString());
	}
	
	@Test
	public void testIntegration() {
		Polinom p = Polinom.convertStringToPolynomial("-12x^5+4x^3+3x^2");
		assertEquals("-2x^6+x^4+x^3", p.integratePolynomial().convertIntegerPolynomialBackToString());
	}
	
	@Test
	public void testMultiplication() {
		Polinom p1 = Polinom.convertStringToPolynomial("-2x^2+4x-3");
		Polinom p2 = Polinom.convertStringToPolynomial("-2x^3+x^2");
		assertEquals("4x^5-10x^4+10x^3-3x^2", p1.multiplyPolynomial(p2).convertIntegerPolynomialBackToString());
	}
	
	@Test
	public void testDivision() {
		Polinom p1 = Polinom.convertStringToPolynomial("x^3+2x+1");
		Polinom p2 = Polinom.convertStringToPolynomial("x+1");
		ArrayList<Polinom> resultDivision = p1.dividePolynomial(p2);
		assertEquals("x^2-x+3", resultDivision.get(0).convertIntegerPolynomialBackToString()); // the quotient
		assertEquals("-2", resultDivision.get(1).convertIntegerPolynomialBackToString()); // the remainder
	}
	
	@Test
	public void testRegex() {
		String PolynomialialAsString = "-3x^2+2x+1";
		Polinom result = Polinom.convertStringToPolynomial(PolynomialialAsString);
		String resultAsString = result.convertIntegerPolynomialBackToString();
		assertEquals(PolynomialialAsString, resultAsString);
	}
	
}
