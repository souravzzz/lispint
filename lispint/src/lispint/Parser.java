package lispint;

public class Parser {

	private Lexer _lexer;

	public Parser(Lexer lexer) {
		_lexer = lexer;
	}

	public SExpression parse() throws Exception {

		if (_lexer.hasMoreTokens()) {
			Token token = _lexer.getNextToken();
			if (token != null) {
				switch (token) {
				case LEFTPAREN:
					return parseRest(false);
				case RIGHTPAREN:
					System.out.println("Ignoring Extra Closing Parens");
					return parse();
				case ATOM:
					return new SExpression(token._val);
				default:
					break;
				}
			}
		}

		return null;
	}

	public SExpression parseRest(boolean isDotOk) throws Exception {
		if (_lexer.hasMoreTokens()) {
			Token token = _lexer.getNextToken();
			switch (token) {
			case RIGHTPAREN:
				return SExpression.NIL;
			case DOT:
				if (!isDotOk) {
					throw new Exception("Invalid input");
				}
				SExpression exp = parse();
				token = _lexer.getNextToken();
				return exp;
			default:
				_lexer.unreadLastToken();
				return new SExpression(parse(), parseRest(true));
			}
		} else {
			throw new Exception("Unexpected EOF");
		}
	}

}
