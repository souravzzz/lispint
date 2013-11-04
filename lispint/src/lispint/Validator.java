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
				throw new Exception("Invalid function name declared");
			}
			break;
		case "FAPPLY":
			if (!isAtom(exp) || isNull(exp) || isInt(exp)) {
				throw new Exception("Invalid function name called");
			}
			break;
		case "EVCON":
			if (isNull(exp)) {
				throw new Exception("Invalid condition");
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
		}
	}
}
