#include "sem.h"
#include <algorithm>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/sem.h>
#include <sys/shm.h>
#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>

#define SEM_NUM 2

#include "buffer.h"

int main(){
  int buf1_id;
  int buf2_id;
  buffer *buf1;
  buffer *buf2;
  int semid;

  // Creazione risorse IPC
  buf1_id = shmget(IPC_PRIVATE, sizeof(buffer), IPC_CREAT | 0666);
  buf2_id = shmget(IPC_PRIVATE, sizeof(buffer), IPC_CREAT | 0666);
  semid = semget(IPC_PRIVATE, SEM_NUM, IPC_CREAT | 0666);

  if (buf1_id == -1 || buf2_id == -1 || semid == -1){
    perror("Errore nella creazione delle risorse IPC\n");
    exit(1);
  }

  // Configurazione risorse IPC
  buf1 = (buffer *)shmat(buf1_id, NULL, 0);
  buf2 = (buffer *)shmat(buf2_id, NULL, 0);

  if (buf1 == (void *)-1 || buf2 == (void*)-1){
    perror("Errore nell'attach SHM dei buffer");
    exit(1);
  }
  
  buf1->status = AVAIABLE;  
  buf2->status = AVAIABLE;

  sem_init(semid, 0, 1);
  sem_init(semid, 1, 0);

  






















}
