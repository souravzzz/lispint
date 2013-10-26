package lispint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Lexer {

	StreamTokenizer _tokenizer;

	public Lexer(InputStream in) {
		_tokenizer = new StreamTokenizer(new BufferedReader(
				new InputStreamReader(in)));

		_tokenizer.resetSyntax();
		_tokenizer.whitespaceChars(0, ' ');
		_tokenizer.wordChars('a', 'z');
		_tokenizer.wordChars('A', 'Z');
		_tokenizer.wordChars('0', '9');
		_tokenizer.ordinaryChar('.');
		_tokenizer.ordinaryChar('(');
		_tokenizer.ordinaryChar(')');
	}

	public boolean hasNext() {
		try {
			_tokenizer.nextToken();
		} catch (IOException e) {
			return false;
		}

		if (_tokenizer.ttype == StreamTokenizer.TT_EOF) {
			return false;
		}

		_tokenizer.pushBack();
		return true;
	}

	public Token next() {
		try {
			_tokenizer.nextToken();
		} catch (IOException e) {
			return null;
		}

		return Token.get(_tokenizer.ttype, _tokenizer.sval);
	}

}
