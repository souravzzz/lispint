JAVAC=/usr/local/jdk1.7.0_15/bin/javac
JAVA=/usr/local/jdk1.7.0_15/bin/java
CPFLAG=-cp dep/junit-4.11.jar
JFLAGS=-g $(CPFLAG) -d bin/
SOURCES=src/lispint/*.java

all:
	$(JAVAC) $(JFLAGS) $(SOURCES) ;\
	cd bin ;\
	$(JAVA) lispint/Interpreter

