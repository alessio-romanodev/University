#ifndef _SEM_H_
#define _SEM_H_

#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>

int wait_sem(int semid, int numsem);
int signal_sem(int semid, int numsem);

#endif // _SEM_H_
