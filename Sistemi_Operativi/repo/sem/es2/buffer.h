#ifndef _BUFFER_H_
#define _BUFFER_H_
#include "sem.h"

typedef struct {
  int val;
  int status;
} buffer;

void produce(int semid, buffer *buf1, buffer *buf2, int val);
int consume(int semid, buffer *buf1, buffer *buf2);

#define AVAIABLE 0
#define USING 1
#define NOT_AVAIABLE 2
#define AVAIABLE_SPACE 0
#define MSG_AVAIABLE 1

#endif // !_BUFFER_H_
