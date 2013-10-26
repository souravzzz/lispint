package lispint;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testParseBlank() {
		testParser("");
	}

	@Test
	public void testParseAtom1() {
		testParser("abc");
	}

	@Test
	public void testParseAtom2() {
		testParser("200");
	}

	@Test
	public void testParseList1() {
		testParser("(a B c D)");
	}

	@Test
	public void testParseProg1() {
		testParser("(PLUS (10 20))");
	}

	@Test
	public void testParseDot1() {
		testParser("(a.B)");
	}

	@Test
	public void testParseDot2() {
		testParser("(100	. 200)");
	}

	@Test
	public void testParseQuote() {
		testParser("(QUOTE (10 20 30 40 50))");
	}

	@Test
	public void testParseDefun() {
		testParser("(defun double (x)\n (TIMES 2 x))");
	}

	private static void testParser(String input) {
		try {
			SExpression exp = getParser(input).parse();
			System.out.println(exp);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	private static Parser getParser(String str) {
		return new Parser(getLexer(str));
	}

	private static Lexer getLexer(String str) {
		return new Lexer(getInputStream(str));
	}

	private static ByteArrayInputStream getInputStream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
}
