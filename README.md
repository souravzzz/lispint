LISP INTERPRETER
================

This is an interpreter for a subset of common lisp defined in a programming language course.

Warning: It does not follow any proper object oriented architecture and instead settles for bunch of static methods residing in classes with super-descriptive names like `Helper`.
Example: the `toString()` method of `SExpression` actually just calls a static method from Helper.
At best, `SExpression` class is nothing better than a `struct` that holds some data.
However, this deviation was intentional and was chosen to make the program look and perform very similar to the interpreter written in lisp.
I really did not want to call `expression.car()` instead of `car(expression)`.

In hindsight, I did avoid it, but I am no longer sure if that was a good idea.
