main: main.o req.o sem.o
		gcc -o main main.o req.o sem.o

main.o: main.c req.h
		gcc -c -o main.o main.c

req.o: req.c req.h
		gcc -c -o req.o req.c

sem.o: sem.c sem.h
		gcc -c -o sem.o sem.c

clean:
		rm -f *.o
		rm -f main
