### Librerie utilizzate
- stdio.h 
- unistd.h 
- stdlib.h 
- sys/types.h 
- sys/wait.h 

### Varie 
int argc -> numero di parametri passati all'avvio di un programma
char * argv[] -> array contenente i parametri

### Funzioni utilizzate 
fork()
sleep()
exit()
wait()
waitpid()
perror()
WIFEXITED()
WEXITSTATUS()
WIFSIGNALED()
WTERMSIG()
execl() -> Percorso completo dell'eseguibile, parametri tramite lista 
execlp() -> nome dell'eseguibile da cercare nelle cartelle di sistema parametri tramite lista
execv() -> percorso completo dell'eseguibile parametri tramite array 
execvp -> nome dell'eseguibile da cercare nelle cartelle di sistema parametri tramite array
