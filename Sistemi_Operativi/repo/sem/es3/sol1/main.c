#include <stdio.h>
#include <stdlib.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include "buf.h"

#define MAX_READERS 3
#define MAX_WRITERS 1

int main(){
  printf("[%d] Creazione shared memory\n", getpid());

  key_t shm_key = ftok(".", 'M');
  int shm_id = shmget(shm_key, sizeof(buffer), IPC_CREAT | 0666);

  if (shm_id < 0){
    perror("Errore nella creazione della shared memory\n");
    exit(1);
  }

  buffer * b = (buffer *)shmat(shm_id, NULL, 0);
  
  if (b == (void*)-1){
    perror("Errore nell'attach della shared memory");
    exit(1);
  }

  b->val1 = 0;
  b->val2 = 0;
  b->num_lettori = 0;

  printf("[%d] Creazione semafori\n", getpid());

  key_t sem_key = ftok(".", 'S'); 
  int semid = semget(sem_key, 2, IPC_CREAT | 0666);
  
  if (semid < 0){
    perror("Errore nella creazione dei semafori\n");
    exit(1);
  }
  
  semctl(semid, MUTEXL, SETVAL, 1);
  semctl(semid, SYNCH, SETVAL, 1);

  printf("[%d] Creazione processo scrittore\n", getpid());

  pid_t pid_w = fork();
  if (pid_w == 0){
    execl("./writer", "writer", NULL);
    perror("Errore nella exec del processo scrittore\n");
    exit(1);
  }
  
  
  printf("[%d] Creazione processi lettori\n", getpid());
  
  for (int i = 0; i<MAX_READERS; i++){
    printf("[%d] Creazione processo lettore numero %d\n", getpid(), i);
    pid_t pid_r = fork();
    if (pid_r == 0){
      execl("./reader", "reader", NULL);
      perror("Errore nella exec del processo lettore\n");
      exit(1);
    }
  }

  for (int i = 0; i< MAX_READERS + MAX_WRITERS; i++){
    wait(NULL);
  }

  printf("[%d] Deallocazione risorse\n", getpid());

  shmctl(shm_id, IPC_RMID, NULL);
  semctl(semid, 0, IPC_RMID);
}
