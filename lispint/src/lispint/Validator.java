package lispint;

import static lispint.Helper.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator {

	private final static List<String> reservedFname = Arrays.asList("T", "NIL",
			"QUOTE", "COND", "DEFUN", "CAR", "CDR", "CONS", "ATOM", "NULL",
			"INT", "EQ", "LESS", "GREATER", "PLUS", "MINUS", "TIMES",
			"QUOTIENT", "REMAINDER");

	private final static List<String> reservedPname = Arrays.asList("T", "NIL");

	public static void validate(String rule, SExpression exp) throws Exception {
		switch (rule) {
		case "FNAME":
			if (!isAtom(exp) || isNull(exp) || isInt(exp)
					|| reservedFname.contains(exp.get_val())) {
				throw new Exception("Declared invalid function name " + exp);
			}
			break;
		case "FAPPLY":
			if (!isAtom(exp) || isNull(exp) || isInt(exp)) {
				throw new Exception("Called invalid function " + exp);
			}
			break;
		case "FPARAMS":
			if (!isEqual(exp, SExpression.NIL)) {
				List<String> pNames = new ArrayList<String>();
				while (!isEqual(exp, SExpression.NIL)) {
					SExpression paramName = car(exp);
					if (!isAtom(paramName) || isNull(paramName)
							|| isInt(paramName)
							|| reservedPname.contains(paramName.get_val())) {
						throw new Exception("Declared invalid parameter name "
								+ paramName);
					} else if (pNames.contains(paramName.get_val())) {
						throw new Exception("Declared duplicate parameter "
								+ paramName.get_val());
					}
					pNames.add(paramName.get_val());
					exp = cdr(exp);
				}
			}
			break;
		case "FBODY":
			if (exp == null) {
				throw new Exception("Declared invalid function body");
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
		case "CAR":
			if (exp == null || car(exp) == null || caar(exp) == null) {
				throw new Exception("CAR called with wrong input");
			}
			break;
		case "CDR":
			if (exp == null || car(exp) == null || cdar(exp) == null) {
				throw new Exception("CDR called with wrong input");
			}
			break;
		case "EQ":
			if (!isAtom(car(exp)) || !isAtom(cadr(exp))) {
				throw new Exception("EQ called with wrong input");
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
