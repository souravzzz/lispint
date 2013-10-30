package lispint;

public class Interpreter {

	public static void main(String[] args) {
		Lexer lexer = new Lexer(System.in);
		Parser parser = new Parser(lexer);
		Environment env = new Environment();

		while (lexer.hasMoreTokens()) {
			try {
				SExpression input = parser.parse();
				SExpression output = Evaluator.eval(input, env);
				System.out.println(output);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
