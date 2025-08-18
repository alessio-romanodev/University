#include <stdio.h>
#include <stdlib.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/sem.h>
#include <sys/wait.h>
#include <unistd.h>
#include <time.h>

#define NUM_PROCESSES 10
#define VECTOR_SIZE 10000
#define CHUNK_SIZE (VECTOR_SIZE / NUM_PROCESSES)
#define MAX_RANDNUM 10000

void sem_wait(int semid){
  struct sembuf sb = {0, -1, 0};
  semop(semid, &sb, 1);
}

void sem_signal(int semid){
  struct sembuf sb = {0, 1, 0};
  semop(semid, &sb, 1);
}

int find_min(int *array, int start, int end){
    int min = array[start];
    for (int i = start + 1; i < end; i++) {
        if (array[i] < min) {
            min = array[i];
        }
    }
    return min;}

int main(){

  // Inizializzo Il vettore contenente 10000 numeri, Il buffer dei minimi e il semaforo per la mutua esclusione
  int shm_vectorid = shmget(IPC_PRIVATE, VECTOR_SIZE * sizeof(int), IPC_CREAT | 0666);
  int shm_bufferid = shmget(IPC_PRIVATE, sizeof(int), IPC_CREAT | 0666);
  int semid = semget(IPC_PRIVATE, 1, IPC_CREAT | 0666);

  // Controllo che le risorse siano state create correttamente
  if (shm_vectorid == -1 || shm_bufferid == -1 || semid == -1){
    perror("Errore nella creazione delle risorse IPC");
    exit(1);
  }

  // Imposto il semaforo come libero
  semctl(semid, 0, SETVAL, 1);

  // Collego alla memoria i segmenti appena creati
  int *vector = (int *)shmat(shm_vectorid, NULL, 0);
  int *buffer = (int *)shmat(shm_bufferid, NULL, 0);

  // Genero numeri casuali da 0 a 10000 e popolo vector
  srand(time(NULL));
  for (int i = 0; i < VECTOR_SIZE; i++){
    vector[i] = rand() % MAX_RANDNUM;
  }
  *buffer = MAX_RANDNUM; 


  for (int i = 0; i < NUM_PROCESSES; i++){
    pid_t pid = fork();
    if (pid == 0) {
      int start = i * CHUNK_SIZE;
      int end = start + CHUNK_SIZE;
      int local_min = find_min(vector, start, end);
      
      sem_wait(semid);
      if (local_min < *buffer){
        *buffer = local_min;
      }
      sem_signal(semid);
  
      shmdt(buffer);
      shmdt(vector);
    }

    for (int i = 0; i < NUM_PROCESSES; i++){
      wait(NULL);
    }
  
    printf("Il minimo trovato è: %d\n", *buffer);

    shmdt(vector);
    shmdt(buffer);

    shmctl(shm_vectorid, IPC_RMID, NULL);
    shmctl(shm_bufferid, IPC_RMID, NULL);
    semctl(semid, 0, IPC_RMID);

    return 0;
  }
}
