#ifndef _BUFFER_H_
#define _BUFFER_H_

typedef struct {
  int val1;
  int val2;
  int num_lettori;
} buffer;

#define MUTEXL 0
#define SYNCH 1

void read_buffer(buffer *b, int semid, int *val1, int *val2);
void write_buffer(buffer *b, int semid, int val1, int val2);

#endif // !_BUFFER_H_
