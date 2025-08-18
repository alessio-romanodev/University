#ifndef _SEM_H_
#define _SEM_H_

#include <sys/types.h>
#include <sys/sem.h>
#include <sys/ipc.h>

int sem_wait(int semid, int numsem);
int sem_signal(int semid, int numsem);

#endif // !_SEM_H_
