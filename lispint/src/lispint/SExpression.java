package lispint;

public class SExpression {

	private SExpression _car;
	private SExpression _cdr;
	private String _val;
	private boolean _isInt;

	public static final SExpression T = new SExpression("T");
	public static final SExpression NIL = new SExpression("NIL");

	public SExpression(SExpression exp1, SExpression exp2) {
		_car = exp1;
		_cdr = exp2;
		_val = null;
		_isInt = false;
	}

	public SExpression(String str) {
		_car = null;
		_cdr = null;
		_val = str.toUpperCase();
		_isInt = Helper.isInt(_val);
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

	public boolean get_isInt() {
		return _isInt;
	}

	public String toString() {
		return Helper.toString(this);
	}
}
