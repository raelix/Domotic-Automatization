all:
	make clean
	gcj -I src -C src/*.java
	gcj -I src -o bin/MainServer --main=MainServer src/*.class
	chmod +x bin/MainServer
	rm -f bin/*.class
	rm -f src/*.class

java:
	make clean
	javac src/*

clean:
	rm -f src/*.class
	rm -f bin/*
