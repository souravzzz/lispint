package lispint;

import static lispint.CommonTest.getParser;
import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {

	@Test
	public void testParseAtom1() {
		testParser("abc", "ABC");
	}

	@Test
	public void testParseAtom2() {
		testParser("222", "222");
	}

	@Test
	public void testParseAtom3() {
		testParser("+333", "+333");
	}

	@Test
	public void testParseAtom4() {
		testParser("-4444", "-4444");
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
	// TODO might need fix
	public void testParseDot3() {
		testParser("(4.5)");
	}

	@Test
	public void testParseQuote() {
		testParser("(QUOTE (10 20 30 40 50))");
	}

	@Test
	public void testParseDefun() {
		testParser("(defun double (x)\n (TIMES 2 x))");
	}

	// TODO add asserts when toList() is done
	private static void testParser(String input) {
		try {
			SExpression exp = getParser(input).parse();
			System.out.println(exp);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	// TODO add asserts when toList() is done
	private static void testParser(String input, String output) {
		try {
			SExpression exp = getParser(input).parse();
			assertEquals(output, exp.toString());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

}
