package lispint;

import static lispint.Helper.*;

import java.util.Arrays;
import java.util.List;

public class Validator {

	private final static List<String> reserved = Arrays.asList("T", "NIL",
			"QUOTE", "COND", "DEFUN", "CAR", "CDR", "CONS", "ATOM", "NULL",
			"INT", "EQ", "LESS", "GREATER", "PLUS", "MINUS", "TIMES",
			"QUOTIENT", "REMAINDER");

	public static void validate(String rule, SExpression exp) throws Exception {
		switch (rule) {
		case "FNAME":
			if (!isAtom(exp) || isNull(exp) || isInt(exp)
					|| reserved.contains(exp.get_val())) {
				throw new Exception("Declared invalid function name " + exp);
			}
			break;
		case "FAPPLY":
			if (!isAtom(exp) || isNull(exp) || isInt(exp)) {
				throw new Exception("Called invalid function " + exp);
			}
			break;
		case "EVCON":
			if (isNull(exp)) {
				throw new Exception("Declared invalid condition");
			}
			break;
		case "GETVAL":
			if (!isAtom(exp) || isNull(exp) || isInt(exp)) {
				throw new Exception("getVal called with wrong input");
			}
			break;
		case "ISBOUND":
			if (!isAtom(exp) || isNull(exp) || isInt(exp)) {
				throw new Exception("isBound called with wrong input");
			}
			break;
		case "NPARAM":
			throw new Exception("validate called improperly");
		}
	}

	public static void validate(String rule, SExpression exp, int nParams)
			throws Exception {
		if (countElements(exp) != nParams) {
			throw new Exception("Wrong number of arguments passed to function");
		}
	}
}
