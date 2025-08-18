#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <time.h>

#include "prodcons.h"


void produttore_1(MonitorPC * p)
{
	srand(getpid());

	for(int i=0; i<4; i++) {

		int valore = rand() % 10;

		produci_tipo_1(p, valore);

		sleep(1);
	}

}


void produttore_2(MonitorPC * p)
{
	srand(getpid());

	for(int i=0; i<4; i++) {

		int valore = rand() % 10;

		produci_tipo_2(p, valore);

		sleep(1);
	}

}


void consumatore_1(MonitorPC * p)
{
	srand(getpid());

	for(int i=0; i<8; i++) {

		int valore = consuma_tipo_1(p);

		sleep(1);
	}

}


void consumatore_2(MonitorPC * p)
{
	srand(getpid());
	
	for(int i=0; i<8; i++) {

		int valore = consuma_tipo_2(p);

		sleep(1);
	}

}

int main(int argc, char *argv[])
{

	int i;

    key_t chiave = IPC_PRIVATE;

	int id_monitor = shmget(chiave, sizeof(MonitorPC), IPC_CREAT | 0644);

	MonitorPC * p = shmat(id_monitor, NULL, 0);


	inizializza(p);
    

	// Creazione di 2 produttori di tipo 1

	for(i=0; i<2; i++) {

		pid_t pid = fork();

		if(pid > 0) {
			// PADRE
		}
		else if(pid == 0) {
			// FIGLIO
			produttore_1(p);
			exit(0);
		}
		else {
			perror("errore fork");
			exit(1);
		}
	}

	// Creazione di 2 produttori di tipo 2

	for(i=0; i<2; i++) {

		pid_t pid = fork();

		if(pid > 0) {
			// PADRE
		}
		else if(pid == 0) {
			// FIGLIO
			produttore_2(p);
			exit(0);
		}
		else {
			perror("errore fork");
			exit(1);
		}
	}



	// Creazione di 1 consumatore di tipo 1
	pid_t pid = fork();

	if(pid > 0) {
		// PADRE
	}
	else if(pid == 0) {
		// FIGLIO
		consumatore_1(p);
		exit(0);
	}
	else {
		perror("errore fork");
		exit(1);
	}



	// Creazione di 1 consumatore di tipo 2

	pid = fork();

	if(pid > 0) {
		// PADRE
	}
	else if(pid == 0) {
		// FIGLIO
		consumatore_2(p);
		exit(0);
	}
	else {
		perror("errore fork");
		exit(1);
	}


	for(i=0; i<6; i++) {

		wait(NULL);
	}
	

	rimuovi(p);

	shmctl(id_monitor, IPC_RMID, NULL);
}

