package lispint;

import static java.util.Arrays.asList;
import static lispint.CommonTest.getLexer;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LexerTest {

	@Test
	public void testEmpty() {
		String input = "";
		testLexer(input, asList(""));
	}

	@Test
	public void testEmptyParens() {
		String input = "()";
		testLexer(input, asList("(", ")"));
	}

	@Test
	public void testNil() {
		String input = "nil";
		testLexer(input, asList("NIL"));
	}

	@Test
	public void testAtom() {
		String input = "a";
		testLexer(input, asList("A"));
	}

	@Test
	public void testSimpleExp() {
		String input = "(a b)";
		testLexer(input, asList("(", "A", "B", ")"));
	}

	@Test
	public void testSimpleList() {
		String input = "(a b c d)";
		testLexer(input, asList("(", "A", "B", "C", "D", ")"));
	}

	@Test
	public void testMixedList() {
		String input = "(a -10 b 20 c +30)";
		testLexer(input, asList("(", "A", "-10", "B", "20", "C", "+30", ")"));
	}

	@Test
	public void test1() {
		String input = "(times (plus 1 2) (minus 10 20))";
		testLexer(
				input,
				asList("(", "TIMES", "(", "PLUS", "1", "2", ")", "(", "MINUS",
						"10", "20", ")", ")"));
	}

	@Test
	public void test2() {
		String input = "(quote (100 +200 -300))";
		testLexer(input,
				asList("(", "QUOTE", "(", "100", "+200", "-300", ")", ")"));
	}

	@Test
	public void test3() {
		String input = "(defun double (x)\n (TIMES 2 x))";
		testLexer(
				input,
				asList("(", "DEFUN", "DOUBLE", "(", "X", ")", "(", "TIMES",
						"2", "X", ")", ")"));
	}

	private static void testLexer(String input, List<String> expected) {
		Lexer l = getLexer(input);
		List<String> actual = new ArrayList<String>();
		while (l.hasMoreTokens()) {
			Token token = l.getNextToken();
			if (token != null) {
				actual.add(token.toString());
			}
		}
		assertEquals(expected.toString(), actual.toString());
	}

}
