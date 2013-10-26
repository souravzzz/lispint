package lispint;

public class Parser {

	Lexer _lexer;

	public Parser(Lexer lexer) {
		_lexer = lexer;
	}

	public SExpression parse() throws Exception {

		if (_lexer.hasMoreTokens()) {
			Token token = _lexer.getNextToken();
			if (token != null) {
				switch (token) {
				case LEFTPAREN:
					return parseRest();
				case RIGHTPAREN:
					System.out.println("Extra Closing Parens Found");
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

	public SExpression parseRest() throws Exception {
		if (_lexer.hasMoreTokens()) {
			Token token = _lexer.getNextToken();
			switch (token) {
			case RIGHTPAREN:
				return SExpression.NIL;
			case DOT:
				SExpression exp = parse();
				token = _lexer.getNextToken();
				if (!token.equals(Token.RIGHTPAREN)) {
					System.out.println("Warning");
				}
				return exp;
			default:
				_lexer.unreadLastToken();
				return new SExpression(parse(), parseRest());
			}
		} else {
			throw new Exception("Unexpected EOF");
		}
	}

}
