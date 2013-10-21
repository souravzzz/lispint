package lispint;

import static org.junit.Assert.*;

import org.junit.Test;

public class SExpressionTest {

	@Test
	public void testStringBased() {
		SExpression a = new SExpression("a");
		assertTrue(a.get_car() == null);
		assertTrue(a.get_cdr() == null);
		assertTrue(a.get_val().equals("A"));
		
		SExpression b = new SExpression("xYz");
		assertTrue(b.get_car() == null);
		assertTrue(b.get_cdr() == null);
		assertTrue(b.get_val().equals("XYZ"));
	}

}
