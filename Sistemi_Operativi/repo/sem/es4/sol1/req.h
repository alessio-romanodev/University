#ifndef _REQ_H_
#define _REQ_H_
#define DIM 10

#include "sem.h"

typedef struct {
  int position;
  pid_t pid;
} request;

typedef struct {
  int semid;
  int shmid;
  request vector[DIM];
  int head;
  int tail;
} request_queue;

#define MUTEX 0
#define FREE_SLOTS 1
#define USED_SLOTS 2


request_queue * queue_init();
void get_request(request_queue * q, request * r);
void add_request(request_queue * q, request * r);
void delete_queue(request_queue * q);


#endif // !_REQ_H_
