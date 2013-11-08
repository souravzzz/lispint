package lispint;

import static lispint.Helper.*;
import static lispint.Validator.*;

public class Builtins {

	public static SExpression CAR(SExpression exp) throws Exception {
		validate("CAR", exp);
		return caar(exp);
	}

	public static SExpression CDR(SExpression exp) throws Exception {
		validate("CDR", exp);
		return cdar(exp);
	}

	public static SExpression CONS(SExpression exp) throws Exception {
		return new SExpression(car(exp), cadr(exp));
	}

	public static SExpression ATOM(SExpression exp) throws Exception {
		validate("NPARAM", exp, 1);
		return isAtom(car(exp)) ? SExpression.T : SExpression.NIL;
	}

	public static SExpression NULL(SExpression exp) throws Exception {
		validate("NPARAM", exp, 1);
		return isNull(car(exp)) ? SExpression.T : SExpression.NIL;
	}

	public static SExpression INT(SExpression exp) throws Exception {
		validate("NPARAM", exp, 1);
		try {
			Integer.parseInt(car(exp).get_val());
			return SExpression.T;
		} catch (NumberFormatException ne) {
			return SExpression.NIL;
		}
	}

	public static SExpression EQ(SExpression exp) throws Exception {
		validate("NPARAM", exp, 2);
		validate("EQ", exp);
		return isEqual(car(exp), cadr(exp)) ? SExpression.T : SExpression.NIL;
	}

	public static SExpression LESS(SExpression exp) throws Exception {
		validate("NPARAM", exp, 2);
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return a < b ? SExpression.T : SExpression.NIL;
	}

	public static SExpression GREATER(SExpression exp) throws Exception {
		validate("NPARAM", exp, 2);
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return a > b ? SExpression.T : SExpression.NIL;
	}

	public static SExpression PLUS(SExpression exp) throws Exception {
		validate("NPARAM", exp, 2);
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return new SExpression(new Integer(a + b).toString());
	}

	public static SExpression MINUS(SExpression exp) throws Exception {
		validate("NPARAM", exp, 2);
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return new SExpression(new Integer(a - b).toString());
	}

	public static SExpression TIMES(SExpression exp) throws Exception {
		validate("NPARAM", exp, 2);
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return new SExpression(new Integer(a * b).toString());
	}

	public static SExpression QUOTIENT(SExpression exp) throws Exception {
		validate("NPARAM", exp, 2);
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return new SExpression(new Integer(a / b).toString());
	}

	public static SExpression REMAINDER(SExpression exp) throws Exception {
		validate("NPARAM", exp, 2);
		int a = Integer.parseInt(car(exp).get_val());
		int b = Integer.parseInt(cadr(exp).get_val());
		return new SExpression(new Integer(a % b).toString());
	}

}
