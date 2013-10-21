package lispint;

import static org.junit.Assert.*;
import static lispint.Helper.*;

import org.junit.Test;

public class HelperTest {

	final SExpression a = new SExpression("a");
	final SExpression b = new SExpression("b");
	final SExpression c = new SExpression("c");
	final SExpression d = new SExpression("d");

	final SExpression p = new SExpression(a, b);
	final SExpression q = new SExpression(c, d);
	final SExpression r = new SExpression(p, q);
	final SExpression s = new SExpression(r, q);

	final SExpression i = new SExpression("2");
	final SExpression j = new SExpression("3");
	final SExpression m = new SExpression(i, j);

	@Test
	public void testCar() {
		assertTrue(car(p).equals(a));
		assertTrue(car(q).equals(c));
		assertTrue(car(m).equals(i));
	}

	@Test
	public void testCdr() {
		assertTrue(cdr(p).equals(b));
		assertTrue(cdr(q).equals(d));
		assertTrue(cdr(m).equals(j));
	}

	@Test
	public void testCaar() {
		assertTrue(caar(r).equals(a));
	}

	@Test
	public void testCadr() {
		assertTrue(cadr(r).equals(c));
	}

	@Test
	public void testCdar() {
		assertTrue(cdar(r).equals(b));
	}

	@Test
	public void testCddr() {
		assertTrue(cddr(r).equals(d));
	}

	@Test
	public void testCadar() {
		assertTrue(cadar(s).equals(c));
	}

	@Test
	public void testIsNull() {
		assertFalse(isNull(a));
		assertFalse(isNull(b));
		assertFalse(isNull(p));
		assertFalse(isNull(r));
		assertFalse(isNull(i));
		assertFalse(isNull(m));
	}

	@Test
	public void testIsAtom() {
		assertTrue(isAtom(a));
		assertTrue(isAtom(b));
		assertTrue(isAtom(i));
		assertTrue(isAtom(j));

		assertFalse(isAtom(p));
		assertFalse(isAtom(q));
		assertFalse(isAtom(r));
		assertFalse(isAtom(m));
	}

	@Test
	public void testIsInt() {
		assertTrue(isInt(i));
		assertTrue(isInt(j));

		assertFalse(isInt(a));
		assertFalse(isInt(b));
		assertFalse(isInt(p));
		assertFalse(isInt(r));
	}

	@Test
	public void testIsEqual() {
		assertTrue(isEqual(a, "a"));
		assertTrue(isEqual(a, "A"));

		assertFalse(isEqual(a, "i"));
		assertFalse(isEqual(p, "A"));
		assertFalse(isEqual(q, "q"));
	}

	@Test
	public void testCons() {
		assertTrue(car(cons(a, b)).equals(a));
		assertTrue(cdr(cons(a, b)).equals(b));

		assertTrue(caar(cons(cons(a, b), cons(i, j))).equals(a));
		assertTrue(cdar(cons(cons(a, b), cons(i, j))).equals(b));
		assertTrue(cadr(cons(cons(a, b), cons(i, j))).equals(i));
		assertTrue(cddr(cons(cons(a, b), cons(i, j))).equals(j));
	}
}
