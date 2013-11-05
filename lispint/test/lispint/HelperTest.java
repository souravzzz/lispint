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

	final SExpression x = new SExpression("a");
	final SExpression t = new SExpression("T");

	final SExpression l1 = cons(a, cons(b, cons(c, cons(d, SExpression.NIL))));
	final SExpression l2 = cons(a, SExpression.NIL);
	final SExpression l3 = cons(l1, cons(l2, SExpression.NIL));
	final SExpression l4 = cons(p, cons(q, SExpression.NIL));
	final SExpression l5 = cons(l2, l2);
	final SExpression l6 = cons(SExpression.NIL,
			cons(SExpression.NIL, SExpression.NIL));

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
	public void testIsEqualStr() {
		assertTrue(isEqual(a, "a"));
		assertTrue(isEqual(a, "A"));
		assertTrue(isEqual(i, "2"));

		assertFalse(isEqual(a, "b"));
		assertFalse(isEqual(i, "i"));
		assertFalse(isEqual(p, "A"));
		assertFalse(isEqual(q, "()()"));
	}

	@Test
	public void testIsEqualExp() {
		assertTrue(isEqual(a, a));
		assertTrue(isEqual(a, a));
		assertTrue(isEqual(a, x));
		assertTrue(isEqual(t, SExpression.T));

		assertFalse(isEqual(a, b));
		assertFalse(isEqual(a, m));
		assertFalse(isEqual(p, q));
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

	@Test
	public void testToDot() {
		assertEquals("A", a.toString());
		assertEquals("B", b.toString());
		assertEquals("2", i.toString());

		assertEquals("(A . B)", p.toString());
		assertEquals("((A . B) . (C . D))", r.toString());
		assertEquals("(2 . 3)", m.toString());

		assertEquals("T", SExpression.T.toString());
		assertEquals("NIL", SExpression.NIL.toString());
	}

	@Test
	public void testToString() {
		assertEquals("A", a.toString());
		assertEquals("B", b.toString());
		assertEquals("2", i.toString());

		assertEquals("(A . B)", p.toString());
		assertEquals("((A . B) . (C . D))", r.toString());
		assertEquals("(2 . 3)", m.toString());

		assertEquals("T", SExpression.T.toString());
		assertEquals("NIL", SExpression.NIL.toString());

		assertEquals("(A B C D)", l1.toString());
		assertEquals("(A)", l2.toString());
		assertEquals("((A B C D) (A))", l3.toString());
		assertEquals("((A . B) (C . D))", l4.toString());
		assertEquals("((A) A)", l5.toString());
		assertEquals("(NIL NIL)", l6.toString());
	}

	@Test
	public void testCountElements() {
		try {
			assertEquals(4, countElements(l1));
			assertEquals(1, countElements(l2));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testCountElementsNonList() {
		try {
			countElements(p);
			fail();
		} catch (Exception e) {
		}
		try {
			countElements(a);
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testIsList() {
		assertTrue(isList(l1));
		assertTrue(isList(l2));
		assertTrue(isList(l3));
		assertTrue(isList(l6));

		assertFalse(isList(a));
		assertFalse(isList(p));
		assertFalse(isList(r));
		assertFalse(isList(t));
		assertFalse(isList(SExpression.NIL));
	}
}
