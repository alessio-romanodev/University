
all: main reader writer

main: main.o buf.o sem.o
	gcc -o main main.o buf.o sem.o

main.o: main.c buf.h sem.h
	gcc -c -o main.o main.c

buf.o: buf.c buf.h
	gcc -c -o buf.o buf.c

sem.o: sem.c sem.h
	gcc -c -o sem.o sem.c

reader: reader.o buf.o sem.o
	gcc -o reader reader.o buf.o sem.o

writer: writer.o buf.o sem.o
	gcc -o writer writer.o buf.o sem.o

reader.o: reader.c buf.h sem.h
	gcc -c -o reader.o reader.c

writer.o: writer.c buf.h sem.h
	gcc -c -o writer.o writer.c

clean:
	rm -f *.o
	rm -f main
	rm -f reader
	rm -f writer
