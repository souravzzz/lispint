package lispint;

public class Interpreter {

	public static void main(String[] args) {
		Lexer lexer = new Lexer(System.in);
		Parser parser = new Parser(lexer);
		SExpression a = SExpression.NIL;
		SExpression d = SExpression.NIL;

		while (lexer.hasMoreTokens()) {
			try {
				SExpression input = parser.parse();
				SExpression output = Evaluator.eval(input, a, d);
				System.out.println(output);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
