import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Polynomial{
	
	double [] coefficients;
	int [] exponents;
	
	Polynomial(){
		this.coefficients = new double[1];
		this.exponents = new int[1];
	}
	
	Polynomial(File file) throws FileNotFoundException {
		
		Polynomial ret = new Polynomial();
		
		Scanner scanner = new Scanner(file);
		if (scanner.hasNextLine()) {
			String polynomialString = scanner.nextLine();

			String [] splittedString = polynomialString.split("[+-]");
			
			int length = splittedString.length;
			// If the first coefficient is negative, then splittedString[0] is empty string
			if(splittedString[0] == "") length--;
			
			// Get the sign of each elements
			String [] sign = new String[length];
			int indexSign = 0;
			for(int i = 0; i < polynomialString.length(); i++) {
				if(i == 0) {
					if(polynomialString.charAt(i) != '-') {
						sign[indexSign] = "+";
					} else {
						sign[indexSign] = "-";
					}
					indexSign++;
				}
				else if(polynomialString.charAt(i) == '-') {
					sign[indexSign] = "-";
					indexSign++;
				}
				else if(polynomialString.charAt(i) == '+') {
					sign[indexSign] = "+";
					indexSign++;
				} 
			}
			
			int index = 0;
			for(String s: splittedString) {
				if(s == "") continue;
				String [] pairCoefExp = s.split("x");
				double [] newCoef = new double[1];
				int [] newExp = new int[1];
				
				
				newCoef[0] = Double.parseDouble(sign[index] + pairCoefExp[0]);
				if (pairCoefExp.length == 1) newExp[0] = 0;
				else newExp[0] = Integer.parseInt(pairCoefExp[1]);
				
				// System.out.println((new Polynomial(newCoef, newExp)).polynomialToString());
				ret = ret.add(new Polynomial(newCoef, newExp));
				//  System.out.println(ret.polynomialToString());
				index++;
			}
		}
		this.coefficients = ret.coefficients;
		this.exponents = ret.exponents;
		scanner.close();
	}
	
	Polynomial(double [] coefficients, int [] exponents) {
		this.coefficients = new double[coefficients.length];
		this.exponents = new int[exponents.length];
		for(int i = 0; i < coefficients.length; i++) {
			this.coefficients[i] = coefficients[i];
			this.exponents[i] = exponents[i];
		}
	}
	
	int [] convertExponentsCountToExponentsArray (double [] exponentsCount) {
		
		int length = 0;
		for(int i = 0; i < exponentsCount.length; i++) {
			if (exponentsCount[i] != 0) length++;	
		}
		
		int [] exponents = new int[Math.max(length, 1)];
		int index = 0;
		for(int i = 0; i < exponentsCount.length; i++) {
			if (exponentsCount[i] != 0) {
				exponents[index] = i;
				index++;
			}
		}
		return exponents;
	}
	
	
	double [] convertExponentsCountToCoefficientsArray (double [] exponentsCount) {
		int length = 0;
		for(int i = 0; i < exponentsCount.length; i++) {
			if (exponentsCount[i] != 0) length++;	
		}
		
		double [] coefficients = new double[Math.max(length, 1)];
		int index = 0;
		for(int i = 0; i < exponentsCount.length; i++) {
			if (exponentsCount[i] != 0) {
				coefficients[index] += exponentsCount[i];
				index++;
			}
			

		}
		return coefficients;
	}
	
	
	Polynomial add(Polynomial poly) {
		int maxDegree = 0;
		for(int degree: this.exponents)
			maxDegree = Math.max(maxDegree, degree);
		for(int degree: poly.exponents)
			maxDegree = Math.max(maxDegree, degree);
		
		double [] exponentsCount = new double[maxDegree + 1];

		for(int i = 0; i < this.exponents.length; i++) {
			exponentsCount[this.exponents[i]] += this.coefficients[i];
		}
		
		for(int i = 0; i < poly.exponents.length; i++) {
			exponentsCount[poly.exponents[i]] += poly.coefficients[i];
		}
		
		
		double [] newCoefficients = convertExponentsCountToCoefficientsArray(exponentsCount);
		int [] newExponents = convertExponentsCountToExponentsArray(exponentsCount);
		
		return new Polynomial(newCoefficients, newExponents);
	}
	
	Polynomial multiply(Polynomial poly) {
		Polynomial ret = new Polynomial();
			
		for(int i = 0; i < this.exponents.length; i++) {
			for(int j = 0; j < poly.exponents.length; j++) {
				int exp1 = this.exponents[i];
				int exp2 = poly.exponents[j];
				double coef1 = this.coefficients[i];
				double coef2 = poly.coefficients[j];
				
				int [] newExponents = new int[1];
				double [] newCoefficients = new double[1];
				
				newExponents[0] = exp1 + exp2;
				newCoefficients[0] = coef1 * coef2;
				
				ret = ret.add(new Polynomial(newCoefficients, newExponents));
			}
		}
		
		return ret;
	}
	
	double evaluate(double x) {
		double result = 0;
		for(int i = 0; i < this.coefficients.length; i++) {
			result += this.coefficients[i] * Math.pow(x, this.exponents[i]);
		}
		return result;
	}
	
	boolean hasRoot(double x) {
		return (evaluate(x) == 0);
	}
	
	String polynomialToString() {
		String output = "";
		for(int i = 0; i < this.coefficients.length; i++) {
			output += this.coefficients[i];
			if(this.exponents[i] != 0)
				output += "x" + this.exponents[i];
			if(i != this.coefficients.length - 1 && this.coefficients[i+1] >= 0)
				output += "+";
			
		}
		return output;
	}
	
	void saveToFile(String filename) throws IOException {
		FileWriter fw = new FileWriter(filename);
		fw.write(this.polynomialToString());
		fw.close();
	}
	

}