package lispint;

public class Environment {
	public SExpression a;
	public SExpression d;

	public Environment() {
		a = SExpression.NIL;
		d = SExpression.NIL;
	}

	public String toString() {
		return "a: " + a + " d: " + d;
	}

}
