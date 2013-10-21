package lispint;

import static lispint.Helper.*;

public class Evaluator {

	public static SExpression eval(SExpression exp, SExpression a, SExpression d)
			throws Exception {

		if (isAtom(exp)) {
			if (isEqual(exp, "T")) {
				return SExpression.T;
			} else if (isEqual(exp, "NIL")) {
				return SExpression.NIL;
			} else if (isInt(exp)) {
				return exp;
			} else if (isBound(exp, a)) {
				return getVal(exp, a);
			} else {
				throw new Exception("Undefined Variable");
			}
		} else {
			if (isEqual(car(exp), "QUOTE")) {
				return cadr(exp);
			} else if (isEqual(car(exp), "COND")) {
				return evcon(cdr(exp), a, d);
			} else if (isEqual(car(exp), "DEFUN")) {
				return null; // TODO add stuff to d list
			} else {
				return apply(car(exp), evlist(cdr(exp), a, d), a, d);
			}
		}
	}

	public static SExpression evcon(SExpression x, SExpression a, SExpression d)
			throws Exception {

		if (isNull(x)) {
			throw new Exception("x is empty");
		} else if (eval(caar(x), a, d) != null) { // TODO check this condition,
													// exception?
			return eval(cadar(x), a, d);
		} else {
			return evcon(cdr(x), a, d);
		}
	}

	public static SExpression evlist(SExpression x, SExpression a, SExpression d)
			throws Exception {

		if (isNull(x)) {
			return SExpression.NIL;
		} else {
			return cons(eval(car(x), a, d), evlist(cdr(x), a, d));
		}
	}

	public static SExpression apply(SExpression f, SExpression x,
			SExpression a, SExpression d) throws Exception {

		if (isAtom(f)) {

			if (isEqual(f, "CAR")) {
				return Builtins.CAR(x);

			} else if (isEqual(f, "CDR")) {
				return Builtins.CDR(x);

			} else if (isEqual(f, "CONS")) {
				return Builtins.CONS(x);

			} else if (isEqual(f, "ATOM")) {
				return Builtins.ATOM(x);

			} else if (isEqual(f, "EQ")) {
				return Builtins.EQ(x);

			} else if (isEqual(f, "NULL")) {
				return Builtins.NULL(x);

			} else if (isEqual(f, "INT")) {
				return Builtins.INT(x);

			} else if (isEqual(f, "LESS")) {
				return Builtins.LESS(x);

			} else if (isEqual(f, "GREATER")) {
				return Builtins.GREATER(x);

			} else if (isEqual(f, "PLUS")) {
				return Builtins.PLUS(x);

			} else if (isEqual(f, "MINUS")) {
				return Builtins.MINUS(x);

			} else if (isEqual(f, "TIMES")) {
				return Builtins.TIMES(x);

			} else if (isEqual(f, "QUOTIENT")) {
				return Builtins.QUOTIENT(x);

			} else if (isEqual(f, "REMAINDER")) {
				return Builtins.REMAINDER(x);

			} else {
				return eval(cdr(getVal(f, d)),
						addPairs(car(getVal(f, d)), x, a), d);
			}
		} else {
			throw new Exception("Function name is not an atom");
		}
	}

	public static SExpression addPairs(SExpression params, SExpression args,
			SExpression a) {

		if (!isNull(params)) {
			SExpression car = new SExpression(car(params), car(args));
			SExpression cdr = addPairs(cdr(params), cdr(args), a);
			return new SExpression(car, cdr);
		}
		return a;
	}

	public static boolean isBound(SExpression exp, SExpression a)
			throws Exception {
		if (!isAtom(exp)) {
			throw new Exception("isBound called with non-atom");
		}

		if (isNull(a)) {
			return false;
		}

		if (isEqual(caar(a), exp.get_val())) {
			return true;
		} else {
			return isBound(cdr(exp), a);
		}
	}

	public static SExpression getVal(SExpression exp, SExpression a)
			throws Exception {
		if (!isAtom(exp)) {
			throw new Exception("isBound called with non-atom");
		}

		if (isEqual(caar(a), exp.get_val())) {
			return cadr(a);
		} else {
			return getVal(exp, cdr(a));
		}
	}

}
