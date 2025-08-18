#include "buffer.h"
#include "sem.h"
#include <unistd.h>
#include <stdio.h>

void produce(int semid, buffer *buf1, buffer *buf2, int val){
  buffer *buf;

  sem_wait(semid, 0);

  if (buf1->status == AVAIABLE){
    printf("[%d] In produzione sul buf1...", getpid());
    buf = buf1;
  } else if (buf2->status == AVAIABLE){
    printf("[%d] In produzione sul buf2...", getpid());
    buf = buf2;
  }

  buf->status = USING;
  sleep(1);

  buf->val = val;
  buf->status = NOT_AVAIABLE;

  printf("[%d] Prodotto il valore %d\n", getpid(), val);
  
  sem_signal(semid, 1);
}

int consume(int semid, buffer *buf1, buffer *buf2){
  int val;
  buffer *buf;

  sem_wait(semid, 1);

  if (buf1->status == NOT_AVAIABLE){
    printf("[%d] Consumazione dal buf1...", getpid());
    buf = buf1;
  } else if (buf2->status == NOT_AVAIABLE){
    printf("[%d] Consumazione dal buf2...", getpid());
    buf = buf2;
  }

  buf->status = USING;
  sleep(5);

  val = buf->val;
  buf->status = AVAIABLE;

  printf("[%d] Consumato il valore: %d", getpid(), val);

  sem_signal(semid, 0);

  return val;
}
