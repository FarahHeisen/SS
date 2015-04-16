package services;

public class OperationService {
	
	
	public static double calcul (double a, double b, String ope) throws ArithmeticException {
		double res = 0;
		if (ope.equals("add")){
			res = a + b;
		}
		else if (ope.equals("sub")){
			res = a - b;
		}
		else if (ope.equals("mult")){
			res = a * b;
		}
		else if (ope.equals("div")){
			if (b == 0){
				throw new ArithmeticException();
			}
			else {
				res = a / b;
			}
		}
	return res;
	}

	}
