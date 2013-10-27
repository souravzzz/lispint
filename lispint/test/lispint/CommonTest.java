package lispint;

import java.io.ByteArrayInputStream;

public class CommonTest {

	public static Parser getParser(String str) {
		return new Parser(getLexer(str));
	}

	public static Lexer getLexer(String str) {
		return new Lexer(getInputStream(str));
	}

	public static ByteArrayInputStream getInputStream(String str) {
		return new ByteArrayInputStream(str.getBytes());
	}
}
