package lispint;

import static lispint.Helper.*;

import java.util.Arrays;

public class Validator {

	private final static String[] builtins = { "CAR", "CDR", "CONS", "ATOM",
			"NULL", "INT", "EQ", "LESS", "GREATER", "PLUS", "MINUS", "TIMES",
			"QUOTIENT", "REMAINDER", };

	public static void validate(String rule, SExpression exp) throws Exception {
		switch (rule) {
		case "FNAME":
			if (isAtom(exp) && !isNull(exp) && !isInt(exp)) {
				if (!Arrays.asList(builtins).contains(exp.get_val())) {
					return;
				}
			}
			throw new Exception("Invalid function name declared");
		case "FAPPLY":
			if (!isAtom(exp) || isNull(exp) || isInt(exp)) {
				throw new Exception("Invalid function name called");
			}
		case "EVCON":
			if (isNull(exp)) {
				throw new Exception("Invalid condition");
			}
		case "GETVAL":
			if (!isAtom(exp) || isNull(exp) || isInt(exp)) {
				throw new Exception("getVal called with wrong input");
			}
		case "ISBOUND":
			if (!isAtom(exp) || isNull(exp) || isInt(exp)) {
				throw new Exception("isBound called with wrong input");
			}
		}
	}
}