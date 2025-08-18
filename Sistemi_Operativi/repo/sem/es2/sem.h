#ifndef _SEMAFORI_H_
#define _SEMAFORI_H_

#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>

int sem_wait(int semid, int numsem);
int sem_signal(int semid, int numsem);
void sem_init(int semid, int semnum, int val);

#endif // _SEMAFORI_H_ 
