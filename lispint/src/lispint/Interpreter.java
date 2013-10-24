package lispint;

public class Interpreter {

	public static void main(String[] args) {
		try {
			SExpression q = new SExpression("TIMES");
			SExpression a = new SExpression("5");
			SExpression b = new SExpression("3");
			SExpression x = new SExpression(b, SExpression.NIL);
			SExpression y = new SExpression(a, x);
			SExpression p = new SExpression(q, y);

			SExpression program = p;
			SExpression result = Evaluator.eval(program, SExpression.NIL,
					SExpression.NIL);
			System.out.println("Input:  " + p);
			System.out.println("Output: " + result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
