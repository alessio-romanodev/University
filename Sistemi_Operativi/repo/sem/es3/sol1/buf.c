#include <unistd.h>
#include <stdio.h>
#include "buf.h"
#include "sem.h"

void read_buffer(buffer *b, int semid, int *val1, int *val2){
  printf("[%d] Inizio lettura\n", getpid());
  
  wait_sem(semid, MUTEXL); // Entro in sezione critica per i lettori
  b->num_lettori++;
  if (b->num_lettori == 1) {
    wait_sem(semid, SYNCH);
  }
  signal_sem(semid, MUTEXL);
  printf("[%d] Lettura dati in corso... Val1 = %d, Val2 = %d\n", getpid(), b->val1, b->val2);
  sleep(2);
  *val1 = b->val1;
  *val2 = b->val2;

  wait_sem(semid, MUTEXL);
  b->num_lettori--;
  if (b->num_lettori == 0){
    signal_sem(semid, SYNCH);
  }
  signal_sem(semid, MUTEXL);

  printf("[%d] Fine lettura \n", getpid());
}

void write_buffer(buffer *b, int semid, int val1, int val2){
  printf("[%d] Inizio scrittura\n", getpid());

  wait_sem(semid, SYNCH);
  printf("[%d] Scrivendo i valori: val1 = %d, val2 = %d\n", getpid(), val1, val2);
  b->val1 = val1;
  b->val2 = val2;
  signal_sem(semid, SYNCH);

  printf("[%d] Fase di scrittura terminata\n", getpid());
}
