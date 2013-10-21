package lispint;

public class SExpression {

	SExpression _car;
	SExpression _cdr;

	String _val;

	public static final SExpression T = new SExpression("T");
	public static final SExpression NIL = new SExpression("NIL");

	public SExpression(SExpression exp1, SExpression exp2) {
		_car = exp1;
		_cdr = exp2;
		_val = null;
	}

	public SExpression(String str) {
		_car = null;
		_cdr = null;
		_val = str.toUpperCase();
	}

	public static SExpression get(boolean b) {
		return b ? T : NIL;
	}

	public SExpression get_car() {
		return _car;
	}

	public SExpression get_cdr() {
		return _cdr;
	}

	public String get_val() {
		return _val;
	}

}
