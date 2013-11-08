package lispint;

import static lispint.CommonTest.getParser;
import static org.junit.Assert.*;

import org.junit.Test;

public class InvalidTests {

	@Test
	public void testInvalidSimple() {
		testEvalFail("((A B) C)");
		testEvalFail("(plus (plus a 6) (plus 5 6))");
		testEvalFail("(null (()))");
		testEvalFail("(atom 123xyz)");
		testEvalFail("(atom xyz123)");
		testEvalFail("(eq 5)");
		testEvalFail("(eq 5 6 7)");
		testEvalFail("plus");
		testEvalFail("(minus)");
		testEvalFail("(eq (quote (1 2 3)) 4)");
		testEvalFail("(cons (5) 6)");
		testEvalFail("(car (quote 5))");
		testEvalFail("(cdr (quote ()))");
	}

	@Test
	public void testInvalidDefun() {
		testEvalFail("(defun)");
		testEvalFail("(defun f)");
		testEvalFail("(defun f0 x)");
		testEvalFail("(defun f1 x (plus x 2))");
		testEvalFail("(defun f2 (nil) (plus x y))");
		testEvalFail("(defun f3 (x x) (plus x 2))");
		testEvalFail("(defun f4 (1 2) (plus 1 2))");
		testEvalFail("(defun f5 (x y x) (plus x y))");
		testEvalFail("(defun f6 (x t) (plus x t))");
		testEvalFail("(defun (x y) (plus x y))");
		testEvalFail("(defun cond (x) (plus x y))");
		testEvalFail("");
	}

	@Test
	public void testInvalidCond() {
		testEvalFail("(cond ((eq 2 3) t))");
		testEvalFail("(cond ((eq 2 3) t) (t a))");
		testEvalFail("(cond ((eq 2 3) t) (t 23 23))");
		testEvalFail("(cond ((eq 2 2) t t) (t nil))");
	}

	public static void testEvalFail(String input) {
		try {
			Parser p = getParser(input);
			Environment env = new Environment();
			String output = Evaluator.eval(p.parse(), env).toString();
			System.out.println(output);
			fail();
		} catch (Exception e) {
			String detail = e.getMessage();
			if (detail == null) {
				detail = "Invalid input";
			}
			// System.out.println("ERROR " + detail);
		}
	}

}
