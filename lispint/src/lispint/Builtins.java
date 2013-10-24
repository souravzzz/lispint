package lispint;

import static lispint.Helper.*;

public class Builtins {

	public static SExpression CAR(SExpression exp) {
		return caar(exp);
	}

	public static SExpression CDR(SExpression exp) {
		return cdar(exp);
	}

	public static SExpression CONS(SExpression exp) {
		return new SExpression(car(exp), cadr(exp));
	}

	public static SExpression ATOM(SExpression exp) {
		return isAtom(car(exp)) ? SExpression.T : SExpression.NIL;
	}

	public static SExpression NULL(SExpression exp) {
		return isNull(car(exp)) ? SExpression.T : SExpression.NIL;
	}

	public static SExpression INT(SExpression exp) {
		try {
			Integer.parseInt(car(exp).get_val());
			return SExpression.T;
		} catch (NumberFormatException ne) {
			return SExpression.NIL;
		}
	}

	public static SExpression EQ(SExpression exp) {
		return isEqual(car(exp), cadr(exp)) ? SExpression.T : SExpression.NIL;
	}

	public static SExpression LESS(SExpression exp) {
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return a < b ? SExpression.T : SExpression.NIL;
	}

	public static SExpression GREATER(SExpression exp) {
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return a > b ? SExpression.T : SExpression.NIL;
	}

	public static SExpression PLUS(SExpression exp) {
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return new SExpression(new Integer(a + b).toString());
	}

	public static SExpression MINUS(SExpression exp) {
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return new SExpression(new Integer(a - b).toString());
	}

	public static SExpression TIMES(SExpression exp) {
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return new SExpression(new Integer(a * b).toString());
	}

	public static SExpression QUOTIENT(SExpression exp) {
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return new SExpression(new Integer(a / b).toString());
	}

	public static SExpression REMAINDER(SExpression exp) {
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return new SExpression(new Integer(a % b).toString());
	}

}
