package lispint;

public class Interpreter {

	public static void main(String[] args) {
		try {
			SExpression a = new SExpression("1");

			SExpression program = a;
			SExpression result = Evaluator.eval(program, SExpression.NIL,
					SExpression.NIL);
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
