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

void sem_wait(int semid) {
    struct sembuf sb = {0, -1, 0};
    semop(semid, &sb, 1);
}

void sem_signal(int semid) {
    struct sembuf sb = {0, 1, 0};
    semop(semid, &sb, 1);
}

int find_min(int *array, int start, int end) {
    int min = array[start];
    for (int i = start + 1; i < end; i++) {
        if (array[i] < min) {
            min = array[i];
        }
    }
    return min;
}

int main() {
    // Inizializzazione delle risorse IPC
    int shm_vectorid = shmget(IPC_PRIVATE, VECTOR_SIZE * sizeof(int), IPC_CREAT | 0666);
    int shm_bufferid = shmget(IPC_PRIVATE, sizeof(int), IPC_CREAT | 0666);
    int shm_minid = shmget(IPC_PRIVATE, sizeof(int), IPC_CREAT | 0666);

    if (shm_vectorid == -1 || shm_bufferid == -1 || shm_minid == -1) {
        perror("Errore nella creazione delle risorse IPC (Mem)");
        exit(1);
    }

    int sem_full = semget(IPC_PRIVATE, 1, IPC_CREAT | 0666);
    int sem_empty = semget(IPC_PRIVATE, 1, IPC_CREAT | 0666);
    int sem_mutex = semget(IPC_PRIVATE, 1, IPC_CREAT | 0666);

    if (sem_full == -1 || sem_empty == -1 || sem_mutex == -1) {
        perror("Errore nella creazione delle risorse IPC (Sem)");
        exit(1);
    }

    // Collego alla memoria i segmenti appena creati
    int *vector = (int *)shmat(shm_vectorid, NULL, 0);
    int *buffer = (int *)shmat(shm_bufferid, NULL, 0);
    int *min_res = (int *)shmat(shm_minid, NULL, 0);

    // Inizializzazione dei semafori
    semctl(sem_full, 0, SETVAL, 0);   // Buffer inizialmente vuoto
    semctl(sem_empty, 0, SETVAL, 1);  // Buffer disponibile
    semctl(sem_mutex, 0, SETVAL, 1);  // Accesso mutuo esclusivo

    // Inizializzo il vettore e il minimo globale
    srand(time(NULL));
    for (int i = 0; i < VECTOR_SIZE; i++) {
        vector[i] = rand() % MAX_RANDNUM;
    }
    *min_res = MAX_RANDNUM;

    // Creazione dei processi figli (produttori)
    for (int i = 0; i < NUM_PROCESSES; i++) {
        pid_t pid = fork();
        if (pid == 0) {  // Codice del figlio
            int start = i * CHUNK_SIZE;
            int end = start + CHUNK_SIZE;
            int local_min = find_min(vector, start, end);
            printf("Il minimo locale per il processo %d è: %d\n", i, local_min);

            // Produzione: scrive il minimo nel buffer
            sem_wait(sem_empty);
            sem_wait(sem_mutex);

            *buffer = local_min;

            sem_signal(sem_mutex);
            sem_signal(sem_full);

            // Detach memoria e termina il figlio
            shmdt(vector);
            shmdt(buffer);
            shmdt(min_res);
            exit(0);
        }
    }

    // Processo padre (consumatore)
    for (int i = 0; i < NUM_PROCESSES; i++) {
        sem_wait(sem_full);
        sem_wait(sem_mutex);

        int value = *buffer;
        printf("Il padre consuma: %d\n", value);
        if (value < *min_res) {
            *min_res = value;
        }

        sem_signal(sem_mutex);
        sem_signal(sem_empty);
    }

    // Attesa della terminazione dei processi figli
    for (int i = 0; i < NUM_PROCESSES; i++) {
        wait(NULL);
    }

    // Stampa finale del minimo globale trovato
    printf("Il minimo globale trovato è: %d\n", *min_res);

    // Detach memoria condivisa
    shmdt(vector);
    shmdt(buffer);
    shmdt(min_res);

    // Rimozione delle risorse IPC
    shmctl(shm_vectorid, IPC_RMID, NULL);
    shmctl(shm_bufferid, IPC_RMID, NULL);
    shmctl(shm_minid, IPC_RMID, NULL);
    semctl(sem_empty, 0, IPC_RMID);
    semctl(sem_full, 0, IPC_RMID);
    semctl(sem_mutex, 0, IPC_RMID);

    return 0;
}

