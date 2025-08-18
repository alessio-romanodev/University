#include <stdio.h>
#include "sem.h"

int wait_sem(int semid, int numsem){
  int err;  
  struct sembuf sb = {numsem, -1, 0};
  
  err = semop(semid, &sb, 1);

  if (err < 0){
    perror("SEM WAIT ERROR");
  }

  return err;
}

int signal_sem(int semid, int numsem){
  int err;
  struct sembuf sb = {numsem, 1, 0};

  err = semop(semid, &sb, 1);

  if(err < 0){
    perror("SEM SIGNAL ERROR");
  }

  return err;
}
