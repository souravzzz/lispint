Sourav Chakraborty

The interpreter has 3 major stages: lexical analysis, parsing, and evaluation. There are 3 corresponding classes: Lexer, Parser and Evaluator. The Lexer uses Java StreamTokenizer and produces different Tokens like DOT, ATOM, LEFTPAREN, RIGHTPAREN. The parser consumes a stream of tokens available from the lexer and builds a S-Expression tree. The SExpression class represents an expression - wither an atom or a compound expression. The Helper class implements most of the methods related to using SExpression like car(), cdr(), etc. This deviation from OO principles was intentional, it was done so that I can call car(cdr(exp)) instead of exp.cdr().car(). The helper also implements the toString() method for SExpression, which follows the given algorithm for printing lists. 

The Evaluator class very closely follows the implementation the eval() method given in class. It also implements the related methods like evlist(), evcon(), isBound(), getval() etc. I have also refactored some code into their own methods like evdefun() and evquote(). It also All these methods are static and takes the association list and the definition list as input. Since eval() can't suitably modify the d list because of java's lack of pointers, these 2 lists are encapsulated in an Enviornment class. The Interpreter class contains a simple main() method that keeps calling parse() and eval() until it reaches the end of input. In case of a parsing or evaluation error, it prints out an error message and tries to parse and eval the next input. The predefined methods like CAR, PLUS, etc. are defined in the Builtins class. The Validator class contains some rules which are used by different methods to validate their inputs.

For compiling the code, just run make.
For running, use Runfile < inputfile > outputfile or java lispint/Interpreter < inputfile > outputfile.
You can run it in an interactive read-eval-print mode by just running it without any other parameter. Note that it prints no prompt, just type in your input.
Please do not change the directory stucture as the files are part of a package and needs to be in a properly named directory.

