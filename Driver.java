import java.io.File;
import java.io.IOException;

public class Driver {
public static void main(String [] args) throws IOException {
	
	
	// x^2 + x + 1
	double [] coef1 = {1, 1, 1};
	int [] exp1 = {0, 1, 2};
	Polynomial p1 = new Polynomial(coef1, exp1); 
	
	// 2x^3 + 2x + 1
	double [] coef2 = {1, 2, 2};
	int [] exp2 = {0, 1, 3};
	Polynomial p2 = new Polynomial(coef2, exp2); 
	

	//	double [] coef3 = {-1, -2, 4};
	//	int [] exp3 = {1, 0, 3};
	//	Polynomial p3 = new Polynomial(coef3, exp3);
	//	p3.saveToFile("p3.txt");
	//
	//	double [] coef4 = {1, 1, -2, -5, -4, 3};
	//	int [] exp4 = {5, 6, 1, 0, 2, 3};
	//	Polynomial p4 = new Polynomial(coef4, exp4);
	//	p4.saveToFile("p4.txt");
	
	File file3 = new File("p3.txt");
	File file4 = new File("p4.txt");
	
	Polynomial p3 = new Polynomial(file3);
	Polynomial p4 = new Polynomial(file4);
//	
	p3.multiply(p4).saveToFile("p3xp4.txt");
	
	Polynomial add = p1.add(p2);
	Polynomial multiply = p1.multiply(p2);
	
	System.out.println("p1 :\n" + p1.polynomialToString());
	System.out.println("p2 :\n" + p2.polynomialToString());
	System.out.println("p1 + p2:\n" + add.polynomialToString());
	System.out.println("p1 x p2 (multiplpy will be saved to multiply.txt):\n" + multiply.polynomialToString());
	
	multiply.saveToFile("multiply.txt");
	
	File f = new File("multiply.txt");
	Polynomial readFromFilePoly = new Polynomial(f);
	System.out.println("Result read from multiply.txt:\n" + readFromFilePoly.polynomialToString());
	
	}
}