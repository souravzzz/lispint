package lispint;

import static org.junit.Assert.*;
import static lispint.CommonTest.*;

import org.junit.Test;

public class EvalTest {

	@Test
	public void testSimple() {
		testEval("1", "1");
		testEval("-20", "-20");
		testEval("t", "T");
		testEval("()", "NIL");
	}

	@Test
	public void testQuote() {
		testEval("(quote (1 2))", "(1 . (2 . NIL))");
		testEval("(quote (1 . 2))", "(1 . 2)");
		testEval("(quote (T NIL))", "(T . (NIL . NIL))");
	}

	@Test
	public void testArithmetic() {
		testEval("(plus 1 2)", "3");
		testEval("(minus -10 +20)", "-30");
		testEval("(times +5 10)", "50");
		testEval("(times +5 10)", "50");
		testEval("(times -111 +9)", "-999");
		testEval("(quotient 10 2)", "5");
		testEval("(quotient -11 +9)", "-1");
	}

	@Test
	public void testWhiteSpace() {
		testEval("\t\t\t   123     ", "123");
		testEval("   (  qUoTieNt   -50   5  )  ", "-10");
		testEval("(  times\n\n \r 3 \n\t  \t5 \n)", "15");
	}

	@Test
	public void testRemainder() {
		testEval("(REMAINDER 5 3)", "2");
		testEval("(REMAINDER -5 3)", "-2");
		testEval("(REMAINDER 5 -3)", "2");
		testEval("(REMAINDER -5 -3)", "-2");
	}

	@Test
	public void testEQ() {
		testEval("(EQ 1 1)", "T");
		testEval("(EQ 1 2)", "NIL");
	}

	@Test
	public void testLess() {
		testEval("(LESS 1 2)", "T");
		testEval("(LESS 2 -2)", "NIL");
	}

	@Test
	public void testGreater() {
		testEval("(GREATER -5 2)", "NIL");
		testEval("(GREATER -2 -5)", "T");
	}

	@Test
	public void testInt() {
		testEval("(INT 0)", "T");
		testEval("(INT 999)", "T");
		testEval("(INT -333)", "T");
	}

	@Test
	public void testAtom() {
		testEval("(ATOM 0)", "T");
		testEval("(ATOM -100)", "T");
		testEval("(ATOM (quote (2 3)))", "NIL");
	}

	@Test
	public void testNull() {
		testEval("(null ())", "T");
		testEval("(null NIL)", "T");
		testEval("(null (quote (1 2 3)))", "NIL");
		testEval("(null (times 3 4))", "NIL");
	}

	@Test
	public void testCar() {
		testEval("(car (cons 1 2))", "1");
		testEval("(car (quote (1 2)))", "1");
	}

	@Test
	public void testCdr() {
		testEval("(cdr (cons 1 2))", "2");
		testEval("(cdr (quote (1 2)))", "(2 . NIL)");
	}

	@Test
	public void testCons() {
		testEval("(cons (cons 1 2) (cons 3 4))", "((1 . 2) . (3 . 4))");
	}

	@Test
	public void testDefun() {
		testEval("(DEFUN DIFF (X Y) \n (COND ((EQ X Y) NIL) \n (T T)))", "DIFF");
	}

	@Test
	public void testMultiEval() {
		String[] inputs = { "1", "2", "(plus 1 2)" };
		String[] outputs = { "1", "2", "3" };
		testMultipleEval(inputs, outputs);
	}

	@Test
	public void testFuncEval() {
		String[] inputs = { "(defun inc (x) (plus x 1))", "(inc 9)", "(inc 99)" };
		String[] outputs = { "INC", "10", "100" };
		testMultipleEval(inputs, outputs);
	}

	@Test
	public void testMultiDefun() {
		String[] inputs = { "(defun inc (x) (plus x 1))",
				"(defun dec (x) (minus x 1))", "(defun nop (x) (inc (dec x)))",
				"(nop 10)", "(inc 5)", "(dec (dec (dec 10)))" };
		String[] outputs = { "INC", "DEC", "NOP", "10", "6", "7" };
		testMultipleEval(inputs, outputs);
	}

	@Test
	public void testDynamicScoping() {
		String[] inputs = { "(defun f (x) (plus x y))", "(defun g (y) (f 10))",
				"(defun h (y) (f 20))", "(g 5)", "(h 5)", "(g (h 5))" };
		String[] outputs = { "F", "G", "H", "15", "25", "35" };
		testMultipleEval(inputs, outputs);
	}

	public static void testEval(String input, String expectedOutput) {
		try {
			Parser p = getParser(input);
			Environment env = new Environment();
			String actual = Evaluator.eval(p.parse(), env).toString();
			assertEquals(expectedOutput, actual);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	public static void testMultipleEval(String[] inputs, String[] outputs) {
		assertTrue(inputs.length == outputs.length);

		Environment env = new Environment();
		for (int i = 0; i < inputs.length; i++) {
			try {
				Parser p = getParser(inputs[i]);
				String actual = Evaluator.eval(p.parse(), env).toString();
				assertEquals(outputs[i], actual);
			} catch (Exception e) {
				e.printStackTrace();
				fail();
			}
		}
	}

}
