class Polynomial{
	
	double [] coefficients;
	
	Polynomial(){
		this.coefficients = new double[1];
	}
	
	Polynomial(double [] coefficients) {
		this.coefficients = coefficients;
	}
	
	Polynomial add(Polynomial poly) {
		double [] newCoefficients = new double [Math.max(this.coefficients.length, poly.coefficients.length)];
		for(int i = 0; i < newCoefficients.length; i++) {
			if (i < this.coefficients.length)
				newCoefficients[i] += this.coefficients[i];
			
			if (i < poly.coefficients.length)
				newCoefficients[i] += poly.coefficients[i];
			
		}
		
		return new Polynomial(newCoefficients);
	}
	
	double evaluate(double x) {
		double result = 0;
		for(int i = 0; i < this.coefficients.length; i++) {
			result += this.coefficients[i] * Math.pow(x, i);
		}
		return result;
	}
	
	boolean hasRoot(double x) {
		if (evaluate(x) == 0) {
			return true;
		}
		return false;
	}
}