package lispint;

public class Interpreter {

	public static void main(String[] args) {
		try {
			SExpression a = new SExpression("a");
			SExpression b = new SExpression("b");
			SExpression c = new SExpression("c");

			// Evaluator.eval(a, b, c);
			Helper.eq();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
