#include "sem.h"
#include <stdlib.h>
#include <stdio.h>
#include <sys/sem.h>

int sem_wait(int semid, int numsem){
  int err;
  struct sembuf sb = {numsem, -1, 0};

  err = semop(semid, &sb, 1);

  if (err < 0){
    perror("SEM WAIT ERROR");
  }

  return err;
}

int sem_signal(int semid, int numsem){
  int err;
  struct sembuf sb = {numsem, 1, 0};

  err = semop(semid, &sb, 1);

  if (err < 0){
    perror("SEM SIGNAL ERROR");
  }

  return err;
}

void sem_init(int semid, int semnum, int val){
  if (semctl(semid, semnum, SETVAL, val) == -1){
    perror("Errore nell'inizializzazione dei semafori!\n");
    exit(1);
  }
}
