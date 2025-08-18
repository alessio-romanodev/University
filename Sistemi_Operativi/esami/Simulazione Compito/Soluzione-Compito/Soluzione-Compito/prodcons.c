#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <sys/types.h>
#include <unistd.h>

#include "prodcons.h"

void inizializza(MonitorPC * p) {

    printf("Inizializzazione monitor\n");

    init_monitor(&p->m, NUM_VAR_COND);

    for(int i=0; i<DIM; i++) {
        p->stato[i] = LIBERO;
    }

    p->num_liberi = DIM;
    p->num_occupati_tipo1 = 0;
    p->num_occupati_tipo2 = 0;
}


void rimuovi(MonitorPC * p) {

    printf("Rimozione monitor\n");

    remove_monitor(&p->m);

}


void produci_tipo_1(MonitorPC * p, int valore) {

    int index = 0;

    enter_monitor(&p->m);

    if(p->num_liberi == 0) {

        printf("Sospensione produttore tipo 1...\n");

        wait_condition(&p->m, CV_PROD);

        printf("Attivazione produttore tipo 1...\n");
    }

    while(index < DIM && p->stato[index] != LIBERO) {
        index++;
    }

    p->num_liberi--;

    p->stato[index] = INUSO;

    leave_monitor(&p->m);


    printf("Produzione di tipo 1 in corso...\n");

    sleep(1);
    p->vettore[index] = valore;

    printf("Produzione di tipo 1 completata (valore=%d, index=%d)\n", valore, index);


    enter_monitor(&p->m);

    p->stato[index] = OCCUPATO1;

    p->num_occupati_tipo1++;

    signal_condition(&p->m, CV_CONS_1);

    leave_monitor(&p->m);

}


void produci_tipo_2(MonitorPC * p, int valore) {

    int index = 0;

    enter_monitor(&p->m);

    if(p->num_liberi == 0) {

        printf("Sospensione produttore tipo 2...\n");

        wait_condition(&p->m, CV_PROD);

        printf("Attivazione produttore tipo 2...\n");
    }

    while(index < DIM && p->stato[index] != LIBERO) {
        index++;
    }

    p->num_liberi--;

    p->stato[index] = INUSO;

    leave_monitor(&p->m);


    printf("Produzione di tipo 2 in corso...\n");

    sleep(1);
    p->vettore[index] = valore;

    printf("Produzione di tipo 2 completata (valore=%d, index=%d)\n", valore, index);


    enter_monitor(&p->m);

    p->stato[index] = OCCUPATO2;

    p->num_occupati_tipo2++;

    signal_condition(&p->m, CV_CONS_2);

    leave_monitor(&p->m);
}


int consuma_tipo_1(MonitorPC * p) {

    int index = 0;

    int valore = 0;

    enter_monitor(&p->m);

    if(p->num_occupati_tipo1 == 0) {

        printf("Sospensione consumatore tipo 1...\n");

        wait_condition(&p->m, CV_CONS_1);

        printf("Attivazione consumatore tipo 1...\n");
    }

    while(index < DIM && p->stato[index] != OCCUPATO1) {
        index++;
    }

    p->num_occupati_tipo1--;

    p->stato[index] = INUSO;

    leave_monitor(&p->m);


    printf("Consumazione di tipo 1 in corso...\n");

    sleep(1);
    valore = p->vettore[index];

    printf("Consumazione di tipo 1 completata (valore=%d, index=%d)\n", valore, index);


    enter_monitor(&p->m);

    p->stato[index] = LIBERO;

    p->num_liberi++;

    signal_condition(&p->m, CV_PROD);

    leave_monitor(&p->m);

    return valore;
}


int consuma_tipo_2(MonitorPC * p) {

    int index = 0;

    int valore = 0;

    enter_monitor(&p->m);

    if(p->num_occupati_tipo2 == 0) {

        printf("Sospensione consumatore tipo 2...\n");

        wait_condition(&p->m, CV_CONS_2);

        printf("Attivazione consumatore tipo 2...\n");
    }

    while(index < DIM && p->stato[index] != OCCUPATO2) {
        index++;
    }

    p->num_occupati_tipo2--;

    p->stato[index] = INUSO;

    leave_monitor(&p->m);


    printf("Consumazione di tipo 2 in corso...\n");

    sleep(1);
    valore = p->vettore[index];

    printf("Consumazione di tipo 2 completata (valore=%d, index=%d)\n", valore, index);


    enter_monitor(&p->m);

    p->stato[index] = LIBERO;

    p->num_liberi++;

    signal_condition(&p->m, CV_PROD);

    leave_monitor(&p->m);

    return valore;
}