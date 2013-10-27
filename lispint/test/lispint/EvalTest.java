package lispint;

import static org.junit.Assert.*;
import static lispint.CommonTest.*;

import org.junit.Test;

public class EvalTest {

	@Test
	public void testSimple() {
		testEvalPass("1", "1");
		testEvalPass("-20", "-20");
		testEvalPass("t", "T");
		testEvalPass("()", "NIL");
	}

	@Test
	public void testQuote() {
		testEvalPass("(quote (1 2))", "(1 . (2 . NIL))");
		testEvalPass("(quote (1 . 2))", "(1 . 2)");
		testEvalPass("(quote (T NIL))", "(T . (NIL . NIL))");
	}

	@Test
	public void testArithmetic() {
		testEvalPass("(plus 1 2)", "3");
		testEvalPass("(minus -10 +20)", "-30");
		testEvalPass("(times +5 10)", "50");
		testEvalPass("(times +5 10)", "50");
		testEvalPass("(times -111 +9)", "-999");
		testEvalPass("(quotient 10 2)", "5");
		testEvalPass("(quotient -11 +9)", "-1");
	}

	@Test
	public void testWhiteSpace() {
		testEvalPass("\t\t\t   123     ", "123");
		testEvalPass("   (  qUoTieNt   -50   5  )  ", "-10");
		testEvalPass("(  times\n\n \r 3 \n\t  \t5 \n)", "15");
	}

	@Test
	public void testRemainder() {
		testEvalPass("(REMAINDER 5 3)", "2");
		testEvalPass("(REMAINDER -5 3)", "-2");
		testEvalPass("(REMAINDER 5 -3)", "2");
		testEvalPass("(REMAINDER -5 -3)", "-2");
	}

	@Test
	public void testEQ() {
		testEvalPass("(EQ 1 1)", "T");
		testEvalPass("(EQ 1 2)", "NIL");
	}

	@Test
	public void testLess() {
		testEvalPass("(LESS 1 2)", "T");
		testEvalPass("(LESS 2 -2)", "NIL");
	}

	@Test
	public void testGreater() {
		testEvalPass("(GREATER -5 2)", "NIL");
		testEvalPass("(GREATER -2 -5)", "T");
	}

	@Test
	public void testInt() {
		testEvalPass("(INT 0)", "T");
		testEvalPass("(INT 999)", "T");
		testEvalPass("(INT -333)", "T");
	}

	@Test
	public void testAtom() {
		testEvalPass("(ATOM 0)", "T");
		testEvalPass("(ATOM -100)", "T");
		testEvalPass("(ATOM (quote (2 3)))", "NIL");
	}

	@Test
	public void testNull() {
		testEvalPass("(null ())", "T");
		testEvalPass("(null NIL)", "T");
		testEvalPass("(null (quote (1 2 3)))", "NIL");
		testEvalPass("(null (times 3 4))", "NIL");
	}

	@Test
	public void testCar() {
		testEvalPass("(car (cons 1 2))", "1");
		testEvalPass("(car (quote (1 2)))", "1");
	}

	@Test
	public void testCdr() {
		testEvalPass("(cdr (cons 1 2))", "2");
		testEvalPass("(cdr (quote (1 2)))", "(2 . NIL)");
	}

	@Test
	public void testCons() {
		testEvalPass("(cons (cons 1 2) (cons 3 4))", "((1 . 2) . (3 . 4))");
	}

	public static void testEvalPass(String input, String expectedOutput) {
		try {
			Parser p = getParser(input);
			String actual = Evaluator.eval(p.parse(), SExpression.NIL,
					SExpression.NIL).toString();
			assertEquals(expectedOutput, actual);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
