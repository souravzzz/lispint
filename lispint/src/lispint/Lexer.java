package lispint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Lexer {

	private StreamTokenizer _tokenizer;

	public Lexer(InputStream in) {
		_tokenizer = new StreamTokenizer(new BufferedReader(
				new InputStreamReader(in)));

		_tokenizer.resetSyntax();
		_tokenizer.whitespaceChars(0, ' ');
		_tokenizer.wordChars('a', 'z');
		_tokenizer.wordChars('A', 'Z');
		_tokenizer.wordChars('0', '9');
		_tokenizer.wordChars('+', '+');
		_tokenizer.wordChars('-', '-');
		_tokenizer.ordinaryChar('.');
		_tokenizer.ordinaryChar('(');
		_tokenizer.ordinaryChar(')');
	}

	public boolean hasMoreTokens() {
		try {
			_tokenizer.nextToken();
			if (_tokenizer.ttype != StreamTokenizer.TT_EOF) {
				_tokenizer.pushBack();
				return true;
			}
		} catch (IOException e) {
		}
		return false;
	}

	public Token getNextToken() {
		try {
			_tokenizer.nextToken();
			return Token.get(_tokenizer.ttype, _tokenizer.sval);
		} catch (IOException e) {
			return null;
		}
	}

	public void unreadLastToken() {
		_tokenizer.pushBack();
	}

}
