#include <stdio.h>
#include <stdlib.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include "req.h"

#define MAX_REQUEST_SERVED 25
#define MAX_USERS 5
#define MAX_SCHEDULERS 1
#define DISK_SIZE 20


void scheduler(request_queue * q){
  int disk[DISK_SIZE];
  int disk_position;
  
  for (int i = 0; i<MAX_REQUEST_SERVED; i++){
    request r;
    get_request(q, &r);
    printf("[%d] Reading request: position=%d, process=%d\n", getpid(), r.position, r.pid);
    int wait = abs(disk_position - (int)r.position);
    printf("[%d] Waiting scheduler... (%d secondi)\n", getpid(), wait);
    sleep(wait);
    disk[r.position] = r.pid;
    disk_position = r.position;
  }
}

void user(request_queue * q){
  for (int i = 0; i<5; i++){
    request r;
    r.pid = getpid();
    r.position = rand() % 19;
    add_request(q, &r);
  }
}

int main(){
  request_queue * q = queue_init();

  pid_t spid = fork();
  if (spid == 0){
    scheduler(q);
  }

  for (int i = 0; i<MAX_USERS; i++){
    pid_t upid = fork();
    if (upid == 0){
      user(q);
    }
  }

  for (int i = 0; i<MAX_SCHEDULERS + MAX_USERS; i++){
    wait(NULL);
  }

  delete_queue(q);
}
