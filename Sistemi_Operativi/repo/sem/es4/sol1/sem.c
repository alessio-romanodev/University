#include <stdio.h>
#include <sys/sem.h>
#include "sem.h"

int sem_wait(int semid, int numsem){
  int err;
  struct sembuf sb = {numsem, -1, 0};

  err = semop(semid, &sb, 1);
  if (err < 0){
    perror("Sem Signal error!");
  }

  return err;
}

int sem_signal(int semid, int numsem){
  int err;
  struct sembuf sb = {numsem, 1, 0};
  
  err = semop(semid, &sb, 1); 
  if (err < 0){
    perror("Sem Wait error!");
  }

  return err;
}

