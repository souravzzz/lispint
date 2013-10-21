package lispint;

import java.util.List;

public class Evaluator {

	private Evaluator() {

	}

	public static SExpression eval(SExpression exp, List<SExpression> a,
			List<SExpression> d) throws Exception {

		if (isAtom(exp)) {
			if (equals(exp, "T")) {
				return SExpression.T;
			} else if (equals(exp, "NIL")) {
				return SExpression.NIL;
			} else if (isInt(exp)) {
				return exp;
			} else if (isBound(exp, a)) {
				return getVal(exp, a);
			} else {
				throw new Exception("Undefined Variable");
			}
		} else {
			if (equals(car(exp), "QUOTE")) {
				return cadr(exp);
			} else if (equals(car(exp), "COND")) {
				return evcon(cdr(exp), a, d);
			} else if (equals(car(exp), "DEFUN")) {
				return null; // TODO add stuff to d list
			} else {
				return apply(car(exp), evlist(cdr(exp), a, d), a, d);
			}
		}
	}

	public static SExpression evcon(SExpression x, List<SExpression> a,
			List<SExpression> d) throws Exception {

		if (isNull(x)) {
			throw new Exception("x is empty");
		} else if (eval(caar(x), a, d) != null) { // TODO check this condition,
													// exception?
			return eval(cadar(x), a, d);
		} else {
			return evcon(cdr(x), a, d);
		}
	}

	public static SExpression evlist(SExpression x, List<SExpression> a,
			List<SExpression> d) throws Exception {

		if (isNull(x)) {
			return null; // TODO return NIL
		} else {
			return cons(eval(car(x), a, d), evlist(cdr(x), a, d));
		}
	}

	public static SExpression apply(SExpression f, SExpression x,
			List<SExpression> a, List<SExpression> d) throws Exception {

		if (isAtom(f)) {

			if (equals(f, "CAR")) {
				return Builtins.CAR(x);

			} else if (equals(f, "CDR")) {
				return Builtins.CDR(x);

			} else if (equals(f, "CONS")) {
				return Builtins.CONS(x);

			} else if (equals(f, "ATOM")) {
				return Builtins.ATOM(x);

			} else if (equals(f, "EQ")) {
				return Builtins.EQ(x);

			} else if (equals(f, "NULL")) {
				return Builtins.NULL(x);

			} else if (equals(f, "INT")) {
				return Builtins.INT(x);

			} else if (equals(f, "LESS")) {
				return Builtins.LESS(x);

			} else if (equals(f, "GREATER")) {
				return Builtins.GREATER(x);

			} else if (equals(f, "PLUS")) {
				return Builtins.PLUS(x);

			} else if (equals(f, "MINUS")) {
				return Builtins.MINUS(x);

			} else if (equals(f, "TIMES")) {
				return Builtins.TIMES(x);

			} else if (equals(f, "QUOTIENT")) {
				return Builtins.QUOTIENT(x);

			} else if (equals(f, "REMAINDER")) {
				return Builtins.REMAINDER(x);

			} else {
				return eval(cdr(getVal(f, d)),
						addPairs(car(getVal(f, d)), x, a), d);
			}
		} else {
			throw new Exception("Function name is not an atom");
		}
	}

	public static List<SExpression> addPairs(SExpression params,
			SExpression args, List<SExpression> a) {

		if (!isNull(params)) {
			a.add(0, new SExpression(car(params), car(args)));
			addPairs(cdr(params), cdr(args), a);
		}
		return a;
	}

	public static boolean isNull(SExpression exp) {
		return false;
	}

	public static boolean isAtom(SExpression exp) {
		return true;
	}

	public static boolean isInt(SExpression exp) {
		return true;
	}

	public static boolean equals(SExpression exp, String id) {
		return false;
	}

	public static boolean isBound(SExpression exp, List<SExpression> a) {
		return true;
	}

	public static SExpression getVal(SExpression exp, List<SExpression> a) {
		return null;
	}

	public static SExpression car(SExpression exp) {
		return exp.get_car();
	}

	public static SExpression cdr(SExpression exp) {
		return exp.get_cdr();
	}

	public static SExpression caar(SExpression exp) {
		return car(car(exp));
	}

	public static SExpression cadr(SExpression exp) {
		return car(cdr(exp));
	}

	public static SExpression cdar(SExpression exp) {
		return cdr(car(exp));
	}

	public static SExpression cddr(SExpression exp) {
		return cdr(cdr(exp));
	}

	public static SExpression cadar(SExpression exp) {
		return car(cdr(car(exp)));
	}

	public static SExpression cons(SExpression exp1, SExpression exp2) {
		return new SExpression(exp1, exp2);
	}
}
