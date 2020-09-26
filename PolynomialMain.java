import java.util.*;

class LabTest {
	static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		while(true) {
			Polynomial a = new Polynomial(2);
			Polynomial b = new Polynomial(2);

			double x = 0.0;
			System.err.println("Enter a real number : ");
			x = in.nextFloat();

			// initialize the Polynomial a
			InitPolynomial(a, "a");
			System.out.printf("a(%.4f) = %.4f\n", x, a.Evaluate(x));

			// initialize the Polynomial a
			InitPolynomial(b, "b");
			x += 0.5;
			System.out.printf("b(%.4f) = %.4f\n", x, b.Evaluate(x));
	
			Polynomial c;
			System.out.println("c = a + b");
			c = a.Add(b);
			x += 0.5;
			System.out.printf("c(%.4f) = %.4f\n", x, c.Evaluate(x));

			Polynomial d;
			System.out.println("d = a - b");
			d = a.Subtract(b);
			x += 0.5;
			System.out.printf("d(%.4f) = %.4f\n", x, d.Evaluate(x));

			System.err.println("Continue (y/n) ");
			String ans = in.next();
			if(ans.equals("n"))
				break;
		}
	}

	static void InitPolynomial(Polynomial p, String msg) {
		System.err.println("Init Polynomial " + msg);
		float coef;
		int exp;
		while (true) {
			System.err.println("Enter a term (coef, exp)");
			coef = in.nextFloat();
			exp = in.nextInt();
			if(exp == -1) {
				return;
			}
			p.NewTerm(coef, exp);
		}
	}
}
