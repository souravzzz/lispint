JAVAC=javac
JAVA=java
CPFLAG=-cp dep/junit-4.11.jar
JFLAGS=-g $(CPFLAG) -d bin/
SOURCES=src/lispint/*.java

all:
	$(JAVAC) $(JFLAGS) $(SOURCES) &&\
	cd bin &&\
	$(JAVA) lispint/Interpreter

clean:
	cd bin &&\
	rm -rf *

