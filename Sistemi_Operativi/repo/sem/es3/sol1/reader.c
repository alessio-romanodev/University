#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/shm.h>
#include "buf.h"
#include "sem.h"

int main() {
    printf("[%d] Avvio lettore\n", getpid());

    key_t chiave_shm = ftok(".", 'M'); // Recupero della chiave della memoria condivisa
    int shm_id = shmget(chiave_shm, sizeof(buffer), 0666); // Acquisizione memoria condivisa
    if (shm_id < 0) {
        perror("Errore acquisizione shared memory");
        exit(1);
    }

    buffer *b = (buffer *)shmat(shm_id, NULL, 0); // Attacco alla memoria condivisa
    if (b == (void*)-1) {
        perror("Errore attach shared memory");
        exit(1);
    }

    key_t sem_chiave = ftok(".", 'S'); // Recupero della chiave del semaforo
    int sem_id = semget(sem_chiave, 2, 0666); // Acquisizione semafori
    if (sem_id < 0) {
        perror("Errore acquisizione semafori");
        exit(1);
    }

    for (int i = 0; i < 5; i++) {
        sleep(1); 

        int val_1, val_2;
        read_buffer(b, sem_id, &val_1, &val_2);
    }

    return 0;
}
