package lispint;

import static lispint.Helper.*;
import static lispint.Validator.*;

public class Evaluator {

	public static SExpression eval(SExpression exp, Environment env)
			throws Exception {

		if (isAtom(exp)) {
			if (isEqual(exp, "T")) {
				return SExpression.T;
			} else if (isEqual(exp, "NIL")) {
				return SExpression.NIL;
			} else if (isInt(exp)) {
				return intExp(exp);
			} else if (isBound(exp, env.a)) {
				return getVal(exp, env.a);
			} else {
				throw new Exception("Used undefined variable " + exp);
			}
		} else {
			if (isEqual(car(exp), "QUOTE")) {
				return evquote(exp);
			} else if (isEqual(car(exp), "COND")) {
				return evcon(cdr(exp), env);
			} else if (isEqual(car(exp), "DEFUN")) {
				return evdefun(exp, env);
			} else {
				return apply(car(exp), evlist(cdr(exp), env), env);
			}
		}
	}

	public static SExpression evquote(SExpression x) throws Exception {
		validate("NPARAM", cdr(x), 1);
		return cadr(x);
	}

	public static SExpression evcon(SExpression x, Environment env)
			throws Exception {

		validate("EVCON", x);

		validate("NPARAMS", car(x), 2);
		if (eval(caar(x), env) != SExpression.NIL) {
			return eval(cadar(x), env);
		} else {
			return evcon(cdr(x), env);
		}
	}

	public static SExpression evlist(SExpression x, Environment env)
			throws Exception {

		if (isNull(x)) {
			return SExpression.NIL;
		} else {
			return cons(eval(car(x), env), evlist(cdr(x), env));
		}
	}

	public static SExpression evdefun(SExpression x, Environment env)
			throws Exception {

		SExpression fName = cadr(x);
		validate("FNAME", fName);

		SExpression fParams = caddr(x);
		validate("FPARAMS", fParams);

		SExpression fBody = cadr(cddr(x));
		SExpression fEntry = cons(fName, cons(fParams, fBody));
		env.d = cons(fEntry, env.d);
		return fName;
	}

	public static SExpression apply(SExpression f, SExpression x,
			Environment env) throws Exception {

		validate("FAPPLY", f);

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
			if (!isBound(f, env.d)) {
				throw new Exception("Called undefined function " + f);
			}
			Environment e = new Environment();
			e.a = addPairs(car(getVal(f, env.d)), x, env.a);
			e.d = env.d;
			return eval(cdr(getVal(f, env.d)), e);
		}
	}

	public static SExpression addPairs(SExpression params, SExpression args,
			SExpression a) throws Exception {

		validate("NPARAMS", params, countElements(args));

		if (!isNull(params)) {
			SExpression car = cons(car(params), car(args));
			SExpression cdr = addPairs(cdr(params), cdr(args), a);
			return cons(car, cdr);
		}
		return a;
	}

	public static boolean isBound(SExpression exp, SExpression a)
			throws Exception {

		validate("ISBOUND", exp);

		if (isNull(a) || isNull(car(a)) || isNull(caar(a))) {
			return false;
		}

		if (isEqual(caar(a), exp.get_val())) {
			return true;
		} else {
			return isBound(exp, cdr(a));
		}
	}

	public static SExpression getVal(SExpression exp, SExpression a)
			throws Exception {

		validate("GETVAL", exp);

		if (isEqual(caar(a), exp.get_val())) {
			return cdar(a);
		} else {
			return getVal(exp, cdr(a));
		}
	}

	public static SExpression intExp(SExpression exp) {
		return new SExpression(Integer.valueOf(exp.get_val()).toString());
	}

}
