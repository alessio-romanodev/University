#include <stdio.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>

#include "req.h"
#include "sem.h"

request_queue * queue_init(){
  
  printf("[%d] Initializing request queue...\n", getpid());

  printf("[%d] Creating shared memory...\n", getpid());
  int shmid = shmget(IPC_PRIVATE, sizeof(request_queue), IPC_CREAT | 0666);
  if (shmid < 0){
    perror("Error creating shared memory!\n");
    exit(1);
  }
  printf("[%d] Attaching shared memory...\n", getpid());
  request_queue * q = (request_queue *)shmat(shmid, NULL, 0); 
  if (q == (void *)-1){
    perror("Error attaching shared memory\n");
    exit(1);
  }
  printf("[%d] Shared memory successfully initilized!\n", getpid());

  printf("[%d] Creating semaphores...\n", getpid());
  int semid = semget(IPC_PRIVATE, 3, IPC_CREAT | 0666);
  if (semid < 0){
    perror("Error creating semaphores!\n");
    exit(1);
  }
  printf("[%d] Initializing semaphores...\n", getpid());
  semctl(semid, MUTEX, SETVAL, 1);
  semctl(semid, FREE_SLOTS, SETVAL, 10);
  semctl(semid, USED_SLOTS, SETVAL, 0);
  printf("[%d] Semaphores successfully initilized!\n", getpid());

  printf("[%d] Initializing request queue fields...\n", getpid());
  q->semid = semid;
  q->shmid = shmid;
  q->head = 0;
  q->tail = 0;

  return q;
}

void get_request(request_queue *q, request *r){
  printf("[%d] Critical section access for the Scheduler\n", getpid());
  sem_wait(q->semid, USED_SLOTS);
  sem_wait(q->semid, MUTEX);

  *r = q->vector[q->head];
  printf("[%d] Reading from position %d of the request vector\n", getpid(), q->head);
  q->head = (q->head + 1) % DIM;
  printf("[%d] Updated queue head to %d\n", getpid(), q->head);

  sem_signal(q->semid, MUTEX);
  sem_signal(q->semid, FREE_SLOTS);
  printf("[%d] Critical section exit for the Scheduler\n", getpid());
}

void add_request(request_queue *q, request *r){
  printf("[%d] Critical section access for user process\n", getpid());
  sem_wait(q->semid, FREE_SLOTS);
  sem_wait(q->semid, MUTEX);
  
  printf("[%d] Writing to position %d of the request vector\n", getpid(), q->tail);
  q->vector[q->tail] = *r;
  q->tail = (q->tail + 1) % DIM;
  printf("[%d] Updated queue tail to %d\n", getpid(), q->tail);
  
  sem_signal(q->semid, MUTEX);
  sem_signal(q->semid, USED_SLOTS);
  printf("[%d] Critical section exit for user process\n", getpid());
}

void delete_queue(request_queue *q){
  printf("[%d] Deleting the request queue...\n", getpid());
  shmctl(q->shmid, IPC_RMID, NULL);
  printf("[%d] Shared memory successfully deleted\n", getpid());
  semctl(q->semid, 0, IPC_RMID);
  printf("[%d] Semaphores vector successfully deleted\n", getpid());
}
