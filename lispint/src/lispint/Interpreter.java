package lispint;

public class Interpreter {

	public static void main(String[] args) {
		try {
			Evaluator.eval(null, null, null);
			Helper.eq();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
