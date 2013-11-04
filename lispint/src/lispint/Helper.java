package lispint;

public class Helper {

	public static SExpression car(SExpression exp) {
		return exp.get_car();
	}

	public static SExpression cdr(SExpression exp) {
		return exp.get_cdr();
	}

	public static SExpression cons(SExpression exp1, SExpression exp2) {
		return new SExpression(exp1, exp2);
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

	public static SExpression caddr(SExpression exp) {
		return car(cdr(cdr(exp)));
	}

	public static boolean isNull(SExpression exp) {
		return (exp.get_car() == null && exp.get_cdr() == null && exp.get_val()
				.equals("NIL"));
	}

	public static boolean isAtom(SExpression exp) {
		return (exp.get_car() == null && exp.get_cdr() == null && exp.get_val() != null);
	}

	public static boolean isInt(SExpression exp) {
		if (!isAtom(exp)) {
			return false;
		}

		try {
			Integer.parseInt(exp.get_val());
			return true;
		} catch (NumberFormatException ne) {
			return false;
		}
	}

	public static boolean isEqual(SExpression exp, String str) {
		if (exp != null && isAtom(exp)) {
			return exp.get_val().equalsIgnoreCase(str);
		}

		return false;
	}

	public static boolean isEqual(SExpression e1, SExpression e2) {
		if (isAtom(e1) && isAtom(e2)) {
			return e1.get_val().equalsIgnoreCase(e2.get_val());
		}

		return false;
	}

	public static String toDot(SExpression exp) {
		if (isAtom(exp)) {
			return exp.get_val();
		} else if (isNull(exp)) {
			return null;
		} else {
			String str = "(";
			if (car(exp) != null) {
				str += toDot(car(exp));
			}
			str += " . ";
			if (cdr(exp) != null) {
				str += toDot(cdr(exp));
			}
			str += ")";
			return str;
		}
	}

	public static String toList(SExpression exp) {
		if (isAtom(exp)) {
			return exp.get_val();
		} else {
			// TODO implement toList properly
		}

		return null;
	}

	public static int countElements(SExpression list) throws Exception {
		if (list == null) {
			throw new Exception("Count called on invalid list");
		} else if (isNull(list)) {
			return 0;
		} else {
			return countElements(cdr(list)) + 1;
		}
	}
}
