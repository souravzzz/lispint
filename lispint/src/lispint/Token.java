package lispint;

public enum Token {
	LEFTPAREN, RIGHTPAREN, DOT, ATOM;
	public String _val;

	public static Token get(int type, String str) {

		switch (type) {
		case '(':
			return LEFTPAREN;
		case ')':
			return RIGHTPAREN;
		case '.':
			return DOT;
		default:
			Token atom = ATOM;
			atom._val = str.toUpperCase();
			return atom;
		}
	}

	public String toString() {
		switch (this) {
		case LEFTPAREN:
			return "(";
		case RIGHTPAREN:
			return ")";
		case DOT:
			return ".";
		case ATOM:
			return _val;
		}
		return null;
	}
}
